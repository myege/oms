 package com.what21.test;
 
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.net.URLDecoder;
 public class TestOrderStatus
 {
   public static void main(String[] args) throws Exception {
     URL wsUrl = new URL("http://47.98.144.242:8081/lcoms/ws/importOrder?wsdl");
     HttpURLConnection conn = (HttpURLConnection)wsUrl.openConnection();
     
     conn.setDoInput(true);
     conn.setDoOutput(true);
     conn.setRequestMethod("POST");
     conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
     
     OutputStream os = conn.getOutputStream();
     String param = "95200138359";
     
     String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:q0=\"http://service.what21.com/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body> <q0:queryOrderStatus><arg0>" + 
       param + "</arg0>  </q0:queryOrderStatus> </soapenv:Body> </soapenv:Envelope>";
     
     os.write(soap.getBytes());
     
     InputStream is = conn.getInputStream();
     
     byte[] b = new byte[1024];
     int len = 0;
     String s = "";
     while ((len = is.read(b)) != -1) {
       String ss = new String(b, 0, len, "UTF-8");
       s = String.valueOf(s) + ss;
     } 
     System.out.println(URLDecoder.decode(s, "UTF-8"));
     
     is.close();
     os.close();
     conn.disconnect();
   }
 }


