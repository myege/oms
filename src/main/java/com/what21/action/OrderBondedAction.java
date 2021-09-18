 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.google.gson.Gson;
 import com.jd.open.api.sdk.DefaultJdClient;
 import com.jd.open.api.sdk.request.JdRequest;
 import com.jd.open.api.sdk.request.customsglobalAPI.PopCustomsCenterServiceCallbackJsfServiceServiceCallbackRequest;
 import com.jd.open.api.sdk.response.customsglobalAPI.PopCustomsCenterServiceCallbackJsfServiceServiceCallbackResponse;
 import com.what21.dao.OrderBondedDao;
 import com.what21.model.OrderBonded;
 import com.what21.model.OrderBondedForExport;
 import com.what21.model.OrderBondedRule;
 import com.what21.model.OrderBondedSku;
 import com.what21.model.OrderSearchDto;
 import com.what21.service.OrderBondedRuleService;
 import com.what21.service.OrderBondedService;
 import com.what21.service.OrderBondedSkuService;
 import com.what21.tools.Tools;
 import com.what21.util.GeneralResult;
 import com.what21.util.ResponseUtil;
 import com.what21.util.timenew;
 import com.zt.kjybd.GetWLGJ;
 import com.zt.kjybd.PushToGY;
 import com.zt.kjybd.newzs;
 import java.io.File;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.text.DateFormat;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.LinkedHashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.ServletOutputStream;
 import javax.servlet.ServletRequest;
 import javax.servlet.ServletResponse;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.poi.hssf.usermodel.HSSFCell;
 import org.apache.poi.hssf.usermodel.HSSFCellStyle;
 import org.apache.poi.hssf.usermodel.HSSFRow;
 import org.apache.poi.hssf.usermodel.HSSFSheet;
 import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
 @RequestMapping({"/orderBonded"})
 public class OrderBondedAction
   extends BaseAction
 {
   @Autowired
   public OrderBondedService orderBondedService;
   @Autowired
   public OrderBondedSkuService orderBondedSkuService;
   @Autowired
   public OrderBondedRuleService orderBondedRuleService;
   @Autowired
   public OrderBondedDao orderBondedDao;
   String SERVER_URL = "https://api.jd.com/routerjson";
   String appKey = "3B03D933A76CF26E84CD449B63FEE94F";
   String accessToken = "a2b7cc35e5d240b3bd529a9fc9524610tjin";
   String appSecret = "33bd053e696c4cd9b6600148dea111ec";
   
   @RequestMapping({"/exportOrderUi"})
   public String exportOrderUi() {
     return "/orderBondedInspection/operate/exportOrderUi";
   }
 
 
 
   
   @RequestMapping({"/betweenOldNewUi"})
   public String queryStartOrEndTime() {
     return "/orderBondedInspection/operate/oldTimeBetweenNewTime";
   }
 
 
   
   @RequestMapping({"/betweenOldNewUi2"})
   public String queryStartOrEndTime2() {
     return "/orderBondedInspection/operate/oldTimeBetweenNewTime2";
   }
 
 
   
   @RequestMapping({"/betweenOldNewUi3"})
   public String queryStartOrEndTime3() {
     return "/orderBondedInspection/operate/oldTimeBetweenNewTime3";
   }
 
   
   @RequestMapping({"/submitStratTimeEndTime"})
   @ResponseBody
   public String queryStartEndTime(String startTime, String endTime, Model model) throws Exception {
     System.out.println("shijian=" + startTime + "==" + endTime);
     DateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
     startTime = startTime.replaceAll("-", "");
     startTime = startTime.replaceAll(" ", "");
     startTime = startTime.replaceAll(":", "");
     endTime = endTime.replaceAll("-", "");
     endTime = endTime.replaceAll(" ", "");
     endTime = endTime.replaceAll(":", "");
     System.out.println("shijian=" + startTime + "==" + endTime);
     
     long beginDate = format.parse(startTime).getTime();
     long endDate = format.parse(endTime).getTime();
     for (int page = 1; page < 10; page++) {
       
       System.out.println("---------------------开始查询京东订单---------------------");
       String methodName = "jingdong.hangzhou.customs.queryOrderByParam";
       
       JSONObject jsonObject = new JSONObject();
       jsonObject.put("beginDate", Long.valueOf(beginDate));
       jsonObject.put("endDate", Long.valueOf(endDate));
       jsonObject.put("page", Integer.valueOf(page));
       jsonObject.put("type", Integer.valueOf(2));
       String result = "";
 
       
       JSONObject msg = this.orderBondedService.queryOrderByParamInsert(result);
       System.out.println(msg);
       
       String resultMsg = msg.getString("resultMsg");
       System.out.println("resultMsg==" + resultMsg);
       if (resultMsg.contains("不存在符合条件的订单记录")) {
         break;
       }
     } 
     
     if (startTime != null && endTime != null) {
       return "0";
     }
     return "1";
   }
 
 
 
   
   @RequestMapping({"/submitStratTimeEndTime2"})
   public void queryStartEndTime2(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String startTime = request.getParameter("startTime1");
     String endTime = request.getParameter("endTime1");
     System.out.println("shijian=" + startTime + "==" + endTime);
     
     long l1 = System.currentTimeMillis();
     
     SXSSFWorkbook ob = new SXSSFWorkbook();
 
     
     Sheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(0);
     CellStyle cellStyle = ob.createCellStyle();
 
     
     cellStyle.setAlignment((short)2);
 
     
     Cell cell = row.createCell(0);
     
     cell.setCellValue("*业务编码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("*标的类型");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("*订单号 ");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("*商品及规格信息");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("*订单金额(外币/人民币）");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("*支付人姓名 ");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("*支付人身份证号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("*支付人电话");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("*商户下单日期");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("商户下单时间");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("*支付日期 ");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("支付时间");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(12);
     cell.setCellValue("*物流单号 ");
     cell.setCellStyle(cellStyle);
     
     OrderBondedForExport orderBondedForExport = new OrderBondedForExport();
     orderBondedForExport.setAuditstatus(Integer.valueOf(9));
     orderBondedForExport.setStartTime(startTime);
     orderBondedForExport.setEndTime(endTime);
     List<OrderBondedForExport> list = this.orderBondedService.exportOrderBonded(orderBondedForExport);
     int i = 1;
     int num = 1;
     String TxLogisticID = "";
     for (OrderBondedForExport order : list) {
       
       if (TxLogisticID.equals(order.getTxLogisticID())) {
         continue;
       }
       TxLogisticID = order.getTxLogisticID();
 
       
       row = sheet.createRow(i++);
       cell = row.createCell(0);
       cell.setCellValue("01121990");
       cell.setCellStyle(cellStyle);
       
       num++;
       
       cell = row.createCell(1);
       cell.setCellValue("4");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(2);
       cell.setCellValue(order.getTxLogisticID());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(3);
       cell.setCellValue(order.getItemName());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(4);
       cell.setCellValue(order.getWorth());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(5);
       cell.setCellValue(order.getBuyerName());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(6);
       cell.setCellValue(order.getBuyerIdNumber());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(7);
       cell.setCellValue(order.getReceiveManPhone());
       cell.setCellStyle(cellStyle);
       
       SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
       
       cell = row.createCell(8);
       cell.setCellValue(df.format(order.getCreateTime()));
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(9);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(10);
       cell.setCellValue(df.format(order.getCreateTime()));
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(11);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(12);
       cell.setCellValue(order.getMailNo());
       cell.setCellStyle(cellStyle);
     } 
 
     
     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
     response.setHeader("Content-disposition", "attachment;filename=orderkj.xlsx");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
     System.out.println("保税完结导出花费时间：" + (System.currentTimeMillis() - l1));
   }
 
 
 
   
   @RequestMapping({"/submitStratTimeEndTime3"})
   public void queryStartEndTime3(HttpServletRequest request, HttpServletResponse response) throws Exception {
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
     String startTime = request.getParameter("startTime1");
     String endTime = request.getParameter("endTime1");
     String sjbm = request.getParameter("sjbm1");
     System.out.println("shijian=" + startTime + "==" + endTime + "==" + sjbm);
     
     long l1 = System.currentTimeMillis();
     
     SXSSFWorkbook ob = new SXSSFWorkbook();
 
     
     Sheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(0);
     CellStyle cellStyle = ob.createCellStyle();
 
     
     cellStyle.setAlignment((short)2);
 
     
     Cell cell = row.createCell(0);
     
     cell.setCellValue("客户订单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("收件人");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("收件省");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("收件市");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("收件区");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("收件地址家");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("收件电话");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("商品数量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("商品总价");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("商品名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("商品重量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("单价");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(12);
     cell.setCellValue("运费");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(13);
     cell.setCellValue("保费");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(14);
     cell.setCellValue("购买人身份证");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(15);
     cell.setCellValue("购买人");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(16);
     cell.setCellValue("商品SKU");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(17);
     cell.setCellValue("快递公司（申通:STO）");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(18);
     cell.setCellValue("发件仓库");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(19);
     cell.setCellValue("商家编码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(20);
     cell.setCellValue("快递单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(21);
     cell.setCellValue("支付流水号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(22);
     cell.setCellValue("订单创建时间");
     cell.setCellStyle(cellStyle);
     OrderBondedForExport orderBondedForExport = new OrderBondedForExport();
     orderBondedForExport.setAuditstatus(Integer.valueOf(9));
     orderBondedForExport.setStartTime(startTime);
     orderBondedForExport.setEndTime(endTime);
     orderBondedForExport.setMerchantNum(sjbm);
     List<OrderBondedForExport> list = this.orderBondedService.exportOrderBonded(orderBondedForExport);
     int i = 1;
     int num = 1;
     for (OrderBondedForExport order : list) {
 
 
       
       row = sheet.createRow(i++);
       cell = row.createCell(0);
       cell.setCellValue(order.getTxLogisticID());
       cell.setCellStyle(cellStyle);
       
       num++;
       
       cell = row.createCell(1);
       cell.setCellValue(order.getReceiveMan());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(2);
       cell.setCellValue(order.getReceiveProvince());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(3);
       cell.setCellValue(order.getReceiveCity());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(4);
       cell.setCellValue(order.getReceiveCounty());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(5);
       cell.setCellValue(order.getReceiveManAddress());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(6);
       cell.setCellValue(order.getReceiveManPhone());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(7);
       cell.setCellValue(order.getItemCount2());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(8);
       cell.setCellValue(order.getAllPrice());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(9);
       cell.setCellValue(order.getItemName2());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(10);
       cell.setCellValue(order.getItemWeight());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(11);
       cell.setCellValue(order.getUnitPrice());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(12);
       cell.setCellValue(order.getFeeAmount());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(13);
       cell.setCellValue(order.getInsureAmount());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(14);
       cell.setCellValue(order.getBuyerIdNumber());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(15);
       cell.setCellValue(order.getBuyerName());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(16);
       cell.setCellValue(order.getItemsku());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(17);
       cell.setCellValue(order.getCarrier());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(18);
       cell.setCellValue(order.getSendWarehouse());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(19);
       cell.setCellValue(order.getMerchantNum());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(20);
       cell.setCellValue(order.getMailNo());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(21);
       cell.setCellValue(order.getPayNumber());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(22);
       cell.setCellValue(df.format(order.getCreateTime()));
       cell.setCellStyle(cellStyle);
     } 
     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
     response.setHeader("Content-disposition", "attachment;filename=order2.xlsx");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
     System.out.println("保税完结导出花费时间：" + (System.currentTimeMillis() - l1));
   }
 
 
   
   @RequestMapping({"/jdExceptionRollback"})
   @ResponseBody
   public int jdExceptionRollback(String[] txLogisticIDs) throws Exception {
     System.out.println("获取到的IDS=" + txLogisticIDs[0]); byte b; int i; String[] arrayOfString;
     for (i = (arrayOfString = txLogisticIDs).length, b = 0; b < i; ) { String ddh = arrayOfString[b];
       List<OrderBonded> lists = this.orderBondedDao.findbygjz(ddh);
       for (OrderBonded OrderBonded : lists) {
 
         
         DefaultJdClient defaultJdClient = new DefaultJdClient(this.SERVER_URL, this.accessToken, this.appKey, this.appSecret);
         PopCustomsCenterServiceCallbackJsfServiceServiceCallbackRequest request = new PopCustomsCenterServiceCallbackJsfServiceServiceCallbackRequest();
         request.setOrderId(Long.valueOf(Long.parseLong(OrderBonded.getTxLogisticID())));
         request.setServiceId("020045");
         request.setCustomsId("hangzhou");
         request.setOrderStatus(Integer.valueOf(0));
         request.setOrderDesc(OrderBonded.getReturnInfo());
         request.setGoodsCheck(0);
         PopCustomsCenterServiceCallbackJsfServiceServiceCallbackResponse response = (PopCustomsCenterServiceCallbackJsfServiceServiceCallbackResponse)defaultJdClient.execute((JdRequest)request);
 
         
         System.out.println("resultMsg==" + response.getMsg());
       } 
       b++; }
     
     return 1;
   }
 
   
   @RequestMapping({"/findInfoUi"})
   public String findInfoUi(String txLogisticID, HttpServletRequest request, Model model) {
     request.getSession().setAttribute("txLogisticID", txLogisticID);
     OrderBonded u = this.orderBondedService.findInfo(txLogisticID);
     u.setTxLogisticID(txLogisticID);
     model.addAttribute("order", u);
     return "/orderBondedInspection/operate/findInfoUi";
   }
   
   @RequestMapping({"/editUi"})
   public String editUi(Model model, String carrier, int id) {
     model.addAttribute("carrier", carrier);
     model.addAttribute("id", Integer.valueOf(id));
     return "/orderBondedInspection/operate/editUi";
   }
   
   @RequestMapping({"/ruleEditUi"})
   public String ruleEditUi(Model model, Integer id) {
     if (id != null) {
       OrderBondedRule orderBondedRule = this.orderBondedRuleService.findById(id);
       model.addAttribute("orderBondedRule", orderBondedRule);
     } 
     return "/orderBondedInspection/operate/ruleEditUi";
   }
   
   @RequestMapping({"/updateAll"})
   @ResponseBody
   public int updateAll(OrderBonded orderBonded) {
     int i = this.orderBondedService.editMatter(orderBonded);
     return i;
   }
   
   @RequestMapping({"/findAll"})
   public void findAll(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     int auditstatus = Integer.parseInt(orderSearchDto.getAuditstatus());
     if (auditstatus == 0) {
       if (orderSearchDto.getMark() != null && orderSearchDto.getMark().intValue() == 2) {
         orderSearchDto.setMark(null);
       } else {
         orderSearchDto.setMark(Integer.valueOf(0));
       } 
     } else if (auditstatus == 1) {
       if (orderSearchDto.getMark() != null && orderSearchDto.getMark().intValue() == 2) {
         orderSearchDto.setMark(null);
       } else {
         orderSearchDto.setMark(Integer.valueOf(1));
       } 
       auditstatus = 0;
     } else if (auditstatus == 2) {
       orderSearchDto.setWms(Integer.valueOf(0));
       auditstatus = 9;
     } 
     
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     pageMap.put("txLogisticID", orderSearchDto.getTxLogisticID());
     pageMap.put("receiveMan", orderSearchDto.getReceiveMan());
     pageMap.put("pc", orderSearchDto.getPc());
     pageMap.put("itemName", orderSearchDto.getItemName());
     pageMap.put("mailNo", orderSearchDto.getMailNo());
     pageMap.put("payNumber", orderSearchDto.getPayNumber());
     pageMap.put("merchantNum", orderSearchDto.getMerchantNum());
     
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("auditstatus", Integer.valueOf(auditstatus));
     pageMap.put("mark", orderSearchDto.getMark());
     pageMap.put("wms", orderSearchDto.getWms());
     
     List<OrderBonded> u = this.orderBondedService.findAll(pageMap);
     int total = this.orderBondedService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
   
   @RequestMapping({"/findByParam"})
   public void findByParam(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     pageMap.put("paramValue", orderSearchDto.getParamValue());
     pageMap.put("paramName", orderSearchDto.getParamName());
     pageMap.put("startPage", Integer.valueOf((orderSearchDto.getPage().intValue() - 1) * orderSearchDto.getRows().intValue()));
     pageMap.put("endPage", orderSearchDto.getRows());
     pageMap.put("userId", Integer.valueOf(userId));
     List<OrderBonded> u = this.orderBondedService.findAll(pageMap);
     int total = this.orderBondedService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
   
   @RequestMapping({"/importOrderBonded"})
   @ResponseBody
   public Map<String, String> importOrderBonded(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request) {
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
       int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
       result = this.orderBondedService.importOrderNew(String.valueOf(path) + fileName, userId);
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
   
   @ResponseBody
   @RequestMapping({"/deleteOrderBonded"})
   public int deleteOrderBonded(String[] ids) {
     int result = this.orderBondedService.deleteOrderBonded(ids);
     return result;
   }
   
   @ResponseBody
   @RequestMapping({"/auditOrder"})
   public int auditOrder(String paramJson, HttpServletRequest request, HttpServletResponse response) {
     try {
       return this.orderBondedService.updateAuditstatus(paramJson);
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
   }
   
   @ResponseBody
   @RequestMapping({"/matchMailNo"})
   public int matchMailNo(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.orderBondedService.updateExpressParam(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/matchDSHKMailNo"})
   public int matchDSHKMailNo(Integer[] ids, HttpServletRequest request, HttpServletResponse response) {
     return this.orderBondedService.updateDSHKExpressParam(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/matchMailNo2ForHTO"})
   public int matchMailNo2ForHTO(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.orderBondedService.updateExpressParam2ForHTO(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/matchMailNo3ForHTO"})
   public int matchMailNo3ForHTO(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.orderBondedService.updateExpressParam3ForHTO(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/pushOrder"})
   public int pushOrder(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.orderBondedService.updateIspost(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/pushOrderZFB"})
   public int pushOrderZFB(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.orderBondedService.updatePushOrderZFB(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/pushOrderToDsds"})
   public int pushOrderToDsds(String ids, HttpServletRequest request, HttpServletResponse response) {
     try {
       return this.orderBondedService.pushOrderToDsds(ids);
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
   }
   
   @ResponseBody
   @RequestMapping({"/pushOrderToYf"})
   public int pushOrderToYf(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
     return this.orderBondedService.updateIspostToYf(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/pushOrderToWms"})
   public void pushOrderToWms(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
     String result = this.orderBondedService.updateIsPushToWmsNew(ids);
     response.getWriter().write(result);
   }
   
   @ResponseBody
   @RequestMapping({"/pushDd"})
   public int pushDd(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.orderBondedService.pushDd(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/pushQd"})
   public int pushQd(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.orderBondedService.pushQd(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/customs"})
   public void customs(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
     String result = this.orderBondedService.updateIsCustoms(ids);
     response.getWriter().write(result);
   }
   
   @ResponseBody
   @RequestMapping({"/push3"})
   public int push3(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
     return this.orderBondedService.push3(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/deliverGoods"})
   public int deliverGoods(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.orderBondedService.updateAuditstatusByIds(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/pushGoods"})
   public int pushGoods(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.orderBondedService.insertAuditstatusByIds(ids);
   }
   
   @RequestMapping({"/Detailed"})
   public void findDetailed(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     String txLogisticID = (String)request.getSession().getAttribute("txLogisticID");
     pageMap.put("txLogisticID", txLogisticID);
     List<OrderBondedSku> u = this.orderBondedSkuService.Detailed(pageMap);
     int total = this.orderBondedSkuService.count(txLogisticID);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"/getBondedBill"})
   public void getBondedBills(String mailNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
     String ret = GetWLGJ.queryWlgj(mailNo);
     if (ret.contains("false") || !ret.contains("<detail>")) {
       response.getWriter().write("1");
     } else {
       response.getWriter().write(ret);
     } 
   }
   
   @ResponseBody
   @RequestMapping({"/payNumberTL"})
   public int payNumberTL(String ids, HttpServletRequest request, HttpServletResponse response) {
     try {
       return this.orderBondedService.payNumberTL(ids);
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
   }
   
   @RequestMapping({"/updaddress"})
   public void updaddress(OrderBonded orderBonded, HttpServletRequest request, HttpServletResponse response) throws Exception {
     String sprovince = request.getParameter("sprovince");
     String scity = request.getParameter("scity");
     String scounty = request.getParameter("scounty");
     String id = request.getParameter("id");
     OrderBonded ob = new OrderBonded();
     ob.setId(Integer.parseInt(id));
     ob.setReceiveProvince(sprovince);
     ob.setReceiveCity(scity);
     ob.setReceiveCounty(scounty);
     int res = this.orderBondedService.updaddress(ob);
     String str = JSONObject.toJSONString(Integer.valueOf(res));
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"/getPayNumber1"})
   public int getPayNumber1(String txLogisticID, HttpServletRequest request, HttpServletResponse response) {
     int ret = -1;
     String txLogisticID1 = request.getParameter("txLogisticID");
     ret = this.orderBondedService.payNumber(txLogisticID1);
     return ret;
   }
 
   
   @ResponseBody
   @RequestMapping({"/findByMailNo"})
   public OrderBonded findByMailNo(@RequestParam(value = "mailNo", required = true) String mailNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
     OrderBonded ob = this.orderBondedService.findByMailNo(mailNo);
     return ob;
   }
   
   @RequestMapping({"/exportOrder"})
   public void OrderBonded(HttpServletRequest request, HttpServletResponse response) throws Exception {
     SXSSFWorkbook ob = new SXSSFWorkbook();
 
     
     Sheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(0);
     CellStyle cellStyle = ob.createCellStyle();
 
     
     cellStyle.setAlignment((short)2);
 
     
     Cell cell = row.createCell(0);
     
     cell.setCellValue("客户订单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("收件人");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("收件省");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("收件市");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("收件区");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("收件地址");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("收件电话");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("商品数量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("商品总价");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("商品名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("商品重量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("单价");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(12);
     cell.setCellValue("运费");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(13);
     cell.setCellValue("保费");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(14);
     cell.setCellValue("购买人身份证");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(15);
     cell.setCellValue("购买人");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(16);
     cell.setCellValue("商品SKU");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(17);
     cell.setCellValue("快递公司(申通：STO)");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(18);
     cell.setCellValue("发件仓库");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(19);
     cell.setCellValue("商家编码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(20);
     cell.setCellValue("快递单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(21);
     cell.setCellValue("备注");
     cell.setCellStyle(cellStyle);
     
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     int auditstatus = Integer.valueOf(request.getParameter("auditstatus")).intValue();
     String ids = request.getParameter("orderBonded");
     String value = request.getParameter("value");
     String name = request.getParameter("name");
     String mark = request.getParameter("mark");
     String wms = request.getParameter("wms");
     String time3 = request.getParameter("time3");
     String time4 = request.getParameter("time4");
     
     if (auditstatus == 0) {
       if (mark != null && !"2".equals(mark) && !"".equals(mark)) {
         mark = "0";
       } else {
         mark = null;
       } 
     } else if (auditstatus == 1) {
       if (mark != null && !"2".equals(mark) && !"".equals(mark)) {
         mark = "1";
       } else {
         mark = null;
       } 
       auditstatus = 0;
     } else if (auditstatus == 2) {
       wms = "0";
       auditstatus = 9;
     } else if (auditstatus == 4) {
       auditstatus = 0;
     } 
     if (ids.equals("cxqbdc")) {
       
       pageMap.put("paramValue", value);
       pageMap.put("auditstatus", Integer.valueOf(auditstatus));
       pageMap.put("paramName", name);
       pageMap.put("userId", Integer.valueOf(userId));
       pageMap.put("mark", mark);
       pageMap.put("wms", wms);
       pageMap.put("time3", time3);
       pageMap.put("time4", time4);
       
       List<OrderBondedForExport> list = this.orderBondedService.exportOrder(pageMap);
       
       int a = 0;
       for (int j = 0; j < list.size(); j++) {
         OrderBondedForExport u = list.get(j);
         a = 1 + j;
         row = sheet.createRow(a);
         
         cell = row.createCell(0);
         cell.setCellValue(u.getTxLogisticID());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(u.getReceiveMan());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(2);
         cell.setCellValue(u.getReceiveProvince());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(3);
         cell.setCellValue(u.getReceiveCity());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(4);
         cell.setCellValue(u.getReceiveCounty());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(5);
         cell.setCellValue(u.getReceiveManAddress());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(6);
         cell.setCellValue(u.getReceiveManPhone());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(7);
         cell.setCellValue(u.getItemCount2());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(8);
         cell.setCellValue(u.getAllPrice());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(9);
         cell.setCellValue(u.getItemName2());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(10);
         cell.setCellValue(u.getItemWeight2());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(11);
         cell.setCellValue(u.getUnitPrice());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(12);
         cell.setCellValue(u.getFeeAmount());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(13);
         cell.setCellValue(u.getInsureAmount());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(14);
         cell.setCellValue(u.getBuyerIdNumber());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(15);
         cell.setCellValue(u.getBuyerName());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(16);
         cell.setCellValue(u.getItemsku());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(17);
         cell.setCellValue(u.getCarrier());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(18);
         cell.setCellValue(u.getSendWarehouse());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(19);
         cell.setCellValue(u.getMerchantNum());
         cell.setCellStyle(cellStyle);
 
         
         cell = row.createCell(20);
         cell.setCellValue(u.getMailNo());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(21);
         cell.setCellValue(u.getPc());
         cell.setCellStyle(cellStyle);
       
       }
 
     
     }
     else {
       
       int a = 0;
       List<OrderBondedForExport> list = this.orderBondedService.exportBondedByIds(ids);
       
       for (int j = 0; j < list.size(); j++) {
         OrderBondedForExport u = list.get(j);
         a = j + 1;
         row = sheet.createRow(a);
         
         cell = row.createCell(0);
         cell.setCellValue(u.getTxLogisticID());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(u.getReceiveMan());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(2);
         cell.setCellValue(u.getReceiveProvince());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(3);
         cell.setCellValue(u.getReceiveCity());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(4);
         cell.setCellValue(u.getReceiveCounty());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(5);
         cell.setCellValue(u.getReceiveManAddress());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(6);
         cell.setCellValue(u.getReceiveManPhone());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(7);
         cell.setCellValue(u.getItemCount2());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(8);
         cell.setCellValue(u.getAllPrice());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(9);
         cell.setCellValue(u.getItemName2());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(10);
         cell.setCellValue(u.getItemWeight2());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(11);
         cell.setCellValue(u.getUnitPrice());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(12);
         cell.setCellValue(u.getFeeAmount());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(13);
         cell.setCellValue(u.getInsureAmount());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(14);
         cell.setCellValue(u.getBuyerIdNumber());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(15);
         cell.setCellValue(u.getBuyerName());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(16);
         cell.setCellValue(u.getItemsku());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(17);
         cell.setCellValue(u.getCarrier());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(18);
         cell.setCellValue(u.getSendWarehouse());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(19);
         cell.setCellValue(u.getMerchantNum());
         cell.setCellStyle(cellStyle);
 
         
         cell = row.createCell(20);
         cell.setCellValue(u.getMailNo());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(21);
         cell.setCellValue(u.getPc());
         cell.setCellStyle(cellStyle);
       } 
     }     
     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
     response.setHeader("Content-disposition", "attachment;filename=orderBonded.xlsx");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
   
   @ResponseBody
   @RequestMapping({"/odbdateTime"})
   public void odbdateTime(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     Map<String, Object> pageMap = new HashMap<>();
     Map<String, Object> map = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     pageMap.put("time3", orderSearchDto.getTime3());
     pageMap.put("time4", orderSearchDto.getTime4());
     pageMap.put("paramName", orderSearchDto.getParamName());
     pageMap.put("paramValue", orderSearchDto.getParamValue());
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("auditstatus", Integer.valueOf(9));
     pageMap.put("startPage", Integer.valueOf((orderSearchDto.getPage().intValue() - 1) * orderSearchDto.getRows().intValue()));
     pageMap.put("endPage", orderSearchDto.getRows());
     List<OrderBonded> odb = this.orderBondedService.odbdateTime(pageMap);
     int total = this.orderBondedService.count(pageMap);
     map.put("rows", odb);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"/merge"})
   public void merger(@RequestParam(value = "id", required = true) String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int a = 0;
     double b = 0.0D;
     double c = 0.0D;
     String ti = "";
     String xx = "";
     int arr = 0;
     List<OrderBonded> odbtxid = this.orderBondedService.findTxId(id);
     List<OrderBondedSku> ods = new ArrayList<>();
     xx = ((OrderBonded)odbtxid.get(0)).getTxLogisticID();
     String id2 = request.getParameter("id");
     String txid = request.getParameter("txid");
     String[] newid = id2.split(",");
     String[] newtxid = txid.split(",");
     List<OrderBonded> odb = new ArrayList<>();
     for (int i = 0; i < newid.length; i++) {
       ti = newid[0];
       odb = this.orderBondedService.findCs(newid[i]);
       for (int m = 0; m < odb.size(); m++) {
         a += ((OrderBonded)odb.get(m)).getItemCount().intValue();
         b += ((OrderBonded)odb.get(m)).getItemWeight().doubleValue();
         c += ((OrderBonded)odb.get(m)).getWorth().doubleValue();
       } 
     } 
     OrderBondedSku ou = new OrderBondedSku();
     for (int j = 0; j < newtxid.length; j++) {
       ods = this.orderBondedSkuService.skuupd(newtxid[j]);
       for (int x = 0; x < ods.size(); x++) {
         arr = ((OrderBondedSku)ods.get(x)).getId();
         ou.setId(arr);
         ou.setTxLogisticID(xx);
         this.orderBondedSkuService.updods(ou);
       } 
     } 
     OrderBonded ob = new OrderBonded();
     ob.setItemCount(Integer.valueOf(a));
     ob.setItemWeight(Double.valueOf(b));
     ob.setWorth(Double.valueOf(c));
     ob.setId(Integer.parseInt(ti));
     int res = this.orderBondedService.upm(ob);
     for (int k = 0; k < newid.length; k++) {
       if (newid[k] != ti) {
         this.orderBondedService.deletetx(newid[k]);
       }
     } 
     String str = JSONObject.toJSONString(Integer.valueOf(res));
     response.getWriter().write(str);
   }
 
 
   
   @RequestMapping({"/editodIdN"})
   public String editodIdN(OrderBonded orderBonded, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     resultTotal = this.orderBondedService.editodIdN(orderBonded);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
 
   
   @RequestMapping({"/editMatter"})
   public String editMatter(OrderBonded orderBonded, HttpServletResponse response, HttpServletRequest request) throws Exception {
     int id = Integer.parseInt(request.getParameter("id"));
     orderBonded.setId(id);
     int resultTotal = 0;
     resultTotal = this.orderBondedService.editMatter(orderBonded);
     
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
   
   @RequestMapping({"/detail"})
   @ResponseBody
   public OrderBonded detail(int id) throws Exception {
     OrderBonded orderBonded = this.orderBondedService.findByIdToBG(Integer.valueOf(id));
     return orderBonded;
   }
   
   @RequestMapping({"/exportOrder2"})
   public void exportOrder2(HttpServletRequest request, HttpServletResponse response) throws Exception {
     HSSFWorkbook ob = new HSSFWorkbook();
 
     
     HSSFSheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     HSSFRow row = sheet.createRow(0);
     HSSFCellStyle hSSFCellStyle = ob.createCellStyle();
 
     
     hSSFCellStyle.setAlignment((short)2);
 
     
     HSSFCell cell = row.createCell(0);
     
     cell.setCellValue("客户订单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("运单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("身份证名称");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("号码");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("创建时间");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("回执内容");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
 
     
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     int auditstatus = Integer.valueOf(request.getParameter("auditstatus")).intValue();
     String ids = request.getParameter("orderBonded2");
     String value = request.getParameter("value");
     String name = request.getParameter("name");
     String mark = request.getParameter("mark");
     String wms = request.getParameter("wms");
     
     if (auditstatus == 0) {
       if (mark != null && !"2".equals(mark) && !"".equals(mark)) {
         mark = "0";
       } else {
         mark = null;
       } 
     } else if (auditstatus == 1) {
       if (mark != null && !"2".equals(mark) && !"".equals(mark)) {
         mark = "1";
       } else {
         mark = null;
       } 
       auditstatus = 0;
     } else if (auditstatus == 2) {
       wms = "0";
       auditstatus = 9;
     } else if (auditstatus == 4) {
       auditstatus = 0;
     } 
     
     if (ids.equals("cxqbdc")) {
       
       pageMap.put("paramValue", value);
       pageMap.put("auditstatus", Integer.valueOf(auditstatus));
       pageMap.put("paramName", name);
       pageMap.put("userId", Integer.valueOf(userId));
       pageMap.put("mark", mark);
       pageMap.put("wms", wms);
       
       List<OrderBondedForExport> list = this.orderBondedService.exportOrder(pageMap);
       
       int a = 0;
       for (OrderBondedForExport u : list)
       {
         row = sheet.createRow(++a);
         
         cell = row.createCell(0);
         cell.setCellValue(u.getTxLogisticID());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(u.getMailNo());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(2);
         cell.setCellValue(u.getBuyerIdNumber());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(3);
         cell.setCellValue(u.getReceiveManPhone());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(4);
         cell.setCellValue(Tools.format("yyyy-MM-dd HH:mm:ss", u.getCreateTime()));
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(5);
         if (u.getAuditstatus().intValue() == 9) {
           cell.setCellValue("放行");
         } else if (u.getReturncode().intValue() == 0) {
           cell.setCellValue("未处理");
         } else if (u.getReturncode().intValue() == 1) {
           String str = u.getReturnInfo();
           cell.setCellValue(str.substring(str.lastIndexOf("Desc:"), str.lastIndexOf("]")));
         } 
         cell.setCellStyle((CellStyle)hSSFCellStyle);
       
       }
 
     
     }
     else {
 
       
       String[] arr = ids.split(",");
       int a = 0;
       for (int i = 0; i < arr.length; i++) {
         
         OrderBonded u = this.orderBondedService.findByIdToBG(Integer.valueOf(Integer.parseInt(arr[i])));
         
         row = sheet.createRow(++a);
         
         cell = row.createCell(0);
         cell.setCellValue(u.getTxLogisticID());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(u.getMailNo());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(2);
         cell.setCellValue(u.getBuyerIdNumber());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(3);
         cell.setCellValue(u.getReceiveManPhone());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(4);
         cell.setCellValue(Tools.format("yyyy-MM-dd HH:mm:ss", u.getCreateTime()));
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(5);
         if (u.getAuditstatus() == 9) {
           cell.setCellValue("放行");
         } else if (u.getReturncode().intValue() == 0) {
           cell.setCellValue("未处理");
         } else if (u.getReturncode().intValue() == 1) {
           String str = u.getReturnInfo();
           cell.setCellValue(str.substring(str.lastIndexOf("Desc:"), str.lastIndexOf("]")));
         } 
         cell.setCellStyle((CellStyle)hSSFCellStyle);
       } 
     }     
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", "attachment;filename=orderBonded.xls");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
   
   @RequestMapping({"/updateAuditstatus0"})
   @ResponseBody
   public int updateAuditstatus0(int[] ids) throws Exception {
     try {
       this.orderBondedService.updateAuditstatus0(ids);
       return 1;
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
   }
   @RequestMapping({"/updateCarrier"})
   @ResponseBody
   public int updateCarrier(String carrier, int id) throws Exception {
     try {
       OrderBonded orderBonded = new OrderBonded();
       orderBonded.setId(id);
       orderBonded.setCarrier(carrier);
       this.orderBondedService.updateCarrier(orderBonded);
       return 1;
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
   }
   
   @RequestMapping({"/getMailNoStraight"})
   public void getMailNoStraight(HttpServletRequest request, HttpServletResponse response, String count, String carrier) throws Exception {
     try {
       int i = 0;
       
       HSSFWorkbook ob = new HSSFWorkbook();
 
       
       HSSFSheet sheet = ob.createSheet("sheet1");
       sheet.setDefaultColumnWidth(20);
 
       
       HSSFRow row = sheet.createRow(i);
       HSSFCellStyle hSSFCellStyle = ob.createCellStyle();
 
       
       hSSFCellStyle.setAlignment((short)2);
 
       
       HSSFCell cell = row.createCell(0);
       
       cell.setCellValue("运单号");
       cell.setCellStyle((CellStyle)hSSFCellStyle);
       int icount = 0;
       try {
         icount = Integer.parseInt(count);
       } catch (Exception e) {
         e.printStackTrace();
         request.setAttribute("msg", "拿单号数量必须为大于0的整数！");
         request.getRequestDispatcher("/msg.jsp").forward((ServletRequest)request, (ServletResponse)response);
         return;
       } 
       if (icount <= 0) {
         request.setAttribute("msg", "拿单号数量不能为0！");
         request.getRequestDispatcher("/msg.jsp").forward((ServletRequest)request, (ServletResponse)response);
         
         return;
       } 
       if (icount > 1000) {
         request.setAttribute("msg", "一次最多拿1000单号！");
         request.getRequestDispatcher("/msg.jsp").forward((ServletRequest)request, (ServletResponse)response);
         
         return;
       } 
       
       List<String> list = this.orderBondedService.getMailNoStraight(carrier, icount);
       if (list.size() == 0) {
         request.setAttribute("msg", "错误！快递不存在！");
         request.getRequestDispatcher("/msg.jsp").forward((ServletRequest)request, (ServletResponse)response);
         return;
       } 
       String str = list.get(0);
       if (str.contains("错误")) {
         request.setAttribute("msg", "错误！单号不足或快递方接口出错！");
         request.getRequestDispatcher("/msg.jsp").forward((ServletRequest)request, (ServletResponse)response);
         
         return;
       } 
       
       for (String string : list) {
         HSSFRow row2 = sheet.createRow(++i);
 
         
         HSSFCell cell2 = row2.createCell(0);
         
         cell2.setCellValue(string);
         cell2.setCellStyle((CellStyle)hSSFCellStyle);
       } 
       
       response.setContentType("application/vnd.ms-excel");
       response.setHeader("Content-disposition", "attachment;filename=orderBonded.xls");
       ServletOutputStream servletOutputStream = response.getOutputStream();
       ob.write((OutputStream)servletOutputStream);
       servletOutputStream.flush();
       servletOutputStream.close();
     } catch (Exception e) {
       e.printStackTrace();
       request.setAttribute("msg", "错误！单号不足或快递方接口出错！");
       request.getRequestDispatcher("/msg.jsp").forward((ServletRequest)request, (ServletResponse)response);
       return;
     } 
   }
 
   
   @RequestMapping({"/cancleOrder"})
   @ResponseBody
   public List<String> cancleOrder(int[] ids) throws Exception {
     return this.orderBondedService.cancleOrder(ids);
   }
   
   @RequestMapping({"/deleteByIds"})
   @ResponseBody
   public int deleteByIds(int[] ids) throws Exception {
     try {
       this.orderBondedService.deleteByIds(ids);
       return 1;
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
   }
   @RequestMapping({"/matchSDM"})
   @ResponseBody
   public int matchSDM(int[] ids) throws Exception {
     try {
       this.orderBondedService.matchSDM(ids);
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
     return 1;
   }
   
   @ResponseBody
   @RequestMapping({"/payNumberKQ"})
   public int payNumberKQ(String ids, HttpServletRequest request, HttpServletResponse response) {
     try {
       return this.orderBondedService.payNumberKQ(ids);
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
   }
   
   @ResponseBody
   @RequestMapping({"/pushBfb"})
   public String pushBfb(String id, String no) throws Exception {
     System.out.println(String.valueOf(id) + "," + no);
     String buffer = this.orderBondedService.pushBfb(id, no);
     return buffer;
   }
   
   @ResponseBody
   @RequestMapping({"/pushBfbzf"})
   public String pushBfbzf(String id, String no) throws Exception {
     System.out.println(String.valueOf(id) + "," + no);
     String[] idArr = id.split(",");
     
     return "success";
   }
   
   @ResponseBody
   @RequestMapping({"/pushSqzf"})
   public String pushSqzf(String id, String no) throws Exception {
     System.out.println(String.valueOf(id) + "," + no);
     String[] idArr = id.split(",");
     String buffer = ""; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String ids = arrayOfString1[b];
       int parseInt = Integer.parseInt(ids);
       OrderBonded orderBonded = this.orderBondedDao.findByIdToBG(Integer.valueOf(parseInt));
       Gson gson = new Gson();
       Map<String, Object> map = new LinkedHashMap<>();
       String guid = "AAAAAAAAAAAABBBBBBBBBBBB" + newzs.getRandomCharAndNumr(Integer.valueOf(12));
       map.put("guid", guid);
       map.put("merAccount", "hbgj");
       map.put("appType", "1");
       map.put("appTime", timenew.newtime());
       map.put("appStatus", "2");
       map.put("orderType", "I");
       map.put("orderNo", orderBonded.getTxLogisticID());
       map.put("ebpCode", "1234567890");
       map.put("ebpName", "电商平台");
       map.put("ebcCode", "1234567890");
       map.put("ebcName", "电商企业");
       map.put("orderMoney", orderBonded.getWorth());
       map.put("freight", "0");
       map.put("discount", "0");
       map.put("taxMoney", "0");
       map.put("payMoney", "100");
       map.put("currency", "142");
       map.put("buyerRegNo", orderBonded.getBuyerName());
       map.put("buyerName", orderBonded.getBuyerName());
       map.put("buyerIdType", "1");
       map.put("buyerIdNumber", orderBonded.getBuyerIdNumber());
       map.put("buyerTelephone", orderBonded.getReceiveManPhone());
       String str = gson.toJson(map);
       String od = "{\"orderInfo\":" + str + ",\"productList\":[]}";
       String str_orderInfo = "";
       
       str_orderInfo = od.replaceAll("\\\\", "");
       String result = PushToGY.sendPost("http://139.129.116.198/cbes/api/report", str_orderInfo);
       if (result.indexOf("成功") != -1) {
         OrderBonded orderBonded2 = new OrderBonded();
         orderBonded2.setId(Integer.parseInt(ids));
         orderBonded2.setIspost(1);
         this.orderBondedDao.updateIspost(orderBonded2);
         buffer = "success";
       } 
       b++; }
     
     return buffer;
   }
   
   @ResponseBody
   @RequestMapping({"/payNumberSQ"})
   public String payNumberSQ(String id, HttpServletRequest request, HttpServletResponse response) {
     System.out.println(id);
     String[] idArr = id.split(","); byte b; int i;
     String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String ids = arrayOfString1[b];
       int parseInt = Integer.parseInt(ids);
       OrderBonded orderBonded = this.orderBondedDao.findByIdToBG(Integer.valueOf(parseInt));
       
       String od = "{\"orderNo\":'" + orderBonded.getTxLogisticID() + "'}";
       System.out.println("od=" + od);
       String result = PushToGY.sendPost("http://139.129.116.198/cbes/api/queryOrder", od);
       
       if (result.indexOf("查询成功") != -1) {
         JSONObject obj = JSONObject.parseObject(result);
         JSONObject obj2 = obj.getJSONObject("order");
         String billno = (String)obj2.get("billno");
         if (billno == null) {
           billno = "1";
         }
         OrderBonded orderBonded1 = this.orderBondedDao.findByTxLogisticID(orderBonded.getTxLogisticID());
         orderBonded1.setPayNumber(billno);
         this.orderBondedDao.updateByBfb(orderBonded1);
       } 
 
       
       b++; }
 
 
     
     return "success";
   }
   
   @ResponseBody
   @RequestMapping({"/pushForCFM"})
   public String pushForCFM(String ids) throws Exception {
     String s1 = this.orderBondedService.pushForCFM(ids);
     return s1;
   }
   
   @ResponseBody
   @RequestMapping({"/checkIdCard"})
   public String checkIdCard(String ids) throws Exception {
     String s1 = this.orderBondedService.checkIdCard(ids);
     return s1;
   }
   
   @RequestMapping({"/exportOrderBonded"})
   public void exportOrderBonded(HttpServletRequest request, HttpServletResponse response) throws IOException {
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
     long l1 = System.currentTimeMillis();
     
     SXSSFWorkbook ob = new SXSSFWorkbook();
 
     
     Sheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(0);
     CellStyle cellStyle = ob.createCellStyle();
 
     
     cellStyle.setAlignment((short)2);
 
     
     Cell cell = row.createCell(0);
     
     cell.setCellValue("客户订单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("收件人");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("收件省");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("收件市");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("收件区");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("收件地址家");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("收件电话");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("商品数量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("商品总价");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("商品名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("商品重量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("单价");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(12);
     cell.setCellValue("运费");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(13);
     cell.setCellValue("保费");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(14);
     cell.setCellValue("购买人身份证");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(15);
     cell.setCellValue("购买人");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(16);
     cell.setCellValue("商品SKU");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(17);
     cell.setCellValue("快递公司（申通:STO）");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(18);
     cell.setCellValue("发件仓库");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(19);
     cell.setCellValue("商家编码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(20);
     cell.setCellValue("快递单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(21);
     cell.setCellValue("支付流水号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(22);
     cell.setCellValue("订单创建时间");
     cell.setCellStyle(cellStyle);
     OrderBondedForExport orderBondedForExport = new OrderBondedForExport();
     orderBondedForExport.setAuditstatus(Integer.valueOf(9));
     String startTime = request.getParameter("startTime");
     String endTime = request.getParameter("endTime");
     orderBondedForExport.setStartTime(startTime);
     orderBondedForExport.setEndTime(endTime);
     List<OrderBondedForExport> list = this.orderBondedService.exportOrderBonded(orderBondedForExport);
     int i = 1;
     int num = 1;
     for (OrderBondedForExport order : list) {
 
 
       
       row = sheet.createRow(i++);
       cell = row.createCell(0);
       cell.setCellValue(order.getTxLogisticID());
       cell.setCellStyle(cellStyle);
       
       num++;
       
       cell = row.createCell(1);
       cell.setCellValue(order.getReceiveMan());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(2);
       cell.setCellValue(order.getReceiveProvince());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(3);
       cell.setCellValue(order.getReceiveCity());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(4);
       cell.setCellValue(order.getReceiveCounty());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(5);
       cell.setCellValue(order.getReceiveManAddress());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(6);
       cell.setCellValue(order.getReceiveManPhone());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(7);
       cell.setCellValue(order.getItemCount());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(8);
       cell.setCellValue(order.getAllPrice());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(9);
       cell.setCellValue(order.getItemName());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(10);
       cell.setCellValue(order.getItemWeight());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(11);
       cell.setCellValue(order.getUnitPrice());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(12);
       cell.setCellValue(order.getFeeAmount());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(13);
       cell.setCellValue(order.getInsureAmount());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(14);
       cell.setCellValue(order.getBuyerIdNumber());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(15);
       cell.setCellValue(order.getBuyerName());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(16);
       cell.setCellValue(order.getItemsku());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(17);
       cell.setCellValue(order.getCarrier());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(18);
       cell.setCellValue(order.getSendWarehouse());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(19);
       cell.setCellValue(order.getMerchantNum());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(20);
       cell.setCellValue(order.getMailNo());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(21);
       cell.setCellValue(order.getPayNumber());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(22);
       cell.setCellValue(df.format(order.getCreateTime()));
       cell.setCellStyle(cellStyle);
     } 
     
     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
     response.setHeader("Content-disposition", "attachment;filename=orderMail.xlsx");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
     System.out.println("保税完结导出花费时间：" + (System.currentTimeMillis() - l1));
   }
   
   @RequestMapping({"/exportOrderBonded_2"})
   public void exportOrderBonded_2(HttpServletRequest request, HttpServletResponse response) throws IOException {
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
     long l1 = System.currentTimeMillis();
     
     SXSSFWorkbook ob = new SXSSFWorkbook();
 
     
     Sheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(0);
     CellStyle cellStyle = ob.createCellStyle();
 
     
     cellStyle.setAlignment((short)2);
 
     
     Cell cell = row.createCell(0);
     
     cell.setCellValue("客户订单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("收件人");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("收件省");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("收件市");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("收件区");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("收件地址家");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("收件电话");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("商品数量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("商品总价");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("商品名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("商品重量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("单价");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(12);
     cell.setCellValue("运费");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(13);
     cell.setCellValue("保费");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(14);
     cell.setCellValue("购买人身份证");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(15);
     cell.setCellValue("购买人");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(16);
     cell.setCellValue("商品SKU");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(17);
     cell.setCellValue("快递公司（申通:STO）");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(18);
     cell.setCellValue("发件仓库");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(19);
     cell.setCellValue("商家编码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(20);
     cell.setCellValue("快递单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(21);
     cell.setCellValue("支付流水号");
     cell.setCellStyle(cellStyle);
     cell = row.createCell(22);
     cell.setCellValue("订单创建时间");
     cell.setCellStyle(cellStyle);
     OrderBondedForExport orderBondedForExport = new OrderBondedForExport();
     String ids = request.getParameter("ids");
     
     if (ids.equals("cxqb")) {
       String queryName = request.getParameter("queryName");
       String queryValue = request.getParameter("queryValue");
       orderBondedForExport.setQueryName(queryName);
       orderBondedForExport.setQueryValue(queryValue);
       orderBondedForExport.setAuditstatus(Integer.valueOf(4));
     } else {
       orderBondedForExport.setIds(ids);
     } 
     List<OrderBondedForExport> list = this.orderBondedService.exportOrderBonded(orderBondedForExport);
     int i = 1;
     int num = 1;
     for (OrderBondedForExport order : list) {
 
 
       
       row = sheet.createRow(i++);
       cell = row.createCell(0);
       cell.setCellValue(order.getTxLogisticID());
       cell.setCellStyle(cellStyle);
       
       num++;
       
       cell = row.createCell(1);
       cell.setCellValue(order.getReceiveMan());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(2);
       cell.setCellValue(order.getReceiveProvince());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(3);
       cell.setCellValue(order.getReceiveCity());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(4);
       cell.setCellValue(order.getReceiveCounty());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(5);
       cell.setCellValue(order.getReceiveManAddress());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(6);
       cell.setCellValue(order.getReceiveManPhone());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(7);
       cell.setCellValue(order.getItemCount());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(8);
       cell.setCellValue(order.getAllPrice());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(9);
       cell.setCellValue(order.getItemName());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(10);
       cell.setCellValue(order.getItemWeight());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(11);
       cell.setCellValue(order.getUnitPrice());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(12);
       cell.setCellValue(order.getFeeAmount());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(13);
       cell.setCellValue(order.getInsureAmount());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(14);
       cell.setCellValue(order.getBuyerIdNumber());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(15);
       cell.setCellValue(order.getBuyerName());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(16);
       cell.setCellValue(order.getItemsku());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(17);
       cell.setCellValue(order.getCarrier());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(18);
       cell.setCellValue(order.getSendWarehouse());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(19);
       cell.setCellValue(order.getMerchantNum());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(20);
       cell.setCellValue(order.getMailNo());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(21);
       cell.setCellValue(order.getPayNumber());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(22);
       cell.setCellValue(df.format(order.getCreateTime()));
       cell.setCellStyle(cellStyle);
     } 
     
     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
     response.setHeader("Content-disposition", "attachment;filename=orderMail.xlsx");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
     System.out.println("保税完结导出花费时间：" + (System.currentTimeMillis() - l1));
   }
   
   @RequestMapping({"/backOrder"})
   @ResponseBody
   public int backOrder(String ids) {
     OrderBondedForExport bondedForExport = new OrderBondedForExport();
     bondedForExport.setIds(ids);
     return this.orderBondedService.backOrder(bondedForExport);
   }
 }


