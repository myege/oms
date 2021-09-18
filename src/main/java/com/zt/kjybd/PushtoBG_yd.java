 package com.zt.kjybd;
 
 import com.what21.model.ZtzConst;
 import com.what21.model.enums.ZtzPropertiesEnum;
 import com.what21.util.AESUtil;
 import com.what21.util.RSAUtil;
 import java.io.File;
 import java.io.IOException;
 import java.io.StringWriter;
 import java.text.SimpleDateFormat;
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
 public class PushtoBG_yd
 {
   private static String token = "open";
 
   
   static String ORDER_URL = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.ORDER_URL.getValue());
   
   String ORDER_ENTERPRISE_NAME = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.ORDER_ENTERPRISE_NAME.getValue());
   
   static String ORDER_CUSTOMS_CODE = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.ORDER_CUSTOMS_CODE.getValue());
   
   String ORDER_DXPID = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.ORDER_DXPID.getValue());
   
   static String ORDER_ASE_SECRETKEY = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.ORDER_ASE_SECRETKEY.getValue());
   
   static String ORDER_ENTERPRISE_RSA_PRIVATEKEY = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.ORDER_ENTERPRISE_RSA_PRIVATEKEY.getValue());
   
   String ORDER_ENTERPRISE_RSA_PUBLICKEY = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.ORDER_ENTERPRISE_RSA_PUBLICKEY.getValue());
   
   String ORDER_RECEIPT_PARSING_SECRETKEY = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.ORDER_RECEIPT_PARSING_SECRETKEY.getValue());
 
 
   
   static String BILL_URL = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.BILL_URL.getValue());
   
   String BILL_ENTERPRISE_NAME = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.BILL_ENTERPRISE_NAME.getValue());
   
   static String BILL_CUSTOMS_CODE = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.BILL_CUSTOMS_CODE.getValue());
   
   String BILL_DXPID = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.BILL_DXPID.getValue());
   
   static String BILL_ASE_SECRETKEY = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.BILL_ASE_SECRETKEY.getValue());
   
   static String BILL_ENTERPRISE_RSA_PRIVATEKEY = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.BILL_ENTERPRISE_RSA_PRIVATEKEY.getValue());
   
   String BILL_ENTERPRISE_RSA_PUBLICKEY = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.BILL_ENTERPRISE_RSA_PUBLICKEY.getValue());
   
   String BILL_RECEIPT_PARSING_SECRETKEY = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.BILL_RECEIPT_PARSING_SECRETKEY.getValue());
   static String CKDM = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.CKDM.getValue());
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
 
     
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     return result;
   }
   public static String toZJyd_sto(String url) {
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
   public static String toZJdd(String url) {
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
       
       String xmlType = "IMPORTORDER";
       String sourceType = "1";
       result = (String)call.invoke(new Object[] { url, xmlType, sourceType });
 
     
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     return result;
   }
   public static String toZJdd_md(String url) {
     String result = "";
     try {
       String privateKey = ORDER_ENTERPRISE_RSA_PRIVATEKEY;
       
       Thread.currentThread();
       Thread.sleep(4000L);
       
       String aesKey = ORDER_ASE_SECRETKEY;
       byte[] inputContent = url.getBytes("utf-8");
       byte[] privateKeyCode = Base64.decodeBase64(privateKey.getBytes("utf-8"));
       byte[] aesKeyCode = Base64.decodeBase64(aesKey.getBytes("utf-8"));
       
       String encData = new String(Base64.encodeBase64(AESUtil.encrypt(inputContent, aesKeyCode)), "utf-8");
 
       
       String sign = new String(Base64.encodeBase64(RSAUtil.sign(inputContent, privateKeyCode)), "utf-8");
 
 
       
       String endpoint = ORDER_URL;
 
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       call.setOperationName(new QName("http://ws.newyork.zjport.gov.cn/", "receive"));
       
       call.addParameter("content", XMLType.XSD_DATE, ParameterMode.IN);
       call.addParameter("msgType", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("dataDigest", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("sendCode", XMLType.XSD_STRING, ParameterMode.IN);
       
       call.setReturnType(XMLType.XSD_STRING);
       
       String msgType = "IMPORTORDER";
       String dataDigest = sign;
       String sendCode = ORDER_CUSTOMS_CODE;
       result = (String)call.invoke(new Object[] { encData, msgType, dataDigest, sendCode });
       
       System.out.println("result is " + result);
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     return result;
   }
   public static String toZJqd(String url) {
     String result = "";
     try {
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       
       Thread.currentThread();
       Thread.sleep(4000L);
 
       
       String endpoint = "http://122.224.230.4:18003/newyorkWS/ws/ReceivedDeclare?wsdl";
 
 
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       call.setOperationName(new QName("http://ws.newyork.zjport.gov.cn/", "checkReceived"));
       
       call.addParameter("xmlStr", XMLType.XSD_DATE, ParameterMode.IN);
       call.addParameter("xmlType", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("sourceType", XMLType.XSD_STRING, ParameterMode.IN);
       
       call.setReturnType(XMLType.XSD_STRING);
       
       String xmlType = "PERSONAL_GOODS_DECLAR";
       String sourceType = "1";
       result = (String)call.invoke(new Object[] { url, xmlType, sourceType });
       
       System.out.println("result is " + result);
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     return result;
   }
   public static String toZJqd_md(String url) {
     String result = "";
     try {
       String privateKey = BILL_ENTERPRISE_RSA_PRIVATEKEY;
       
       String aesKey = BILL_ASE_SECRETKEY;
       
       byte[] inputContent = url.getBytes("utf-8");
       byte[] privateKeyCode = Base64.decodeBase64(privateKey.getBytes("utf-8"));
       byte[] aesKeyCode = Base64.decodeBase64(aesKey.getBytes("utf-8"));
       
       String encData = new String(Base64.encodeBase64(AESUtil.encrypt(inputContent, aesKeyCode)), "utf-8");
 
       
       String sign = new String(Base64.encodeBase64(RSAUtil.sign(inputContent, privateKeyCode)), "utf-8");
 
 
       
       String endpoint = BILL_URL;
 
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       call.setOperationName(new QName("http://ws.newyork.zjport.gov.cn/", "receive"));
       
       call.addParameter("content", XMLType.XSD_DATE, ParameterMode.IN);
       call.addParameter("msgType", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("dataDigest", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("sendCode", XMLType.XSD_STRING, ParameterMode.IN);
       
       call.setReturnType(XMLType.XSD_STRING);
       
       String msgType = "PERSONAL_GOODS_DECLAR";
       String dataDigest = sign;
       String sendCode = BILL_CUSTOMS_CODE;
       result = (String)call.invoke(new Object[] { encData, msgType, dataDigest, sendCode });
       
       System.out.println("result is " + result);
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     return result;
   }
   public static String testQd(String url, String qdToken) {
     if ("".equals(url)) {
       token = "open";
       return "end";
     } 
     
     if (token.equals(qdToken)) {
       token = "close";
     } else {
       return "令牌无效";
     } 
     
     String result = "";
     try {
       Thread.currentThread();
       Thread.sleep(3000L);
       
       String endpoint = "http://122.224.69.179:8080/newyorkWS/ws/ReceivedDeclare?wsdl";
 
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       call.setOperationName(new QName("http://ws.newyork.zjport.gov.cn/", "checkReceived"));
       
       call.addParameter("xmlStr", XMLType.XSD_DATE, ParameterMode.IN);
       call.addParameter("xmlType", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("sourceType", XMLType.XSD_STRING, ParameterMode.IN);
       
       call.setReturnType(XMLType.XSD_STRING);
       
       String xmlType = "PERSONAL_GOODS_DECLAR";
       String sourceType = "1";
       result = (String)call.invoke(new Object[] { url, xmlType, sourceType });
     
     }
     catch (Exception e) {
       token = "open";
       System.err.println(e.toString());
     } 
     return result;
   }
   public static String toZJqd_mdcs(String url) {
     String result = "";
     try {
       String privateKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAlARirhFwtIV81skDozZWWHnfndLju2xkAz+ccUnhNnytdPDjzOyeH9rq+1g2kO/1moKdzESZdnJDwqRT36faiwIDAQABAkAOngBAwGgo66XQcDlzvMH6Ks7yfwkOUT3vNDD23xzmjHLykQGd0BR5thU3djoQW2WyZ+zT5Idme5c+SqW7CEmBAiEAzfkbJ8jXCxJ+KuaA+GpDpmXGMBU0sZY1OeaqzQ/UogMCIQC397kW4YZz+GBGCF0JXJF9LTxM+IL1rh9iElPejreC2QIgBz26tqtY5ArCZL+1yQXzWuY5EXetYpDlTiPN3WexEgECIC6fcF/1Hd8MJlH5UqLJu/FuYmbbYE0lKLzxCiJQJv/ZAiA0biu+J+SyNLhry8MLXWsnlD6c7K0M2qbY90pz6FSrTQ==";
       
       String aesKey = "e5sfqSWqjMDA4WzwYnIoLQ==";
       byte[] inputContent = url.getBytes("utf-8");
       byte[] privateKeyCode = Base64.decodeBase64(privateKey.getBytes("utf-8"));
       byte[] aesKeyCode = Base64.decodeBase64(aesKey.getBytes("utf-8"));
       
       String encData = new String(Base64.encodeBase64(AESUtil.encrypt(inputContent, aesKeyCode)), "utf-8");
 
       
       String sign = new String(Base64.encodeBase64(RSAUtil.sign(inputContent, privateKeyCode)), "utf-8");
       
       String endpoint = "http://122.224.230.4:18003/newyorkWS/ws/ReceiveEncryptDeclare?wsdl";
 
 
 
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       call.setOperationName(new QName("http://ws.newyork.zjport.gov.cn/", "receive"));
       
       call.addParameter("content", XMLType.XSD_DATE, ParameterMode.IN);
       call.addParameter("msgType", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("dataDigest", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("sendCode", XMLType.XSD_STRING, ParameterMode.IN);
       
       call.setReturnType(XMLType.XSD_STRING);
       
       String msgType = "PERSONAL_GOODS_DECLAR";
       String dataDigest = sign;
       String sendCode = "3318W60056";
       result = (String)call.invoke(new Object[] { encData, msgType, dataDigest, sendCode });
       
       System.out.println("result is " + result);
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     return result;
   }
 
 
   
   public static String pushzs(String xmlStr) {
     String result = "";
 
     
     try {
       String endpoint = "http://122.224.69.179:8080/newyorkWS/ws/ReceiveCebDeclare?wsdl";
 
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       call.setOperationName(new QName("http://ws.newyork.zjport.gov.cn/", "receive"));
       call.addParameter("content", XMLType.XSD_STRING, ParameterMode.IN);
       call.setReturnType(XMLType.XSD_STRING);
 
 
       
       result = (String)call.invoke(new Object[] { xmlStr });
 
 
     
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     return result;
   }
   
   public static void main(String[] args) {
     String url = "";
     String url2 = "";
 
 
 
     
     url2 = "E:\\xml\\gydd.xml";
     pushzs(XmlToString(url2));
   }
 }


