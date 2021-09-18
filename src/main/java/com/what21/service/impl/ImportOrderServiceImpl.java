 package com.what21.service.impl;
 
 import cn.gov.zjport.manchester.encrypt.AESEncrypt;
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.MergerDao;
 import com.what21.dao.OrderBondedDao;
 import com.what21.dao.OrderBondedRuleDao;
 import com.what21.dao.OrderBondedSkuDao;
 import com.what21.dao.StockDeleteDao;
 import com.what21.dao.TinventoryDao;
 import com.what21.dao.UserDao;
 import com.what21.model.Merger;
 import com.what21.model.OrderBonded;
 import com.what21.model.OrderBondedPushSku;
 import com.what21.model.OrderBondedRule;
 import com.what21.model.OrderBondedSku;
 import com.what21.model.StockDelete;
 import com.what21.model.Tinventory;
 import com.what21.model.Users;
 import com.what21.service.ImportOrderService;
 import com.what21.tools.Tools;
 import com.what21.util.StringUtil;
 import com.zt.kjybd.HTOydbg;
 import com.zt.kjybd.stocx;
 import java.math.BigDecimal;
 import java.net.URLEncoder;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import javax.jws.WebParam;
 import org.apache.commons.lang.StringUtils;
 import org.dom4j.Document;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
