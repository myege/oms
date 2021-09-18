 package com.what21.dao.impl;
 
 import com.what21.dao.OutPcDao;
 import com.what21.model.OutPc;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class OutPcDaoImpl
   implements OutPcDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
 
   
   public List<OutPc> findAll(Map<String, Object> pageMap) {
     return ((OutPcDao)this.sqlSessionTemplate.getMapper(OutPcDao.class)).findAll(pageMap);
   }
 
 
   
   public int count(Map<String, Object> pageMap) {
     return ((OutPcDao)this.sqlSessionTemplate.getMapper(OutPcDao.class)).count(pageMap);
   }
 
 
   
   public int addO(OutPc o) {
     return ((OutPcDao)this.sqlSessionTemplate.getMapper(OutPcDao.class)).addO(o);
   }
 
 
   
   public int deleteO(String id) {
     return ((OutPcDao)this.sqlSessionTemplate.getMapper(OutPcDao.class)).deleteO(id);
   }
 }


