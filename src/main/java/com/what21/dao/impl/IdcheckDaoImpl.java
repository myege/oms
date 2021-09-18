 package com.what21.dao.impl;
 
 import com.what21.dao.IdcheckDao;
 import com.what21.model.Idcheck;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class IdcheckDaoImpl
   implements IdcheckDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public void insertBatch(List<Idcheck> list) {
     ((IdcheckDao)this.sqlSessionTemplate.getMapper(IdcheckDao.class)).insertBatch(list);
   }
 
 
   
   public List<Idcheck> findgjz(String gzj) {
     return ((IdcheckDao)this.sqlSessionTemplate.getMapper(IdcheckDao.class)).findgjz(gzj);
   }
 }


