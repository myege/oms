 package com.what21.util;
 
 import cn.gov.zjport.gtw.gateway.in.client.SSLClient;
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.net.URLEncoder;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Map;
 import org.apache.commons.lang.StringEscapeUtils;
 import org.dom4j.Document;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 import sun.misc.BASE64Encoder;
 public class EncryptHG
 {
   public static String getXmlMW(Document document) throws Exception {
     URL url = new URL("http://60.190.249.117:9090/newyorkTransferWebapps/rest/transferDeclare");
     
     HttpURLConnection connection = (HttpURLConnection)url.openConnection();
     connection.setRequestMethod("POST");
     connection.setDoOutput(true);
     connection.setDoInput(true);
     connection.setUseCaches(false);
     connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
     connection.connect();
     DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
     
     String xmls = "xml=" + URLEncoder.encode(document.asXML(), "UTF-8");
     byte[] bytes = xmls.getBytes("UTF-8");
     outputStream.write(bytes);
     outputStream.flush();
     outputStream.close();
     InputStreamReader is = new InputStreamReader(connection.getInputStream(), "UTF-8");
     BufferedReader br = new BufferedReader(is);
     String line = "";
     StringBuffer sb = new StringBuffer();
     while ((line = br.readLine()) != null) {
       line = new String(line.getBytes());
       sb.append(line);
     } 
     br.close();
     String xml = null;
     if (sb.toString().indexOf("true") != -1) {
       xml = sb.toString().substring(23, sb.length() - 2);
     }
     String tmp = StringEscapeUtils.unescapeJavaScript(xml);
 
     
     return tmp;
   }
 
   
   public static void pushQd(String args, String Code) throws Exception {
     SSLClient sSLClient = new SSLClient("https://openapi.zjport.gov.cn/gateway/receive");
     
     String appId = "JKF00336";
     String appKey = "UDZRgErZQ@7W5GvttrISb4Slg*^*uYcfB3M*V1sY";
 
 
     
     String timestamp = String.valueOf(System.currentTimeMillis());
     String bizCode = "CEB" + Code + "00";
     String bizId = "311af236-6fed-4603-8c5d-49b1fa4b4b9b";
     
     String content = args;
     String sign = sSLClient.getSign(bizCode, bizId, content, timestamp, appKey);
     Map<String, String> parameters = new HashMap<>();
     parameters.put("appId", appId);
     parameters.put("bizCode", bizCode);
     parameters.put("bizId", bizId);
     parameters.put("content", content);
     parameters.put("timestamp", timestamp);
     parameters.put("sign", sign);
     parameters.put("companyCode", "3316965628");
     parameters.put("cusSign", "1");
     String result = sSLClient.post(parameters);
     System.out.println(result);
   }
 
   
   public static Document getZSXml(String xml, String copMsgId, int type, int mesg) throws Exception {
     Date date = new Date();
     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
     SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
     BASE64Encoder base64Decoder = new BASE64Encoder();
     Element root = DocumentHelper.createElement("dxp:DxpMsg").addAttribute("xmlns:dxp", "http://www.chinaport.gov.cn/dxp")
       .addAttribute("ver", "1.0").addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
     root.addNamespace("dxp", "http://www.chinaport.gov.cn/dxp");
     Document document1 = DocumentHelper.createDocument(root);
     Element TransInfo = root.addElement("dxp:TransInfo");
     Element Data = root.addElement("dxp:Data");
     Element CopMsgId = TransInfo.addElement("dxp:CopMsgId");
     Element SenderId = TransInfo.addElement("dxp:SenderId");
     Element ReceiverIds = TransInfo.addElement("dxp:ReceiverIds");
     Element ReceiverId = ReceiverIds.addElement("dxp:ReceiverId");
     Element CreaTime = TransInfo.addElement("dxp:CreatTime");
     Element MsgType = TransInfo.addElement("dxp:MsgType");
     Data.addText(base64Decoder.encode(xml.toString().getBytes("utf-8")));
     CopMsgId.addText(String.valueOf(copMsgId) + formatter.format(date));
     SenderId.addText(copMsgId);
     if (type == 1) {
       ReceiverId.addText("DXPEDCCEB0000003");
     } else {
       ReceiverId.addText("DXPEDCCEB0000002");
     } 
     CreaTime.addText(formatter1.format(date));
     MsgType.addText("CEB" + mesg + "Message");
     return document1;
   }
   
   public static void main(String[] args) throws Exception {
     sendGet("123", 303, "123", "123", "123");
   }
 
 
   
   public static String sendGet(String xml, int mesg, String name, String pwd, String key) throws Exception {
     URL url = new URL("http://116.62.156.170:8066/wlsd/twlsd/AddOutSign.do");
 
 
 
     
     HttpURLConnection connection = (HttpURLConnection)url.openConnection();
     connection.setRequestMethod("POST");
     connection.setDoOutput(true);
     connection.setDoInput(true);
     connection.setUseCaches(false);
     connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
     connection.connect();
     DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
     byte[] bytes = ("xml=" + xml + "&name=" + name + "&pwd=" + pwd + "&key=" + key + "&mesg=" + mesg).getBytes("UTF-8");
     outputStream.write(bytes);
     outputStream.flush();
     outputStream.close();
     
     InputStreamReader is = new InputStreamReader(connection.getInputStream());
     BufferedReader br = new BufferedReader(is);
     String line = "";
     StringBuffer sb = new StringBuffer();
     while ((line = br.readLine()) != null) {
       line = new String(line.getBytes(), "UTF-8");
       sb.append(line);
     } 
     br.close();
     System.out.println(sb.toString());
     return sb.toString();
   }
 }


