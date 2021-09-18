 package com.what21.action;
 
 import com.what21.dao.JjPickingDao;
 import com.what21.model.JjPicking;
 import com.what21.model.JjPickingCustom;
 import com.what21.model.JjPickingVo;
 import com.what21.model.JjUser;
 import com.what21.model.PageQuery;
 import com.what21.result.AjaxResult;
 import com.what21.result.DatagridResultInfo;
 import com.what21.result.ResultInfoUtil;
 import com.what21.service.JjPickingService;
 import com.what21.service.JjUserService;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.io.IOException;
 import java.io.OutputStream;
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
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.multipart.MultipartFile;
 @Controller
 @RequestMapping({"/JjPicking"})
 public class JjPickingAction
   extends BaseAction
 {
   @Autowired
   private JjPickingService jjPickingService;
   @Autowired
   private JjUserService jjUserService;
   @Autowired
   public JjPickingDao jjPickingDao;
   
   @RequestMapping({"/findAll"})
   @ResponseBody
   public DatagridResultInfo findAll(int page, int rows, JjPickingVo jjPickingVo) throws Exception {
     PageQuery pageQuery = new PageQuery(page, rows);
     jjPickingVo.setPageQuery(pageQuery);
     int total = this.jjPickingService.count(jjPickingVo);
     List<JjPicking> list = this.jjPickingService.findAll(jjPickingVo);
     return ResultInfoUtil.createDatagrid(list, total);
   }
   
   @RequestMapping({"/distribute"})
   public String distribute(String[] picks, HttpServletRequest request, Model model) {
     List<JjUser> lists = this.jjUserService.findJjUser();
     request.getSession().setAttribute("picks", picks);
     model.addAttribute("picks", picks);
     model.addAttribute("lists", lists);
     return "/picking/distribute";
   }
   
   @RequestMapping({"/distributeLeaflets"})
   @ResponseBody
   public AjaxResult distributeLeaflets(HttpServletRequest request, JjPickingCustom pickingCustom) {
     try {
       String pickname = request.getParameter("pickname");
       String pick = request.getParameter("pick");
       int shop = Integer.parseInt(request.getParameter("shop"));
       int picksum = Integer.parseInt(request.getParameter("picksum"));
       
       pickingCustom.setPick(pick);
       pickingCustom.setPickname(pickname);
       pickingCustom.setShop(shop);
       pickingCustom.setPicksum(picksum);
       
       return toAjax(this.jjPickingService.distributeLeaflets(pickingCustom));
     } catch (Exception e) {
       return error(e.getMessage());
     } 
   }
   
   @RequestMapping({"/xcxdistributeLeaflets"})
   @ResponseBody
   public AjaxResult xcxdistributeLeaflets(HttpServletRequest request, JjPickingCustom pickingCustom) {
     try {
       String pickname = request.getParameter("pickname");
       String pick = request.getParameter("pick");
       int shop = Integer.parseInt(request.getParameter("shop"));
       int picksum = Integer.parseInt(request.getParameter("picksum"));
       String lx = request.getParameter("lx");
       System.out.println("1==" + pickname);
       System.out.println("2==" + pick);
       System.out.println("3==" + shop);
       System.out.println("4==" + picksum);
       System.out.println("5==" + lx);
 
       
       pickingCustom.setPick(pick);
       pickingCustom.setPickname(pickname);
       pickingCustom.setShop(shop);
       pickingCustom.setPicksum(picksum);
       pickingCustom.setLx(lx);
       
       return toAjax(this.jjPickingService.distributeLeaflets(pickingCustom));
     } catch (Exception e) {
       return error(e.getMessage());
     } 
   }
 
   
   @RequestMapping({"/importDistributeLeaflets"})
   @ResponseBody
   public Map<String, String> importDistributeLeaflets(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request) {
     GeneralResult result = new GeneralResult();
     Map<String, String> map = new HashMap<>();
     String i = "0";
     try {
       String path = String.valueOf(System.getProperty("user.home")) + File.separator + "upload/";
       String fileName = "offline_" + System.currentTimeMillis() + ".xlsx";
       File targetFile = new File(path, fileName);
       if (!targetFile.exists()) {
         targetFile.mkdirs();
       }
       excelFile.transferTo(targetFile);
       result = this.jjPickingService.importDistributeLeaflets(String.valueOf(path) + fileName);
       if (result.getMessage().contains("导入成功")) {
         i = "1";
       } else {
         i = "0";
       }
     
     } catch (Exception ex) {
       ex.printStackTrace();
       result.setMessage("导入失败！");
       i = "0";
     } 
     map.put("code", i);
     map.put("msg", result.getMessage());
     return map;
   }
 
   
   @RequestMapping({"/exportba"})
   public void export(HttpServletRequest request, HttpServletResponse response) throws IOException {
     String startTime = request.getParameter("startTime");
     String endTime = request.getParameter("endTime");
     System.out.println("startTime=" + startTime);
     System.out.println("endTime=" + endTime);
     
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     SimpleDateFormat df9 = new SimpleDateFormat("yyyyMMdd");
     
     long l1 = System.currentTimeMillis();
     
     SXSSFWorkbook ob = new SXSSFWorkbook();
     CellStyle cellStyle = ob.createCellStyle();
     
     cellStyle.setAlignment((short)2);
     
     Sheet sheet = ob.createSheet("所有明细");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(0);
     
     Cell cell = row.createCell(0);
     
     cell.setCellValue("*拣货单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("*订单数");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("*商品数");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("*拣货员");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("*领单时间");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("*日期");
     cell.setCellStyle(cellStyle);
     
     String gjz = " 1=1";
     System.out.println("gjz" + gjz);
     List<JjPicking> list = this.jjPickingDao.findbygjz2(startTime, endTime);
     int i = 1;
     int num = 1;
     for (JjPicking order : list) {
       row = sheet.createRow(i++);
       cell = row.createCell(0);
       cell.setCellValue(order.getPick());
       cell.setCellStyle(cellStyle);
       num++;
       cell = row.createCell(1);
       cell.setCellValue(order.getPicksum().intValue());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(2);
       if (order.getShop() != null) {
         cell.setCellValue(order.getShop().intValue());
         cell.setCellStyle(cellStyle);
       } else {
         cell.setCellValue("0");
         cell.setCellStyle(cellStyle);
       } 
 
       
       cell = row.createCell(3);
       cell.setCellValue(order.getPickname());
       cell.setCellStyle(cellStyle);
 
       
       cell = row.createCell(4);
       cell.setCellValue(df.format(order.getPicktime()));
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(5);
       cell.setCellValue(df9.format(order.getPicktime()));
       cell.setCellStyle(cellStyle);
     } 
 
     
     Sheet sheet1 = ob.createSheet("本月汇总");
     sheet1.setDefaultColumnWidth(20);
 
     
     Row row1 = sheet1.createRow(0);
     
     Cell cell1 = row1.createCell(0);
     
     cell1.setCellValue("排名");
     cell1.setCellStyle(cellStyle);
     
     cell1 = row1.createCell(1);
     cell1.setCellValue("订单数");
     cell1.setCellStyle(cellStyle);
     
     cell1 = row1.createCell(2);
     cell1.setCellValue("商品数");
     cell1.setCellStyle(cellStyle);
     
     cell1 = row1.createCell(3);
     cell1.setCellValue("拣货员");
     cell1.setCellStyle(cellStyle);
 
     
     List<JjPicking> list2 = this.jjPickingDao.findbyby(gjz);
     int i1 = 1;
     int num1 = 1;
     for (JjPicking order1 : list2) {
       row1 = sheet1.createRow(i1++);
       cell1 = row1.createCell(0);
       cell1.setCellValue(num1);
       cell1.setCellStyle(cellStyle);
       num1++;
       cell1 = row1.createCell(1);
       cell1.setCellValue(order1.getPicksum().intValue());
       cell1.setCellStyle(cellStyle);
       
       cell1 = row1.createCell(2);
       cell1.setCellValue(order1.getShop().intValue());
       cell1.setCellStyle(cellStyle);
       
       cell1 = row1.createCell(3);
       cell1.setCellValue(order1.getPickname());
       cell1.setCellStyle(cellStyle);
     } 
 
     
     Sheet sheet2 = ob.createSheet("本年汇总");
     sheet2.setDefaultColumnWidth(20);
 
     
     Row row2 = sheet2.createRow(0);
     
     Cell cell2 = row2.createCell(0);
     
     cell2.setCellValue("排名");
     cell2.setCellStyle(cellStyle);
     
     cell2 = row2.createCell(1);
     cell2.setCellValue("订单数");
     cell2.setCellStyle(cellStyle);
     
     cell2 = row2.createCell(2);
     cell2.setCellValue("商品数");
     cell2.setCellStyle(cellStyle);
     
     cell2 = row2.createCell(3);
     cell2.setCellValue("拣货员");
     cell2.setCellStyle(cellStyle);
 
     
     List<JjPicking> list3 = this.jjPickingDao.findbybn(gjz);
     int i2 = 1;
     int num2 = 1;
     for (JjPicking order2 : list3) {
       row2 = sheet2.createRow(i2++);
       cell2 = row2.createCell(0);
       cell2.setCellValue(num2);
       cell2.setCellStyle(cellStyle);
       num2++;
       cell2 = row2.createCell(1);
       cell2.setCellValue(order2.getPicksum().intValue());
       cell2.setCellStyle(cellStyle);
       
       cell2 = row2.createCell(2);
       cell2.setCellValue(order2.getShop().intValue());
       cell2.setCellStyle(cellStyle);
       
       cell2 = row2.createCell(3);
       cell2.setCellValue(order2.getPickname());
       cell2.setCellStyle(cellStyle);
     } 
 
 
     
     List<JjPicking> list4 = this.jjPickingDao.findbygjz3(startTime, endTime);
     
     Sheet sheet3 = ob.createSheet("拣货名单");
     sheet3.setDefaultColumnWidth(20);
     
     int ii = 1;
     int jj = 1;
     String pickname = "";
     for (JjPicking order : list4) {
       System.out.println("拣货员=" + order.getPickname());
       System.out.println("类型=" + order.getLx());
       
       pickname = order.getPickname();
       Row row3 = sheet3.createRow(ii);
       Cell cell3 = row3.createCell(jj);
       cell3.setCellValue(order.getPickname());
       cell3.setCellStyle(cellStyle);
       ii++;
     }     
     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
     response.setHeader("Content-disposition", "attachment;filename=jj" + df9.format(new Date()) + ".xlsx");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
     System.out.println("保税完结导出花费时间：" + (System.currentTimeMillis() - l1));
   }
   @RequestMapping({"/editUi"})
   public String editUi(Model model, String id) {
     JjPicking jjPicking = this.jjPickingDao.findById(id);
     model.addAttribute("jjPicking", jjPicking);
     return "/picking/editUi";
   }
   
   @ResponseBody
   @RequestMapping({"/edit"})
   public int edit(JjPicking jjPicking) {
     int i = 0;
     
     i = this.jjPickingDao.updateById(jjPicking);
     
     return i;
   }
 
 
 
   
   @RequestMapping({"/betweenOldNewUi"})
   public String queryStartOrEndTime() {
     return "/picking/oldTimeBetweenNewTime";
   }
 }


