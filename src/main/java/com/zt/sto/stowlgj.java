 package com.zt.sto;
 
 import com.what21.util.MD5Util;
 import com.what21.util.timenew;
 import com.zt.kjybd.PushtoWQ;
 import java.net.URLEncoder;
 public class stowlgj
 {
   public static void main(String[] args) {
     String json = "{\"trackingNumber\":\"BH2019113254\"}";
     
     String appToken = "TESTC78C-7923-404C-82CF-CD881539123C";
     String serviceMethod = "searchtrack";
     String paramsJson = json;
     String signature = "";
     String endTime = timenew.newtime4();
     
     String timestamp = (new StringBuilder(String.valueOf((int)(System.currentTimeMillis() / 1000L)))).toString();
     String mdStr = String.valueOf(timestamp) + appToken + paramsJson;
     System.out.println("mdStr=" + mdStr);
     signature = MD5Util.MD5S_forbm(mdStr, "utf-8");
     System.out.println("signature=" + signature);
     String url3 = "http://ldp.uat.stosolution.com/api/service/entrance";
     String parm3 = "appToken=" + appToken + "&serviceMethod=" + serviceMethod + "&paramsJson=" + URLEncoder.encode(paramsJson) + "&signature=" + signature + "&timestamp=" + timestamp;
     System.out.println("parm3=" + parm3);
     String resout3 = PushtoWQ.sendPost2(url3, parm3);
     System.out.println("resout3=" + resout3);
   }
 }


