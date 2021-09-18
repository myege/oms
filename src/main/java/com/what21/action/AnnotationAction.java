 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.AnnotationSkuDao;
 import com.what21.dao.ItemDao;
 import com.what21.model.Annotation;
 import com.what21.model.AnnotationCustom;
 import com.what21.model.AnnotationQueryVo;
 import com.what21.model.AnnotationSku;
 import com.what21.model.Item;
 import com.what21.model.OrderSearchDto;
 import com.what21.model.PageQuery;
 import com.what21.result.DatagridResultInfo;
 import com.what21.result.ResultInfoUtil;
 import com.what21.service.AnnotationService;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.math.BigDecimal;
 import java.text.DecimalFormat;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.ServletOutputStream;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.poi.ss.usermodel.Cell;
 import org.apache.poi.ss.usermodel.CellStyle;
 import org.apache.poi.ss.usermodel.Row;
 import org.apache.poi.ss.usermodel.Sheet;
 import org.apache.poi.xssf.streaming.SXSSFWorkbook;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.multipart.MultipartFile;
 @Controller
 @RequestMapping({"/annotation"})
 public class AnnotationAction
   extends BaseAction
 {
   @Autowired
   private AnnotationService annotationService;
   @Autowired
   private AnnotationSkuDao annotationSkuDao;
   @Autowired
   public ItemDao itemdao;
   
   @RequestMapping({"/findAll2"})
   @ResponseBody
   public DatagridResultInfo findAll2(int page, int rows, AnnotationQueryVo annotationQueryVo) throws Exception {
     PageQuery pageQuery = new PageQuery(page, rows);
     annotationQueryVo.setPageQuery(pageQuery);
     int total = this.annotationService.count2(annotationQueryVo);
     List<AnnotationCustom> list = this.annotationService.findAll2(annotationQueryVo);
     return ResultInfoUtil.createDatagrid(list, total);
   }
   
   @RequestMapping({"/findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     List<AnnotationSku> u = this.annotationSkuDao.findAll(pageMap);
     int total = this.annotationSkuDao.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/actdelete"})
   public int actdelete(@RequestParam(value = "id", required = true) String id) {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       result = this.annotationSkuDao.actdelete(ids[i]);
     }
     return result;
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/findact"})
   public Map<String, Object> findact(OrderSearchDto orderSearchDto, HttpServletRequest request) throws Exception {
     Map<String, Object> pageMap = new HashMap<>();
     String mc = orderSearchDto.getParamValue();
     int mcx = Integer.parseInt(orderSearchDto.getParamName());
     if (mc != "" && mc != null) {
       if (mcx == 1) {
         pageMap.put("paramValue", mc);
         pageMap.put("type", Integer.valueOf(mcx));
       } else if (mcx == 2) {
         pageMap.put("paramValue", mc);
         pageMap.put("type", Integer.valueOf(mcx));
       } else if (mcx == 3) {
         pageMap.put("paramValue", mc);
         pageMap.put("type", Integer.valueOf(mcx));
       } 
     }
     pageMap.put("startPage", Integer.valueOf((orderSearchDto.getPage().intValue() - 1) * orderSearchDto.getRows().intValue()));
     pageMap.put("endPage", orderSearchDto.getRows());
     Map<String, Object> map = new HashMap<>();
     List<AnnotationSku> lu = this.annotationSkuDao.findact(pageMap);
     int total = this.annotationSkuDao.count(pageMap);
     map.put("rows", lu);
     map.put("total", Integer.valueOf(total));
     return map;
   }
 
 
 
   
   @RequestMapping({"/inmera"})
   @ResponseBody
   public Map<String, String> inmera(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request) {
     GeneralResult result = new GeneralResult();
     Map<String, String> map = new HashMap<>();
     String i = "0";
     String path = String.valueOf(System.getProperty("user.home")) + File.separator + "upload/";
     String fileName = "offline_" + System.currentTimeMillis() + ".xlsx";
     File targetFile = new File(path, fileName);
     try {
       if (!targetFile.exists()) {
         targetFile.mkdirs();
       }
       
       excelFile.transferTo(targetFile);
       int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
       result = this.annotationService.inmera(String.valueOf(path) + fileName, userId);
       if (result.getMessage().contains("导入成功")) {
         i = "1";
       } else {
         i = "0";
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       result.setMessage("导入失败！");
       i = "0";
       if (targetFile.exists()) {
         targetFile.delete();
       }
     } 
     map.put("code", i);
     map.put("msg", result.getMessage());
     return map;
   }
   
   @RequestMapping({"arDetailedUi"})
   public String findSkuUi(String jobFormId, Model model) {
     model.addAttribute("jobFormId", jobFormId);
     return "/item/operate/arDetailedDetailed";
   }
   
   @RequestMapping({"/arDetailed"})
   public void prDetailed(@RequestParam(value = "jobFormId", required = true) String jobFormId, OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("jobFormId", jobFormId);
     List<Annotation> u = this.annotationService.findAll(pageMap);
     int total = this.annotationService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"/actts"})
   public void actts(String ids, HttpServletRequest request, HttpServletResponse response) throws IOException {
     String str = "";
     System.out.println(ids);
 
     
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/exportba"})
   public void exportOrderBonded_2(HttpServletRequest request, HttpServletResponse response) throws IOException {
     long l1 = System.currentTimeMillis();
     
     SXSSFWorkbook ob = new SXSSFWorkbook();
 
     
     Sheet sheet = ob.createSheet("表体(成品)");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(0);
     CellStyle cellStyle = ob.createCellStyle();
 
     
     cellStyle.setAlignment((short)2);
 
     
     Cell cell = row.createCell(0);
     
     cell.setCellValue("序号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("备案序号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("商品料号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("报关单商品序号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("流转申报表序号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("商品编码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("商品名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("规格型号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("币制");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("申报计量单位代码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("法定计量单位代码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("法定第二计量单位代码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(12);
     cell.setCellValue("申报数量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(13);
     cell.setCellValue("法定数量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(14);
     cell.setCellValue("第二法定数量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(15);
     cell.setCellValue("企业申报单价");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(16);
     cell.setCellValue("企业申报总价");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(17);
     cell.setCellValue("原产国(地区）");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(18);
     cell.setCellValue("最终目的国（地区）");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(19);
     cell.setCellValue("重量比例因子");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(20);
     cell.setCellValue("第一比例因子");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(21);
     cell.setCellValue("第二比例因子");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(22);
     cell.setCellValue("毛重");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(23);
     cell.setCellValue("净重");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(24);
     cell.setCellValue("征免方式");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(25);
     cell.setCellValue("单耗版本号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(26);
     cell.setCellValue("备注");
     cell.setCellStyle(cellStyle);
 
 
     
     String ids = request.getParameter("ids");
     String gjz = " an.id in (" + ids + ")";
     System.out.println("gjz=" + gjz);
 
     
     HashSet<String> hashSet = new HashSet<>();
     List<Item> list = this.itemdao.findhzqd(gjz);
     DecimalFormat df_2 = new DecimalFormat("0.00");
     DecimalFormat df_1 = new DecimalFormat("0");
     int i = 1;
     int num = 1;
     String sku = "";
     BigDecimal sl = new BigDecimal("0");
     BigDecimal zl = new BigDecimal("0");
     BigDecimal jz = new BigDecimal("0");
     BigDecimal dj = new BigDecimal("0");
     BigDecimal zj = new BigDecimal("0");
 
 
     
     for (Item order : list) {
       String st = String.valueOf(order.getInvtno()) + "-" + order.getMailno();
       hashSet.add(st);
       if (sku.equals(order.getItemSKU())) {
         sl = sl.add(new BigDecimal(order.getItemcount()));
         
         jz = zl.multiply(sl);
 
         
         dj = new BigDecimal(order.getUnitprice());
         
         zj = dj.multiply(sl);
 
 
         
         cell = row.createCell(12);
         cell.setCellValue(df_1.format(sl));
         cell.setCellStyle(cellStyle);
         
         if (order.getOneUnitDesc().equals("035")) {
           cell = row.createCell(13);
           cell.setCellValue((new StringBuilder(String.valueOf(df_2.format(jz)))).toString());
           cell.setCellStyle(cellStyle);
         } else {
           
           cell = row.createCell(13);
           cell.setCellValue((new StringBuilder(String.valueOf(df_1.format(sl)))).toString());
           cell.setCellStyle(cellStyle);
         } 
         
         cell = row.createCell(15);
         cell.setCellValue((new StringBuilder(String.valueOf(df_2.format(dj)))).toString());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(16);
         cell.setCellValue((new StringBuilder(String.valueOf(df_2.format(zj)))).toString());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(23);
         cell.setCellValue(df_2.format(jz));
         cell.setCellStyle(cellStyle); continue;
       } 
       sl = new BigDecimal(order.getItemcount());
       zl = new BigDecimal(order.getUnitWeight());
       jz = zl.multiply(sl);
       dj = new BigDecimal(order.getUnitprice());
       
       zj = dj.multiply(sl);
       sku = order.getItemSKU();
       dj = zj.divide(sl, 2, 4);
       
       row = sheet.createRow(i++);
       cell = row.createCell(0);
       cell.setCellValue(num);
       cell.setCellStyle(cellStyle);
       
       num++;
       
       cell = row.createCell(1);
       cell.setCellValue(order.getInternalNumber());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(2);
       cell.setCellValue(order.getItemSKU());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(3);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(4);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(5);
       cell.setCellValue(order.getHscode());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(6);
       cell.setCellValue(order.getItemName());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(7);
       cell.setCellValue(order.getItemSpec());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(8);
       cell.setCellValue("142");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(9);
       cell.setCellValue(order.getUnitDesc());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(10);
       cell.setCellValue(order.getOneUnitDesc());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(11);
       cell.setCellValue((new StringBuilder(String.valueOf(order.getTwoUnitDesc()))).toString());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(12);
       cell.setCellValue(order.getItemcount());
       cell.setCellStyle(cellStyle);
       
       if (order.getOneUnitDesc().equals("035")) {
         cell = row.createCell(13);
         cell.setCellValue((new StringBuilder(String.valueOf(df_2.format(jz)))).toString());
         cell.setCellStyle(cellStyle);
       } else {
         
         cell = row.createCell(13);
         cell.setCellValue(order.getItemcount());
         cell.setCellStyle(cellStyle);
       } 
       
       String twounitdesc = "";
       if (order.getTwoUnitDesc() == null || order.getTwoUnitDesc().equals("")) {
         twounitdesc = "";
       } else {
         twounitdesc = order.getTwoUnitDesc();
       } 
       
       if (twounitdesc.equals("")) {
         cell = row.createCell(14);
         cell.setCellValue("");
         cell.setCellStyle(cellStyle);
       }
       else if (twounitdesc.equals("035")) {
         cell = row.createCell(14);
         cell.setCellValue((new StringBuilder(String.valueOf(df_2.format(jz)))).toString());
         cell.setCellStyle(cellStyle);
       } else {
         
         cell = row.createCell(14);
         cell.setCellValue(order.getItemcount());
         cell.setCellStyle(cellStyle);
       } 
       cell = row.createCell(15);
       cell.setCellValue(order.getUnitprice());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(16);
       cell.setCellValue(order.getAllprice());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(17);
       cell.setCellValue(order.getCountry());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(18);
       cell.setCellValue("142");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(19);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(20);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(21);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(22);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(23);
       cell.setCellValue(df_2.format(zl));
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(24);
       cell.setCellValue("3");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(25);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(26);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
     } 
 
 
     
     Sheet sheet1 = ob.createSheet("保税电商清单");
     sheet1.setDefaultColumnWidth(20);
 
     
     Row row1 = sheet1.createRow(0);
     
     Cell cell1 = row1.createCell(0);
     
     cell1.setCellValue("保税电商清单编号");
     cell1.setCellStyle(cellStyle);
     
     cell = row1.createCell(1);
     cell.setCellValue("物流单号");
     cell.setCellStyle(cellStyle);
 
 
     
     i = 1;
     for (String s : hashSet) {
       String[] ss = s.split("-");
       
       row1 = sheet1.createRow(i++);
       cell1 = row1.createCell(0);
       cell1.setCellValue(ss[0]);
       cell1.setCellStyle(cellStyle);
       
       cell1 = row1.createCell(1);
       cell1.setCellValue(ss[1]);
       cell1.setCellStyle(cellStyle);
     } 
 
     
     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
     response.setHeader("Content-disposition", "attachment;filename=annotation.xlsx");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
     System.out.println("保税完结导出花费时间：" + (System.currentTimeMillis() - l1));
   }
 }


