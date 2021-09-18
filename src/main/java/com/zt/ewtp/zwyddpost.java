 package com.zt.ewtp;
 
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;

 public class zwyddpost
 {
   public void createOrder() {}
   
   public static String refreshToken() throws Exception {
     String appid = "Sr8Xdp2Gth";
     String authToken = "Halj2bSjWzMffYLvKsxh";
     String data = "appid=" + appid + 
       "&auth_token=" + authToken;
     System.out.println(data);
     
     String token = doPost("http://esinotrans.com/api/auth", data);
     
     return token;
   }
 
   
   public static String pushorder(String token, String data) throws Exception {
     String parm = "auth_token=" + token + "&platform=OMSHZ&function=BSCreateOrder&type=0&data=" + data;
     System.out.println("parm=" + parm);
     String ret = doPost("http://esinotrans.com/api/post", parm);
     return ret;
   }
 
   
   public static String doPost(String url1, String data) throws Exception {
     URL url = new URL(url1);
     
     String result = "";
     try {
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
       

       
       out.write(data.getBytes(charset));
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
     } catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
 
 
   
   public static void main(String[] args) {
     String appid = "Sr8Xdp2Gth";
     String authToken = "Halj2bSjWzMffYLvKsxh";
     String data = "appid=" + appid + 
       "&auth_token=" + authToken;
     System.out.println(data);
     
     try {
       String result = doPost("http://esinotrans.com/api/auth", data);
       System.out.println("result=" + result);
     } catch (Exception e) {
       
       e.printStackTrace();
     } 
   }
 }


