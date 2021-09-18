 package com.zt.kjybd;
 
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.File;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.net.URLEncoder;
 import org.dom4j.Document;
 import org.dom4j.io.SAXReader;
import org.apache.commons.codec.binary.Base64;
 
 
 public class PushToEMS
 {
   public static String putData(String xmlStr) throws Exception {
     URL url = new URL("http://211.156.193.152:8080/kjjktbApi_Server/call.api");
     
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
       String msg_type = "511";
 
       
       String content = "content=" + URLEncoder.encode(Base64.encodeBase64String(xmlStr.getBytes(charset))) + "&msg_type=" + msg_type;
       
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
       File f = new File("C:\\Users\\Administrator\\Desktop\\1.xml");
       SAXReader reader = new SAXReader();
       Document doc = reader.read(f);
       
       putData(doc.asXML());
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
   }
 }


