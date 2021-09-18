 package com.zt.kjybd;
 
 import cn.gov.zjport.gtw.gateway.in.client.SSLClient;
 import com.what21.model.Outshipto;
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringEscapeUtils;
 import org.dom4j.Document;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
import org.apache.commons.codec.binary.Base64;
 public class BOAOh
 {
   public static void pushQd(String args) {
     SSLClient sSLClient = new SSLClient("https://openapi.zjport.gov.cn/gateway/receive");
     
     String appId = "ZJZT0001";
     String bizCode = "CEB50700";
     String bizId = "311af125-6fed-4603-8c5d-49b1fa4b4b9b";
 
     
     String content = args;
     String timestamp = String.valueOf(System.currentTimeMillis());
     String appKey = "DzZPPYVEzESip6A4Tzuo7cOcBfzzgUqLWgs5GhKp";
     String sign = sSLClient.getSign(bizCode, bizId, content, timestamp, appKey);
 
     
     Map<String, String> parameters = new HashMap<>();
     parameters.put("appId", appId);
     parameters.put("bizCode", bizCode);
     parameters.put("bizId", bizId);
     parameters.put("content", content);
     parameters.put("timestamp", timestamp);
     parameters.put("sign", sign);
     parameters.put("companyCode", "3301963H79");
     parameters.put("cusSign", "1");
     
     String result = sSLClient.post(parameters);
     
     System.out.println(result);
   }
 
 
   
   public static void installXml(List<Outshipto> findId) throws Exception {
     Date date = new Date();
     SimpleDateFormat f = new SimpleDateFormat("yyyyMMddkkmm");
     try {
       Element cebRoot = DocumentHelper.createElement("ceb:CEB507Message");
       cebRoot.addNamespace("ceb", "http://www.chinaport.gov.cn/ceb");
       
       cebRoot.addAttribute("guid", "311af125-6fed-4603-8c5d-49b1fa4b4b9b");
       cebRoot.addAttribute("version", "1.0");
       cebRoot.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
       Document document = DocumentHelper.createDocument(cebRoot);
       for (Outshipto ship : findId) {
         Element Arrival = cebRoot.addElement("ceb:Arrival");
         Element ArrivalHead = Arrival.addElement("ceb:ArrivalHead");
         ArrivalHead.addElement("ceb:guid").addText("311af125-6fed-4603-8c5d-49b1fa4b4b9b");
         ArrivalHead.addElement("ceb:appType").addText("1");
         
         ArrivalHead.addElement("ceb:appTime").addText(String.valueOf(f.format(date)) + "1");
         ArrivalHead.addElement("ceb:appStatus").addText(ship.getAppstatus());
         
         ArrivalHead.addElement("ceb:customsCode").addText(ship.getCustomscode());
         
         ArrivalHead.addElement("ceb:copNo").addText(ship.getCopno());
         ArrivalHead.addElement("ceb:operatorCode").addText(ship.getOperatorcode());
         ArrivalHead.addElement("ceb:operatorName").addText(ship.getOperatorname());
         ArrivalHead.addElement("ceb:loctNo");
         ArrivalHead.addElement("ceb:ieFlag").addText(ship.getIeflag());
         
         ArrivalHead.addElement("ceb:trafMode").addText(ship.getMsgseqno().toString());
         ArrivalHead.addElement("ceb:billNo").addText(ship.getBillno());
         ArrivalHead.addElement("ceb:domesticTrafNo").addText(ship.getDomestictrafno());
         ArrivalHead.addElement("ceb:logisticsCode").addText(ship.getLogisticscode());
         ArrivalHead.addElement("ceb:logisticsName").addText(ship.getLogisticsname());
         ArrivalHead.addElement("ceb:msgCount").addText("1");
         ArrivalHead.addElement("ceb:msgSeqNo").addText("1");
         ArrivalHead.addElement("ceb:note");
         String logisticsNo = ship.getLogisticsNo();
         int i = 1; byte b; int j; String[] arrayOfString;
         for (j = (arrayOfString = logisticsNo.split(",")).length, b = 0; b < j; ) { String str = arrayOfString[b];
           Element ArrivalList = Arrival.addElement("ceb:ArrivalList");
           ArrivalList.addElement("ceb:gnum").addText((new StringBuilder(String.valueOf(i++))).toString());
           ArrivalList.addElement("ceb:logisticsNo").addText(str);
           ArrivalList.addElement("ceb:totalPackageNo");
           ArrivalList.addElement("ceb:note"); b++; }
       
       } 
       Element BaseTransfer = cebRoot.addElement("ceb:BaseTransfer");
       BaseTransfer.addElement("ceb:copCode").addText("3301963H79");
       BaseTransfer.addElement("ceb:copName").addText("浙江曌通供应链管理有限公司");
       BaseTransfer.addElement("ceb:dxpMode").addText("DXP");
       BaseTransfer.addElement("ceb:dxpId").addText("DXPENT0000019448");
       BaseTransfer.addElement("ceb:note");
       
       URL url = new URL("http://60.190.249.117:9090/newyorkTransferWebapps/rest/transferDeclare");
       
       HttpURLConnection connection = (HttpURLConnection)url.openConnection();
       connection.setRequestMethod("POST");
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
       connection.connect();
       DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
       byte[] bytes = ("xml=" + new String(document.asXML().getBytes(), "UTF-8")).getBytes();
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
       String xml = null;
       if (sb.toString().indexOf("true") != -1) {
         xml = sb.toString().substring(23, sb.length() - 2);
       }
       String tmp = StringEscapeUtils.unescapeJavaScript(xml);
 
 
       
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
       Data.addText(Base64.encodeBase64String(tmp.toString().getBytes()));
       CopMsgId.addText("DXPENT0000019448201808021557170007");
       SenderId.addText("DXPENT0000019448");
       ReceiverId.addText("DXPDSWEXPCEB0001");
       CreaTime.addText(String.valueOf(f.format(date)) + "1");
       
       Rabbitmq.rabbitmq(document1.asXML());
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
   }
 }


