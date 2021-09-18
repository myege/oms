 package com.what21.dao.impl;
 
 import com.what21.dao.SzqgWlgjDao;
 import com.what21.model.SzqgwlGj;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class SzqgWlgjDaoImpl
   implements SzqgWlgjDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
 
   
   public List<SzqgwlGj> findgjz(String gzj) {
     return ((SzqgWlgjDao)this.sqlSessionTemplate.getMapper(SzqgWlgjDao.class)).findgjz(gzj);
   }
 }


