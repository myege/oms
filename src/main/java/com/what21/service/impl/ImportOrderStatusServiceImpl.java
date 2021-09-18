 package com.what21.service.impl;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.what21.service.ImportOrderStatusService;
 import com.what21.service.OrderBondedService;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
import org.apache.commons.codec.binary.Base64;
 @Service
 public class ImportOrderStatusServiceImpl
   implements ImportOrderStatusService
 {
   @Autowired
   public OrderBondedService orderBondedService;
   
   public String importOrderStatus(String jsonStr) {
     JSONObject retJson = new JSONObject();
     retJson.put("txLogisticIDList", new JSONArray());
     retJson.put("result", "fail");
     try {
       if (StringUtils.isNotEmpty(jsonStr)) {
         byte[] b = Base64.decodeBase64(jsonStr);
         String param = new String(b, "utf-8");
         
         JSONObject jSONObject = JSONObject.parseObject(param);
         JSONArray orderStatusArray = jSONObject.getJSONArray("orderList");
         
         JSONArray retJSONArray = new JSONArray();
         for (int j = 0; j < orderStatusArray.size(); j++) {
           JSONObject orderJSONObject = orderStatusArray.getJSONObject(j);
           String txLogisticID = orderJSONObject.getString("txLogisticID");
           int ret = this.orderBondedService.updateIsCustomsByTxLogisticID(txLogisticID);
           if (ret != 1) {
             JSONObject retData = new JSONObject();
             retData.put("txLogisticID", txLogisticID);
             retJSONArray.add(retData);
           } 
         } 
         if (retJSONArray.size() > 0) {
           retJson.put("msg", "import order status fail");
           retJson.put("txLogisticIDList", retJSONArray);
           return retJson.toJSONString();
         } 
         retJson.put("msg", "import order status success");
         retJson.put("result", "success");
         return retJson.toJSONString();
       } 
       retJson.put("msg", "param is empty, please check!");
       return retJson.toJSONString();
     } catch (Exception e) {
       e.printStackTrace();
       retJson.put("msg", "database connection fails, please check!");
       return retJson.toJSONString();
     } 
   }
 }


