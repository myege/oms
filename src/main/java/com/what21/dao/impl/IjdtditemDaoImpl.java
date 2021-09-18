 package com.what21.dao.impl;
 
 import com.what21.dao.IjdtditemDao;
 import com.what21.model.Ijdtditem;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class IjdtditemDaoImpl
   implements IjdtditemDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
   
   public void insertBatch(List<Ijdtditem> list) {
     ((IjdtditemDao)this.sqlSessionTemplate.getMapper(IjdtditemDao.class)).insertBatch(list);
   }
 
   
   public void delete(String ladNo) {
     ((IjdtditemDao)this.sqlSessionTemplate.getMapper(IjdtditemDao.class)).delete(ladNo);
   }
 
 
   
   public List<Ijdtditem> findbygjz(String ladNo) {
     return ((IjdtditemDao)this.sqlSessionTemplate.getMapper(IjdtditemDao.class)).findbygjz(ladNo);
   }
 }


