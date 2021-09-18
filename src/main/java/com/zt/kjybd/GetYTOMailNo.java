 package com.zt.kjybd;
 
 import com.what21.util.MD5Util;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.net.URL;
 import java.net.URLConnection;
 import java.net.URLEncoder;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
 
 
 public class GetYTOMailNo
 {
   public static String sendPost(String url, String param) {
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
   
   public static void main(String[] args) throws Exception {
     String testXml = "<order></order>";
     String partnerId = "123456";
     String md5Data = MD5Util.EncoderByMd5(String.valueOf(testXml) + partnerId);
     System.out.println(md5Data);
     System.out.println(URLEncoder.encode(md5Data));
   }
 }


