 package com.what21.util;
 
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.net.URLEncoder;
 
 
 
 public class PayNumberUtil
 {
   public static String getFromTL(String param) {
     String result = "";
     
     try {
       URL url = new URL("https://service.allinpay.com/bizweb/index.do");
       String charset = "UTF-8";
       HttpURLConnection connection = null;
       connection = (HttpURLConnection)url.openConnection();
       connection.setConnectTimeout(600000);
       connection.setReadTimeout(600000);
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setRequestMethod("POST");
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
       connection.connect();
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
       String content = "verificationText=" + URLEncoder.encode(param, "UTF-8");
       
       out.write(content.getBytes(charset));
       out.flush();
       out.close();
       BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
       StringBuffer buffer = new StringBuffer();
       String line = "";
       while ((line = reader.readLine()) != null) {
         buffer.append(line);
       }
       
       reader.close();
       result = buffer.toString();
       System.out.println("result====" + result);
     } catch (Exception ex) {
       ex.printStackTrace();
       return result;
     } 
     return result;
   }
 }


