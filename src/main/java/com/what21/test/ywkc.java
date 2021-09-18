 package com.what21.test;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.util.MD5Util;
 import com.zt.kjybd.PushtoWQ;
 public class ywkc
 {
   public static void main(String[] args) {
     String SECRET = "c0f8f3ee-adfa-4a33-aca6-7fcce3d1ad5e";
     SECRET = MD5Util.MD5(SECRET);
     
     JSONObject order = new JSONObject();
     order.put("msgid", "1693562581964243912");
     order.put("copNo", "YWKC20210408001");
     order.put("invtNo", "29232021I292793820");
     order.put("returnInfo", "[Code:2600;Desc:放行]");
     order.put("code", "88888");
 
     
     JSONObject abc = new JSONObject();
     abc.put("APP_ID", "880140");
     abc.put("SECRET", SECRET);
     abc.put("REQUEST_DATA", order);
 
     
     System.out.println("abc=" + abc.toJSONString());
     String url = "http://storage.kjruyigou.com/api/ewtpRequest.php";
     String ret = PushtoWQ.sendPostewtpkc(url, abc.toJSONString());
     System.out.println("凯昌订单下发回执" + ret);
   }
 }


