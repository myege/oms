 package com.what21.dao.impl;
 
 import com.what21.dao.BeCategoryDao;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class BeCategoryDaoImpl implements BeCategoryDao {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public String findQzddjByName(String name) {
     return ((BeCategoryDao)this.sqlSessionTemplate.getMapper(BeCategoryDao.class)).findQzddjByName(name);
   }
 
   
   public String findZxggdjByName(String name) {
     return ((BeCategoryDao)this.sqlSessionTemplate.getMapper(BeCategoryDao.class)).findZxggdjByName(name);
   }
 
   
   public String findQpmdj() {
     return ((BeCategoryDao)this.sqlSessionTemplate.getMapper(BeCategoryDao.class)).findQpmdj();
   }
 }


