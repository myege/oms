 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.OrderCommoditie;
 import com.what21.model.OrderSearchDto;
 import com.what21.service.OrderCommoditieService;
 import java.io.OutputStream;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.ServletOutputStream;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.poi.hssf.usermodel.HSSFCell;
 import org.apache.poi.hssf.usermodel.HSSFCellStyle;
 import org.apache.poi.hssf.usermodel.HSSFRow;
 import org.apache.poi.hssf.usermodel.HSSFSheet;
 import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 import org.apache.poi.ss.usermodel.CellStyle;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 
 
 
 @Controller
 @RequestMapping({"/orderCommoditie"})
 public class OrderCommoditieAction
   extends BaseAction
 {
   @Autowired
   public OrderCommoditieService orderCommoditieService;
   
   @RequestMapping({"/findAll"})
   public void findAll(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("paramValue", orderSearchDto.getParamValue());
     pageMap.put("paramName", orderSearchDto.getParamName());
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("hctime1", orderSearchDto.getHctime1());
     pageMap.put("hctime2", orderSearchDto.getHctime2());
     pageMap.put("expressNumQuery", orderSearchDto.getExpressNumQuery());
     pageMap.put("commoditieCodeQuery", orderSearchDto.getCommoditieCodeQuery());
     pageMap.put("commoditieNameQuery", orderSearchDto.getCommoditieNameQuery());
     List<OrderCommoditie> u = this.orderCommoditieService.findAll(pageMap);
     int total = this.orderCommoditieService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
   
   @RequestMapping({"/exporitie"})
   public void exportTinventory(HttpServletRequest request, HttpServletResponse response) throws Exception {
     HSSFWorkbook ob = new HSSFWorkbook();
     
     HSSFSheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     HSSFRow row = sheet.createRow(0);
     HSSFCellStyle hSSFCellStyle = ob.createCellStyle();
     
     hSSFCellStyle.setAlignment((short)2);
     
     HSSFCell cell = row.createCell(0);
     
     cell.setCellValue("快递单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("耗材单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("耗材名称");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("耗材数量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("创建时间");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     Map<String, Object> pageMap = new HashMap<>();
     String ida = request.getParameter("oditie");
     String hctime1 = request.getParameter("hctime3");
     String hctime2 = request.getParameter("hctime4");
     String expressNum = request.getParameter("expressNumQuerys");
     String commoditieCode = request.getParameter("commoditieCodeQuerys");
     String commoditieName = request.getParameter("commoditieNameQuerys");
     if (ida.equals("cxqbdc")) {
       pageMap.put("hctime1", hctime1);
       pageMap.put("hctime2", hctime2);
       pageMap.put("expressNumQuery", expressNum);
       pageMap.put("commoditieCodeQuery", commoditieCode);
       pageMap.put("commoditieNameQuery", commoditieName);
       List<OrderCommoditie> list = this.orderCommoditieService.dateTime(pageMap);
       int i = 1;
       for (OrderCommoditie orderCommoditie : list) {
         row = sheet.createRow(i++);
         cell = row.createCell(0);
         cell.setCellValue(orderCommoditie.getExpressNum());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(orderCommoditie.getCommoditieCode());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(2);
         cell.setCellValue(orderCommoditie.getCommoditieName());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(3);
         cell.setCellValue(orderCommoditie.getCommoditieNum());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(4);
         cell.setCellValue(orderCommoditie.getCreateTime());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
       } 
     } else {
       String[] arr = ida.split(",");
       for (int i = 0; i < arr.length; i++) {
         List<OrderCommoditie> u = this.orderCommoditieService.findeXport(Integer.valueOf(Integer.parseInt(arr[i])));
         for (int j = 0; j < u.size(); j++) {
           OrderCommoditie orderCommoditie = u.get(j);
           row = sheet.createRow(i + 1);
           cell = row.createCell(0);
           cell.setCellValue(orderCommoditie.getExpressNum());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(1);
           cell.setCellValue(orderCommoditie.getCommoditieCode());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(2);
           cell.setCellValue(orderCommoditie.getCommoditieName());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(3);
           cell.setCellValue(orderCommoditie.getCommoditieNum());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(4);
           cell.setCellValue(orderCommoditie.getCreateTime());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
         } 
       } 
     } 
     
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", "attachment;filename=OrderCommoditie.xls");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
 }


