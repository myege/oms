 package com.what21.hcwms;
 
 import java.io.IOException;
 import java.io.UnsupportedEncodingException;
 import java.net.URLEncoder;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.Map;
 import org.apache.http.HttpEntity;
 import org.apache.http.client.methods.CloseableHttpResponse;
 import org.apache.http.client.methods.HttpPost;
 import org.apache.http.client.methods.HttpUriRequest;
 import org.apache.http.entity.StringEntity;
 import org.apache.http.impl.client.CloseableHttpClient;
 import org.apache.http.impl.client.HttpClients;
 import org.apache.http.util.EntityUtils;
 import org.springframework.stereotype.Controller;
 @Controller
 public class TestC
 {
   public static void main(String[] args) throws UnsupportedEncodingException {
     String method = "deliveryorder.create";
     String timestamp = "2019-03-04 15:18:55";
     String format = "xml";
     String app_key = "TY1026a";
     String v = "1.0";
     String sign_method = "md5";
     String customerId = "TY1026c";
     String secret = "TY1026s";
     Map<String, String> paramMap = new HashMap<>();
     paramMap.put("method", method);
     paramMap.put("timestamp", "2019-03-04 15:18:55");
     paramMap.put("format", "format");
     paramMap.put("v", "1.0");
     paramMap.put("sign_method", "md5");
     paramMap.put("customerId", customerId);
 
 
 
     
     Map<String, String> headers = new HashMap<>();
     headers.put("Content-Type", "text/xml");
     
     String sign = "111";
     System.out.println("sign=" + sign);
 
 
 
     
     String url = "http://exchange.highstore.cn/esbcenter/api/esb";
     StringBuffer sbStr = new StringBuffer();
     for (Map.Entry<String, String> entry : paramMap.entrySet()) {
       if (sbStr.length() == 0) {
         sbStr.append("?");
       } else {
         sbStr.append("&");
       } 
       sbStr.append(entry.getKey());
       sbStr.append("=");
       sbStr.append(URLEncoder.encode(entry.getValue(), "utf-8"));
     } 
     String params = sbStr.toString();
     url = String.valueOf(url) + params;
     
     String msg = httpPostxml2(url, "", headers, "UTF-8");
     System.out.println("msg=" + msg);
   }
 
   
   public static String httpPostxml2(String url, String xml, Map<String, String> headers, String encode) {
     if (encode == null) {
       encode = "utf-8";
     }
     
     CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
     HttpPost httpost = new HttpPost(url);
     httpost.setHeader("Content-Type", "application/json;charset=UTF-8");
     if (headers != null && headers.size() > 0) {
       Iterator<Map.Entry<String, String>> var6 = headers.entrySet().iterator();
       
       while (var6.hasNext()) {
         Map.Entry<String, String> entry = var6.next();
         httpost.setHeader(entry.getKey(), entry.getValue());
       } 
     } 
 
     
     StringEntity stringEntity = new StringEntity(xml, encode);
     httpost.setEntity((HttpEntity)stringEntity);
     String content = null;
     CloseableHttpResponse httpResponse = null;
     
     try {
       httpResponse = closeableHttpClient.execute((HttpUriRequest)httpost);
       HttpEntity entity = httpResponse.getEntity();
       content = EntityUtils.toString(entity, encode);
     } catch (Exception var20) {
       var20.printStackTrace();
     } finally {
       try {
         httpResponse.close();
       } catch (IOException var18) {
         var18.printStackTrace();
       } 
     } 
 
     
     try {
       closeableHttpClient.close();
     } catch (IOException var19) {
       var19.printStackTrace();
     } 
     
     return content;
   }
 }


