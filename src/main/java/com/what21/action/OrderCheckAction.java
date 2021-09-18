 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.OrderCheck;
 import com.what21.model.OrderCheckSku;
 import com.what21.model.OrderSearchDto;
 import com.what21.service.OrderCheckService;
 import com.what21.service.OrderCheckSkuService;
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
 import org.apache.poi.hssf.usermodel.HSSFCell;
 import org.apache.poi.hssf.usermodel.HSSFCellStyle;
 import org.apache.poi.hssf.usermodel.HSSFRow;
 import org.apache.poi.hssf.usermodel.HSSFSheet;
 import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 import org.apache.poi.ss.usermodel.CellStyle;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 import wms.EbamUtils;
 @Controller
 @RequestMapping({"/orderCheck"})
 public class OrderCheckAction
   extends BaseAction
 {
   @Autowired
   private OrderCheckService orderCheckService;
   @Autowired
   private OrderCheckSkuService orderCheckSkuService;
   
   @RequestMapping({"/findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     List<OrderCheck> u = this.orderCheckService.getAll(pageMap);
     int total = this.orderCheckService.countOrderCheck();
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/find"})
   public Map<String, Object> find(HttpServletRequest request, OrderSearchDto orderSearchDto) throws Exception {
     Map<String, Object> pageMap = new HashMap<>();
     String pv = orderSearchDto.getParamValue();
     int pn = Integer.parseInt(orderSearchDto.getParamName());
     if (pv != "" && pv != null) {
       if (pn == 1) {
         pageMap.put("paramValue", pv);
         pageMap.put("type", Integer.valueOf(pn));
       } else if (pn == 2) {
         pageMap.put("paramValue", pv);
         pageMap.put("type", Integer.valueOf(pn));
       } 
     }
     pageMap.put("startPage", Integer.valueOf((orderSearchDto.getPage().intValue() - 1) * orderSearchDto.getRows().intValue()));
     pageMap.put("endPage", orderSearchDto.getRows());
     Map<String, Object> map = new HashMap<>();
     List<OrderCheck> lu = this.orderCheckService.find(pageMap);
     int total = this.orderCheckService.count(pageMap);
     map.put("rows", lu);
     map.put("total", Integer.valueOf(total));
     return map;
   }
 
   
   @ResponseBody
   @RequestMapping({"/addO"})
   public void addO(HttpServletResponse response, HttpServletRequest request) throws Exception {
     String tmn = request.getParameter("totalMailNo");
     String pc = request.getParameter("pc");
     String userName = request.getParameter("userName");
     String userId = request.getParameter("userId");
     SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     OrderCheck o = new OrderCheck();
     o.setTotalMailNo(tmn);
     o.setPc(pc);
     o.setUserid(Integer.parseInt(userId));
     o.setUsername(userName);
     o.setCreatedatatime(d.format(new Date()));
     int res = this.orderCheckService.addO(o);
     String str = JSONObject.toJSONString(Integer.valueOf(res));
     response.getWriter().write(str);
   }
 
   
   @ResponseBody
   @RequestMapping({"/deleteO"})
   public int deleteO(@RequestParam(value = "id", required = true) String id) throws Exception {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       result = this.orderCheckService.deleteO(ids[i]);
     }
     return result;
   }
   
   @ResponseBody
   @RequestMapping({"/findByWeight"})
   public void findByWeight(@RequestParam(value = "id", required = true) String id, @RequestParam(value = "totalMailNo", required = true) String totalMailNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("totalMailNo", totalMailNo);
     pageMap.put("totalid", id);
     List<OrderCheckSku> u = this.orderCheckSkuService.findBySku(pageMap);
     int total = this.orderCheckSkuService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/changeor"})
   public void changeor(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String totalMailNo = request.getParameter("totalMailNo");
     String text1 = request.getParameter("text1");
     String w = request.getParameter("w");
     double aa = Double.parseDouble(w);
     String totalid = request.getParameter("totalid");
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("totalMailNo", totalMailNo);
     pageMap.put("billcode", text1);
     pageMap.put("totalid", totalid);
     OrderCheckSku o = this.orderCheckSkuService.findId(pageMap);
     String str = null;
     
     if (o != null) {
       
       str = JSONObject.toJSONString("重复");
     } else {
       OrderCheckSku n = new OrderCheckSku();
       n.setBillcode(text1);
       n.setWeight(Double.valueOf(aa));
       n.setTotalMailNo(totalMailNo);
       n.setTotalid(Integer.parseInt(totalid));
       SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       n.setCreatedatatime(d.format(new Date()));
       this.orderCheckSkuService.add(n);
     } 
     
     str = JSONObject.toJSONString(null);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/orderAndCheck"})
   public void orderAndCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
     String mailNo1 = request.getParameter("mailNo2");
     String pc2 = request.getParameter("pc3");
     
     OrderCheck orderCheck = new OrderCheck();
     orderCheck.setTotalMailNo(mailNo1);
     orderCheck.setPc(pc2);
     
     OrderCheck nu = this.orderCheckService.findByPc(orderCheck);
     
     if (nu == null) {
       String str1 = JSONObject.toJSONString(null);
       response.getWriter().write(str1);
     } else {
       String str2 = JSONObject.toJSONString("存在");
       response.getWriter().write(str2);
     } 
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/getweight"})
   public void getweight(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String result = EbamUtils.GOODWEIGHT;
 
     
     response.getWriter().write(JSONObject.toJSONString(result));
   }
 
 
 
   
   @RequestMapping({"/out"})
   public void out(HttpServletRequest request, HttpServletResponse response) throws Exception {
     HSSFWorkbook ob = new HSSFWorkbook();
     
     HSSFSheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
     sheet.createFreezePane(22, 1);
     
     HSSFRow row = sheet.createRow(0);
     HSSFCellStyle hSSFCellStyle = ob.createCellStyle();
     
     hSSFCellStyle.setAlignment((short)2);
     
     HSSFCell cell = row.createCell(0);
     
     cell.setCellValue("分运单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("重量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     Map<String, Object> pageMap = new HashMap<>();
     String ids = request.getParameter("totaa");
     String totalMailNo = request.getParameter("totidaa");
     String totalId = request.getParameter("totAa");
     
     if (ids.equals("cxqbdc")) {
       
       pageMap.put("totidaa", totalMailNo);
       pageMap.put("totAa", totalId);
       
       List<OrderCheckSku> l = this.orderCheckSkuService.findAll(pageMap);
       
       int i = 0;
       for (OrderCheckSku go : l) {
         i++;
         row = sheet.createRow(i);
         
         cell = row.createCell(0);
         cell.setCellValue(go.getBillcode());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(go.getWeight().doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
       } 
     } else {
       String[] arr = ids.split(",");
       int a = 0;
       for (int i = 0; i < arr.length; i++) {
         List<OrderCheckSku> u = this.orderCheckSkuService.findByBillcode(arr[i]);
         for (int j = 0; j < u.size(); j++) {
           a = i + 1;
           row = sheet.createRow(a);
           
           cell = row.createCell(0);
           cell.setCellValue(((OrderCheckSku)u.get(j)).getBillcode());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(1);
           cell.setCellValue(((OrderCheckSku)u.get(j)).getWeight().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
         } 
       } 
     } 
 
 
     
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", "attachment;filename=orderCheck.xls");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
 }


