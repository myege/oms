 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.OrderPh;
 import com.what21.model.OrderPhSku;
 import com.what21.model.OrderSearchDto;
 import com.what21.service.OrderPhService;
 import com.what21.service.OrderPhSkuService;
 import com.what21.util.GeneralResult;
 import com.what21.util.ResponseUtil;
 import com.zt.kjybd.GetWLGJ;
 import com.zt.request.GetPayNumber;
 import java.io.File;
 import java.io.OutputStream;
 import java.util.ArrayList;
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
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.ModelAndView;
 @Controller
 @RequestMapping({"/orderPh"})
 public class OrderPhAction
   extends BaseAction
 {
   @Autowired
   public OrderPhService OrderPhService;
   @Autowired
   public OrderPhSkuService OrderPhSkuService;
   
   @RequestMapping({"/findAll"})
   public void findAll(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     int auditstatus = Integer.parseInt(request.getSession().getAttribute("tz").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     pageMap.put("paramValue", orderSearchDto.getParamValue());
     pageMap.put("paramName", orderSearchDto.getParamName());
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("auditstatus", Integer.valueOf(auditstatus));
     List<OrderPh> u = this.OrderPhService.findAll(pageMap);
     int total = this.OrderPhService.count(pageMap);
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
     List<OrderPh> u = this.OrderPhService.findAll(pageMap);
     int total = this.OrderPhService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
   
   @RequestMapping({"/importOrderPh"})
   public ModelAndView importOrderPh(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request) {
     ModelAndView modelAndView = new ModelAndView();
     modelAndView.setViewName("/tools/commonMsg");
     GeneralResult result = new GeneralResult();
     try {
       String path = String.valueOf(System.getProperty("user.home")) + File.separator + "upload/";
       String fileName = "offline_" + System.currentTimeMillis() + ".xlsx";
       File targetFile = new File(path, fileName);
       if (!targetFile.exists()) {
         targetFile.mkdirs();
       }
       excelFile.transferTo(targetFile);
       int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
       result = this.OrderPhService.importOrderNew(String.valueOf(path) + fileName, userId);
       modelAndView.addObject("result", result);
       return modelAndView;
     } catch (Exception ex) {
       ex.printStackTrace();
       result.setMessage("导入失败！");
       modelAndView.addObject("result", result);
       
       return modelAndView;
     } 
   }
   @ResponseBody
   @RequestMapping({"/deleteOrderPh"})
   public int deleteOrderPh(@RequestParam(value = "txLogisticID", required = true) String txLogisticID) {
     txLogisticID = txLogisticID.substring(0, txLogisticID.length() - 1);
     int result = this.OrderPhService.deleteOrderPh(txLogisticID);
     return result;
   }
   
   @ResponseBody
   @RequestMapping({"/auditOrder"})
   public int auditOrder(String paramJson, HttpServletRequest request, HttpServletResponse response) {
     return this.OrderPhService.updateAuditstatus(paramJson);
   }
   
   @ResponseBody
   @RequestMapping({"/matchMailNo"})
   public int matchMailNo(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.OrderPhService.updateExpressParam(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/pushOrder"})
   public int pushOrder(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.OrderPhService.updateIspost(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/pushOrderToWms"})
   public void pushOrderToWms(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
     String result = this.OrderPhService.updateIsPushToWms(ids);
     response.getWriter().write(result);
   }
   
   @ResponseBody
   @RequestMapping({"/pushDd"})
   public int pushDd(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.OrderPhService.pushDd(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/pushQd"})
   public int pushQd(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.OrderPhService.pushQd(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/customs"})
   public void customs(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
     String result = this.OrderPhService.updateIsCustoms(ids);
     response.getWriter().write(result);
   }
   
   @ResponseBody
   @RequestMapping({"/deliverGoods"})
   public int deliverGoods(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.OrderPhService.updateAuditstatusByIds(ids);
   }
   
   @RequestMapping({"/Detailed"})
   public void findDetailed(@RequestParam(value = "txLogisticID", required = true) String txLogisticID, OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("txLogisticID", txLogisticID);
     List<OrderPhSku> u = this.OrderPhSkuService.Detailed(pageMap);
     int total = this.OrderPhSkuService.count(txLogisticID);
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
   
   @RequestMapping({"/updaddress"})
   public void updaddress(OrderPh orderPh, HttpServletRequest request, HttpServletResponse response) throws Exception {
     String sprovince = request.getParameter("sprovince");
     String scity = request.getParameter("scity");
     String scounty = request.getParameter("scounty");
     String id = request.getParameter("id");
     OrderPh ob = new OrderPh();
     ob.setId(Integer.parseInt(id));
     ob.setReceiveProvince(sprovince);
     ob.setReceiveCity(scity);
     ob.setReceiveCounty(scounty);
     int res = this.OrderPhService.updaddress(ob);
     String str = JSONObject.toJSONString(Integer.valueOf(res));
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"/getPayNumber1"})
   public void getPayNumber1(String txLogisticID, HttpServletRequest request, HttpServletResponse response) throws Exception {
     String txLogisticID1 = request.getParameter("txLogisticID");
     OrderPh OrderPh = new OrderPh();
     int ret = -1;
     if (txLogisticID1 == "") {
       
       List<OrderPh> v = this.OrderPhService.findTxLogisticID();
       
       if (v.size() == 0) {
         ret = 0;
       } else {
         for (OrderPh n : v) {
           String str1 = GetPayNumber.get("http://www.haidai5.com/tools/getPay.php?order_sn=" + n.getTxLogisticID());
           str1 = str1.replace("支付单号是：", "");
           OrderPh.setPayNumber(str1);
           OrderPh.setTxLogisticID(n.getTxLogisticID());
           ret = this.OrderPhService.payNumber(OrderPh);
         }
       
       } 
     } else {
       
       String[] arr = txLogisticID1.split(",");
       for (int i = 0; i < arr.length; i++) {
         String str1 = GetPayNumber.get("http://www.haidai5.com/tools/getPay.php?order_sn=" + arr[i]);
         str1 = str1.replace("支付单号是：", "");
         OrderPh.setPayNumber(str1);
         OrderPh.setTxLogisticID(arr[i]);
         ret = this.OrderPhService.payNumber(OrderPh);
       } 
     } 
 
     
     String str = JSONObject.toJSONString(Integer.valueOf(ret));
     response.getWriter().write(str);
   }
 
   
   @ResponseBody
   @RequestMapping({"/findByMailNo"})
   public OrderPh findByMailNo(@RequestParam(value = "mailNo", required = true) String mailNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
     OrderPh ob = this.OrderPhService.findByMailNo(mailNo);
     return ob;
   }
   
   @RequestMapping({"/OrderPh"})
   public void OrderPh(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
     cell.setCellValue("收件人");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("收件省");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("收件市");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("收件区");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("收件地址");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("收件电话");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("商品数量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("商品总价");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("商品名称");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("商品重量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("单价");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(12);
     cell.setCellValue("运费");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(13);
     cell.setCellValue("保费");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(14);
     cell.setCellValue("购买人身份证");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(15);
     cell.setCellValue("购买人");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(16);
     cell.setCellValue("商品SKU");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(17);
     cell.setCellValue("快递公司(申通：STO)");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(18);
     cell.setCellValue("发件仓库");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(19);
     cell.setCellValue("商家编码");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(20);
     cell.setCellValue("快递单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(21);
     cell.setCellValue("备注");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     String auditstatus = request.getSession().getAttribute("tz").toString();
     String ids = request.getParameter("OrderPh");
     String value = request.getParameter("value");
     String name = request.getParameter("name");
     
     if (ids.equals("cxqbdc")) {
       
       pageMap.put("paramValue", value);
       pageMap.put("auditstatus", auditstatus);
       pageMap.put("paramName", name);
       pageMap.put("userId", Integer.valueOf(userId));
       
       List<OrderPh> list = this.OrderPhService.exportOrder(pageMap);
       
       int a = 0;
       for (OrderPh u : list)
       {
         List<OrderPhSku> sku = this.OrderPhSkuService.findById(u.getTxLogisticID());
         
         int l = a;
         for (int j = 0; j < sku.size(); j++) {
           a = 1 + j + l;
           row = sheet.createRow(a);
           
           cell = row.createCell(0);
           cell.setCellValue(u.getTxLogisticID());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(1);
           cell.setCellValue(u.getReceiveMan());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(2);
           cell.setCellValue(u.getReceiveProvince());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(3);
           cell.setCellValue(u.getReceiveCity());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(4);
           cell.setCellValue(u.getReceiveCounty());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(5);
           cell.setCellValue(u.getReceiveManAddress());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(6);
           cell.setCellValue(u.getReceiveManPhone());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(7);
           cell.setCellValue(((OrderPhSku)sku.get(j)).getItemCount().intValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(8);
           cell.setCellValue(((OrderPhSku)sku.get(j)).getAllPrice().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(9);
           cell.setCellValue(((OrderPhSku)sku.get(j)).getItemName());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(10);
           cell.setCellValue(((OrderPhSku)sku.get(j)).getItemWeight().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(11);
           cell.setCellValue(((OrderPhSku)sku.get(j)).getUnitPrice().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(12);
           cell.setCellValue(u.getFeeAmount().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(13);
           cell.setCellValue(u.getInsureAmount().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(14);
           cell.setCellValue(u.getBuyerIdNumber());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(15);
           cell.setCellValue(u.getBuyerName());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(16);
           cell.setCellValue(((OrderPhSku)sku.get(j)).getItemsku());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(17);
           cell.setCellValue(u.getCarrier());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(18);
           cell.setCellValue(u.getSendWarehouse());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(19);
           cell.setCellValue(u.getMerchantNum());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
 
           
           cell = row.createCell(20);
           cell.setCellValue(u.getMailNo());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(21);
           cell.setCellValue(u.getPc());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
         }
       
       }
     
     }
     else {
       
       String[] arr = ids.split(",");
       int a = 0;
       for (int i = 0; i < arr.length; i++) {
         
         OrderPh u = this.OrderPhService.findByIdToBG(Integer.valueOf(Integer.parseInt(arr[i])));
         
         List<OrderPhSku> sku = this.OrderPhSkuService.findById(u.getTxLogisticID());
         
         int l = a;
         for (int j = 0; j < sku.size(); j++) {
           a = 1 + j + l;
           row = sheet.createRow(a);
           
           cell = row.createCell(0);
           cell.setCellValue(u.getTxLogisticID());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(1);
           cell.setCellValue(u.getReceiveMan());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(2);
           cell.setCellValue(u.getReceiveProvince());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(3);
           cell.setCellValue(u.getReceiveCity());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(4);
           cell.setCellValue(u.getReceiveCounty());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(5);
           cell.setCellValue(u.getReceiveManAddress());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(6);
           cell.setCellValue(u.getReceiveManPhone());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(7);
           cell.setCellValue(((OrderPhSku)sku.get(j)).getItemCount().intValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(8);
           cell.setCellValue(((OrderPhSku)sku.get(j)).getAllPrice().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(9);
           cell.setCellValue(((OrderPhSku)sku.get(j)).getItemName());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(10);
           cell.setCellValue(((OrderPhSku)sku.get(j)).getItemWeight().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(11);
           cell.setCellValue(((OrderPhSku)sku.get(j)).getUnitPrice().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(12);
           cell.setCellValue(u.getFeeAmount().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(13);
           cell.setCellValue(u.getInsureAmount().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(14);
           cell.setCellValue(u.getBuyerIdNumber());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(15);
           cell.setCellValue(u.getBuyerName());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(16);
           cell.setCellValue(((OrderPhSku)sku.get(j)).getItemsku());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(17);
           cell.setCellValue(u.getCarrier());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(18);
           cell.setCellValue(u.getSendWarehouse());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(19);
           cell.setCellValue(u.getMerchantNum());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
 
           
           cell = row.createCell(20);
           cell.setCellValue(u.getMailNo());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(21);
           cell.setCellValue(u.getPc());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
         } 
       } 
     }     
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", "attachment;filename=OrderPh.xls");
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
     List<OrderPh> odb = this.OrderPhService.odbdateTime(pageMap);
     int total = this.OrderPhService.count(pageMap);
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
     List<OrderPh> odbtxid = this.OrderPhService.findTxId(id);
     List<OrderPhSku> ods = new ArrayList<>();
     xx = ((OrderPh)odbtxid.get(0)).getTxLogisticID();
     String id2 = request.getParameter("id");
     String txid = request.getParameter("txid");
     String[] newid = id2.split(",");
     String[] newtxid = txid.split(",");
     List<OrderPh> odb = new ArrayList<>();
     for (int i = 0; i < newid.length; i++) {
       ti = newid[0];
       odb = this.OrderPhService.findCs(newid[i]);
       for (int m = 0; m < odb.size(); m++) {
         a += ((OrderPh)odb.get(m)).getItemCount().intValue();
         b += ((OrderPh)odb.get(m)).getItemWeight().doubleValue();
         c += ((OrderPh)odb.get(m)).getWorth().doubleValue();
       } 
     } 
     OrderPhSku ou = new OrderPhSku();
     for (int j = 0; j < newtxid.length; j++) {
       ods = this.OrderPhSkuService.skuupd(newtxid[j]);
       for (int x = 0; x < ods.size(); x++) {
         arr = ((OrderPhSku)ods.get(x)).getId();
         ou.setId(arr);
         ou.setTxLogisticID(xx);
         this.OrderPhSkuService.updods(ou);
       } 
     } 
     OrderPh ob = new OrderPh();
     ob.setItemCount(Integer.valueOf(a));
     ob.setItemWeight(Double.valueOf(b));
     ob.setWorth(Double.valueOf(c));
     ob.setId(Integer.parseInt(ti));
     int res = this.OrderPhService.upm(ob);
     for (int k = 0; k < newid.length; k++) {
       if (newid[k] != ti) {
         this.OrderPhService.deletetx(newid[k]);
       }
     } 
     String str = JSONObject.toJSONString(Integer.valueOf(res));
     response.getWriter().write(str);
   }
 
 
   
   @RequestMapping({"/editodIdN"})
   public String editodIdN(OrderPh orderPh, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     resultTotal = this.OrderPhService.editodIdN(orderPh);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
 }


