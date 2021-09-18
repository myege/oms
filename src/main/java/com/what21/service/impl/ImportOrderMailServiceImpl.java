 package com.what21.service.impl;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.OrderMailDao;
 import com.what21.dao.OrderMailSkuDao;
 import com.what21.dao.UserDao;
 import com.what21.model.OrderMail;
 import com.what21.model.OrderMailSku;
 import com.what21.model.Users;
 import com.what21.service.ImportOrderMailService;
 import com.what21.util.StringUtil;
 import java.math.BigDecimal;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
import org.apache.commons.codec.binary.Base64;
 @Service
 public class ImportOrderMailServiceImpl
   implements ImportOrderMailService
 {
   @Autowired
   private OrderMailDao orderMailDao;
   @Autowired
   private OrderMailSkuDao orderMailSkuDao;
   @Autowired
   private UserDao userDao;
   
   public synchronized String importOrder(String jsonStr) {
     String result = "fail";
     JSONObject retJson = new JSONObject();
     
     try {
       byte[] b = Base64.decodeBase64(jsonStr);
       String param = new String(b, "utf-8");
       if (StringUtils.isNotEmpty(param)) {
         
         JSONObject jSONObject = JSONObject.parseObject(param);
         String userName = jSONObject.getString("userName");
         String password = jSONObject.getString("password");
         Users user = new Users();
         user.setUserName(userName);
         user.setPassword(password);
         Users retUser = this.userDao.findUser(user);
         if (retUser != null) {
           JSONArray orderJSONArray = jSONObject.getJSONArray("orderList");
           for (int i = 0; i < orderJSONArray.size(); i++) {
             JSONObject orderJSONObject = orderJSONArray.getJSONObject(i);
             if (StringUtil.isEmpty(orderJSONObject.getString("txLogisticID")).booleanValue()) {
               retJson.put("msg", "txLogisticID is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("carrier")).booleanValue()) {
               retJson.put("msg", "carrier is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("mailNo")).booleanValue()) {
               retJson.put("msg", "mailNo is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("receiveMan")).booleanValue()) {
               retJson.put("msg", "receiveMan is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("buyerName")).booleanValue()) {
               retJson.put("msg", "buyerName is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("buyerIdNumber")).booleanValue()) {
               retJson.put("msg", "buyerIdNumber is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("receiveProvince")).booleanValue()) {
               retJson.put("msg", "receiveProvince is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("receiveCity")).booleanValue()) {
               retJson.put("msg", "receiveCity is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("receiveCounty")).booleanValue()) {
               retJson.put("msg", "receiveCounty is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("receiveManAddress")).booleanValue()) {
               retJson.put("msg", "receiveManAddress is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("receiveCode")).booleanValue()) {
               retJson.put("msg", "receiveCode is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("receiveCountry")).booleanValue()) {
               retJson.put("msg", "receiveCountry is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("receiveManPhone")).booleanValue()) {
               retJson.put("msg", "receiveManPhone is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             
             if (StringUtil.isEmpty(orderJSONObject.getString("tradeCountry")).booleanValue()) {
               retJson.put("msg", "tradeCountry is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("skuList")).booleanValue()) {
               retJson.put("msg", "skuList is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
           } 
           StringBuffer sb = new StringBuffer();
           for (int j = 0; j < orderJSONArray.size(); j++) {
             JSONObject orderJSONObject = orderJSONArray.getJSONObject(j);
             String txLogisticID = orderJSONObject.getString("txLogisticID");
             
             OrderMail isData = null;
             isData = this.orderMailDao.findByTxLogisticID(txLogisticID);
             
             if (sb.toString().contains(txLogisticID)) {
               break;
             }
             sb.append(String.valueOf(txLogisticID) + ",");
             if (isData == null) {
               OrderMail orderMail = new OrderMail();
               orderMail.setTxLogisticID(txLogisticID);
               orderMail.setCarrier(orderJSONObject.getString("carrier").trim());
               orderMail.setMailNo(orderJSONObject.getString("mailNo").trim());
               orderMail.setReceiveMan(orderJSONObject.getString("receiveMan").trim());
               orderMail.setBuyerName(orderJSONObject.getString("buyerName").trim());
               orderMail.setBuyerIdNumber(orderJSONObject.getString("buyerIdNumber").trim());
               orderMail.setReceiveProvince(orderJSONObject.getString("receiveProvince").trim());
               orderMail.setReceiveCity(orderJSONObject.getString("receiveCity").trim());
               orderMail.setReceiveCounty(orderJSONObject.getString("receiveCounty").trim());
               orderMail.setReceiveManAddress(orderJSONObject.getString("receiveManAddress").trim());
               orderMail.setReceivecode(orderJSONObject.getString("receiveCode").trim());
               orderMail.setReceiveCountr(orderJSONObject.getString("receiveCountry").trim());
               orderMail.setReceiveManPhone(orderJSONObject.getString("receiveManPhone").trim());
               orderMail.setTotalMailNo(orderJSONObject.getString("totalMailNo").trim());
               orderMail.setFightNumber(orderJSONObject.getString("fightNumber").trim());
               orderMail.setTradeCountry(orderJSONObject.getString("tradeCountry").trim());
               
               JSONArray skuList = orderJSONObject.getJSONArray("skuList");
               BigDecimal wegiht = new BigDecimal(0);
               BigDecimal allPrice = new BigDecimal(0);
               BigDecimal itemcount = new BigDecimal(0);
               
               for (int k = 0; k < skuList.size(); k++) {
                 JSONObject sku = skuList.getJSONObject(k);
                 
                 if (StringUtil.isEmpty(sku.getString("itemWeight")).booleanValue()) {
                   retJson.put("msg", "itemWeight is empty, please check!");
                   retJson.put("result", result);
                   return retJson.toJSONString();
                 } 
                 if (StringUtil.isEmpty(sku.getString("itemName")).booleanValue()) {
                   retJson.put("msg", "itemName is empty, please check!");
                   retJson.put("result", result);
                   return retJson.toJSONString();
                 } 
                 if (StringUtil.isEmpty(sku.getString("unitPrice")).booleanValue()) {
                   retJson.put("msg", "unitPrice is empty, please check!");
                   retJson.put("result", result);
                   return retJson.toJSONString();
                 } 
                 if (StringUtil.isEmpty(sku.getString("itemCount")).booleanValue()) {
                   retJson.put("msg", "itemCount is empty, please check!");
                   retJson.put("result", result);
                   return retJson.toJSONString();
                 } 
                 if (StringUtil.isEmpty(sku.getString("allPrice")).booleanValue()) {
                   retJson.put("msg", "allPrice is empty, please check!");
                   retJson.put("result", result);
                   return retJson.toJSONString();
                 } 
                 if (StringUtil.isEmpty(sku.getString("standard")).booleanValue()) {
                   retJson.put("msg", "standard is empty, please check!");
                   retJson.put("result", result);
                   return retJson.toJSONString();
                 } 
                 if (StringUtil.isEmpty(sku.getString("unitName")).booleanValue()) {
                   retJson.put("msg", "unitName is empty, please check!");
                   retJson.put("result", result);
                   return retJson.toJSONString();
                 } 
                 if (StringUtil.isEmpty(sku.getString("hs")).booleanValue()) {
                   retJson.put("msg", "hs is empty, please check!");
                   retJson.put("result", result);
                   return retJson.toJSONString();
                 } 
                 
                 OrderMailSku orderMailSku = new OrderMailSku();
                 orderMailSku.setTxLogisticID(txLogisticID);
                 orderMailSku.setItemWeight(sku.getDouble("itemWeight"));
                 orderMailSku.setItemName(sku.getString("itemName").trim());
                 orderMailSku.setUnitPrice(sku.getDouble("unitPrice"));
                 orderMailSku.setItemCount(sku.getInteger("itemCount"));
                 orderMailSku.setAllPrice(sku.getDouble("allPrice"));
                 orderMailSku.setStandard(sku.getString("country"));
                 orderMailSku.setUnitname(sku.getString("unitName"));
                 orderMailSku.setItemsku("ZY" + sku.getString("hs").trim());
                 orderMailSku.setHz(sku.getString("hs").trim());
                 
                 orderMailSku.setUrl(sku.getString("url").trim());
                 orderMailSku.setBatchNumber(String.format("%05d", new Object[] { Integer.valueOf(k + 1) }));
                 
                 wegiht = (new BigDecimal(sku.getString("itemWeight"))).add(wegiht);
                 allPrice = (new BigDecimal(sku.getString("allPrice"))).add(allPrice);
                 itemcount = (new BigDecimal(sku.getString("itemCount"))).add(itemcount);
                 
                 this.orderMailSkuDao.insert(orderMailSku);
               } 
               orderMail.setItemCount(Integer.valueOf(itemcount.intValue()));
               orderMail.setItemWeight(Double.valueOf(wegiht.doubleValue()));
               orderMail.setWorth(Double.valueOf(allPrice.doubleValue()));
               orderMail.setDestinationPort(orderJSONObject.getString("tradeCountry").trim());
               orderMail.setUserId(retUser.getId());
               orderMail.setIspost(0);
               orderMail.setIsCustoms(0);
               orderMail.setAuditstatus(0);
               this.orderMailDao.insert(orderMail);
             } 
           } 
           retJson.put("msg", "import order success");
           retJson.put("result", "success");
           return retJson.toJSONString();
         } 
         retJson.put("msg", "username or password is error, please check!");
         retJson.put("result", result);
         return retJson.toJSONString();
       } 
       
       retJson.put("msg", "param is empty, please check!");
       retJson.put("result", result);
       return retJson.toJSONString();
     } catch (Exception e) {
       e.printStackTrace();
       retJson.put("msg", "database connection fails, please check!");
       retJson.put("result", result);
       return retJson.toJSONString();
     } 
   }
 }


