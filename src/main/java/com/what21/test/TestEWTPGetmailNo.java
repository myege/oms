 package com.what21.test;
 
 import com.alibaba.fastjson.JSONObject;
 import com.zt.kjybd.PushtoWQ;
 import java.net.URLEncoder;
 public class TestEWTPGetmailNo
 {
   public static void main(String[] args) {
     String orderNo = "1320293450836076666";
     JSONObject orderJson = new JSONObject();
     orderJson.put("fromto", "vip001");
     orderJson.put("orderNo", orderNo);
 
 
     
     String parm = orderJson.toJSONString();
     String url = "http://59.111.89.9:8066/hcerp/receiveEwtpData/GetMailno.do";
     String resout3 = PushtoWQ.sendPost2(url, URLEncoder.encode(parm));
   }
 }


