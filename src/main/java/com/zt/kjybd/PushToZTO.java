 package com.zt.kjybd;
 
 import com.what21.util.MD5Util;
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 
 
 public class PushToZTO
 {
   public static String putData(String data, String msg_type) throws Exception {
     URL url = new URL("http://gjapi.zt-express.com/api/import/init");
     
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
       connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
       connection.connect();
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
       String data_digest = "";
 
       
       String company_id = "TTGYL1512257E107";
       String key = "7EA2947E3D2CF1F6F9";
       
       data_digest = MD5Util.EncoderByMd5(String.valueOf(data) + key);
       
       String content = "data=" + data + "&data_digest=" + data_digest + "&msg_type=" + msg_type + "&company_id=" + company_id;
       System.out.println("content------>" + content);
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
     
     }
     catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
   
   public static void main(String[] args) {
     try {
       String testXml = "{\"tradeId\": \"000\",\"mailNo\": \"000\"}";
       String key = "123456";
       String data_digest = MD5Util.EncoderByMd5(String.valueOf(testXml) + key);
       String testData = "f3B1vg9S5o8rEffEmXDDHw==";
       System.out.println(data_digest);
       System.out.println(data_digest.equals(testData));
     } catch (Exception e) {
       e.printStackTrace();
     } 
   }
 }


