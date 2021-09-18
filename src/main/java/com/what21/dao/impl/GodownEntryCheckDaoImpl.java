 package com.what21.dao.impl;
 
 import com.what21.dao.GodownEntryCheckDao;
 import com.what21.model.GodownEntryCheck;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class GodownEntryCheckDaoImpl
   implements GodownEntryCheckDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<GodownEntryCheck> findAll(Map<String, Object> map) {
     return ((GodownEntryCheckDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckDao.class)).findAll(map);
   }
 
   
   public List<GodownEntryCheck> find(Map<String, Object> pageMap) {
     return ((GodownEntryCheckDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckDao.class)).find(pageMap);
   }
 
   
   public int addGo(GodownEntryCheck g) {
     return ((GodownEntryCheckDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckDao.class)).addGo(g);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return ((GodownEntryCheckDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckDao.class)).count(pageMap);
   }
 
   
   public int deleteG(String id) {
     return ((GodownEntryCheckDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckDao.class)).deleteG(id);
   }
 
   
   public int countGodownEntryCheck() {
     return ((GodownEntryCheckDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckDao.class)).countGodownEntryCheck();
   }
 
   
   public GodownEntryCheck findByTotalMailNo(String totalMailNo) {
     return ((GodownEntryCheckDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckDao.class)).findByTotalMailNo(totalMailNo);
   }
 
   
   public void update(GodownEntryCheck g) {
     ((GodownEntryCheckDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckDao.class)).update(g);
   }
 
   
   public List<GodownEntryCheck> checkGo(Map<String, Object> map) {
     return ((GodownEntryCheckDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckDao.class)).checkGo(map);
   }
 
   
   public GodownEntryCheck findByPc(GodownEntryCheck g) {
     return ((GodownEntryCheckDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckDao.class)).findByPc(g);
   }
 
   
   public GodownEntryCheck findById(String id) {
     return ((GodownEntryCheckDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckDao.class)).findById(id);
   }
 }


