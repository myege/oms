 package com.what21.dao.impl;
 
 import com.what21.dao.Log_inventory_actionDao;
 import com.what21.model.Log_inventory_action;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class Log_inventory_actionDaoImpl
   implements Log_inventory_actionDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
 
   
   public void insert(Log_inventory_action log) {
     ((Log_inventory_actionDao)this.sqlSessionTemplate.getMapper(Log_inventory_actionDao.class)).insert(log);
   }
 }


