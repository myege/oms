 package com.what21.service.impl;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.OrderMailDao;
 import com.what21.model.OrderMail;
 import com.what21.service.YTOPayService;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import net.sf.json.JSONException;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 @Service
 public class YTOPayServiceImpl
   implements YTOPayService
 {
   @Autowired
   public OrderMailDao orderMailDao;
   
   public int YTOPiPei(String ids) {
     String[] idArr = ids.split(",");
     int i = 0; byte b; int j; String[] arrayOfString1;
     for (j = (arrayOfString1 = idArr).length, b = 0; b < j; ) { String id = arrayOfString1[b];
       try {
         OrderMail order = this.orderMailDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id)));
         
         URL wsUrl = new URL("http://47.90.107.70:8000/webservice/PublicService.asmx");
 
         
         HttpURLConnection conn = (HttpURLConnection)wsUrl.openConnection();
         
         conn.setDoInput(true);
         conn.setDoOutput(true);
         conn.setRequestMethod("POST");
         conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
         conn.setRequestProperty("soapActionString", "ServiceEntrance");
         System.out.println(order.getWorth() + ":" + order.getItemCount());
         order.setPrice(Double.valueOf(order.getWorth().doubleValue() / order.getItemCount().intValue()));
         OutputStream os = conn.getOutputStream();
         JSONObject jsonObject = new JSONObject();
         jsonObject.put("reference_no", order.getTxLogisticID());
         jsonObject.put("shipping_method", "TEST");
         
         jsonObject.put("order_weight", order.getItemWeight());
         jsonObject.put("order_pieces", order.getWorth());
         jsonObject.put("mail_cargo_type", "4");
         jsonObject.put("return_sign", "");
         jsonObject.put("buyer_id", "");
         jsonObject.put("order_info", "备注");
         
         JSONObject shipper = new JSONObject();
         shipper.put("shipper_name", "SHIPPERNAME");
         shipper.put("shipper_countrycode", "US");
         shipper.put("shipper_street", "SHIPPERADDRESS1");
         shipper.put("shipper_city", "SHIPPERADDRESS2");
         shipper.put("shipper_province", "SHIPPERADDRESS3");
         shipper.put("shipper_postcode", "12345");
         shipper.put("shipper_mobile", "15999999999");
         jsonObject.put("shipper", shipper);
 
         
         JSONObject consignee = new JSONObject();
         consignee.put("consignee_name", order.getReceiveMan());
         consignee.put("consignee_countrycode", "CN");
         consignee.put("consignee_street", order.getReceiveManAddress());
         consignee.put("consignee_city", order.getReceiveCity());
         consignee.put("consignee_district", order.getReceiveCounty());
         consignee.put("consignee_province", order.getReceiveProvince());
         consignee.put("consignee_postcode", "55555");
         consignee.put("consignee_telephone", order.getReceiveManPhone());
         consignee.put("consignee_certificatetype", "ID");
         consignee.put("consignee_certificatecode", order.getBuyerIdNumber());
         jsonObject.put("consignee", consignee);
         
         JSONArray invoice = new JSONArray();
         JSONObject obj = new JSONObject();
         obj.put("invoice_enname", "shoes");
         obj.put("invoice_cnname", order.getItemName());
         obj.put("invoice_quantity", order.getItemCount());
         obj.put("invoice_unitcharge", order.getPrice());
         obj.put("unit_code", "PCE");
 
         
         invoice.add(obj);
         
         jsonObject.put("invoice", invoice);
 
         
         String str = 
           "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n  <soap:Body>\n    <ServiceEntrance xmlns=\"http://tempuri.org/\">\n      <appToken>2tv9zn37ikx0h8cb</appToken>\n      <appKey>tdrb5gj3kq04f1p2osmx7i8wzlvycan6</appKey>\n      <serviceMethod>createorder</serviceMethod>\n      <paramsJson>" +           
           jsonObject.toJSONString() + "</paramsJson>\n" + 
           "    </ServiceEntrance>\n" + 
           "  </soap:Body>\n" + 
           "</soap:Envelope>";
 
         
         byte[] buf = str.getBytes("UTF-8");
         os.write(buf);
         
         InputStream is = conn.getInputStream();
         
         byte[] arrayOfByte1 = new byte[1024];
         int len = 0;
         String s = "";
         while ((len = is.read(arrayOfByte1)) != -1) {
           String ss = new String(arrayOfByte1, 0, len, "UTF-8");
           s = String.valueOf(s) + ss;
         } 
 
         
         System.out.println(s);
         String a = s.substring(s.indexOf("{") + 8, s.lastIndexOf("}") + 1);
         
         try {
           net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(a);
           String ding = json.getString("shipping_method_no");
           String dong = json.getString("distribution_code");
           System.out.println("--------------------------------------------" + dong);
           if (dong == "null") {
             dong = "000-000-000";
           }
           order.setMailNo(ding);
           order.setBillProvideSiteCode(dong);
         } catch (JSONException e) {
           
           System.out.println("已有YTOGJ单号");
           return 0;
         } 
         OrderMail or = new OrderMail();
         or.setId(Integer.parseInt(id));
         or.setAuditstatus(2);
         this.orderMailDao.updateIsPushDd(or);
         i = this.orderMailDao.updateIsMailNo(order);
         System.out.println("i=" + i);
         
         is.close();
         os.close();
         conn.disconnect();
       } catch (Exception e) {
         e.printStackTrace();
       }  b++; }
     
     return i;
   }
 }


