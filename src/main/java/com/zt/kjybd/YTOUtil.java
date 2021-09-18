 package com.zt.kjybd;
 
 import com.what21.tools.Tools;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.net.URL;
 import java.net.URLConnection;
 import java.security.MessageDigest;
 import java.util.Date;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import org.dom4j.Document;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 public class YTOUtil
 {
   public static String checkOrder(String url, String param) throws Exception {
     PrintWriter out = null;
     BufferedReader in = null;
     String result = "";
     try {
       URL realUrl = new URL(url);
       URLConnection conn = realUrl.openConnection();
       conn.setRequestProperty("accept", "*/*");
       conn.setRequestProperty("connection", "Keep-Alive");
       conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
       
       conn.setDoOutput(true);
       conn.setDoInput(true);
       out = new PrintWriter(conn.getOutputStream());
       String str = new String(param.getBytes(), "UTF-8");
       out.print(str);
       out.flush();
       in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
       
       String line;
       while ((line = in.readLine()) != null) {
         result = String.valueOf(result) + line;
       }
       System.out.println(result);
     } catch (Exception e) {
       e.printStackTrace();
     } finally {
       
       try {
         if (out != null) {
           out.close();
         }
         if (in != null) {
           in.close();
         }
       }
       catch (IOException ex) {
         ex.printStackTrace();
       } 
     } 
     return result;
   }
 
 
   
   public static String replaceBlank(String str) {
     String dest = "";
     if (str != null) {
       Pattern p = Pattern.compile("\\s*|\t|\r|\n");
       Matcher m = p.matcher(str);
       dest = m.replaceAll("");
     } 
     return dest;
   }
 
   
   public static String queryByMailno(String mailno) throws Exception {
     String url = "http://MarketingInterface.yto.net.cn";
     
     String sign = null;
     String app_key = "8ONYOb";
     String method = "yto.Marketing.WaybillTrace";
     String timestamp = Tools.format("yyyy-MM-dd HH:mm:ss", new Date());
     String user_id = "HZLCXS";
     
     String param = null;
     String secret_key = "MXRA5U";
     
     String end = "app_key" + app_key + "formatXMLmethod" + method + "timestamp" + timestamp + "user_id" + user_id + "v1.01";
     end = String.valueOf(secret_key) + end;
     
     MessageDigest md = MessageDigest.getInstance("MD5");
     md.update(end.getBytes());
     byte[] b = md.digest();
     
     StringBuffer buf = new StringBuffer("");
     for (int offset = 0; offset < b.length; offset++) {
       int i = b[offset];
       if (i < 0)
         i += 256; 
       if (i < 16)
         buf.append("0"); 
       buf.append(Integer.toHexString(i));
     } 
     end = buf.toString();
     sign = end.toUpperCase();
     
     Element element1 = DocumentHelper.createElement("ufinterface");
     Document document = DocumentHelper.createDocument(element1);
     Element element2 = element1.addElement("Result");
     Element element3 = element2.addElement("WaybillCode");
     element3.addElement("Number").addText(mailno);
     param = document.asXML();
     
     String data = "sign=" + sign + "&app_key=" + app_key + "&format=XML&method=" + method + "&timestamp=" + timestamp + "&user_id=" + user_id + "&v=1.01&param=" + param;
     
     return checkOrder(url, data);
   }
 }