import org.apache.commons.codec.binary.Base64;
 @Service
 public class ImportOrderServiceImpl
   implements ImportOrderService
 {
   @Autowired
   private OrderBondedDao orderBondedDao;
   @Autowired
   private OrderBondedSkuDao orderBondedSkuDao;
   @Autowired
   private UserDao userDao;
   @Autowired
   private MergerDao mergerDao;
   @Autowired
   private StockDeleteDao stockDeleteDao;
   @Autowired
   private TinventoryDao tinventoryDao;
   @Autowired
   private OrderBondedRuleDao orderBondedRuleDao;
   
   public String importOrderByJsonString(String jsonStr) {
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
         Map<String, OrderBonded> orderBondedMap = new HashMap<>();
         Map<String, OrderBondedSku> orderBondedSkuMap = new HashMap<>();
         List<OrderBondedSku> obsList = new ArrayList<>();
         if (retUser != null) {
           JSONArray orderJSONArray = jSONObject.getJSONArray("orderList");
           for (int i = 0; i < orderJSONArray.size(); i++) {
             JSONObject orderJSONObject = orderJSONArray.getJSONObject(i);
             String txLogisticID = orderJSONObject.getString("txLogisticID").trim();
             if (StringUtil.isEmpty(txLogisticID).booleanValue()) {
               retJson.put("msg", "txLogisticID is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("receiveMan")).booleanValue()) {
               retJson.put("msg", "receiveMan is empty, please check!");
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
             if (StringUtil.isEmpty(orderJSONObject.getString("receiveManPhone")).booleanValue()) {
               retJson.put("msg", "receiveManPhone is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("itemName")).booleanValue()) {
               retJson.put("msg", "itemName is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("itemSku")).booleanValue()) {
               retJson.put("msg", "itemSku is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("itemCount")).booleanValue()) {
               retJson.put("msg", "itemCount is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("unitPrice")).booleanValue()) {
               retJson.put("msg", "unitPrice is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("allPrice")).booleanValue()) {
               retJson.put("msg", "allPrice is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("itemWeight")).booleanValue()) {
               retJson.put("msg", "itemWeight is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("feeAmount")).booleanValue()) {
               retJson.put("msg", "feeAmount is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("insureAmount")).booleanValue()) {
               retJson.put("msg", "insureAmount is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("buyerIdNumber")).booleanValue()) {
               retJson.put("msg", "buyerIdNumber is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             String buyerID = orderJSONObject.getString("buyerIdNumber").trim();
             
             if (StringUtil.isEmpty(orderJSONObject.getString("buyerName")).booleanValue()) {
               retJson.put("msg", "buyerName is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("carrier")).booleanValue()) {
               retJson.put("msg", "carrier is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("sendWarehouse")).booleanValue()) {
               retJson.put("msg", "sendWarehouse is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("merchantNum")).booleanValue()) {
               retJson.put("msg", "merchantNum is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             
             BigDecimal itemCount = new BigDecimal(orderJSONObject.getString("itemCount"));
             BigDecimal unitPrice = new BigDecimal(orderJSONObject.getString("unitPrice"));
             BigDecimal allPrice = new BigDecimal(orderJSONObject.getString("allPrice"));
             BigDecimal price = unitPrice.multiply(itemCount);
             
             if (!allPrice.equals(price)) {
               retJson.put("msg", "txLogisticID(" + txLogisticID + "): itemCount * unitPrice != allPrice, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             
             OrderBonded orderBonded = new OrderBonded();
             orderBonded.setMark(Integer.valueOf(0));
             orderBonded.setTxLogisticID(txLogisticID);
             orderBonded.setReceiveMan(orderJSONObject.getString("receiveMan").trim());
             orderBonded.setReceiveProvince(orderJSONObject.getString("receiveProvince").trim());
             orderBonded.setReceiveCity(orderJSONObject.getString("receiveCity").trim());
             orderBonded.setReceiveCounty(orderJSONObject.getString("receiveCounty").trim());
             orderBonded.setReceiveManAddress(orderJSONObject.getString("receiveManAddress").trim());
             orderBonded.setReceiveManPhone(orderJSONObject.getString("receiveManPhone").trim());
             orderBonded.setItemCount(orderJSONObject.getInteger("itemCount"));
             orderBonded.setWorth(orderJSONObject.getDouble("allPrice"));
             orderBonded.setItemName(orderJSONObject.getString("itemName").trim());
             orderBonded.setItemWeight(orderJSONObject.getDouble("itemWeight"));
             orderBonded.setFeeAmount(orderJSONObject.getDouble("feeAmount"));
             orderBonded.setInsureAmount(orderJSONObject.getDouble("insureAmount"));
             orderBonded.setBuyerIdNumber(orderJSONObject.getString("buyerIdNumber").trim());
             orderBonded.setBuyerName(orderJSONObject.getString("buyerName").trim());
             orderBonded.setCarrier(orderJSONObject.getString("carrier").trim());
             orderBonded.setSendWarehouse(orderJSONObject.getString("sendWarehouse").trim());
             orderBonded.setMerchantNum(orderJSONObject.getString("merchantNum").trim());
             String mailNo = orderJSONObject.getString("mailNo").trim();
             if (!"0".equals(mailNo) && StringUtil.isNotEmpty(mailNo).booleanValue()) {
               orderBonded.setMailNo(mailNo);
             }
             orderBonded.setPc(orderJSONObject.getString("pc").trim());
             orderBonded.setUserId(retUser.getId());
             orderBonded.setIspost(0);
             orderBonded.setIsCustoms(0);
             orderBonded.setAuditstatus(0);
             
             OrderBonded ob = orderBondedMap.get(txLogisticID);
             if (ob != null) {
               ob.setItemCount(Integer.valueOf(ob.getItemCount().intValue() + Integer.parseInt(orderJSONObject.getString("itemCount").trim())));
               ob.setWorth(Double.valueOf(ob.getWorth().doubleValue() + Double.valueOf(orderJSONObject.getString("allPrice").trim()).doubleValue()));
               ob.setItemWeight(Double.valueOf(ob.getItemWeight().doubleValue() + Double.valueOf(orderJSONObject.getString("itemWeight").trim()).doubleValue()));
               
               if (ob.getWorth().doubleValue() >= 2000.0D) {
                 ob.setAuditstatus(7);
               }
               double weight = ob.getItemWeight().doubleValue();
               OrderBondedRule orderBondedRule = new OrderBondedRule();
               orderBondedRule.setCarrier(ob.getCarrier());
               orderBondedRule.setProvince(ob.getReceiveProvince());
               orderBondedRule = this.orderBondedRuleDao.findByProvinceAndCarrier(orderBondedRule);
               if (orderBondedRule != null) {
                 if (orderBondedRule.getWeight().doubleValue() == 0.0D) {
                   ob.setAuditstatus(7);
                 }
                 else if (weight > orderBondedRule.getWeight().doubleValue()) {
                   ob.setAuditstatus(7);
                 } 
               }
               
               orderBondedMap.put(txLogisticID, ob);
             } else {
               orderBonded.setItemCount(Integer.valueOf(Integer.parseInt(orderJSONObject.getString("itemCount").trim())));
               orderBonded.setWorth(Double.valueOf(orderJSONObject.getString("allPrice").trim()));
               orderBonded.setItemWeight(Double.valueOf(orderJSONObject.getString("itemWeight").trim()));
               
               if (orderBonded.getWorth().doubleValue() >= 2000.0D) {
                 orderBonded.setAuditstatus(7);
               }
               double weight = orderBonded.getItemWeight().doubleValue();
               OrderBondedRule orderBondedRule = new OrderBondedRule();
               orderBondedRule.setCarrier(orderBonded.getCarrier());
               orderBondedRule.setProvince(orderBonded.getReceiveProvince());
               orderBondedRule = this.orderBondedRuleDao.findByProvinceAndCarrier(orderBondedRule);
               if (orderBondedRule != null) {
                 if (orderBondedRule.getWeight().doubleValue() == 0.0D) {
                   orderBonded.setAuditstatus(7);
                 }
                 else if (weight > orderBondedRule.getWeight().doubleValue()) {
                   orderBonded.setAuditstatus(7);
                 } 
               }
               
               orderBondedMap.put(txLogisticID, orderBonded);
             } 
             
             OrderBondedSku orderBondedSku = new OrderBondedSku();
             orderBondedSku.setTxLogisticID(txLogisticID);
             orderBondedSku.setItemCount(orderJSONObject.getInteger("itemCount"));
             orderBondedSku.setAllPrice(orderJSONObject.getDouble("allPrice"));
             orderBondedSku.setItemName(orderJSONObject.getString("itemName").trim());
             orderBondedSku.setItemWeight(orderJSONObject.getDouble("itemWeight"));
             orderBondedSku.setUnitPrice(orderJSONObject.getDouble("unitPrice"));
             orderBondedSku.setItemsku(orderJSONObject.getString("itemSku").trim());
             OrderBondedSku obs = orderBondedSkuMap.get(String.valueOf(txLogisticID) + orderJSONObject.getString("itemSku").trim());
             if (obs != null) {
               retJson.put("msg", "txLogisticID(" + txLogisticID + "): txLogisticID + itemSku can't be the same, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             orderBondedSkuMap.put(String.valueOf(txLogisticID) + orderJSONObject.getString("itemSku").trim(), orderBondedSku);
             obsList.add(orderBondedSku);
           } 
           
           Object[] obArr = orderBondedMap.values().toArray();
           List<OrderBonded> retList = this.orderBondedDao.findByObArr(obArr);
           if (retList != null && retList.size() > 0) {
             String retTxLogisticID = "";
             for (OrderBonded retOb : retList) {
               retTxLogisticID = String.valueOf(retTxLogisticID) + retOb.getTxLogisticID() + ",";
             }
             retTxLogisticID = retTxLogisticID.substring(0, retTxLogisticID.length() - 1);
             retJson.put("msg", "txLogisticID:(" + retTxLogisticID + ") already is exist, please check!");
             retJson.put("result", result);
             return retJson.toJSONString();
           } 
           this.orderBondedDao.batchInsert(obArr);
           this.orderBondedSkuDao.batchInsert(obsList);
           
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
 
   
   public String importOrder(String jsonStr) {
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
         Map<String, OrderBonded> orderBondedMap = new HashMap<>();
         Map<String, OrderBondedSku> orderBondedSkuMap = new HashMap<>();
         List<OrderBondedSku> obsList = new ArrayList<>();
         if (retUser != null) {
           JSONArray orderJSONArray = jSONObject.getJSONArray("orderList");
           if (orderJSONArray == null) {
             retJson.put("msg", "orderList is null, please check!");
             retJson.put("result", result);
             return retJson.toJSONString();
           } 
           for (int i = 0; i < orderJSONArray.size(); i++) {
             OrderBonded orderBonded = new OrderBonded();
             orderBonded.setMark(Integer.valueOf(0));
             JSONObject orderJSONObject = orderJSONArray.getJSONObject(i);
             String txLogisticID = orderJSONObject.getString("txLogisticID").trim();
             if (StringUtil.isEmpty(txLogisticID).booleanValue()) {
               retJson.put("msg", "txLogisticID is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("receiveMan")).booleanValue()) {
               retJson.put("msg", "receiveMan is empty, please check!");
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
             if (StringUtil.isEmpty(orderJSONObject.getString("receiveManPhone")).booleanValue()) {
               retJson.put("msg", "receiveManPhone is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("feeAmount")).booleanValue()) {
               retJson.put("msg", "feeAmount is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("insureAmount")).booleanValue()) {
               retJson.put("msg", "insureAmount is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("buyerIdNumber")).booleanValue()) {
               retJson.put("msg", "buyerIdNumber is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             String buyerID = orderJSONObject.getString("buyerIdNumber").trim();
             
             if (StringUtil.isEmpty(orderJSONObject.getString("buyerName")).booleanValue()) {
               retJson.put("msg", "buyerName is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("carrier")).booleanValue()) {
               retJson.put("msg", "carrier is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("sendWarehouse")).booleanValue()) {
               retJson.put("msg", "sendWarehouse is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("merchantNum")).booleanValue()) {
               retJson.put("msg", "merchantNum is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             JSONArray skuJSONArray = orderJSONObject.getJSONArray("skuList");
             if (skuJSONArray == null) {
               retJson.put("msg", "skuList is null, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             BigDecimal count = new BigDecimal("0");
             BigDecimal price = new BigDecimal("0");
             BigDecimal weight = new BigDecimal("0");
             for (int j = 0; j < skuJSONArray.size(); j++) {
               JSONObject skuJSONObject = skuJSONArray.getJSONObject(j);
               if (StringUtil.isEmpty(skuJSONObject.getString("itemName")).booleanValue()) {
                 retJson.put("msg", "itemName is empty, please check!");
                 retJson.put("result", result);
                 return retJson.toJSONString();
               } 
               if (StringUtil.isEmpty(skuJSONObject.getString("itemSku")).booleanValue()) {
                 retJson.put("msg", "itemSku is empty, please check!");
                 retJson.put("result", result);
                 return retJson.toJSONString();
               } 
               if (StringUtil.isEmpty(skuJSONObject.getString("itemCount")).booleanValue()) {
                 retJson.put("msg", "itemCount is empty, please check!");
                 retJson.put("result", result);
                 return retJson.toJSONString();
               } 
               if (StringUtil.isEmpty(skuJSONObject.getString("unitPrice")).booleanValue()) {
                 retJson.put("msg", "unitPrice is empty, please check!");
                 retJson.put("result", result);
                 return retJson.toJSONString();
               } 
               if (StringUtil.isEmpty(skuJSONObject.getString("allPrice")).booleanValue()) {
                 retJson.put("msg", "allPrice is empty, please check!");
                 retJson.put("result", result);
                 return retJson.toJSONString();
               } 
               if (StringUtil.isEmpty(skuJSONObject.getString("itemWeight")).booleanValue()) {
                 retJson.put("msg", "itemWeight is empty, please check!");
                 retJson.put("result", result);
                 return retJson.toJSONString();
               } 
               BigDecimal itemWeight = new BigDecimal(skuJSONObject.getString("itemWeight"));
               BigDecimal itemCount = new BigDecimal(skuJSONObject.getString("itemCount"));
               BigDecimal unitPrice = new BigDecimal(skuJSONObject.getString("unitPrice"));
               BigDecimal allPrice = new BigDecimal(skuJSONObject.getString("allPrice"));
               BigDecimal operPrice = unitPrice.multiply(itemCount);
               
               if (!allPrice.equals(operPrice)) {
                 retJson.put("msg", "txLogisticID(" + txLogisticID + "): itemCount * unitPrice != allPrice, please check!");
                 retJson.put("result", result);
                 return retJson.toJSONString();
               } 
 
               
               count = count.add(itemCount);
               price = price.add(allPrice);
               weight = weight.add(itemWeight);
               orderBonded.setItemName(skuJSONObject.getString("itemName").trim());
 
               
               OrderBondedSku orderBondedSku = new OrderBondedSku();
               orderBondedSku.setTxLogisticID(txLogisticID);
               orderBondedSku.setItemCount(skuJSONObject.getInteger("itemCount"));
               orderBondedSku.setAllPrice(skuJSONObject.getDouble("allPrice"));
               orderBondedSku.setItemName(skuJSONObject.getString("itemName").trim());
               orderBondedSku.setItemWeight(skuJSONObject.getDouble("itemWeight"));
               orderBondedSku.setUnitPrice(skuJSONObject.getDouble("unitPrice"));
               orderBondedSku.setItemsku(skuJSONObject.getString("itemSku").trim());
               OrderBondedSku obs = orderBondedSkuMap.get(String.valueOf(txLogisticID) + skuJSONObject.getString("itemSku").trim());
               if (obs != null) {
                 retJson.put("msg", "txLogisticID(" + txLogisticID + "): txLogisticID + itemSku can't be the same, please check!");
                 retJson.put("result", result);
                 return retJson.toJSONString();
               } 
               orderBondedSkuMap.put(String.valueOf(txLogisticID) + skuJSONObject.getString("itemSku").trim(), orderBondedSku);
               obsList.add(orderBondedSku);
             } 
             
             orderBonded.setTxLogisticID(txLogisticID);
             orderBonded.setReceiveMan(orderJSONObject.getString("receiveMan").trim());
             orderBonded.setReceiveProvince(orderJSONObject.getString("receiveProvince").trim());
             orderBonded.setReceiveCity(orderJSONObject.getString("receiveCity").trim());
             orderBonded.setReceiveCounty(orderJSONObject.getString("receiveCounty").trim());
             orderBonded.setReceiveManAddress(orderJSONObject.getString("receiveManAddress").trim());
             orderBonded.setReceiveManPhone(orderJSONObject.getString("receiveManPhone").trim());
             
             orderBonded.setItemCount(Integer.valueOf(count.intValue()));
             orderBonded.setWorth(Double.valueOf(price.doubleValue()));
             orderBonded.setItemWeight(Double.valueOf(weight.doubleValue()));
             
             orderBonded.setFeeAmount(orderJSONObject.getDouble("feeAmount"));
             orderBonded.setInsureAmount(orderJSONObject.getDouble("insureAmount"));
             orderBonded.setBuyerIdNumber(orderJSONObject.getString("buyerIdNumber").trim());
             orderBonded.setBuyerName(orderJSONObject.getString("buyerName").trim());
             orderBonded.setCarrier(orderJSONObject.getString("carrier").trim());
             orderBonded.setSendWarehouse(orderJSONObject.getString("sendWarehouse").trim());
             orderBonded.setMerchantNum(orderJSONObject.getString("merchantNum").trim());
             String mailNo = orderJSONObject.getString("mailNo").trim();
             if (!"0".equals(mailNo) && StringUtil.isNotEmpty(mailNo).booleanValue()) {
               orderBonded.setMailNo(mailNo);
             }
             orderBonded.setPc(orderJSONObject.getString("pc").trim());
             orderBonded.setUserId(retUser.getId());
             orderBonded.setIspost(0);
             orderBonded.setIsCustoms(0);
             orderBonded.setAuditstatus(0);
             OrderBonded ob = orderBondedMap.get(txLogisticID);
             if (ob != null) {
               retJson.put("msg", "txLogisticID(" + txLogisticID + "): txLogisticID can't be the same, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (orderBonded.getWorth().doubleValue() >= 2000.0D) {
               orderBonded.setAuditstatus(7);
             }
             double weightt = orderBonded.getItemWeight().doubleValue();
             OrderBondedRule orderBondedRule = new OrderBondedRule();
             orderBondedRule.setCarrier(orderBonded.getCarrier());
             orderBondedRule.setProvince(orderBonded.getReceiveProvince());
             orderBondedRule = this.orderBondedRuleDao.findByProvinceAndCarrier(orderBondedRule);
             if (orderBondedRule != null) {
               if (orderBondedRule.getWeight().doubleValue() == 0.0D) {
                 orderBonded.setAuditstatus(7);
               }
               else if (weightt > orderBondedRule.getWeight().doubleValue()) {
                 orderBonded.setAuditstatus(7);
               } 
             }
             
             orderBondedMap.put(txLogisticID, orderBonded);
           } 
 
           
           Object[] obArr = orderBondedMap.values().toArray();
           List<OrderBonded> retList = this.orderBondedDao.findByObArr(obArr);
           if (retList != null && retList.size() > 0) {
             String retTxLogisticID = "";
             for (OrderBonded retOb : retList) {
               retTxLogisticID = String.valueOf(retTxLogisticID) + retOb.getTxLogisticID() + ",";
             }
             retTxLogisticID = retTxLogisticID.substring(0, retTxLogisticID.length() - 1);
             retJson.put("msg", "txLogisticID:(" + retTxLogisticID + ") already exist, please check!");
             retJson.put("result", result);
             return retJson.toJSONString();
           } 
 
           
           this.orderBondedDao.batchInsert(obArr);
           this.orderBondedSkuDao.batchInsert(obsList);
           
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
 
   
   public synchronized String importOrderNew(String jsonStr) {
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
         Map<String, OrderBonded> orderBondedMap = new HashMap<>();
         Map<String, OrderBondedSku> orderBondedSkuMap = new HashMap<>();
         List<OrderBondedSku> obsList = new ArrayList<>();
         if (retUser != null) {
           JSONArray orderJSONArray = jSONObject.getJSONArray("orderList");
           if (orderJSONArray == null) {
             retJson.put("msg", "orderList is null, please check!");
             retJson.put("result", result);
             return retJson.toJSONString();
           } 
           for (int i = 0; i < orderJSONArray.size(); i++) {
             OrderBonded orderBonded = new OrderBonded();
             orderBonded.setMark(Integer.valueOf(0));
             JSONObject orderJSONObject = orderJSONArray.getJSONObject(i);
             String txLogisticID = orderJSONObject.getString("txLogisticID").trim();
             if (StringUtil.isEmpty(txLogisticID).booleanValue()) {
               retJson.put("msg", "txLogisticID is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("receiveMan")).booleanValue()) {
               retJson.put("msg", "receiveMan is empty, please check!");
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
             String receiveCounty = orderJSONObject.getString("receiveCounty");
             if (StringUtil.isEmpty(orderJSONObject.getString("receiveCounty")).booleanValue()) {
               receiveCounty = orderJSONObject.getString("receiveCity");
             }
 
 
 
             
             if (StringUtil.isEmpty(orderJSONObject.getString("receiveManAddress")).booleanValue()) {
               retJson.put("msg", "receiveManAddress is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("receiveManPhone")).booleanValue()) {
               retJson.put("msg", "receiveManPhone is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("feeAmount")).booleanValue()) {
               retJson.put("msg", "feeAmount is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("insureAmount")).booleanValue()) {
               retJson.put("msg", "insureAmount is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("buyerIdNumber")).booleanValue()) {
               retJson.put("msg", "buyerIdNumber is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             String buyerID = orderJSONObject.getString("buyerIdNumber").trim();
             
             if (StringUtil.isEmpty(orderJSONObject.getString("buyerName")).booleanValue()) {
               retJson.put("msg", "buyerName is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("carrier")).booleanValue()) {
               retJson.put("msg", "carrier is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("sendWarehouse")).booleanValue()) {
               retJson.put("msg", "sendWarehouse is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             if (StringUtil.isEmpty(orderJSONObject.getString("merchantNum")).booleanValue()) {
               retJson.put("msg", "merchantNum is empty, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             JSONArray skuJSONArray = orderJSONObject.getJSONArray("skuList");
             if (skuJSONArray == null) {
               retJson.put("msg", "skuList is null, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             BigDecimal count = new BigDecimal("0");
             BigDecimal price = new BigDecimal("0");
             BigDecimal weight = new BigDecimal("0");
             for (int j = 0; j < skuJSONArray.size(); j++) {
               JSONObject skuJSONObject = skuJSONArray.getJSONObject(j);
               if (StringUtil.isEmpty(skuJSONObject.getString("itemName")).booleanValue()) {
                 retJson.put("msg", "itemName is empty, please check!");
                 retJson.put("result", result);
                 return retJson.toJSONString();
               } 
               if (StringUtil.isEmpty(skuJSONObject.getString("itemSku")).booleanValue()) {
                 retJson.put("msg", "itemSku is empty, please check!");
                 retJson.put("result", result);
                 return retJson.toJSONString();
               } 
               if (StringUtil.isEmpty(skuJSONObject.getString("itemCount")).booleanValue()) {
                 retJson.put("msg", "itemCount is empty, please check!");
                 retJson.put("result", result);
                 return retJson.toJSONString();
               } 
               if (StringUtil.isEmpty(skuJSONObject.getString("unitPrice")).booleanValue()) {
                 retJson.put("msg", "unitPrice is empty, please check!");
                 retJson.put("result", result);
                 return retJson.toJSONString();
               } 
               if (StringUtil.isEmpty(skuJSONObject.getString("allPrice")).booleanValue()) {
                 retJson.put("msg", "allPrice is empty, please check!");
                 retJson.put("result", result);
                 return retJson.toJSONString();
               } 
               if (StringUtil.isEmpty(skuJSONObject.getString("itemWeight")).booleanValue()) {
                 retJson.put("msg", "itemWeight is empty, please check!");
                 retJson.put("result", result);
                 return retJson.toJSONString();
               } 
               BigDecimal itemWeight = new BigDecimal(skuJSONObject.getString("itemWeight"));
               BigDecimal itemCount = new BigDecimal(skuJSONObject.getString("itemCount"));
               BigDecimal unitPrice = new BigDecimal(skuJSONObject.getString("unitPrice"));
               BigDecimal allPrice = new BigDecimal(skuJSONObject.getString("allPrice"));
               BigDecimal operPrice = unitPrice.multiply(itemCount);
               System.out.println("operPrice====" + operPrice);
               
               count = count.add(itemCount);
               price = price.add(allPrice);
               weight = weight.add(itemWeight);
               orderBonded.setItemName(skuJSONObject.getString("itemName").trim());
 
               
               OrderBondedSku orderBondedSku = new OrderBondedSku();
               orderBondedSku.setTxLogisticID(txLogisticID);
               orderBondedSku.setItemCount(skuJSONObject.getInteger("itemCount"));
               orderBondedSku.setAllPrice(skuJSONObject.getDouble("allPrice"));
               orderBondedSku.setItemName(skuJSONObject.getString("itemName").trim());
               orderBondedSku.setItemWeight(skuJSONObject.getDouble("itemWeight"));
               orderBondedSku.setUnitPrice(skuJSONObject.getDouble("unitPrice"));
               orderBondedSku.setItemsku(skuJSONObject.getString("itemSku").trim());
               OrderBondedSku obs = orderBondedSkuMap.get(String.valueOf(txLogisticID) + skuJSONObject.getString("itemSku").trim());
               if (obs != null) {
                 retJson.put("msg", "txLogisticID(" + txLogisticID + "): txLogisticID + itemSku can't be the same, please check!");
                 retJson.put("result", result);
                 return retJson.toJSONString();
               } 
               orderBondedSkuMap.put(String.valueOf(txLogisticID) + skuJSONObject.getString("itemSku").trim(), orderBondedSku);
               obsList.add(orderBondedSku);
             } 
             
             orderBonded.setTxLogisticID(txLogisticID);
             orderBonded.setReceiveMan(orderJSONObject.getString("receiveMan").trim());
             orderBonded.setReceiveProvince(orderJSONObject.getString("receiveProvince").trim());
             orderBonded.setReceiveCity(orderJSONObject.getString("receiveCity").trim());
             orderBonded.setReceiveCounty(receiveCounty);
             orderBonded.setReceiveManAddress(orderJSONObject.getString("receiveManAddress").trim());
             orderBonded.setReceiveManPhone(orderJSONObject.getString("receiveManPhone").trim());
             
             orderBonded.setItemCount(Integer.valueOf(count.intValue()));
             orderBonded.setWorth(Double.valueOf(price.doubleValue()));
             orderBonded.setItemWeight(Double.valueOf(weight.doubleValue()));
             
             orderBonded.setFeeAmount(orderJSONObject.getDouble("feeAmount"));
             orderBonded.setInsureAmount(orderJSONObject.getDouble("insureAmount"));
             orderBonded.setBuyerIdNumber(orderJSONObject.getString("buyerIdNumber").trim());
 
             
             orderBonded.setPayCompany(orderJSONObject.getString("payCompany").trim());
             orderBonded.setPayNumber(orderJSONObject.getString("payNumber").trim());
             
             orderBonded.setBuyerName(orderJSONObject.getString("buyerName").trim());
             orderBonded.setCarrier(orderJSONObject.getString("carrier").trim());
             orderBonded.setSendWarehouse(orderJSONObject.getString("sendWarehouse").trim());
             orderBonded.setMerchantNum(orderJSONObject.getString("merchantNum").trim());
             String mailNo = orderJSONObject.getString("mailNo").trim();
             if (!"0".equals(mailNo) && StringUtil.isNotEmpty(mailNo).booleanValue()) {
               orderBonded.setMailNo(mailNo);
             }
             orderBonded.setPc(orderJSONObject.getString("pc").trim());
             orderBonded.setUserId(retUser.getId());
             orderBonded.setIspost(0);
             orderBonded.setIsCustoms(0);
             orderBonded.setAuditstatus(0);
             OrderBonded ob = orderBondedMap.get(txLogisticID);
             if (ob != null) {
               retJson.put("msg", "txLogisticID(" + txLogisticID + "): txLogisticID can't be the same, please check!");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
 
 
             
             double weightt = orderBonded.getItemWeight().doubleValue();
             OrderBondedRule orderBondedRule = new OrderBondedRule();
             orderBondedRule.setCarrier(orderBonded.getCarrier());
             orderBondedRule.setProvince(orderBonded.getReceiveProvince());
             orderBondedRule = this.orderBondedRuleDao.findByProvinceAndCarrier(orderBondedRule);
             if (orderBondedRule != null) {
               if (orderBondedRule.getWeight().doubleValue() == 0.0D) {
                 orderBonded.setAuditstatus(7);
               }
               else if (weightt > orderBondedRule.getWeight().doubleValue()) {
                 orderBonded.setAuditstatus(7);
               } 
             }
             
             orderBondedMap.put(txLogisticID, orderBonded);
           } 
 
           
           Object[] obArr = orderBondedMap.values().toArray();
           List<OrderBonded> retList = this.orderBondedDao.findByObArr(obArr);
           if (retList != null && retList.size() > 0) {
             String retTxLogisticID = "";
             for (OrderBonded retOb : retList) {
               retTxLogisticID = String.valueOf(retTxLogisticID) + retOb.getTxLogisticID() + ",";
             }
             retTxLogisticID = retTxLogisticID.substring(0, retTxLogisticID.length() - 1);
             retJson.put("msg", "txLogisticID:(" + retTxLogisticID + ") already exist, please check!");
             retJson.put("result", result);
             return retJson.toJSONString();
           } 
 
           
           this.orderBondedDao.batchInsert(obArr);
           this.orderBondedSkuDao.batchInsert(obsList);
           
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
 
 
   
   public String sendMergerInfo(@WebParam(name = "content") String content) {
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String businessNo = "";
     String companyCode = "";
     String manualId = "";
     String itemNo = "";
     String itemType = "";
     String sourceNo = "";
     String goodsName = "";
     String goodsNo = "";
     String goodsSpec = "";
     String currencyType = "";
     String declareUnit = "";
     String useFlag = "";
     String actionType = "";
     String unit1 = "";
     String unit2 = "";
     try {
       System.out.println("jsonStr3********=" + AESEncrypt.decryptor(content));
       
       Document document = DocumentHelper.parseText(AESEncrypt.decryptor(content));
       Element root = document.getRootElement();
       Iterator<Element> it = root.elementIterator();
       while (it.hasNext()) {
         Element element = it.next();
         
         if (element.getName().equals("body")) {
           Iterator<Element> body = element.elementIterator();
           Element body_1 = body.next();
           
           Iterator<Element> mergerInfo = body_1.elementIterator();
           while (mergerInfo.hasNext()) {
             Element mergerInfo_1 = mergerInfo.next();
             
             Iterator<Element> manSign = mergerInfo_1.elementIterator();
             
             if (mergerInfo_1.getName().equals("manSign")) {
               while (manSign.hasNext()) {
                 Element manSign_1 = manSign.next();
                 
                 if (manSign_1.getName().equals("companyCode")) {
                   companyCode = manSign_1.getText();
                 }
                 if (manSign_1.getName().equals("businessNo")) {
                   businessNo = manSign_1.getText();
                 }
               } 
             }
             
             if (mergerInfo_1.getName().equals("manItemSourceList")) {
               while (manSign.hasNext()) {
                 Element manSign_1 = manSign.next();
 
                 
                 Iterator<Element> manItemSource = manSign_1.elementIterator();
                 if (manSign_1.getName().equals("manItemSource")) {
                   while (manItemSource.hasNext()) {
                     Element manItemSource_1 = manItemSource.next();
                     
                     if (manItemSource_1.getName().equals("manualId")) {
                       manualId = manItemSource_1.getText();
                     }
                     if (manItemSource_1.getName().equals("itemNo")) {
                       itemNo = manItemSource_1.getText();
                     }
                     if (manItemSource_1.getName().equals("itemType")) {
                       itemType = manItemSource_1.getText();
                     }
                     if (manItemSource_1.getName().equals("sourceNo")) {
                       sourceNo = manItemSource_1.getText();
                     }
                     if (manItemSource_1.getName().equals("goodsName")) {
                       goodsName = manItemSource_1.getText();
                     }
                     if (manItemSource_1.getName().equals("goodsNo")) {
                       goodsNo = manItemSource_1.getText();
                     }
                     if (manItemSource_1.getName().equals("goodsSpec")) {
                       goodsSpec = manItemSource_1.getText();
                     }
                     if (manItemSource_1.getName().equals("currencyType")) {
                       currencyType = manItemSource_1.getText();
                     }
                     if (manItemSource_1.getName().equals("declareUnit")) {
                       declareUnit = manItemSource_1.getText();
                     }
                     if (manItemSource_1.getName().equals("useFlag")) {
                       useFlag = manItemSource_1.getText();
                     }
                     if (manItemSource_1.getName().equals("actionType")) {
                       actionType = manItemSource_1.getText();
                     }
                     if (manItemSource_1.getName().equals("unit1")) {
                       unit1 = manItemSource_1.getText();
                     }
                     if (manItemSource_1.getName().equals("unit2")) {
                       unit2 = manItemSource_1.getText();
                     }
                   } 
                   
                   Merger merger = new Merger();
                   merger.setCompanyCode(companyCode);
                   merger.setManualId(manualId);
                   merger.setItemNo(itemNo);
                   merger.setItemType(itemType);
                   merger.setSourceNo(sourceNo);
                   merger.setGoodsName(goodsName);
                   merger.setGoodsNo(goodsNo);
                   merger.setGoodsSpec(goodsSpec);
                   merger.setCurrencyType(currencyType);
                   merger.setDeclareUnit(declareUnit);
                   merger.setUseFlag(useFlag);
                   merger.setActionType(actionType);
                   merger.setUnit1(unit1);
                   merger.setUnit2(unit2);
                   this.mergerDao.insert1(merger);
                 } 
               } 
             }
           } 
         } 
       } 
     } catch (Exception e) {
       e.printStackTrace();
     } 
     Element root2 = DocumentHelper.createElement("mo");
     Document document1 = DocumentHelper.createDocument(root2);
     
     root2.addAttribute("version", "1.0.0");
     
     Element element1 = root2.addElement("head");
     element1.addElement("businessType").addText("RESULT");
     Element element2 = root2.addElement("body");
     Element element3 = element2.addElement("resultInfo");
     Element element4 = element3.addElement("manResultHead");
     element4.addElement("companyCode").addText(companyCode);
     element4.addElement("businessType").addText("MERGER");
     element4.addElement("businessNo").addText(businessNo);
     element4.addElement("processTime").addText(df.format(new Date()));
     element4.addElement("processResult").addText("S");
     element4.addElement("processComment").addText("入库");
     
     Element element5 = element3.addElement("manResultDetailList");
     Element element6 = element5.addElement("manResultDetail");
     element6.addElement("information").addText("");
     
     String res = null;
     try {
       res = AESEncrypt.encrytor(document1.asXML());
     } catch (Exception e) {
       e.printStackTrace();
     } 
     
     return res;
   }
 
 
   
   public String Logisticstrack(String jsonStr) throws Exception {
     String urlString = "http://tracequery.sto-express.cn/track.aspx?billcode=" + jsonStr;
     
     String res = stocx.url(urlString);
 
     
     return URLEncoder.encode(res, "UTF-8");
   }
 
 
 
   
   public String newLogisticstrack(String jsonStr) throws Exception {
     String urlString = "http://tracequery.sto-express.cn/track.aspx?billcode=" + jsonStr;
     
     String res = stocx.url(urlString);
 
     
     return URLEncoder.encode(res);
   }
 
   
   public String getHtoGj(String jsonStr) throws Exception {
     String ret = "fail";
     if (StringUtil.isNotEmpty(jsonStr).booleanValue()) {
       Element requestEl = DocumentHelper.createElement("Request");
       Element mailNosEl = requestEl.addElement("mailNos");
       String[] mailNoArr = jsonStr.split(","); byte b; int i; String[] arrayOfString1;
       for (i = (arrayOfString1 = mailNoArr).length, b = 0; b < i; ) { String mailNo = arrayOfString1[b];
         Element mailNoEl = mailNosEl.addElement("mailNo");
         mailNoEl.addText(mailNo); b++; }
       
       String result = HTOydbg.htogetgj(requestEl.asXML());
       Pattern p1 = Pattern.compile("<response>(.*)</response>");
       Matcher m1 = p1.matcher(result);
       while (m1.find()) {
         result = m1.group(1);
       }
       return result;
     } 
     return ret;
   }
 
   
   public String queryOrderStatus(String txLogisticID) throws Exception {
     if (StringUtil.isNotEmpty(txLogisticID).booleanValue()) {
       String[] idArr = txLogisticID.split(",");
       List<OrderBonded> obList = this.orderBondedDao.findOrderStatus(idArr);
       if (obList != null) {
         JSONObject jSONObject = new JSONObject();
         JSONArray jarr = new JSONArray();
         for (OrderBonded ob : obList) {
           JSONObject jobj = new JSONObject();
           jobj.put("txLogisticID", ob.getTxLogisticID());
           jobj.put("mailNo", ob.getMailNo());
           jobj.put("status", Integer.valueOf(ob.getAuditstatus()));
           jarr.add(jobj);
         } 
         jSONObject.put("orderList", jarr);
         return URLEncoder.encode(jSONObject.toJSONString(), "UTF-8");
       } 
     } 
     return "";
   }
   
   public String sendStockDeleteInfo(String arg0) throws Exception {
     String xmlStr = AESEncrypt.decryptor(arg0);
     System.out.println("删单申请处理结果---->" + AESEncrypt.decryptor(arg0));
     StockDelete stockDelete = new StockDelete();
     Pattern p1 = Pattern.compile("<stockDeleteId>(.*)</stockDeleteId>");
     Matcher m1 = p1.matcher(xmlStr);
     while (m1.find()) {
       String stockDeleteId = m1.group(1);
       stockDelete.setStockDeleteId(stockDeleteId);
     } 
     
     Pattern p2 = Pattern.compile("<processResult>(.*)</processResult>");
     Matcher m2 = p2.matcher(xmlStr);
     while (m2.find()) {
       String processResult = m2.group(1);
       stockDelete.setFlag(processResult);
     } 
     this.stockDeleteDao.updateFlag(stockDelete);
     return "success";
   }
 
   
   public String cancleOrder(String param) throws Exception {
     String result = "fail";
     JSONObject retJson = new JSONObject();
     StringBuffer sb = new StringBuffer();
     try {
       if (StringUtils.isNotEmpty(param)) {
         String[] strs = param.split(",");
         List<OrderBonded> list = new ArrayList<>(); byte b; int j; String[] arrayOfString1;
         for (j = (arrayOfString1 = strs).length, b = 0; b < j; ) { String orderno = arrayOfString1[b];
           OrderBonded orderBonded = this.orderBondedDao.findByTxLogisticID(orderno);
           if (orderBonded == null) {
             retJson.put("msg", "订单号" + orderno + "不存在！");
             retJson.put("result", result);
             System.out.println("取消失败：订单号" + orderno + "不存在！");
             Tools.fileLog("取消失败：订单号" + orderno + "不存在！");
             return retJson.toJSONString();
           }           
           if (orderBonded.getIsPushQd() == 1) {
             retJson.put("msg", "订单号" + orderno + "已经推过清单，不得取消订单！");
             retJson.put("result", result);
             System.out.println("取消失败：订单号" + orderno + "已经推过清单，不得取消订单！");
             Tools.fileLog("取消失败：订单号" + orderno + "已经推过清单，不得取消订单！");
             return retJson.toJSONString();
           } 
           if (orderBonded.getAuditstatus() == 8) {
             retJson.put("msg", "订单号" + orderno + "已是取消的订单！");
             retJson.put("result", result);
             System.out.println("取消失败：订单号" + orderno + "已是取消的订单");
             Tools.fileLog("取消失败：订单号" + orderno + "已是取消的订单");
             return retJson.toJSONString();
           } 
           list.add(orderBonded); b++; }
         
         int i = 0;
         for (OrderBonded orderBonded : list) {
           int auditstatus = orderBonded.getAuditstatus();
           if (auditstatus == 2 || auditstatus == 1) {
             List<OrderBondedPushSku> orderBondedSkus = this.orderBondedSkuDao.findByTxLogisticID(orderBonded.getTxLogisticID());
             for (OrderBondedPushSku orderBondedPushSku : orderBondedSkus) {
               List<Tinventory> tinventories = this.tinventoryDao.findInventory(orderBonded.getMerchantNum(), orderBondedPushSku.getGoodsSn());
               Tinventory inventory = tinventories.get(0);
               if (inventory != null) {
                 inventory.setPreUsedInventory(inventory.getPreUsedInventory() - orderBondedPushSku.getGoodsNumber().intValue());
                 inventory.setSurplusInventory(inventory.getSurplusInventory() + orderBondedPushSku.getGoodsNumber().intValue());
                 this.tinventoryDao.updateInventory(inventory);
               } 
             } 
           } 
           
           orderBonded.setAuditstatus(8);
           this.orderBondedDao.updateAuditstatusByTxLogisticID(orderBonded);
           if (i == 0) {
             sb.append(orderBonded.getTxLogisticID());
           } else {
             sb.append("，" + orderBonded.getTxLogisticID());
           } 
           i++;
         } 
         
         retJson.put("msg", "取消成功,共" + list.size() + "条。");
         retJson.put("result", "success");
         System.out.println("取消成功,共" + list.size() + "条。" + sb.toString());
         Tools.fileLog("取消成功,共" + list.size() + "条。" + sb.toString());
         return retJson.toJSONString();
       } 
       retJson.put("msg", "请填写订单号进去查询！");
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


