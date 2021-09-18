 package com.zt.tsjgq;
 
 import cn.gov.zjport.manchester.encrypt.AESEncrypt;
 import java.io.File;
 import java.io.IOException;
 import java.io.StringWriter;
 import javax.xml.namespace.QName;
 import javax.xml.rpc.ParameterMode;
 import org.apache.axis.client.Call;
 import org.apache.axis.client.Service;
 import org.apache.axis.encoding.XMLType;
 import org.jdom.Document;
 import org.jdom.input.SAXBuilder;
 import org.jdom.output.Format;
 import org.jdom.output.XMLOutputter;
 public class postxml
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
   public static String toZJyd(String url) {
     String result = "";
     
     try {
       String endpoint = "http://122.224.230.10:6060/manchesterDep/ws/manPlaningReceiptsWs?wsdl";
 
       
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
 
   
   public static String toZG_jhrkd(String str) {
     String result = "";
 
 
     
     try {
       String endpoint = "http://122.224.69.187:57090/manchesterDep/ws/manPlaningReceiptsWs?wsdl";
 
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       
       call.setOperationName(new QName("http://jobform.webservice.manchester.zjport.gov.cn/", "createPlaningReceipts"));
       
       call.addParameter("arg0", XMLType.XSD_DATE, ParameterMode.IN);
       call.setReturnType(XMLType.XSD_STRING);
 
       
       str = AESEncrypt.encrytor(str);
 
 
 
       
       result = (String)call.invoke(new Object[] { str });
 
 
     
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     
     return result;
   }
 
   
   public static String toZG_crkjr(String str) {
     String result = "";
     
     try {
       String endpoint = "http://122.224.69.187:57090/manchesterDep/ws/manStockInOutWs?wsdl";
 
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       
       call.setOperationName(new QName("http://warehouse.webservice.manchester.zjport.gov.cn/", "createStockInOut"));
       
       call.addParameter("arg0", XMLType.XSD_DATE, ParameterMode.IN);
       call.setReturnType(XMLType.XSD_STRING);
 
       
       str = AESEncrypt.encrytor(str);
 
 
 
       
       result = (String)call.invoke(new Object[] { str });
       result = AESEncrypt.decryptor(result);
 
 
     
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
 
 
     
     return result;
   }
   
   public static String toZG_sjrk(String str) {
     String result = "";
     
     try {
       String endpoint = "http://122.224.69.187:57090/manchesterDep/ws/manActualReceiveWs?wsdl";
 
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       
       call.setOperationName(new QName("http://jobform.webservice.manchester.zjport.gov.cn/", "updateActualReceive"));
       
       call.addParameter("arg0", XMLType.XSD_DATE, ParameterMode.IN);
       call.setReturnType(XMLType.XSD_STRING);
 
       
       str = AESEncrypt.encrytor(str);
 
 
 
       
       result = (String)call.invoke(new Object[] { str });
       
       result = AESEncrypt.decryptor(result);
     
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     return result;
   }
 
 
   
   public static String toZG_kc(String str) {
     String result = "";
     try {
       String endpoint = "http://122.224.230.10:6060/manchesterDep/ws/manInventoryWs?wsdl";
 
 
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       
       call.setOperationName(new QName("http://warehouse.webservice.manchester.zjport.gov.cn/", "createInventory"));
       
       call.addParameter("arg0", XMLType.XSD_DATE, ParameterMode.IN);
       call.setReturnType(XMLType.XSD_STRING);
 
       
       str = AESEncrypt.encrytor(str);
 
 
 
       
       result = (String)call.invoke(new Object[] { str });
 
 
     
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
 
 
     
     return result;
   }
 
 
 
   
   public static String toZG_sdsq(String str) {
     String result = "";
 
     
     try {
       String endpoint = "http://122.224.69.187:57090/manchesterDep/ws/manStockDeleteWs?wsdl";
 
 
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       
       call.setOperationName(new QName("http://warehouse.webservice.manchester.zjport.gov.cn/", "addStockDelete"));
       
       call.addParameter("arg0", XMLType.XSD_DATE, ParameterMode.IN);
       call.setReturnType(XMLType.XSD_STRING);
 
       
       str = AESEncrypt.encrytor(str);
 
 
 
       
       result = (String)call.invoke(new Object[] { str });
 
 
     
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     
     return result;
   }
 
 
 
   
   public static String test2(String str) {
     String result = "";
     
     try {
       String endpoint = "http://114.55.105.214:8085/ztz/ws/importOrder?wsdl";
 
 
 
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       
       call.setOperationName(new QName("http://service.what21.com/", "sendMergerInfo"));
       
       call.addParameter("content", XMLType.XSD_DATE, ParameterMode.IN);
       call.setReturnType(XMLType.XSD_STRING);
 
       
       str = AESEncrypt.encrytor(str);
 
 
 
       
       result = (String)call.invoke(new Object[] { str });
 
 
     
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     
     return result;
   }
   
   public static void main(String[] args) {
     String url = "D:\\xml\\25.xml";
 
     
     String url1 = "D:\\xml\\22069416501.xml";
 
     
     String url2 = "D:\\xml\\rkd.xml";
 
     
     String url3 = "D:\\xml\\22069416503.xml";
     String url9 = "D:\\xml\\31.xml";
     toZG_kc(XmlToString(url9));
   }
 }


