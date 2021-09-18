 package com.what21.service.impl;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.TinventoryDao;
 import com.what21.model.Tinventory;
 import com.what21.service.CheckInventoryService;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
import org.apache.commons.codec.binary.Base64;
 
 @Service
 public class CheckInventoryServiceImpl
   implements CheckInventoryService {
   @Autowired
   private TinventoryDao tinventoryDao;
   
   public String getInventoryNum(String jsonStr) {
     String result = "fail";
     JSONObject retJson = new JSONObject();
     
     try {
       byte[] b = Base64.decodeBase64(jsonStr);
       String param = new String(b, "utf-8");
       System.out.println(param);
       if (StringUtils.isNotEmpty(param)) {
         
         JSONObject jSONObject = JSONObject.parseObject(param);
         String merchant = jSONObject.getString("merchant");
         String itemsku = jSONObject.getString("itemsku");
         if (StringUtils.isEmpty(merchant) || StringUtils.isEmpty(itemsku)) {
           retJson.put("msg", "商家编码或sku未输入！");
           retJson.put("result", result);
           return retJson.toJSONString();
         } 
         Tinventory tinventory = new Tinventory();
         tinventory.setMerchant(merchant);
         tinventory.setItemsku(itemsku);
         Integer num = this.tinventoryDao.findSumBySku(tinventory);
         if (num == null) {
           retJson.put("msg", "没有sku为" + itemsku + " 商家编码为" + merchant + "的库存！");
           retJson.put("result", result);
           return retJson.toJSONString();
         } 
         JSONObject object = new JSONObject();
         object.put("onShelfNum", num);
         object.put("imperfectNum", Integer.valueOf(0));
         object.put("surplusNum", num);
         object.put("lockedNum", Integer.valueOf(0));
         retJson.put("msg", object.toString());
         retJson.put("result", "success");
         return retJson.toJSONString();
       } 
       retJson.put("msg", "没有接收到任何数据！");
       retJson.put("result", result);
       return retJson.toJSONString();
     
     }
     catch (Exception e) {
       e.printStackTrace();
       retJson.put("msg", "database connection fails, please check!");
       retJson.put("result", result);
       return retJson.toJSONString();
     } 
   }
 }


