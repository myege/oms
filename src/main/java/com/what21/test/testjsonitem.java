 package com.what21.test;
 
 import com.alibaba.fastjson.JSONObject;
 import com.zt.kjybd.PushtoWQ;
 import java.net.URLEncoder;
 public class testjsonitem
 {
   public static void main(String[] args) {
     JSONObject jsonObject = new JSONObject();
     
     jsonObject.put("fromto", "vip001");
     jsonObject.put("skuCode", "SKU00212315421");
     jsonObject.put("codeTs", "20123654");
     jsonObject.put("barCode", "3120980110");
     jsonObject.put("goodsName", "测试商品");
     jsonObject.put("goodsNameEn", "test item");
     jsonObject.put("goodsDesc", "asdasdjlashd");
     jsonObject.put("goodsComposition", "成分%");
     jsonObject.put("goodsFrom", "116");
     jsonObject.put("goodsSpec", "20ml/盒");
     jsonObject.put("goodsModel", "12586");
     jsonObject.put("hsCode", "0000000001");
     jsonObject.put("goodsUnit", "001");
     jsonObject.put("firstUnit", "035");
     
     jsonObject.put("secondUnit", "011");
     jsonObject.put("highPrice", "111.33");
     jsonObject.put("lowPrice", "0.03");
     jsonObject.put("supplier", "asdasd");
     jsonObject.put("standardCountry", "116");
     jsonObject.put("brand", "品牌中文");
     jsonObject.put("brandEn", "asjdakjsdh  asdjsad asd");
     jsonObject.put("busRegion", "2916");
     
     jsonObject.put("grossWeight", "0.9");
     jsonObject.put("netWeight", "0.8");
     System.out.println(jsonObject.toJSONString());
     
     String url = "http://59.111.89.9:8066/hcerp/receiveEwtpData/CreateItem.do";
     PushtoWQ.postjson(url, URLEncoder.encode(jsonObject.toJSONString()));
   }
 }


