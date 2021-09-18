 package org.yu;
 
 import com.what21.util.AESUtil;
 import com.what21.util.RSAUtil;
 import java.io.File;
 import java.io.IOException;
 import java.io.StringWriter;
 import javax.xml.namespace.QName;
 import javax.xml.rpc.ParameterMode;
 import org.apache.axis.client.Call;
 import org.apache.axis.client.Service;
 import org.apache.axis.encoding.XMLType;
 import org.apache.commons.codec.binary.Base64;
 import org.jdom.Document;
 import org.jdom.input.SAXBuilder;
 import org.jdom.output.Format;
 import org.jdom.output.XMLOutputter;
 
 
 
 public class zjpost
 {
   public static Document load(String url) {
     Document document = null;
     
     try {
       SAXBuilder reader = new SAXBuilder();
       document = reader.build(new File(url));
     } catch (Exception e) {
       e.printStackTrace();
     } 
     return document;
   }
   public static String XmlToString(String url) {
     Document document = null;
     document = load(url);
     
     Format format = Format.getPrettyFormat();
     format.setEncoding("UTF-8");
     
     StringWriter out = null;
     String sReturn = "";
     XMLOutputter outputter = new XMLOutputter();
     out = new StringWriter();
     try {
       outputter.output(document, out);
     } catch (IOException e) {
       e.printStackTrace();
     } 
     sReturn = out.toString();
     return sReturn;
   }
   public static String toZJyd_ttk(String url) {
     String result = "";
     try {
       String privateKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAkMzB+KEZ5aTD/gyiv1NQ0wqrESIVUl4Fs2zzye4NMaCtik3N/cjArolJffwyPLIWSXaTnqqCs9TudZWeqeMDCwIDAQABAkBlA2Kh5wkg6eOMXBZA8Idm4n9aUXOcZVkGxZAAo5/yqPk/x/YN0uxWfk5YTbrhhl2exp58B7BbOFNJN3Nc0rDxAiEA7Vh9xLtB06c2mkWgpY8IS0L7GS/qhXcepkheHjeqd/MCIQCcLjMod3bQwTnOn7eJKoX9Uvr1dxBqylWcVTnlIipmiQIgHfuaz7p0I1+xRyXamG+MNa29eL4T205kAc+MjDauAz8CIGrWE7rH1ehBdajJiwRyqUu8Gt4VP469sEiL6Fm8wGJ5AiBQURKVnpxve9VXwjWOvmfN3GGRtRbD+AEh6ZhStEqS6g==";
       String aesKey = "tP1TieCiZS8far66qfgDhQ==";
       byte[] inputContent = url.getBytes("utf-8");
       byte[] privateKeyCode = Base64.decodeBase64(privateKey.getBytes("utf-8"));
       byte[] aesKeyCode = Base64.decodeBase64(aesKey.getBytes("utf-8"));
       
       String encData = new String(Base64.encodeBase64(AESUtil.encrypt(inputContent, aesKeyCode)), "utf-8");
 
       
       String sign = new String(Base64.encodeBase64(RSAUtil.sign(inputContent, privateKeyCode)), "utf-8");
 
       
       String endpoint = "http://122.224.69.179:8080/newyorkWS/ws/ReceiveEncryptDeclare?wsdl";
 
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       call.setOperationName(new QName("http://ws.newyork.zjport.gov.cn/", "receive"));
       
       call.addParameter("content", XMLType.XSD_DATE, ParameterMode.IN);
       call.addParameter("msgType", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("dataDigest", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("sendCode", XMLType.XSD_STRING, ParameterMode.IN);
       
       call.setReturnType(XMLType.XSD_STRING);
       
       String msgType = "IMPORTBILL";
       String dataDigest = sign;
       String sendCode = "669437562";
       result = (String)call.invoke(new Object[] { encData, msgType, dataDigest, sendCode });
       
       System.out.println("result is " + result);
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     return result;
   }
   public static String toZJyd(String url) {
     String result = "";
     
     try {
       String endpoint = "http://122.224.69.179:8080/newyorkWS/ws/ReceivedDeclare?wsdl";
 
 
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       call.setOperationName(new QName("http://ws.newyork.zjport.gov.cn/", "checkReceived"));
       
       call.addParameter("xmlStr", XMLType.XSD_DATE, ParameterMode.IN);
       call.addParameter("xmlType", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("sourceType", XMLType.XSD_STRING, ParameterMode.IN);
       
       call.setReturnType(XMLType.XSD_STRING);
       
       String xmlType = "IMPORTBILL";
       String sourceType = "1";
       result = (String)call.invoke(new Object[] { url, xmlType, sourceType });
       
       System.out.println("result is " + result);
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     
     return result;
   }
   public static String toZJyd3(String url) {
     String result = "";
     
     try {
       String endpoint = "http://122.224.230.4:18003/newyorkWS/ws/ReceiveCebDeclare?wsdl";
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       call.setOperationName(new QName("http://ws.newyork.zjport.gov.cn/", "receive"));
       
       call.addParameter("content", XMLType.XSD_DATE, ParameterMode.IN);
 
 
       
       call.setReturnType(XMLType.XSD_STRING);
       
       String xmlType = "IMPORTBILL";
       String sourceType = "1";
       result = (String)call.invoke(new Object[] { url });
       
       System.out.println("result is " + result);
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     
     return result;
   }
   
   public static String toZJqd2(String url) {
     String result = "";
 
 
     
     try {
       String endpoint = "http://122.224.69.179:8080/newyorkWS/ws/CheckGoodsDecl?wsdl";
 
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       call.setOperationName(new QName("http://ws.newyork.zjport.gov.cn/", "receive"));
       
       call.addParameter("content", XMLType.XSD_STRING, ParameterMode.IN);
 
 
       
       call.setReturnType(XMLType.XSD_STRING);
 
 
       
       result = (String)call.invoke(new Object[] { url });
       
       System.out.println("result is " + result);
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     
     return result;
   }
   
   public static String toZJyd_jm(String url) {
     String result = "";
     try {
       String privateKey = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAvvO7Hbd7HdWRsIViSAOVTiwwxbMpnIGhk6NpG3JIAjGMxQ8dZBZ+ucSts6M9bJwffjwAuCJoDW707+0lw5bsGwIDAQABAkEAoWYdyJZnh+YWyJ9VXQ06S+LqLpCrIVAopEPA+aP03XRSdE/WhFzrm0iCqOtk1TaOwLeF7hE4YbpOagM5mXnwAQIhAOF8fOfqkr3f85J6hSB6KbWAoJnT1qOtxXwJzkuJOmwBAiEA2MrgOWSJMpCmT+REi82nYpcoXw5DNyD9+q+ZM8UNiBsCIQDKfGQeT0PfRpEQZP3PoGR8HTobaWRCL/Y74QJkSqMwAQIgHfXDkQrszvkgks/oDS4JoN1k7eYJsfe0Qc28rWPhH9sCIBAhfKuUQYpGqUPYKuuhGj2Rpz1itwYREP0AdWzyJqIU";
       String aesKey = "93bFXIVXZB/uCrXFbG2Dcw==";
       byte[] inputContent = url.getBytes("utf-8");
       byte[] privateKeyCode = Base64.decodeBase64(privateKey.getBytes("utf-8"));
       byte[] aesKeyCode = Base64.decodeBase64(aesKey.getBytes("utf-8"));
       
       String encData = new String(Base64.encodeBase64(AESUtil.encrypt(inputContent, aesKeyCode)), "utf-8");
 
       
       String sign = new String(Base64.encodeBase64(RSAUtil.sign(inputContent, privateKeyCode)), "utf-8");
 
       
       String endpoint = "http://122.224.69.179:8080/newyorkWS/ws/ReceiveEncryptDeclare?wsdl";
 
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       call.setOperationName(new QName("http://ws.newyork.zjport.gov.cn/", "receive"));
       
       call.addParameter("content", XMLType.XSD_DATE, ParameterMode.IN);
       call.addParameter("msgType", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("dataDigest", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("sendCode", XMLType.XSD_STRING, ParameterMode.IN);
       
       call.setReturnType(XMLType.XSD_STRING);
       
       String msgType = "IMPORTBILL";
       String dataDigest = sign;
       String sendCode = "669437562";
       result = (String)call.invoke(new Object[] { encData, msgType, dataDigest, sendCode });
       
       System.out.println("result is " + result);
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     return result;
   }
 
   
   public static void main(String[] args) {
     String originalContent = XmlToString("D://xml//2222.xml");
     System.out.println(originalContent);
     toZJyd3(originalContent);
   }
 }


