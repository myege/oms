 package com.zt.kjybd;
 
 import com.what21.model.ZtzConst;
 import com.what21.model.enums.ZtzPropertiesEnum;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.net.HttpURLConnection;
 import java.net.URL;
import org.apache.commons.codec.binary.Base64;
 
 
 public class PushToWmsNew
 {
   public static String pushData(String param) {
     String result = "";
     String CKDMurl = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.CKDMURL.getValue());
     try {
       URL wsUrl = new URL(CKDMurl);
       HttpURLConnection conn = (HttpURLConnection)wsUrl.openConnection();
       
       conn.setDoInput(true);
       conn.setDoOutput(true);
       conn.setRequestMethod("POST");
       conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
       
       OutputStream os = conn.getOutputStream();
       
       String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:q0=\"http://service.zt.com/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body> <q0:importOrder><arg0>" + 
         Base64.encodeBase64String(param.getBytes("utf-8")) + "</arg0>  </q0:importOrder> </soapenv:Body> </soapenv:Envelope>";
       
       os.write(soap.getBytes());
       
       InputStream is = conn.getInputStream();
       
       byte[] b = new byte[1024];
       int len = 0;
       while ((len = is.read(b)) != -1) {
         String ss = new String(b, 0, len, "UTF-8");
         result = String.valueOf(result) + ss;
       } 
       is.close();
       os.close();
       conn.disconnect();
     } catch (Exception e) {
       e.printStackTrace();
     } 
     return result;
   }
   
   public static String ygpushData(String param) {
     String result = "";
     String CKDMurl = "http://111.85.254.10:81/default/svc/web-service";
     try {
       URL wsUrl = new URL(CKDMurl);
       HttpURLConnection conn = (HttpURLConnection)wsUrl.openConnection();
       
       conn.setDoInput(true);
       conn.setDoOutput(true);
       conn.setRequestMethod("POST");
       conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
       
       OutputStream os = conn.getOutputStream();
       
       String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ec=\"http://www.example.org/Ec/\"><soapenv:Header/><soapenv:Body><ec:callService><!--Optional:--><paramsJson>" + 
         param + "</paramsJson><appToken>24449ac81ac91d82f6ae2442e5d94c7c</appToken><appKey>383c76f688ef33402f463e656f9621d9</appKey><service>createOrder</service></ec:callService></soapenv:Body></soapenv:Envelope>";
 
       
       os.write(soap.getBytes("UTF-8"));
       
       InputStream is = conn.getInputStream();
       
       byte[] b = new byte[1024];
       int len = 0;
       while ((len = is.read(b)) != -1) {
         String ss = new String(b, 0, len, "UTF-8");
         result = String.valueOf(result) + ss;
       } 
       is.close();
       os.close();
       conn.disconnect();
     } catch (Exception e) {
       e.printStackTrace();
     } 
     return result;
   }
   public static String ygpushDataforitem(String param) {
     String result = "";
     String CKDMurl = "http://111.85.254.10:81/default/svc/web-service";
     try {
       URL wsUrl = new URL(CKDMurl);
       HttpURLConnection conn = (HttpURLConnection)wsUrl.openConnection();
       
       conn.setDoInput(true);
       conn.setDoOutput(true);
       conn.setRequestMethod("POST");
       conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
       
       OutputStream os = conn.getOutputStream();
       
       String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ec=\"http://www.example.org/Ec/\"><soapenv:Header/><soapenv:Body><ec:callService><!--Optional:--><paramsJson>" + 
         param + "</paramsJson><appToken>24449ac81ac91d82f6ae2442e5d94c7c</appToken><appKey>383c76f688ef33402f463e656f9621d9</appKey><service>createProduct</service></ec:callService></soapenv:Body></soapenv:Envelope>";
 
       
       os.write(soap.getBytes("UTF-8"));
       
       InputStream is = conn.getInputStream();
       
       byte[] b = new byte[1024];
       int len = 0;
       while ((len = is.read(b)) != -1) {
         String ss = new String(b, 0, len, "UTF-8");
         result = String.valueOf(result) + ss;
       } 
       is.close();
       os.close();
       conn.disconnect();
     } catch (Exception e) {
       e.printStackTrace();
     } 
     return result;
   }
 }


