 package com.zt.kjybd;
 
 import com.what21.util.AESUtil;
 import com.what21.util.RSAUtil;
 import java.io.File;
 import javax.xml.namespace.QName;
 import javax.xml.rpc.ParameterMode;
 import org.apache.axis.client.Call;
 import org.apache.axis.client.Service;
 import org.apache.axis.encoding.XMLType;
 import org.dom4j.Document;
 import org.dom4j.io.SAXReader;
import org.apache.commons.codec.binary.Base64;
 public class OutWld
 {
   public static String pushWld(String xmlStr) {
     String result = "";
 
     
     try {
       String endpoint = "http://122.224.69.179:8080/newyorkWS/ws/ReceiveEncryptDeclare?wsdl";
 
 
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       call.setOperationName(new QName("http://ws.newyork.zjport.gov.cn/", "receive"));
 
       
       call.addParameter("content", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("msgType", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("dataDigest", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("sendCode", XMLType.XSD_STRING, ParameterMode.IN);
       
       call.setReturnType(XMLType.XSD_STRING);
       
       String privateKey = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAvvO7Hbd7HdWRsIViSAOVTiwwxbMpnIGhk6NpG3JIAjGMxQ8dZBZ+ucSts6M9bJwffjwAuCJoDW707+0lw5bsGwIDAQABAkEAoWYdyJZnh+YWyJ9VXQ06S+LqLpCrIVAopEPA+aP03XRSdE/WhFzrm0iCqOtk1TaOwLeF7hE4YbpOagM5mXnwAQIhAOF8fOfqkr3f85J6hSB6KbWAoJnT1qOtxXwJzkuJOmwBAiEA2MrgOWSJMpCmT+REi82nYpcoXw5DNyD9+q+ZM8UNiBsCIQDKfGQeT0PfRpEQZP3PoGR8HTobaWRCL/Y74QJkSqMwAQIgHfXDkQrszvkgks/oDS4JoN1k7eYJsfe0Qc28rWPhH9sCIBAhfKuUQYpGqUPYKuuhGj2Rpz1itwYREP0AdWzyJqIU";
 
       
       String aesKey = "93bFXIVXZB/uCrXFbG2Dcw==";
       
       byte[] inputContent = xmlStr.getBytes("utf-8");
       byte[] privateKeyCode = Base64.decodeBase64(privateKey);
       byte[] aesKeyCode = Base64.decodeBase64(aesKey);
       
       
       String content = new String(Base64.encodeBase64String(AESUtil.encrypt(inputContent, aesKeyCode)));
 
       
       String dataDigest = new String(Base64.encodeBase64String(RSAUtil.sign(inputContent, privateKeyCode)));
       
       String msgType = "EXPORTWAYBILL";
       String sendCode = "669437562";
       
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
       File f = new File("D:\\xml\\2.xml");
       SAXReader reader = new SAXReader();
       Document doc = reader.read(f);
       
       pushWld(doc.asXML());
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
   }
 }


