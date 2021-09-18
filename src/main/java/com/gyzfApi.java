 package com;
 
 import com.alibaba.fastjson.JSONObject;
 import com.google.gson.Gson;
 import com.zt.kjybd.PushToGY;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.StringWriter;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.util.LinkedHashMap;
 import java.util.Map;
 import javax.xml.rpc.ParameterMode;
 import org.apache.axis.client.Call;
 import org.apache.axis.client.Service;
 import org.apache.axis.encoding.XMLType;
 import org.jdom.Document;
 import org.jdom.input.SAXBuilder;
 import org.jdom.output.Format;
 import org.jdom.output.XMLOutputter;
 public class gyzfApi
 {
   public static void ddsend(String merAccount) throws Exception {
     Gson gson = new Gson();
     Map<String, Object> map = new LinkedHashMap<>();
     map.put("guid", "adadadadadadadadadadadadadadadasasas");
     map.put("merAccount", merAccount);
     map.put("appType", "1");
     map.put("appTime", "20200220152600");
     map.put("appStatus", "2");
     map.put("orderType", "I");
     map.put("orderNo", "20200220001");
     map.put("ebpCode", "1234567890");
     map.put("ebpName", "电商平台");
     map.put("ebcCode", "1234567890");
     map.put("ebcName", "电商企业");
     map.put("orderMoney", "100");
     map.put("freight", "0");
     map.put("discount", "0");
     map.put("taxMoney", "0");
     map.put("payMoney", "100");
     map.put("currency", "142");
     map.put("buyerRegNo", "朱赛君");
     map.put("buyerName", "朱赛君");
     map.put("buyerIdType", "1");
     map.put("buyerIdNumber", "330726198111071341");
     map.put("buyerTelephone", "13957120327");
     
     String str = gson.toJson(map);
     
     String od = "{\"orderInfo\":" + str + ",\"productList\":[]}";
     
     String str_orderInfo = "";
     System.out.println("str_orderInfo=" + str_orderInfo);
     str_orderInfo = od.replaceAll("\\\\", "");
     
     System.out.println("str_orderInfo2=" + str_orderInfo);
     PushToGY.sendPost("http://139.129.116.198/cbes/api/report", str_orderInfo);
   }
 
 
 
   
   public static void ddgetNumber(String ddh) throws Exception {
     String od = "{\"orderNo\":'" + ddh + "'}";
     
     String result = PushToGY.sendPost("http://139.129.116.198/cbes/api/queryOrder", od);
 
     
     JSONObject obj = JSONObject.parseObject(result);
     JSONObject obj2 = obj.getJSONObject("order");
     String order = (String)obj2.get("billno");
     System.out.println("str=" + order);
   }
   
   public static String towms(String url) {
     String result = "";
     
     try {
       String endpoint = "http://111.85.254.10:83/default/svc/wsdl";
 
       
       Service service1 = new Service();
       Call call = (Call)service1.createCall();
       call.setTargetEndpointAddress(endpoint);
       
       call.setOperationName("callService");
       call.addParameter("paramsJson", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("appToken", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("appKey", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("service", XMLType.XSD_STRING, ParameterMode.IN);
       
       call.setReturnType(XMLType.XSD_STRING);
       String appToken = "24449ac81ac91d82f6ae2442e5d94c7c";
       String appKey = "383c76f688ef33402f463e656f9621d9";
       String paramsJson = url;
       String service = "createOrder";
       result = (String)call.invoke(new Object[] { paramsJson, appToken, appKey, service });
       
       System.out.println("result is " + result);
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     return result;
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
 
 
 
   
   public static void main(String[] args) throws Exception {
     byte[] bytes = new byte[2048];
     InputStream in = new FileInputStream("E:\\format.1548289205787.json");
     StringBuffer sb = new StringBuffer();
     int n = -1;
     while ((n = in.read(bytes, 0, bytes.length)) != -1) {
       String str1 = new String(bytes, 0, n, "GBK");
       sb.append(str1);
     } 
     
     String str = sb.toString();
     pushToYjData(str, "http://111.85.254.10:81/default/svc/web-service", "createOrder");
   }
   
   public static String pushToYjData(String param, String url, String str) throws Exception {
     url = "http://111.85.254.10:81/default/svc/web-service";
     URL wsUrl = new URL(url);
     HttpURLConnection conn = (HttpURLConnection)wsUrl.openConnection();
     conn.setDoInput(true);
     conn.setDoOutput(true);
     conn.setRequestMethod("POST");
     conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
     conn.setDoOutput(true);
     conn.setDoInput(true);
     boolean a = true;
     OutputStream os = null;
     InputStream is = null;
     String result = "fail";
     try {
       os = conn.getOutputStream();
 
       
       String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ec=\"http://www.example.org/Ec/\"><soapenv:Header/><soapenv:Body><ec:callService><!--Optional:--><paramsJson>" + 
         param + "</paramsJson><appToken>e67cd9fc050cb1c43c84dc39c6fb1380</appToken><appKey>1a171f707697af8b6dc92d213216e661</appKey><service>createOrder</service></ec:callService></soapenv:Body></soapenv:Envelope>";
       os.write(soap.getBytes());
       is = conn.getInputStream();
     } catch (Exception e) {
       e.printStackTrace();
       a = false;
       System.out.println(e);
     } 
     if (a) {
       byte[] b = new byte[1024];
       int len = 0;
       String s = "";
       while ((len = is.read(b)) != -1) {
         String ss = new String(b, 0, len, "UTF-8");
         s = String.valueOf(s) + ss;
       } 
       System.out.println(s);
     } 
     return result;
   }
 }


