 package com.what21.dao.impl;
 
 import com.what21.dao.ImEntryBillListDao;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class ImEntryBillListDaoImpl implements ImEntryBillListDao {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 }

