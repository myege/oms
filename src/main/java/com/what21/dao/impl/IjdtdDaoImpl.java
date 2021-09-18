 package com.what21.dao.impl;
 
 import com.what21.dao.IjdtdDao;
 import com.what21.model.Ijdtd;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class IjdtdDaoImpl
   implements IjdtdDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<Ijdtd> findAll(Map<String, Object> map) {
     return ((IjdtdDao)this.sqlSessionTemplate.getMapper(IjdtdDao.class)).findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return ((IjdtdDao)this.sqlSessionTemplate.getMapper(IjdtdDao.class)).count(map);
   }
 
   
   public void insertBatch(List<Ijdtd> list) {
     ((IjdtdDao)this.sqlSessionTemplate.getMapper(IjdtdDao.class)).insertBatch(list);
   }
 
   
   public void delete(String ladNo) {
     ((IjdtdDao)this.sqlSessionTemplate.getMapper(IjdtdDao.class)).delete(ladNo);
   }
 
   
   public List<Ijdtd> findbygjz(String ladNo) {
     return ((IjdtdDao)this.sqlSessionTemplate.getMapper(IjdtdDao.class)).findbygjz(ladNo);
   }
 
   
   public void updatebygjz(String ladNo, String notes, String status) {
     ((IjdtdDao)this.sqlSessionTemplate.getMapper(IjdtdDao.class)).updatebygjz(ladNo, notes, status);
   }
 }


