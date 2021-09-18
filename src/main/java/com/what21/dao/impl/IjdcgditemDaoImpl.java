 package com.what21.dao.impl;
 
 import com.what21.dao.IjdcgditemDao;
 import com.what21.model.Ijdcgditem;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class IjdcgditemDaoImpl
   implements IjdcgditemDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
   
   public void insertBatch(List<Ijdcgditem> list) {
     ((IjdcgditemDao)this.sqlSessionTemplate.getMapper(IjdcgditemDao.class)).insertBatch(list);
   }
 
 
   
   public void delete(String poNo) {
     ((IjdcgditemDao)this.sqlSessionTemplate.getMapper(IjdcgditemDao.class)).delete(poNo);
   }
 
 
   
   public List<Ijdcgditem> findbygjz(String ladNo) {
     return ((IjdcgditemDao)this.sqlSessionTemplate.getMapper(IjdcgditemDao.class)).findbygjz(ladNo);
   }
 }


