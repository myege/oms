 package com.zt.kjybd;
 
 import com.what21.util.MD5Util;
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.net.URL;
 import java.net.URLConnection;
 import java.net.URLEncoder;
 import java.security.MessageDigest;
 import java.util.HashMap;
 import java.util.Map;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 public class HTOydbg
 {
   public static String htoYdbg(String bizData) throws Exception {
     String url = "http://ltbg.800best.com/api/erp/syncBillInfoWithCustomsCode";
     String parternID = "1003225";
     String key = "m3yq2y5";
 
 
     
     String signature = String.valueOf(bizData) + key;
     signature = MD5Util.EncoderByMd5(signature);
     
     String param = "parternID=" + parternID + 
       "&signature=" + URLEncoder.encode(signature) + 
       "&bizData=" + bizData + 
       "&customsCode=" + "2901" + 
       "&billMode=" + "保税";
     
     String result = PushtoWQ.sendPost2(url, param);
     return result;
   }
 
   
   public static String htogetgj(String bizData) throws Exception {
     String url = "http://edi-q9.ns.800best.com/kd/api/process";
     String partnerID = "64212";
     String key = "yf4m4k7eij7x";
     String serviceType = "KD_TRACE_QUERY";
     
     String signature = doSign(bizData, "UTF-8", key);
     Map<String, String> map = new HashMap<>();
     map.put("bizData", bizData);
     map.put("partnerID", partnerID);
     map.put("serviceType", serviceType);
     map.put("sign", signature);
     String result = sendPost(url, map);
     
     System.out.println("result------->" + result);
     return result;
   }
 
   
   public static String doSign(String bizData, String charset, String keys) {
     String sign = "";
     bizData = String.valueOf(bizData) + keys;
     try {
       MessageDigest md = MessageDigest.getInstance("MD5");
       md.update(bizData.getBytes(charset));
       byte[] b = md.digest();
       StringBuilder output = new StringBuilder(32);
       for (int i = 0; i < b.length; i++) {
         String temp = Integer.toHexString(b[i] & 0xFF);
         if (temp.length() < 2) {
           output.append("0");
         }
         output.append(temp);
       } 
       sign = output.toString();
     } catch (Exception e) {
       throw new RuntimeException(e);
     } 
     return sign;
   }
   
   public static void main(String[] args) throws Exception {
     Element requestEl = DocumentHelper.createElement("Request");
     Element mailNosEl = requestEl.addElement("mailNos");
     String mailNoStr = "50486046809116,50486046809115";
     String[] mailNoArr = mailNoStr.split(","); byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = mailNoArr).length, b = 0; b < i; ) { String mailNo = arrayOfString1[b];
       Element mailNoEl = mailNosEl.addElement("mailNo");
       mailNoEl.addText(mailNo);
 
       
       b++; }
 
     
     System.out.println(requestEl.asXML());
     
     htogetgj(requestEl.asXML());
   }
   
   public static String sendPost(String url, Map<String, String> map) throws Exception {
     StringBuffer buffer = new StringBuffer();
     StringBuffer result = new StringBuffer();
     
     URL httpUrl = new URL(url);
     
     URLConnection connection = httpUrl.openConnection();
     connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
     connection.setRequestProperty("connection", "keep-alive");
     connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
     connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
     connection.setDoOutput(true);
     connection.setDoInput(true);
     PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
     String request = "";
     if (map != null && map.size() > 0) {
       for (String str : map.keySet()) {
         buffer.append(str).append("=").append(URLEncoder.encode(map.get(str), "utf-8")).append("&");
       }
       
       request = buffer.toString().substring(0, buffer.toString().length() - 1);
     } 
     System.out.println("---------------->" + request);
     printWriter.print(request);
     printWriter.flush();
     connection.connect();
     
     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
     String line;
     while ((line = bufferedReader.readLine()) != null) {
       result.append(line);
     }
     bufferedReader.close();
     return result.toString();
   }
 }


