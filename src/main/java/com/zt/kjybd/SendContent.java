 package com.zt.kjybd;
 
 import java.io.BufferedReader;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.security.MessageDigest;
 import org.apache.commons.codec.binary.Base64;
 public class SendContent
 {
   public static String encryptSendData(String key, String data) {
     if (key == null || data == null) {
       return data;
     }
     
     String encryptData = getEncString(key, data);
     System.out.println("===des:" + key + "===:" + encryptData);
     encryptData = new String(Base64.encodeBase64(encryptData.getBytes()));
     return encryptData;
   }
   
   public static String getEncString(String publicKey, String data) {
     return (new Des(publicKey)).getEncString(data);
   }
 
   
   public static String signuatureSendData(String companyCode, String encryptData) {
     if (companyCode == null || encryptData == null) {
       return encryptData;
     }
     try {
       MessageDigest messagedigest = MessageDigest.getInstance("MD5");
       messagedigest.update((String.valueOf(companyCode) + encryptData + companyCode).getBytes("UTF-8"));
       byte[] digestData = messagedigest.digest();
       String encodeData = new String(Base64.encodeBase64(digestData));
       return encodeData;
     } catch (Exception exception) {
       return encryptData;
     } 
   }
   public static String signuatureSendData1(String strSrc) {
     String outString = null;
     try {
       MessageDigest md5 = MessageDigest.getInstance("MD5");
       byte[] outByte = md5.digest(strSrc.getBytes("UTF-8"));
       
       StringBuffer buf = new StringBuffer("");
       for (int offset = 0; offset < outByte.length; offset++) {
         int i = outByte[offset];
         if (i < 0)
           i += 256; 
         if (i < 16)
           buf.append("0"); 
         buf.append(Integer.toHexString(i));
       } 
       outString = buf.toString();
       String encodeData = new String(Base64.encodeBase64(outString.getBytes("utf-8")));
       return encodeData;
     } catch (Exception e) {
       e.printStackTrace();
       
       return outString;
     } 
   }
   public static String sendAndGetStr(String apiUrl, String data) {
     String responseString = "";
     try {
       URL url = new URL(apiUrl);
       HttpURLConnection connection = (HttpURLConnection)url.openConnection();
       connection.setDoOutput(true);
       connection.setRequestMethod("POST");
       OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
       
       long a = System.currentTimeMillis();
       
       out.write(data);
       out.flush();
       out.close();
 
       
       String strLine = "";
       InputStream in = connection.getInputStream();
       BufferedReader reader = new BufferedReader(new InputStreamReader(in));
       while ((strLine = reader.readLine()) != null) {
         responseString = String.valueOf(responseString) + strLine + "\n";
       }
       
       in.close();
       
       long b = System.currentTimeMillis();
       
       long c = b - a;
       System.err.println("时间：" + c + "ms");
       
       System.err.println("返回信息：\n" + responseString);
     } catch (Exception e) {
       e.printStackTrace();
     } 
     return responseString;
   }
 }


