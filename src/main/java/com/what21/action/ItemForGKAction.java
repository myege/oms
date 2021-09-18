 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.ItemForGk;
 import com.what21.service.ItemForGKService;
 import com.what21.util.GeneralResult;
 import com.what21.util.ResponseUtil;
 import com.what21.util.TimeUtil;
 import java.io.File;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.HashMap;
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
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.ModelAndView;
 @Controller
 @RequestMapping({"/itemForGKAction"})
 public class ItemForGKAction
   extends BaseAction
 {
   @Autowired
   ItemForGKService gkService;
   
   @RequestMapping({"/findAll"})
   public void findAll(String sku, HttpServletRequest request, HttpServletResponse response) throws IOException {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
     
     userId = getUserId(userName, userId);
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("userId", Integer.valueOf(userId));
     if (sku != null) {
       pageMap.put("Sku", sku);
     }
     pageMap.put("name", "findAll");
     List<ItemForGk> list = this.gkService.findAll(pageMap);
     
     int total = this.gkService.count(pageMap);
     map.put("rows", list);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
   
   @RequestMapping({"/deleteItem"})
   public void deleteItem(int[] ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int resultCount = this.gkService.deleteItem(ids);
     JSONObject result = new JSONObject();
     if (resultCount > 0) {
       result.put("success", "删除成功");
     } else {
       result.put("success", "删除失败");
     } 
     ResponseUtil.write(response, result);
   }
   
   @RequestMapping({"/ItemaddUi"})
   public String addUi() {
     return "ItemaddUi";
   }
   
   @RequestMapping({"/ItemupdateUi"})
   public String updateUi(Model model, int id, HttpServletRequest request, HttpServletResponse response) {
     System.out.println(id);
     ItemForGk item = this.gkService.queryOne(id);
     request.getSession().setAttribute("lhyid", Integer.valueOf(id));
     model.addAttribute("itemForGk", item);
     return "ItemupdateUi";
   }
   
   @RequestMapping({"/insertItem"})
   public String insertItem(ItemForGk itemForGk, HttpServletRequest request, HttpServletResponse response) throws Exception {
     JSONObject result = new JSONObject();
     int i = this.gkService.findByItemSku(itemForGk.getItemSku());
     if (i > 0) {
       result.put("msg", "存在已有的sku");
       ResponseUtil.write(response, result);
       return null;
     } 
     int resultTotal = this.gkService.insertItem(itemForGk);
     
     if (resultTotal > 0) {
       result.put("success", "添加成功");
     } else {
       result.put("msg", "添加失败");
     } 
     ResponseUtil.write(response, result);
     return null;
   }
   
   @RequestMapping({"/updateItem"})
   public void updateItem(ItemForGk itemForGk, HttpServletRequest request, HttpServletResponse response) throws Exception {
     itemForGk.setId(((Integer)request.getSession().getAttribute("lhyid")).intValue());
     int resultTotal = this.gkService.updateForItem(itemForGk);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
   }
 
   
   @RequestMapping({"/importExcel"})
   public ModelAndView importExcel(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request) {
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     ModelAndView modelAndView = new ModelAndView();
     modelAndView.setViewName("/tools/ytobillMsg");
     GeneralResult result = new GeneralResult();
     String path = String.valueOf(System.getProperty("user.home")) + File.separator + "upload/";
     String fileName = "offline_" + System.currentTimeMillis() + ".xls";
     File targetFile = new File(path, fileName);
     try {
       if (!targetFile.exists()) {
         targetFile.mkdirs();
       }
 
       
       excelFile.transferTo(targetFile);
 
 
       
       result = this.gkService.importExcel(String.valueOf(path) + fileName, userId);
       modelAndView.addObject("result", result);
       return modelAndView;
     } catch (Exception ex) {
       ex.printStackTrace();
       result.csetCode(Integer.valueOf(-1)).csetMessage(ex.getMessage());
       modelAndView.addObject("result", result);
       if (targetFile.exists()) {
         targetFile.delete();
       }
       return modelAndView;
     } 
   }
 
 
   
   @RequestMapping({"/exportExcel"})
   public void exportExcel(HttpServletRequest request, HttpServletResponse response, int[] ids) throws IOException, ParseException {
     SXSSFWorkbook wb = new SXSSFWorkbook();
     
     Sheet sheet = wb.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(0);
     CellStyle cellStyle = wb.createCellStyle();
     
     cellStyle.setAlignment((short)2);
     
     Cell cell = row.createCell(0);
     cell.setCellValue("商品SKU");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("商品价格");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("商品重量(kg)");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("海关备案号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("条形码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("商品名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("商品品名");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("商品规格");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("计量单位名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("创建时间");
     cell.setCellStyle(cellStyle);
     List<ItemForGk> list = null;
     int j = 1;
     if (ids.length == 0) {
       
       list = this.gkService.findAllByEx();
     } else {
       list = this.gkService.findByIDS(ids);
     } 
     
     for (ItemForGk itemForGk : list) {
       row = sheet.createRow(j++);
       cell = row.createCell(0);
       cell.setCellValue(itemForGk.getItemSku());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(1);
       cell.setCellValue(itemForGk.getPrice());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(2);
       cell.setCellValue(itemForGk.getWeight());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(3);
       cell.setCellValue(itemForGk.getHsCode());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(4);
       cell.setCellValue(itemForGk.getItemCode());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(5);
       cell.setCellValue(itemForGk.getItemName());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(6);
       cell.setCellValue(itemForGk.getItemClass());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(7);
       cell.setCellValue(itemForGk.getStandard());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(8);
       cell.setCellValue(itemForGk.getUnitName());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(9);
       Date sdf = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(itemForGk.getCreateTime());
       cell.setCellValue(TimeUtil.defaultTime("yyyy-MM-dd HH:mm:ss", sdf));
       cell.setCellStyle(cellStyle);
     } 
     
     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
     response.setHeader("Content-disposition", "attachment;filename=ItemGK.xlsx");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     wb.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
 }


