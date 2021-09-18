 package com.zt.ywbjg;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.util.MD5Util;
 import java.io.UnsupportedEncodingException;
 import org.apache.commons.codec.binary.Base64;
 public class bjgApi
 {
   public static void main(String[] args) {
     JSONObject gettoken = new JSONObject();
     gettoken.put("APP_ID", "880003");
     gettoken.put("SECRET", "AFB57D6F-B94B-4FFF-A909-AFBB344964AD");
     
     String SECRET = "AFB57D6F-B94B-4FFF-A909-AFBB344964AD";
     
     String md5ke = MD5Util.MD5S(SECRET);
     System.out.println("md5ke=" + md5ke);
     
     try {
       String bas64 = Base64.encodeBase64String(md5ke.getBytes("utf-8"));
       
       System.out.println("bas64=" + bas64);
       gettoken.put("SECRET", bas64);
     } catch (UnsupportedEncodingException e) {
       
       e.printStackTrace();
     } 
     
     System.out.println("gettoken=" + gettoken.toJSONString());
   }
 }


