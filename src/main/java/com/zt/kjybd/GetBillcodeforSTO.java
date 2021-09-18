 package com.zt.kjybd;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.net.URL;
 import java.net.URLConnection;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
 public class GetBillcodeforSTO
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
       conn.setRequestProperty("user-agent", 
           "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
 
 
 
       
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
 
 
 
   
   public static void main(String[] args) {
     String url2 = "http://183.129.231.82:8066/ecm/interface/rest/tendInterface/getbill";
     String param2 = "Sign=0888A1AFB593666C658BF5C315B3E3F3&User=ST&Password=123456&len=1";
 
     
     String resout = sendPost(url2, param2);
     
     resout = resout.replaceAll("\\\\\"", "");
     String[] str = resout.replaceAll("}", "").split("-");
   }
 }


