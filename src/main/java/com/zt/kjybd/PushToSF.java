 package com.zt.kjybd;
 
 import com.zt.aes256.AES256CipherExternalFactory;
 import com.zt.aes256.HmacSha512CoderFactory;
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import org.dom4j.Document;
 import org.dom4j.io.SAXReader;
 
 
 public class PushToSF
 {
   public static void main(String[] args) {
     try {
       File f = new File("C:\\Users\\Administrator\\Desktop\\OrderConfirm.xml");
       SAXReader reader = new SAXReader();
       Document doc = reader.read(f);
       
       putData_OrderConfirm(doc.asXML());
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
   }
   
   public static String putData(String xmlStr, String PartnerID, String aesKey, String hmacsha512Key, String Url) throws Exception {
     System.out.println(xmlStr);
     
     String MsgData = AES256CipherExternalFactory.AES256Encode(xmlStr, aesKey);
     
     System.out.println("MsgData==" + MsgData);
     
     String DataDigest = HmacSha512CoderFactory.getHmacSha512Coder(hmacsha512Key, MsgData);
     String ServiceCode = "OrderService";
 
 
     
     URL wsUrl = new URL(Url);
     HttpURLConnection conn = (HttpURLConnection)wsUrl.openConnection();
     
     conn.setDoInput(true);
     conn.setDoOutput(true);
     conn.setRequestMethod("POST");
     conn.setUseCaches(false);
     conn.setRequestProperty("Content-Type", "application/xml;charset=UTF-8");
     
     DataOutputStream os = new DataOutputStream(conn.getOutputStream());
     
     String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" ><soap:Body> <tns:sfexpressService xmlns:tns=\"http://service.expressservice.integration.sf.com/\"><MsgData>" + 
 
 
       
       MsgData + "</MsgData> " + 
       "<DataDigest>" + DataDigest + "</DataDigest> " + 
       "<PartnerID>" + PartnerID + "</PartnerID> " + 
       "<ServiceCode>" + ServiceCode + "</ServiceCode> " + 
       " </tns:sfexpressService > " + 
       "</soap:Body> " + 
       "</soap:Envelope>";
     
     os.writeBytes(content);
     os.flush();
     os.close();
     InputStream is = conn.getInputStream();
     InputStreamReader isr = new InputStreamReader(is, "UTF-8");
     BufferedReader reader = new BufferedReader(isr);
     
     StringBuffer buffer = new StringBuffer(); String line;
     while ((line = reader.readLine()) != null) {
       buffer.append(line);
     }
     System.out.println("推送给SF以后的返回值:" + buffer.toString());
     reader.close();
     conn.disconnect();
     
     return buffer.toString();
   }
 
   
   public static String putData_OrderConfirm(String xmlStr) throws Exception {
     String aesKey = "OARDCH8BFcShB3DCtoPgFmsmqFtoUfRb";
     String hmacsha512Key = "XuzatWKMkkyh1u8lL/dz76gyQxABRgt2";
     
     String PartnerID = "ZKKJISP";
     String MsgData = AES256CipherExternalFactory.AES256Encode(xmlStr, aesKey);
     String DataDigest = HmacSha512CoderFactory.getHmacSha512Coder(hmacsha512Key, MsgData);
     String ServiceCode = "OrderConfirm";
     
     URL wsUrl = new URL("http://218.17.248.249:6810/isp/ws/sfexpressService");
     
     HttpURLConnection conn = (HttpURLConnection)wsUrl.openConnection();
     
     conn.setDoInput(true);
     conn.setDoOutput(true);
     conn.setRequestMethod("POST");
     conn.setUseCaches(false);
     conn.setRequestProperty("Content-Type", "application/xml;charset=UTF-8");
     
     DataOutputStream os = new DataOutputStream(conn.getOutputStream());
     
     String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" ><soap:Body> <tns:sfexpressService xmlns:tns=\"http://service.expressservice.integration.sf.com/\"><MsgData>" + 
 
 
       
       MsgData + "</MsgData> " + 
       "<DataDigest>" + DataDigest + "</DataDigest> " + 
       "<PartnerID>" + PartnerID + "</PartnerID> " + 
       "<ServiceCode>" + ServiceCode + "</ServiceCode> " + 
       " </tns:sfexpressService > " + 
       "</soap:Body> " + 
       "</soap:Envelope>";
     
     os.writeBytes(content);
     os.flush();
     os.close();
     InputStream is = conn.getInputStream();
     InputStreamReader isr = new InputStreamReader(is, "UTF-8");
     BufferedReader reader = new BufferedReader(isr);
     
     StringBuffer buffer = new StringBuffer(); String line;
     while ((line = reader.readLine()) != null) {
       buffer.append(line);
     }
     
     reader.close();
     conn.disconnect();
     return buffer.toString();
   }
 }


