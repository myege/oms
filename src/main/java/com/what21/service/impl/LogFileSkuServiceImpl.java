 package com.what21.service.impl;
 
 import com.what21.dao.LogFileSkuDao;
 import com.what21.model.LogFileSku;
 import com.what21.service.LogFileSkuService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 @Service
 public class LogFileSkuServiceImpl
   implements LogFileSkuService
 {
   @Autowired
   private LogFileSkuDao logFileSkuDao;
   
   public int saveLog(LogFileSku log) {
     return this.logFileSkuDao.saveLog(log);
   }
 
   
   public List<LogFileSku> findAll(Map<String, Object> map) {
     return this.logFileSkuDao.findAll(map);
   }
 
   
   public int countLogSku(Map<String, Object> map) {
     return this.logFileSkuDao.countLogSku(map);
   }
 
   
   public int deleteSku(String companybm) {
     return this.logFileSkuDao.deleteSku(companybm);
   }
 
   
   public LogFileSku findTxId(String txLogisticID) {
     return this.logFileSkuDao.findTxId(txLogisticID);
   }
 
   
   public LogFileSku findSkuCom(String com) {
     return this.logFileSkuDao.findTxId(com);
   }
 }


