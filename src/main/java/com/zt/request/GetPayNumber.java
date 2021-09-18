 package com.zt.request;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 
 
 
 public class GetPayNumber
 {
   public static String get(String path) throws Exception {
     HttpURLConnection httpConn = null;
     BufferedReader in = null;
     try {
       URL url = new URL(path);
       httpConn = (HttpURLConnection)url.openConnection();
 
       
       if (httpConn.getResponseCode() == 200) {
         StringBuffer content = new StringBuffer();
         String tempStr = "";
         in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
         while ((tempStr = in.readLine()) != null) {
           content.append(tempStr);
         }
         
         return content.toString();
       } 
       throw new Exception("请求出现了问题!");
     }
     catch (IOException e) {
       e.printStackTrace();
     } finally {
       in.close();
       httpConn.disconnect();
     } 
     return null;
   }
   
   public static void main(String[] args) throws Exception {
     String resMessage = get("http://www.haidai5.com/tools/getPay.php?order_sn=HYJ201608240005");
     
     resMessage = resMessage.replace("支付单号是：", "");
   }
 }


