 package com.zt.kjybd;
 
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 public class PushToYf
 {
   public static String sendPost(String data) {
     String result = "";
     try {
       URL url = new URL("http://www.alulugoods.com/sendApi.php");
       
       String charset = "UTF-8";
       HttpURLConnection connection = null;
       connection = (HttpURLConnection)url.openConnection();
       connection.setConnectTimeout(600000);
       connection.setReadTimeout(600000);
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setRequestMethod("POST");
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
       connection.connect();
       String username = "lecang";
       String password = "c583bee1f481c4d7a6be39ede8184465";
       String func = "ORDERINFO";
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
       String content = "username=" + username + "&password=" + password + "&func=" + func + "&data=" + data;
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
 
       
       System.out.println("result=" + result);
     }
     catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
   
   public static void main(String[] args) {
     sendPost("");
   }
 }


