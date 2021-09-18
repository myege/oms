 package com.what21.dao.impl;
 
 import com.what21.dao.LogFileSkuDao;
 import com.what21.model.LogFileSku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class LogFileSkuDaoImpl
   implements LogFileSkuDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public int saveLog(LogFileSku log) {
     return this.sqlSessionTemplate.insert(String.valueOf(LogFileSku.class.getName()) + ".saveLog", log);
   }
 
   
   public List<LogFileSku> findAll(Map<String, Object> map) {
     return this.sqlSessionTemplate.selectList(String.valueOf(LogFileSku.class.getName()) + ".findAll", map);
   }
 
   
   public int countLogSku(Map<String, Object> map) {
     return ((Integer)this.sqlSessionTemplate.selectOne(String.valueOf(LogFileSku.class.getName()) + ".countLogSku", map)).intValue();
   }
 
   
   public int deleteSku(String companybm) {
     return this.sqlSessionTemplate.delete(String.valueOf(LogFileSku.class.getName()) + ".deleteSku", companybm);
   }
 
 
   
   public LogFileSku findTxId(String txLogisticId) {
     return (LogFileSku)this.sqlSessionTemplate.selectOne(String.valueOf(LogFileSku.class.getName()) + ".findTxId", txLogisticId);
   }
 
   
   public LogFileSku findSkuCom(String com) {
     return (LogFileSku)this.sqlSessionTemplate.selectOne(String.valueOf(LogFileSku.class.getName()) + ".findSkuCom", com);
   }
 }


