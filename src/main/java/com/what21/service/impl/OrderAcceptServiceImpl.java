 package com.what21.service.impl;
 
 import com.what21.dao.GoodsAcceptDao;
 import com.what21.dao.OrderAcceptDao;
 import com.what21.model.GoodsAccept;
 import com.what21.model.OrderAccept;
 import com.what21.service.OrderAcceptService;
 import com.what21.util.ExcelUtil;
 import com.what21.util.GeneralResult;
 import com.zt.kjybd.GetBillcodeforSTO;
 import java.io.File;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class OrderAcceptServiceImpl
   implements OrderAcceptService
 {
   @Autowired
   private OrderAcceptDao orderAcceptDao;
   @Autowired
   private GoodsAcceptDao goodsAcceptDao;
   
   public Boolean findByExpressNumber(String expressNumber) {
     OrderAccept order = this.orderAcceptDao.findByExpressNumber(expressNumber);
     if (order != null) {
       return Boolean.valueOf(true);
     }
     return Boolean.valueOf(false);
   }
 
   
   public void insert(OrderAccept order) {
     this.orderAcceptDao.insert(order);
   }
 
   
   public List<OrderAccept> findAll(Map<String, Object> map) {
     return this.orderAcceptDao.findAll(map);
   }
 
   
   public OrderAccept findById(int id) {
     return this.orderAcceptDao.findById(id);
   }
 
   
   public void update(String expressNumber) {
     this.orderAcceptDao.update(expressNumber);
   }
 
   
   public void updatePrintType(int id) {
     this.orderAcceptDao.updatePrintType(id);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return this.orderAcceptDao.count(pageMap);
   }
 
   
   public List<OrderAccept> findExpressNumber(Map<String, Object> pageMap) {
     return this.orderAcceptDao.findExpressNumber(pageMap);
   }
 
   
   public int updates(String string) {
     return this.orderAcceptDao.updates(string);
   }
 
   
   public GeneralResult importOrder(String path, int userId) {
     GeneralResult result = new GeneralResult();
     StringBuffer message = new StringBuffer();
 
     
     Map<Integer, Object[]> items = ExcelUtil.read(path);
     int successData = 0;
     int totalData = items.entrySet().size() - 1;
     boolean firstRow = true;
     for (Map.Entry<Integer, Object[]> entry : items.entrySet()) {
       if (firstRow) {
         firstRow = false;
         
         continue;
       } 
       Object[] nowRowData = entry.getValue();
       
       Object data1 = nowRowData[0];
       if (data1 == null || data1.toString().trim().isEmpty()) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：订单号没有输入！<br>");
         continue;
       } 
       Object data2 = nowRowData[1];
       if (data2 == null || data2.toString().trim().isEmpty()) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：快件单号没有输入！<br>");
         continue;
       } 
       String expressNumber = data2.toString();
       
       Object data3 = nowRowData[2];
       if (data3 == null || data3.toString().trim().isEmpty()) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：快件公司编号没有输入！<br>");
         continue;
       } 
       Object data4 = nowRowData[3];
       if (data4 == null || data4.toString().trim().isEmpty()) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：收件人姓名没有输入！<br>");
         continue;
       } 
       Object data5 = nowRowData[4];
       if (data5 == null || data5.toString().trim().isEmpty()) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：收件人省份没有输入！<br>");
         continue;
       } 
       Object data6 = nowRowData[5];
       if (data6 == null || data6.toString().trim().isEmpty()) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：收件人城市没有输入！<br>");
         continue;
       } 
       Object data7 = nowRowData[6];
       if (data7 == null || data7.toString().trim().isEmpty()) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：收件人地区没有输入！<br>");
         continue;
       } 
       Object data8 = nowRowData[7];
       if (data8 == null || data8.toString().trim().isEmpty()) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：收件人详细地址没有输入！<br>");
         continue;
       } 
       Object data9 = nowRowData[8];
       if (data9 == null || data9.toString().trim().isEmpty()) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：收件人联系电话没有输入！<br>");
         continue;
       } 
       Object data10 = nowRowData[12];
       if (data10 == null || data10.toString().trim().isEmpty()) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：寄件人没有输入！<br>");
         continue;
       } 
       Object data11 = nowRowData[13];
       if (data11 == null || data11.toString().trim().isEmpty()) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：电话没有输入！<br>");
         continue;
       } 
       Object data12 = nowRowData[14];
       if (data12 == null || data12.toString().trim().isEmpty()) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：地址没有输入！<br>");
         
         continue;
       }       
       OrderAccept isData = this.orderAcceptDao.findByExpressNumber(expressNumber);
       if (isData == null) {
         OrderAccept order = new OrderAccept();
         order.setOrderNumber(data1.toString());
         order.setExpressNumber(data2.toString());
         order.setExpressCode(data3.toString());
         order.setBuyerName(data4.toString());
         order.setBuyerProvince(data5.toString());
         order.setBuyerCity(data6.toString());
         order.setBuyerArea(data7.toString());
         order.setBuyerAddress(data8.toString());
         order.setBuyerTel(data9.toString());
         order.setSender(data10.toString());
         order.setSenderTel(data11.toString());
         order.setSenderAddress(data12.toString());
         order.setPrintType(0);
         order.setUserId(userId);
 
         
         this.orderAcceptDao.insert(order);
       } 
       
       Object data13 = nowRowData[9];
       if (data13 == null || data13.toString().trim().isEmpty()) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：物品名称没有输入！<br>");
         continue;
       } 
       Object data14 = nowRowData[10];
       if (data14 == null || data14.toString().trim().isEmpty()) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：物品重量没有输入！<br>");
         continue;
       } 
       Object data15 = nowRowData[11];
       if (data15 == null || data15.toString().trim().isEmpty()) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：物品数量没有输入！<br>");
         
         continue;
       } 
       GoodsAccept goods = new GoodsAccept();
       goods.setOrderId(this.orderAcceptDao.findByExpressNumber(expressNumber).getId());
       goods.setGoodsName(data13.toString());
       goods.setWeight(Double.valueOf(data14.toString()));
       goods.setNum(Integer.parseInt(data15.toString()));
       goods.setExpressNumber(expressNumber);
       goods.setUserId(userId);
       this.goodsAcceptDao.insert(goods);
       successData++;
     } 
     
     File delFile = new File(path);
     if (delFile.exists()) {
       delFile.delete();
     }
     
     StringBuffer resultMessage = new StringBuffer();
     resultMessage.append("总共" + totalData + "条数据，商品导入成功" + successData + "条!<br>");
     resultMessage.append(message);
     result.setCode(Integer.valueOf(1));
     result.setMessage(resultMessage.toString());
     return result;
   }
 
 
 
   
   public int updateExpressmatch(String ids, String name) {
     String[] idArr = ids.split(",");
     String Name = name;
     String MailNo = "";
     int result = -1;
     OrderAccept ocp = new OrderAccept(); byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       ocp.setId(Integer.parseInt(id));
       
       String url2 = "http://183.129.231.82:8066/ecm/interface/rest/tendInterface/getbill";
       String param2 = "Sign=0888A1AFB593666C658BF5C315B3E3F3&User=" + Name + "&Password=123456&len=1";
       String res = GetBillcodeforSTO.sendPost(url2, param2);
       
       res = res.replaceAll("\\\\\"", "");
       res = res.replaceAll("\"", "");
       String[] str = res.replaceAll("}", "").split("-");
       if (str.length > 1) {
         byte b1; int j; String[] arrayOfString; for (j = (arrayOfString = str).length, b1 = 0; b1 < j; ) { String s = arrayOfString[b1];
           MailNo = s;
           b1++; }
         
         ocp.setExpressNumber(MailNo);
       }  b++; }
     
     if (MailNo == null || MailNo == "") {
       result = 0;
     } else if (MailNo != null || MailNo != "") {
       result = this.orderAcceptDao.updateExpressM(ocp);
     } 
     return result;
   }
 
   
   public int updateExpressM(OrderAccept ocp) {
     return this.orderAcceptDao.updateExpressM(ocp);
   }
 
   
   public int delete(String[] expressNumbers) {
     this.orderAcceptDao.delete(expressNumbers);
     return this.goodsAcceptDao.delete(expressNumbers);
   }
 }


