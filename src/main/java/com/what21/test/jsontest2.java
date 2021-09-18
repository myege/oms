 package com.what21.test;
 
 import com.alibaba.fastjson.JSONObject;
 public class jsontest2
 {
   public static void main(String[] args) {
     JSONObject jsonObject = new JSONObject();
     
     jsonObject.put("orderNo", "L2923B18A027");
     jsonObject.put("logisticsNo", "");
     jsonObject.put("status", "9");
     jsonObject.put("msg", "成功");
 
 
     
     String res = "{\"success\":true,\"code\":\"200\",\"message\":\"成功\",\"time\":1594705086944,\"data\":{\"guid\":\"729C60B9-52EB-4EAF-9ED2-33FCA4DC9E5A\"}}";
     JSONObject ret = JSONObject.parseObject(res);
 
 
     
     String code = ret.getString("code");
     
     String data = ret.getString("data");
     System.out.println("code=====" + code);
     System.out.println("data=====" + data);
     if (code.equals("200")) {
       JSONObject datas = JSONObject.parseObject(data);
       String guid = datas.getString("guid");
       System.out.println("guid=====" + guid);
     } 
   }
 }


