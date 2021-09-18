 package com.zt.kjybd;
 
 import com.alibaba.fastjson.JSONObject;
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 
 
 public class PushToCzNew
 {
   public static String sendPost(String urlStr, String param) {
     String result = "";
     try {
       URL url = new URL(urlStr);
       String charset = "UTF-8";
       HttpURLConnection connection = null;
       connection = (HttpURLConnection)url.openConnection();
       connection.setConnectTimeout(600000);
       connection.setReadTimeout(600000);
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setRequestMethod("POST");
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
       connection.connect();
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
       out.write(param.getBytes(charset));
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
       System.out.println("result=" + result);
     } catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
 
   
   public static String login() {
     JSONObject loginJson = new JSONObject();
     loginJson.put("username", "lc_admin");
     loginJson.put("password", "123456");
     String loginUrl = "http://183.129.233.106:9081/kmb/a/api/token";
     String ret = sendPost(loginUrl, loginJson.toString());
     JSONObject retJson = JSONObject.parseObject(ret);
     
     return retJson.getString("token");
   }
 
   
   public static void main(String[] args) {
     String token = login();
   }
 }


