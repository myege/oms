 package com.what21.service.impl;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.BuyerLimitDao;
 import com.what21.dao.OrderBondDao;
 import com.what21.dao.OrderBondedDao;
 import com.what21.dao.OrderDsDao;
 import com.what21.dao.OrderMailDao;
 import com.what21.dao.OrderOutDao;
 import com.what21.dao.OrderOutLogDao;
 import com.what21.dao.ReceiveHghzDataDao;
 import com.what21.model.ReceiveHghzData;
 import com.what21.service.OrderBondedService;
 import com.what21.service.ReceiveHghzDataService;
 import com.zt.kjybd.newzs;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.OutputStreamWriter;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class ReceiveHghzDataServiceImpl
   implements ReceiveHghzDataService
 {
   @Autowired
   private ReceiveHghzDataDao receiveHghzDataDao;
   @Autowired
   public OrderBondedService orderBondedService;
   @Autowired
   private OrderMailDao orderMailDao;
   @Autowired
   public OrderBondDao orderBondDao;
   @Autowired
   private OrderBondedDao orderBondedDao;
   @Autowired
   private OrderOutLogDao orderOutLogDao;
   @Autowired
   private OrderOutDao orderOutDao;
   @Autowired
   private BuyerLimitDao buyerLimitDao;
   @Autowired
   private OrderDsDao orderDsDao;
   
   public String receiveData(String param) {
     try {
       JSONObject paramJson = JSONObject.parseObject(param);
       String paramsObj = paramJson.getString("paramsObj");
       
       String name = newzs.getRandomCharAndNumr(Integer.valueOf(10));
       String fileName = "D:\\hz\\" + name + ".xml";
       saveFile(fileName, paramsObj);
       
       return "<response><success>false</success><errorCode>123</errorCode><errorMsg>异常啦</errorMsg></response>";
     } catch (Exception e) {
       e.printStackTrace();
       return "<response><success>false</success><errorCode>123</errorCode><errorMsg>异常啦</errorMsg></response>";
     } 
   }
   
   private Map<String, String> parseContentData(List<ReceiveHghzData> receiveHghzDatas) {
     Map<String, String> map = new HashMap<>();
     for (ReceiveHghzData receiveHghzData : receiveHghzDatas) {
       String contentData = receiveHghzData.getContentData();
       Pattern p = Pattern.compile("<status>(.*)</status>");
       Matcher m = p.matcher(contentData);
       String status = null;
       while (m.find()) {
         status = m.group(1);
       }
       
       if (status != null) {
         p = Pattern.compile("<customsTax>(.*)</customsTax>");
         m = p.matcher(contentData);
         String customsTax = null;
         while (m.find()) {
           customsTax = m.group(1);
           map.put("customsTax", customsTax);
         } 
         
         p = Pattern.compile("<valueAddedTax>(.*)</valueAddedTax>");
         m = p.matcher(contentData);
         String valueAddedTax = null;
         while (m.find()) {
           valueAddedTax = m.group(1);
           map.put("valueAddedTax", valueAddedTax);
         } 
         
         p = Pattern.compile("<consumptionTax>(.*)</consumptionTax>");
         m = p.matcher(contentData);
         String consumptionTax = null;
         while (m.find()) {
           consumptionTax = m.group(1);
           map.put("consumptionTax", consumptionTax);
         } 
         continue;
       } 
       p = Pattern.compile("<copNo>(.*)</copNo>");
       m = p.matcher(contentData);
       String copNo = null;
       while (m.find()) {
         copNo = m.group(1);
         map.put("copNo", copNo);
       } 
     } 
 
     
     return map;
   }
 
 
   
   public List<ReceiveHghzData> findAll(Map<String, Object> pageMap) {
     return this.receiveHghzDataDao.findAll(pageMap);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return this.receiveHghzDataDao.count(pageMap);
   }
 
   
   public int update(String id) {
     String[] idArr = id.split(",");
     return this.receiveHghzDataDao.update(idArr);
   }
 
   
   public List<ReceiveHghzData> findByInvtNo(String invtNo) {
     return this.receiveHghzDataDao.findByInvtNo(invtNo);
   }
 
   
   public List<ReceiveHghzData> find(Map<String, Object> map) {
     return this.receiveHghzDataDao.find(map);
   }
   
   public static void saveFile(String fileName, String content) {
     try {
       OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fileName, true), "UTF-8");
       osw.write(content);
       osw.close();
     } catch (IOException e) {
       e.printStackTrace();
     } 
   }
 }


