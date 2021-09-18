 package com;
 
 import com.alibaba.fastjson.JSONObject;
 import com.google.gson.Gson;
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.security.MessageDigest;
 import java.util.LinkedHashMap;
 import java.util.Map;
 public class gyApi
 {
   public static void main(String[] args) {
     String appkey = "173554";
     String secret = "8a50ef19df604956ac88a103f620389e";
     String sessionKey = "a8ad4dbdb2d34b0fbc570e1ffb71324c";
     
     Gson gson = new Gson();
     
     Map<String, Object> map = new LinkedHashMap<>();
     map.put("appkey", appkey);
     map.put("sessionkey", sessionKey);
     map.put("method", "gy.erp.area.get");
     map.put("page_no", "1");
     map.put("page_size", "10");
     map.put("start_date", "");
     map.put("end_date", "");
     
     String str = gson.toJson(map);
     String sign = sign(str, secret);
     map.put("sign", sign);
     String str2 = gson.toJson(map);
     String url2 = "http://v2.api.guanyierp.com/rest/erp_open";
 
     
     String result = sendPost(url2, str2);
     
     JSONObject ret = JSONObject.parseObject(result);
     String success = ret.getString("success");
     System.out.println("success=" + success);
   }
 
 
 
   
   public static String sign(String str, String secret) {
     StringBuilder enValue = new StringBuilder();
     enValue.append(secret);
     enValue.append(str.toString());
     enValue.append(secret);
 
     
     return encryptByMD5(enValue.toString());
   }
 
   
   private static String encryptByMD5(String data) {
     StringBuilder sign = new StringBuilder();
     try {
       MessageDigest md = MessageDigest.getInstance("MD5");
       byte[] bytes = md.digest(data.getBytes("UTF-8"));
       for (int i = 0; i < bytes.length; i++) {
         String hex = Integer.toHexString(bytes[i] & 0xFF);
         if (hex.length() == 1) {
           sign.append("0");
         }
         sign.append(hex.toUpperCase());
       }
     
     } catch (Exception e) {
       e.printStackTrace();
     } 
     return sign.toString();
   }
   
   public static String sendPost(String url, String po) {
     String result = "";
 
     
     try {
       String charset = "UTF-8";
       URL urla = null;
       
       HttpURLConnection connection = null;
       urla = new URL(url);
       
       connection = (HttpURLConnection)urla.openConnection();
       connection.setConnectTimeout(600000);
       connection.setReadTimeout(600000);
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setRequestMethod("POST");
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
       connection.connect();
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
 
 
       
       System.out.println(po);
       out.write(po.getBytes(charset));
 
       
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
       result = result.replaceAll("(null)\\s*,", "\"\",");
       
       System.out.println("result=" + result);
     }
     catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
 }


