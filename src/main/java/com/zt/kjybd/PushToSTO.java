 package com.zt.kjybd;
 
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.security.GeneralSecurityException;
 import java.security.MessageDigest;
 import java.util.Date;
 
 
 public class PushToSTO
 {
   public static String putData(String data, String secret) throws Exception {
     URL url = new URL("http://storder.sto.cn:2227/order/partner/message_receiver");
     
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
       
       String signer = "01D5727887844620A2421990B47510D4";
       Date date = new Date();
       long stamp = date.getTime();
       String timestamp = String.valueOf(stamp);
 
       
       String partnerCode = "PORTZJZTXS";
       String logisticsInfo = data;
       String a = String.valueOf(signer) + "logisticsInfo" + logisticsInfo + "partnerCode" + partnerCode + "timestamp" + timestamp + signer;
       signer = sha1(a);
       String content = "partnerCode=" + partnerCode + "&timestamp=" + timestamp + "&logisticsInfo=" + logisticsInfo + "&signer=" + signer;
 
       
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
   
   public static String sha1(String data) throws IOException {
     return byte2hex(getSHA1Digest(data));
   }
   
   private static byte[] getSHA1Digest(String data) throws IOException {
     byte[] bytes = null;
     try {
       MessageDigest md = MessageDigest.getInstance("SHA-1");
       bytes = md.digest(data.getBytes("UTF-8"));
     } catch (GeneralSecurityException gse) {
       throw new IOException(gse);
     } 
     return bytes;
   }
   
   private static String byte2hex(byte[] bytes) {
     StringBuilder sign = new StringBuilder();
     for (int i = 0; i < bytes.length; i++) {
       String hex = Integer.toHexString(bytes[i] & 0xFF);
       if (hex.length() == 1) {
         sign.append("0");
       }
       sign.append(hex.toUpperCase());
     } 
     return sign.toString();
   }
   
   public static void main(String[] args) {
     String partnerCode = "test";
     String timestamp = "330523156522";
     String logisticsInfo = "sto";
     String a = "123456logisticsInfo" + logisticsInfo + "partnerCode" + partnerCode + "timestamp" + timestamp + "123456";
     
     try {
       String signer = sha1(a);
       String signer2 = sha1("123456logistics_interfacestopartnerCodetesttimestamp330523156522123456");
       System.out.println("1----=" + signer);
       System.out.println("2----=" + signer2);
     } catch (IOException e) {
       
       e.printStackTrace();
     } 
   }
 
 
 
   
   public static String putDataForOut(String data) throws Exception {
     URL url = new URL("http://storder.sto.cn:2227/order/partner/message_receiver");
     
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
       
       String signer = "01D5727887844620A2421990B47510D4";
       
       Date date = new Date();
       long stamp = date.getTime();
       String timestamp = String.valueOf(stamp);
       
       String partnerCode = "PORTZJZTXS";
       
       String logisticsInfo = data;
       String a = String.valueOf(signer) + "logisticsInfo" + logisticsInfo + "partnerCode" + partnerCode + "timestamp" + timestamp + signer;
       signer = sha1(a);
       String content = "partnerCode=" + partnerCode + "&timestamp=" + timestamp + "&logisticsInfo=" + logisticsInfo + "&signer=" + signer;
 
       
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
   
   public static String putDataOUt(String jsonString) throws Exception {
     URL url = new URL("http://storder.sto.cn:2227/order/partner/message_receiver");
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
       
       String signer = "5D9DAB5D3DE64BEDBA4715909E0BCA65";
       Date date = new Date();
       long stamp = date.getTime();
       String timestamp = String.valueOf(stamp);
       
       String partnerCode = "EXITZJZT";
       String logisticsInfo = jsonString;
       String a = String.valueOf(signer) + "logisticsInfo" + logisticsInfo + "partnerCode" + partnerCode + "timestamp" + timestamp + signer;
       signer = sha1(a);
       String content = "partnerCode=" + partnerCode + "&timestamp=" + timestamp + "&logisticsInfo=" + logisticsInfo + "&signer=" + signer;
 
       
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
 }


