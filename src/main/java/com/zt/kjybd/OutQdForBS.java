 package com.zt.kjybd;
 
 import com.what21.util.AESUtil;
 import com.what21.util.RSAUtil;
 import java.io.File;
 import javax.xml.namespace.QName;
 import javax.xml.rpc.ParameterMode;
 import org.apache.axis.client.Call;
 import org.apache.axis.client.Service;
 import org.apache.axis.encoding.XMLType;
import org.apache.commons.codec.binary.Base64;
 import org.dom4j.Document;
 import org.dom4j.io.SAXReader;
 public class OutQdForBS
 {
   public static String pushQd(String xmlStr) {
     String result = "";
     try {
       String endpoint = "http://122.224.230.4:18003/newyorkWS/ws/ReceiveEncryptDeclare?wsdl";
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       call.setOperationName(new QName("http://ws.newyork.zjport.gov.cn/", "receive"));
 
       
       call.addParameter("content", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("msgType", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("dataDigest", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("sendCode", XMLType.XSD_STRING, ParameterMode.IN);
       
       call.setReturnType(XMLType.XSD_STRING);
       
       String privateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAlGtpZOdKVzNri1Ijac5wQi1PVJfng6hU4jP63zstBu5xSKXuWdcKLi1T0A6/L3RFKgZQACVW7gGNWw/yKnHDVQIDAQABAkBxnlCOeCkG3ExF2VlW1DxHzNvgDHGk7iYdDGHOpkRv+sp7e/NK3+3Jx8m+aJX98C4ZeEeHH+ToEZ22mDUkjRO5AiEAyuiZ0dKVThKGZrYzVNTQ4L3rdLDHlKpFQUnZzb5zLOMCIQC7QPrr96Ge1JrbETYmE/H7rUYnFmH+b2v46dHGQ4y8ZwIgBK/+7bP5swYGiNTotqr78FUy3U1ALxUH6Iw2NeSScJUCIEyWfkrHJPamtJPHTWNzovSyYVAZaok7n2jWkbNK66QfAiEAht0/T+UZiQml5gGgsTu0VPvjd+uOinROTcUAcGDDBds=";
       
       String aesKey = "FTP5clKAOYCAb2qZHHWWkA==";
       
       byte[] inputContent = xmlStr.getBytes("utf-8");
       byte[] privateKeyCode = Base64.decodeBase64(privateKey);
       byte[] aesKeyCode = Base64.decodeBase64(aesKey);
       
       
       String content = new String(Base64.encodeBase64String(AESUtil.encrypt(inputContent, aesKeyCode)));
 
       
       String dataDigest = new String(Base64.encodeBase64String(RSAUtil.sign(inputContent, privateKeyCode)));
       
       String msgType = "BILL";
       String sendCode = "330156K004";
       
       result = (String)call.invoke(new Object[] { content, msgType, dataDigest, sendCode });
       
       System.out.println("result is------------>" + result);
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     return result;
   }
 
   
   public static void main(String[] args) throws Exception {
     try {
       File f = new File("D:\\xml\\4.xml");
       SAXReader reader = new SAXReader();
       Document doc = reader.read(f);
       System.out.println(doc.asXML());
       pushQd(doc.asXML());
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
   }
 }


