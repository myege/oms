 package com.what21.test;
 
 import com.alibaba.fastjson.JSONObject;
 public class jsontest
 {
   public static void main(String[] args) {
     String res = "{\"pdd_waybill_get_response\":{\"modules\":[{\"object_id\":\"1000163379\",\"waybill_code\":\"777033444568921\"}]}}";
     JSONObject ret = JSONObject.parseObject(res);
 
     
     String pdd_waybill_get_response = ret.getString("pdd_waybill_get_response");
     System.out.println("pdd_waybill_get_response=====" + pdd_waybill_get_response);
     JSONObject modules = JSONObject.parseObject(pdd_waybill_get_response);
     System.out.println("modules=====" + modules);
     JSONObject jsonObject2 = JSONObject.parseObject(modules.getJSONArray("modules").get(0).toString());
     
     System.out.println("jsonObject2=====" + jsonObject2.getString("waybill_code"));
   }
 }


