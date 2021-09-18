 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.OrderSearchDto;
 import com.what21.model.Waybill;
 import com.what21.service.WaybillService;
 import com.what21.util.GeneralResult;
 import java.io.BufferedOutputStream;
 import java.io.DataOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.ServletOutputStream;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.poi.ss.usermodel.Row;
 import org.apache.poi.xssf.usermodel.XSSFRow;
 import org.apache.poi.xssf.usermodel.XSSFSheet;
 import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.ModelAndView;
 @Controller
 @RequestMapping({"/waybill"})
 public class WaybillAction
   extends BaseAction
 {
   @Autowired
   private WaybillService waybillService;
   
   @RequestMapping({"/findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String isSign = request.getSession().getAttribute("tz").toString();
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
     pageMap.put("isSign", isSign);
     pageMap.put("name", "findAll");
     List<Waybill> u = this.waybillService.findAll(pageMap);
     
     int total = this.waybillService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/exportNoSucces"})
   public void exportNoSucces(HttpServletRequest request, HttpServletResponse response) throws IOException {
     String set = request.getParameter("set");
     String url = request.getParameter("url");
     String[] arr = set.split(",");
     InputStream is = new FileInputStream(url);
     
     XSSFWorkbook hssfWorkbook = null;
     try {
       hssfWorkbook = new XSSFWorkbook(is);
       
       for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
         XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
         if (hssfSheet != null)
         {
 
           
           if (hssfSheet.getLastRowNum() != arr.length)
           {
             for (int i = 0; i < arr.length; i++) {
               
               XSSFRow row = hssfSheet.getRow(Integer.parseInt(arr[i]));
               hssfSheet.removeRow((Row)row);
             }  } 
         }
       } 
     } catch (IOException e) {
       
       e.printStackTrace();
     } 
 
     
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", "attachment;filename=exportNoSuccess.xls");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     hssfWorkbook.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
     
     File delFile = new File(url);
     delFile.delete();
   }
 
   
   @RequestMapping({"/importOrder"})
   public ModelAndView importOrder(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request) {
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     ModelAndView modelAndView = new ModelAndView();
     modelAndView.setViewName("/tools/waybillMsg");
     GeneralResult result = new GeneralResult();
     String path = String.valueOf(System.getProperty("user.home")) + File.separator + "upload/";
     String fileName = "offline_" + System.currentTimeMillis() + ".xls";
     File targetFile = new File(path, fileName);
     try {
       if (!targetFile.exists()) {
         targetFile.mkdirs();
       }
 
       
       excelFile.transferTo(targetFile);
 
       
       result = this.waybillService.importOrder(String.valueOf(path) + fileName, userId);
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
   
   @RequestMapping({"/deleted"})
   public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
 
     
     String value = request.getParameter("value");
     
     String name = request.getParameter("name");
     
     String mz = request.getParameter("mz");
     
     String time1 = request.getParameter("time1");
     
     String time2 = request.getParameter("time2");
     
     String isSign = request.getSession().getAttribute("tz").toString();
     
     int res = 0;
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("value", value);
     pageMap.put("name", name);
     pageMap.put("mz", mz);
     pageMap.put("time1", time1);
     pageMap.put("time2", time2);
     pageMap.put("isSign", isSign);
     if (mz.equals("7878")) {
       String ids = request.getParameter("ids");
       String[] arr = ids.split(",");
       for (int i = 0; i < arr.length; i++) {
         int id = Integer.parseInt(arr[i]);
         pageMap.put("id", Integer.valueOf(id));
         res = this.waybillService.delete(pageMap);
       } 
     } else {
       res = this.waybillService.delete(pageMap);
     } 
     String str = JSONObject.toJSONString(Integer.valueOf(res));
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/exportOrder"})
   public void exportOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
     userId = getUserId(userName, userId);
     String isSign = request.getSession().getAttribute("tz").toString();
     String ids = request.getParameter("waybill");
     String value = request.getParameter("value");
     String name = request.getParameter("name");
     String time3 = request.getParameter("time3");
     String time4 = request.getParameter("time4");
     List<Waybill> u = new ArrayList<>();
     if (ids.equals("cxqbdc")) {
       pageMap.put("paramValue", value);
       pageMap.put("value", value);
       pageMap.put("isSign", isSign);
       pageMap.put("time3", time3);
       pageMap.put("time4", time4);
       pageMap.put("paramName", name);
       pageMap.put("name", name);
       pageMap.put("userId", Integer.valueOf(userId));
       pageMap.put("startPage", null);
       pageMap.put("endPage", null);
       u = this.waybillService.findSearch(pageMap);
     } else {
       String[] arr = ids.split(",");
       u = this.waybillService.findIdArray(arr);
     } 
     StringBuffer sb = new StringBuffer();
     try {
       DataOutputStream rafs = new DataOutputStream(
           new BufferedOutputStream(new FileOutputStream(new File(
                 "d://test.xls"))));
       sb.append("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
       sb.append("\n");
       sb.append("<?mso-application progid=\"Excel.Sheet\"?>");
       sb.append("\n");
       sb.append("<Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\"");
       sb.append("\n");
       sb.append(" xmlns:o=\"urn:schemas-microsoft-com:office:office\"");
       sb.append("\n");
       sb.append(" xmlns:x=\"urn:schemas-microsoft-com:office:excel\"");
       sb.append("\n");
       sb.append(" xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\"");
       sb.append("\n");
       sb.append(" xmlns:html=\"http://www.w3.org/TR/REC-html40\">");
       sb.append("\n");
       sb.append("<DocumentProperties xmlns=\"urn:schemas-microsoft-com:office:office\"> ");
       sb.append("\n");
       sb.append("<Version>11.9999</Version>\n");
       sb.append(" </DocumentProperties>\n");
       sb.append(" <Styles>\n");
       sb.append(" <Style ss:ID=\"Default\" ss:Name=\"Normal\">\n");
       sb.append("  <Alignment ss:Vertical=\"Center\"/>\n");
       sb.append("  <Borders/>\n");
       sb.append("  <Font ss:FontName=\"宋体\" x:CharSet=\"134\" ss:Size=\"12\"/>\n");
       sb.append("  <Interior/>\n");
       sb.append("  <NumberFormat/>\n");
       sb.append("  <Protection/>\n");
       sb.append(" </Style>\n");
       sb.append(" </Styles>\n");
       int sheetcount = 0;
       int recordcount = 65535;
       int currentRecord = 0;
       int total = (int)Math.ceil(((u.size() + 1) / 65535)) + 1;
       int col = 6;
       sb.append("<Worksheet ss:Name=\"Sheet0\">");
       sb.append("\n");
       sb.append("<Table ss:ExpandedColumnCount=\"" + col + 
           "\" ss:ExpandedRowCount=\"" + u.size() + '\001' + 
           "\" x:FullColumns=\"1\" x:FullRows=\"1\">");
       sb.append("\n");
       for (int i = 0; i <= total; i++) {
         if ((currentRecord == recordcount || 
           currentRecord > recordcount || currentRecord == 0) && 
           i != 0) {
           currentRecord = 0;
           rafs.write(sb.toString().getBytes());
           sb.setLength(0);
           sb.append("</Table>");
           sb.append("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
           sb.append("\n");
           sb.append("<ProtectObjects>False</ProtectObjects>");
           sb.append("\n");
           sb.append("<ProtectScenarios>False</ProtectScenarios>");
           sb.append("\n");
           sb.append("</WorksheetOptions>");
           sb.append("\n");
           sb.append("</Worksheet>");
           sb.append("<Worksheet ss:Name=\"Sheet" + (i / recordcount) + 
               "\">");
           sb.append("\n");
           sb.append("<Table ss:ExpandedColumnCount=\"" + col + 
               "\" ss:ExpandedRowCount=\"" + recordcount + 
               "\" x:FullColumns=\"1\" x:FullRows=\"1\">");
           sb.append("\n");
         } 
         if (i == 0) {
           sb.append("<Row>");
           sb.append("<Cell><Data ss:Type=\"String\">快递编号</Data></Cell>\n");
           sb.append("<Cell><Data ss:Type=\"String\">快递单号</Data></Cell>\n");
           sb.append("<Cell><Data ss:Type=\"String\">商家编码</Data></Cell>\n");
           sb.append("<Cell><Data ss:Type=\"String\">揽件时间</Data></Cell>\n");
           sb.append("<Cell><Data ss:Type=\"String\">签收时间</Data></Cell>\n");
           sb.append("<Cell><Data ss:Type=\"String\">时效</Data></Cell>\n");
           sb.append("</Row>");
         } else {
           for (Waybill ww : u) {
             sb.append("<Row>");
             sb.append("<Cell><Data ss:Type=\"String\">" + ww.getExpressCode() + "</Data></Cell>\n");
             sb.append("<Cell><Data ss:Type=\"String\">" + ww.getExpressNumber() + "</Data></Cell>\n");
             sb.append("<Cell><Data ss:Type=\"String\">" + ww.getBusiness() + "</Data></Cell>\n");
             sb.append("<Cell><Data ss:Type=\"String\">" + ww.getAcceptTime() + "</Data></Cell>\n");
             sb.append("<Cell><Data ss:Type=\"String\">" + ww.getEndTime() + "</Data></Cell>\n");
             sb.append("<Cell><Data ss:Type=\"String\">" + ww.getSc() + "</Data></Cell>\n");
             sb.append("</Row>");
           } 
         } 
         
         if (i % 5000 == 0) {
           rafs.write(sb.toString().getBytes());
           rafs.flush();
           sb.setLength(0);
         } 
         sb.append("\n");
         currentRecord++;
       } 
       rafs.write(sb.toString().getBytes());
       sb.setLength(0);
       sb.append("</Table>");
       sb.append("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
       sb.append("\n");
       sb.append("<ProtectObjects>False</ProtectObjects>");
       sb.append("\n");
       sb.append("<ProtectScenarios>False</ProtectScenarios>");
       sb.append("\n");
       sb.append("</WorksheetOptions>");
       sb.append("\n");
       sb.append("</Worksheet>");
       sb.append("</Workbook>");
       sb.append("\n");
       rafs.write(sb.toString().getBytes());
       rafs.flush();
       rafs.close();
     } catch (FileNotFoundException e) {
       e.printStackTrace();
     } catch (IOException e) {
       e.printStackTrace();
     } 
   }
   
   @ResponseBody
   @RequestMapping({"/findByNumber"})
   public Map<String, Object> findByNumber(OrderSearchDto orderSerachDto, HttpServletRequest request) throws Exception {
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     String isSign = request.getSession().getAttribute("tz").toString();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     pageMap.put("time1", orderSerachDto.getTime1());
     pageMap.put("time2", orderSerachDto.getTime2());
     pageMap.put("paramName", orderSerachDto.getParamName());
     pageMap.put("paramValue", orderSerachDto.getParamValue());
     pageMap.put("startPage", Integer.valueOf((orderSerachDto.getPage().intValue() - 1) * orderSerachDto.getRows().intValue()));
     pageMap.put("endPage", orderSerachDto.getRows());
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("isSign", isSign);
     pageMap.put("value", orderSerachDto.getParamValue());
     pageMap.put("name", orderSerachDto.getParamName());
     pageMap.put("userId", Integer.valueOf(userId));
     
     List<Waybill> u = this.waybillService.findSearch(pageMap);
     int total = this.waybillService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     return map;
   }
   
   @ResponseBody
   @RequestMapping({"/dateTime"})
   public Map<String, Object> dateTime(OrderSearchDto orderSerachDto, HttpServletRequest request) {
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     String isSign = request.getSession().getAttribute("tz").toString();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     pageMap.put("time1", orderSerachDto.getTime1());
     pageMap.put("time2", orderSerachDto.getTime2());
     pageMap.put("name", orderSerachDto.getParamName());
     pageMap.put("value", orderSerachDto.getParamValue());
     pageMap.put("startPage", Integer.valueOf((orderSerachDto.getPage().intValue() - 1) * orderSerachDto.getRows().intValue()));
     pageMap.put("endPage", orderSerachDto.getRows());
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("isSign", isSign);
     
     List<Waybill> u = this.waybillService.dateTime(pageMap);
     int total = this.waybillService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     return map;
   }
 }


