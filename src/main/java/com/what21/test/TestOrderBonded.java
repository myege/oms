 package com.what21.test;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.net.URLDecoder;
import org.apache.commons.codec.binary.Base64;
 
 
 public class TestOrderBonded
 {
   public static void main(String[] args) throws Exception {
     URL wsUrl = new URL("http://localhost:8080/ztz/ws/importOrder?wsdl");
     HttpURLConnection conn = (HttpURLConnection)wsUrl.openConnection();
     
     conn.setDoInput(true);
     conn.setDoOutput(true);
     conn.setRequestMethod("POST");
     conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
     
     OutputStream os = conn.getOutputStream();
     String param = "";
     
     JSONObject jSONObject = new JSONObject();
     JSONArray jSONArray = new JSONArray();
     
     System.out.println(param);
 
     
     for (int a = 1; a < 2; a++) {
       
       JSONObject orderListJson = new JSONObject();
       JSONArray orderJSONArray = new JSONArray();
       orderListJson.put("txLogisticID", "azxs222222" + a);
       orderListJson.put("receiveMan", "王XX");
       orderListJson.put("receiveProvince", "浙江省");
       orderListJson.put("receiveCity", "杭州市");
       orderListJson.put("receiveCounty", "江干区");
       orderListJson.put("receiveManAddress", "国家大学科技园");
       orderListJson.put("receiveManPhone", "13912345678");
       orderListJson.put("feeAmount", Integer.valueOf(0));
       orderListJson.put("insureAmount", Integer.valueOf(0));
       orderListJson.put("buyerIdNumber", "361234199001014321");
       orderListJson.put("buyerName", "陈一一");
       orderListJson.put("carrier", "HTO");
       orderListJson.put("sendWarehouse", "test");
       orderListJson.put("merchantNum", "test");
       orderListJson.put("mailNo", "0");
       orderListJson.put("pc", "");
       orderListJson.put("payCompany", "支付企业");
       orderListJson.put("payNumber", "12345678");
       for (int j = 1; j < 3; j++) {
         JSONObject skuListJson = new JSONObject();
         skuListJson.put("itemName", "奶粉");
         skuListJson.put("itemSku", "qqWSB1001" + j + a);
         skuListJson.put("itemCount", Integer.valueOf(2));
         skuListJson.put("unitPrice", Double.valueOf(1.11D));
         skuListJson.put("allPrice", Double.valueOf(2.22D));
         skuListJson.put("itemWeight", Double.valueOf(3.33D));
         orderJSONArray.add(skuListJson);
       } 
       orderListJson.put("itemName", "奶粉");
       orderListJson.put("itemSku", "WSB1001" + a);
       orderListJson.put("itemCount", Integer.valueOf(2));
       orderListJson.put("unitPrice", Double.valueOf(1.11D));
       orderListJson.put("allPrice", Double.valueOf(2.22D));
       orderListJson.put("itemWeight", Double.valueOf(3.33D));
       orderListJson.put("skuList", orderJSONArray);
       jSONArray.add(orderListJson);
     } 
     
     jSONObject.put("orderList", jSONArray);
     jSONObject.put("userName", "admin");
     jSONObject.put("password", "ztgyl2018");
     
     System.out.println("--------->" + jSONObject);
 
 
     
     String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:q0=\"http://service.what21.com/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body> <q0:importOrderNew><arg0>" + 
       Base64.encodeBase64String(jSONObject.toString().getBytes()) + "</arg0>  </q0:importOrderNew> </soapenv:Body> </soapenv:Envelope>";
     
     os.write(soap.getBytes());
     
     InputStream is = conn.getInputStream();
     
     byte[] b = new byte[1024];
     int len = 0;
     String s = "";
     while ((len = is.read(b)) != -1) {
       String ss = new String(b, 0, len, "UTF-8");
       s = String.valueOf(s) + ss;
     } 
     System.out.println(URLDecoder.decode(s, "UTF-8"));
     
     is.close();
     os.close();
     conn.disconnect();
   }
 }


