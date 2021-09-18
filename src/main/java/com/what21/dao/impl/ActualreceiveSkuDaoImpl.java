 package com.what21.dao.impl;
 
 import com.what21.dao.ActualreceiveSkuDao;
 import com.what21.model.ActualreceiveSku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class ActualreceiveSkuDaoImpl
   implements ActualreceiveSkuDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
 
   
   public int count(Map<String, Object> pageMap) {
     return ((ActualreceiveSkuDao)this.sqlSessionTemplate.getMapper(ActualreceiveSkuDao.class)).count(pageMap);
   }
 
 
   
   public List<ActualreceiveSku> findAll(Map<String, Object> pageMap) {
     return ((ActualreceiveSkuDao)this.sqlSessionTemplate.getMapper(ActualreceiveSkuDao.class)).findAll(pageMap);
   }
 
 
   
   public List<ActualreceiveSku> findact(Map<String, Object> pageMap) {
     return ((ActualreceiveSkuDao)this.sqlSessionTemplate.getMapper(ActualreceiveSkuDao.class)).findact(pageMap);
   }
 
 
 
   
   public void insert(ActualreceiveSku actualreceiveSku) {
     ((ActualreceiveSkuDao)this.sqlSessionTemplate.getMapper(ActualreceiveSkuDao.class)).insert(actualreceiveSku);
   }
 
 
 
   
   public ActualreceiveSku findByA(String id) {
     return ((ActualreceiveSkuDao)this.sqlSessionTemplate.getMapper(ActualreceiveSkuDao.class)).findByA(id);
   }
 
 
   
   public int actdelete(String id) {
     return ((ActualreceiveSkuDao)this.sqlSessionTemplate.getMapper(ActualreceiveSkuDao.class)).actdelete(id);
   }
 
 
   
   public void aflag(ActualreceiveSku actualreceiveSku) {
     ((ActualreceiveSkuDao)this.sqlSessionTemplate.getMapper(ActualreceiveSkuDao.class)).aflag(actualreceiveSku);
   }
 
 
   
   public void asInsert(Object[] ob) {
     ((ActualreceiveSkuDao)this.sqlSessionTemplate.getMapper(ActualreceiveSkuDao.class)).asInsert(ob);
   }
 }


