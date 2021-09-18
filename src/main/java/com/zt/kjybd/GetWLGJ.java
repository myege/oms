 package com.zt.kjybd;
 
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.net.URLDecoder;
 
 
 public class GetWLGJ
 {
   public static String queryWlgj(String mailNo) throws Exception {
     URL wsUrl = new URL("http://60.191.5.202:8123/ztz/ws/importOrder?wsdl");
     
     HttpURLConnection conn = (HttpURLConnection)wsUrl.openConnection();
     
     conn.setDoInput(true);
     conn.setDoOutput(true);
     conn.setRequestMethod("POST");
     conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
     
     OutputStream os = conn.getOutputStream();
     
     String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:q0=\"http://service.what21.com/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body> <q0:newLogisticstrack><arg0>" + 
       mailNo + "</arg0>  </q0:newLogisticstrack> </soapenv:Body> </soapenv:Envelope>";
     os.write(soap.getBytes());
     
     InputStream is = conn.getInputStream();
     
     byte[] b = new byte[1024];
     int len = 0;
     String s = "";
     while ((len = is.read(b)) != -1) {
       String ss = new String(b, 0, len, "UTF-8");
       s = String.valueOf(s) + ss;
     } 
 
     
     is.close();
     os.close();
     conn.disconnect();
     return URLDecoder.decode(s);
   }
   
   public static void main(String[] args) throws Exception {
     String queryWlgj = queryWlgj("221208877988,221208877998");
     System.out.println(queryWlgj);
   }
 }


