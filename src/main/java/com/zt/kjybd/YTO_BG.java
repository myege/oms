 package com.zt.kjybd;
 
 import com.what21.util.DESUtil;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.net.URL;
 import java.net.URLConnection;
import org.apache.commons.codec.binary.Base64;
 public class YTO_BG
 {
   public static String toZJyd(String url, String param) {
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
   
   public static void main(String[] args) throws Exception {
     System.out.println(DESUtil.encrypt("INTERFACESTANDARDENCRYPTKEY2014", "123456"));
     byte[] b = Base64.decodeBase64("Z1JsTVdmV3lpTU44S2VGQkZuWlBldEYwMmVEVHdnZTlaL2NCQzBEdXU4cz0=");
     DESUtil.decrypt("INTERFACESTANDARDENCRYPTKEY2014", new String(b));
     System.out.println(Base64.encodeBase64String("rYiqQCqnl8TPpQGjcwL8pQ==".getBytes("utf-8")));
   }
 }


