 package com.what21.dao.impl;
 
 import com.what21.dao.Log_inventoryDao;
 import com.what21.model.Log_inventory;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class Log_inventoryDaoImpl
   implements Log_inventoryDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public void insert(Log_inventory log) {
     ((Log_inventoryDao)this.sqlSessionTemplate.getMapper(Log_inventoryDao.class)).insert(log);
   }
 
 
 
   
   public List<Log_inventory> findtoTinven(Map<String, Object> pageMap) {
     return ((Log_inventoryDao)this.sqlSessionTemplate.getMapper(Log_inventoryDao.class)).findtoTinven(pageMap);
   }
 
 
   
   public int count(Map<String, Object> pageMap) {
     return ((Log_inventoryDao)this.sqlSessionTemplate.getMapper(Log_inventoryDao.class)).count(pageMap);
   }
 }


