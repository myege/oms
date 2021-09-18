 package com.what21.service.impl;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.GylFinanceDao;
 import com.what21.dao.UserDao;
 import com.what21.model.GylFinance;
 import com.what21.model.OrderBonded;
 import com.what21.model.OrderBondedSku;
 import com.what21.model.Users;
 import com.what21.service.ImportGylFinanceOrderService;
 import com.what21.util.StringUtil;
 import java.math.BigDecimal;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
import org.apache.commons.codec.binary.Base64;
 @Service
 public class ImportGylFinanceOrderServiceImpl
   implements ImportGylFinanceOrderService
 {
   @Autowired
   private GylFinanceDao gylFinanceDao;
   @Autowired
   private UserDao userDao;
   
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
           String totalWorth = jSONObject.getString("totalWorth");
           String totalNum = jSONObject.getString("totalNum");
           String merchant = jSONObject.getString("merchant");
           String merchantNum = jSONObject.getString("merchantNum");
           if (StringUtil.isEmpty(totalWorth).booleanValue()) {
             retJson.put("msg", "totalWorth is empty, please check!");
             retJson.put("result", result);
             return retJson.toJSONString();
           } 
           if (StringUtil.isEmpty(totalNum).booleanValue()) {
             retJson.put("msg", "totalNum is empty, please check!");
             retJson.put("result", result);
             return retJson.toJSONString();
           } 
           if (StringUtil.isEmpty(merchant).booleanValue()) {
             retJson.put("msg", "merchant is empty, please check!");
             retJson.put("result", result);
             return retJson.toJSONString();
           } 
           if (StringUtil.isEmpty(merchantNum).booleanValue()) {
             retJson.put("msg", "merchantNum is empty, please check!");
             retJson.put("result", result);
             return retJson.toJSONString();
           } 
           JSONArray orderJSONArray = jSONObject.getJSONArray("orderList");
           if (orderJSONArray == null) {
             retJson.put("msg", "orderList is null, please check!");
             retJson.put("result", result);
             return retJson.toJSONString();
           }           
           SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
           String gylFinanceNumber = df.format(new Date());
           GylFinance gylFinance = new GylFinance();
           gylFinance.setGylFinanceNumber(gylFinanceNumber);
           gylFinance.setTotalWorth(Double.valueOf(totalWorth));
           gylFinance.setTotalNum(Integer.valueOf(Integer.parseInt(totalNum)));
           gylFinance.setMerchant(merchantNum);
           gylFinance.setMerchantNum(merchantNum);
           gylFinance.setUserId(Integer.valueOf(retUser.getId()));
           
           for (int i = 0; i < orderJSONArray.size(); i++) {
             OrderBonded orderBonded = new OrderBonded();
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
               orderBondedSku.setGylFinanceNumber(gylFinanceNumber);
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
             orderBonded.setGylFinanceNumber(gylFinanceNumber);
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
             orderBondedMap.put(txLogisticID, orderBonded);
           } 
 
           
           Object[] obArr = orderBondedMap.values().toArray();
           List<OrderBonded> retList = this.gylFinanceDao.findByObArr(obArr);
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
 
           
           this.gylFinanceDao.insert(gylFinance);
           this.gylFinanceDao.batchInsertOrder(obArr);
           this.gylFinanceDao.batchInsertOrderSku(obsList);
           
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


