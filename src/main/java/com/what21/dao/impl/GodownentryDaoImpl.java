 package com.what21.dao.impl;
 
 import com.what21.dao.GodownentryDao;
 import com.what21.model.Godownentry;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class GodownentryDaoImpl
   implements GodownentryDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<Godownentry> findAll(Map<String, Object> map) {
     return ((GodownentryDao)this.sqlSessionTemplate.getMapper(GodownentryDao.class)).findAll(map);
   }
 
   
   public List<Godownentry> find(Map<String, Object> pageMap) {
     return ((GodownentryDao)this.sqlSessionTemplate.getMapper(GodownentryDao.class)).find(pageMap);
   }
 
   
   public int addGo(Godownentry g) {
     return ((GodownentryDao)this.sqlSessionTemplate.getMapper(GodownentryDao.class)).addGo(g);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return ((GodownentryDao)this.sqlSessionTemplate.getMapper(GodownentryDao.class)).count(pageMap);
   }
 
   
   public int deleteG(String id) {
     return ((GodownentryDao)this.sqlSessionTemplate.getMapper(GodownentryDao.class)).deleteG(id);
   }
 
   
   public int countGodownentry() {
     return ((GodownentryDao)this.sqlSessionTemplate.getMapper(GodownentryDao.class)).countGodownentry();
   }
 
   
   public Godownentry findByTotalMailNo(String totalMailNo) {
     return ((GodownentryDao)this.sqlSessionTemplate.getMapper(GodownentryDao.class)).findByTotalMailNo(totalMailNo);
   }
 
   
   public void update(Godownentry g) {
     ((GodownentryDao)this.sqlSessionTemplate.getMapper(GodownentryDao.class)).update(g);
   }
 
   
   public List<Godownentry> checkGo(Map<String, Object> map) {
     return ((GodownentryDao)this.sqlSessionTemplate.getMapper(GodownentryDao.class)).checkGo(map);
   }
 
   
   public Godownentry findByPc(Godownentry g) {
     return ((GodownentryDao)this.sqlSessionTemplate.getMapper(GodownentryDao.class)).findByPc(g);
   }
 
   
   public Godownentry findById(String id) {
     return ((GodownentryDao)this.sqlSessionTemplate.getMapper(GodownentryDao.class)).findById(id);
   }
 }


