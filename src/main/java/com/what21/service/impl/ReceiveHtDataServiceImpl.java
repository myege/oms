 package com.what21.service.impl;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.ReceiveHtDataDao;
 import com.what21.model.ReceiveHtData;
 import com.what21.service.ReceiveHtDataService;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class ReceiveHtDataServiceImpl
   implements ReceiveHtDataService
 {
   @Autowired
   private ReceiveHtDataDao receiveHtDataDao;
   
   public String receiveData(String param) {
     JSONObject paramJson = JSONObject.parseObject(param);
     if (paramJson != null) {
       String statusName = paramJson.getString("statusName");
       String detailInfo = paramJson.getString("detailInfo");
       String expressNo = paramJson.getString("expressNo");
       String isSuccess = paramJson.getString("isSuccess");
       SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       String time = f.format(new Date());
       
       ReceiveHtData receiveHtData = new ReceiveHtData();
       receiveHtData.setStatusName(statusName);
       receiveHtData.setDetailInfo(detailInfo);
       receiveHtData.setExpressNo(expressNo);
       receiveHtData.setIsSuccess(isSuccess);
       int result = this.receiveHtDataDao.insert(receiveHtData);
       if (result >= 1) {
         return "SUCCESS";
       }
     } 
     return "FAILED";
   }
 
   
   public List<ReceiveHtData> findAll(Map<String, Object> pageMap) {
     return this.receiveHtDataDao.findAll(pageMap);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return this.receiveHtDataDao.count(pageMap);
   }
 
   
   public int update(String id) {
     String[] idArr = id.split(",");
     return this.receiveHtDataDao.update(idArr);
   }
 }


