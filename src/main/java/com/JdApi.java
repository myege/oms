 package com;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.tools.Tools;
 import com.what21.util.MD5Util;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.net.URL;
 import java.net.URLConnection;
 import java.util.Date;
 public class JdApi
 {
   public static void main(String[] args) throws Exception {
     JSONObject jsonObject = new JSONObject();
     jsonObject.put("customsId", "hangzhou");
     jsonObject.put("serviceId", "020045");
     jsonObject.put("orderId", Integer.valueOf(321321));
     jsonObject.put("result", Integer.valueOf(2));
     jsonObject.put("message", "清关成功");
     jsonObject.put("goodsCheck", Integer.valueOf(2));
     
     sendPost("https://api.jd.com/routerjson?", "jingdong.pop.customs.center.OrderYnJsfService.getOrderYnJsfResult", "3B03D933A76CF26E84CD449B63FEE94F", "b82343345a7a4daab1abcd446b278215zmzq", "addcd05bfe384d0483026fbe0150622c", jsonObject.toJSONString());
   }
   
   public static String sendPost(String url, String methodName, String appKey, String accessToken, String app_secret, String param) throws Exception {
     StringBuffer sign = new StringBuffer();
     
     sign.append(app_secret).append("access_token").append(accessToken).append("app_key").append(appKey).append("method").append(methodName).append("timestamp").append(Tools.format("yyyy-MM-ddHH:mm:ss", new Date())).append("v2.0").append(app_secret);
     String signMd = MD5Util.MD5S(sign.toString()).toUpperCase();
     System.out.println(signMd);
     StringBuffer sb = new StringBuffer();
     sb.append("v=2.0").append("&method=").append(methodName).append("&app_key=").append(appKey).append("&360buy_param_json=").append(param).append("&timestamp=").append(Tools.format("yyyy-MM-ddHH:mm:ss", new Date())).append("&sign=").append(signMd);
     System.out.println(sb.toString());
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
       String str = new String(sb.toString().getBytes(), "UTF-8");
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
 }


