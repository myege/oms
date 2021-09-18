 package com.zt.kjybd;
 
 import com.what21.util.MD5Util;
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.UnsupportedEncodingException;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.net.URLEncoder;
 import java.security.GeneralSecurityException;
 import java.security.MessageDigest;
 import java.security.NoSuchAlgorithmException;
 import java.util.Date;
import org.apache.commons.codec.binary.Base64;
 
 
 
 public class PushToTTK
 {
   public static String putData(String data) throws Exception {
     URL url = new URL("http://api.wms.ttphsupplychain.com:20106/bs/s_interface.aspx");
     
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
 
 
       
       String msg_type = "ORDER_CREATE";
       String logistics_interface = data;
       
       String Key = "dibaihz20170921";
       String cuscode = "DIBAI20170921";
       
       Date date = new Date();
       long stamp = date.getTime();
       String timestamp = String.valueOf(stamp);
       
       String str = String.valueOf(msg_type) + logistics_interface + timestamp + Key;
       
       String signer = Base64.encodeBase64String(MD5Util.MD5S(str).getBytes());
 
       
       String content = "logistics_interface=" + URLEncoder.encode(logistics_interface) + "&data_digest=" + URLEncoder.encode(signer) + "&msg_type=" + msg_type + "&timestamp=" + timestamp + "&cuscode=" + cuscode;
 
 
 
       
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
     
     try {
       System.out.println(MD5Util.EncoderByMd5("1234567890ABC"));
       String aa = Base64.encodeBase64String(MD5Util.EncoderByMd5("1234567890ABC").getBytes());
       System.out.println(aa);
     } catch (NoSuchAlgorithmException e) {
       
       e.printStackTrace();
     } catch (UnsupportedEncodingException e) {
       
       e.printStackTrace();
     } 
   }
 }


