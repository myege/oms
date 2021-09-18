 package com.zt.kjybd;
 
 import com.what21.util.MD5Util;
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.net.URLEncoder;
import org.apache.commons.codec.binary.Base64;
 public class PushToYD
 {
   public static String putData(String xmlStr) throws Exception {
     URL url = new URL("http://116.228.72.130:8080/oms/interface.php");
     
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
       
       String app_key = "hzqr";
       String buz_type = "partner";
       String data = xmlStr;
       String format = "xml";
       String method = "global_order_create";
       String tradeId = "1612140001,360588EE1A5F2720B58C50DF9B3AAE58";
       String version = "1.0";
       String app_secret = "123456";
       String testXml = "app_key" + app_key + "buz_type" + buz_type + "data" + data + "format" + format + "method" + method + "tradeId" + tradeId + "version" + version + app_secret;
 
       
       
       String sign = Base64.encodeBase64String(MD5Util.MD5S(testXml).getBytes("UTF-8"));
       
       String param = "app_key=" + URLEncoder.encode(app_key) + 
         "&buz_type=" + URLEncoder.encode(buz_type) + 
         "&data=" + URLEncoder.encode(data) + 
         "&format=" + URLEncoder.encode(format) + 
         "&method=" + URLEncoder.encode(method) + 
         "&tradeId=" + URLEncoder.encode(tradeId) + 
         "&version=" + URLEncoder.encode(version) + 
         "&sign=" + URLEncoder.encode(sign);
       
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
     } catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
   
   public static void main(String[] args) {
     try {
       String app_key = "app123";
       String buz_type = "partner";
       String data = "test";
       String format = "xml";
       String method = "global_order_create";
       String tradeId = "trade123";
       String version = "1.0";
       String app_secret = "secret";
       
       String testXml = "app_keyapp123buz_typepartnerdatatestformatxmlmethodglobal_order_createtradeIdtrade123version1.0secret";
       String data_digest = MD5Util.EncoderByMd5(testXml);
       String testData = "NzgyNzE3ZTA2Zjk4N2JiZThhOTZkOGFmMWI1YzYyOGU=";
       data_digest = Base64.encodeBase64String(MD5Util.MD5(testXml).getBytes("UTF-8"));
       System.out.println(data_digest);
       System.out.println(data_digest.equals(testData));
     } catch (Exception e) {
       e.printStackTrace();
     } 
   }
 }


