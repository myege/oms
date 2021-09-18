 package com.what21.dao.impl;
 
 import com.what21.dao.GodownEntryCheckSkuDao;
 import com.what21.model.GodownEntryCheckSku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class GodownEntryCheckSkuDaoImpl
   implements GodownEntryCheckSkuDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<GodownEntryCheckSku> findBySku(Map<String, Object> pageMap) {
     return ((GodownEntryCheckSkuDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckSkuDao.class)).findBySku(pageMap);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return ((GodownEntryCheckSkuDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckSkuDao.class)).count(pageMap);
   }
 
   
   public void add(GodownEntryCheckSku g) {
     ((GodownEntryCheckSkuDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckSkuDao.class)).add(g);
   }
 
   
   public GodownEntryCheckSku findByTotalMailNo(GodownEntryCheckSku g) {
     return ((GodownEntryCheckSkuDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckSkuDao.class)).findByTotalMailNo(g);
   }
 
   
   public List<GodownEntryCheckSku> findByBill(String billcode) {
     return ((GodownEntryCheckSkuDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckSkuDao.class)).findByBill(billcode);
   }
 
   
   public List<GodownEntryCheckSku> printout(Map<String, Object> pageMap) {
     return ((GodownEntryCheckSkuDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckSkuDao.class)).printout(pageMap);
   }
 
   
   public List<GodownEntryCheckSku> findAll(Map<String, Object> pageMap) {
     return ((GodownEntryCheckSkuDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckSkuDao.class)).findAll(pageMap);
   }
 
   
   public GodownEntryCheckSku findByMailNo(GodownEntryCheckSku g) {
     return ((GodownEntryCheckSkuDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckSkuDao.class)).findByMailNo(g);
   }
 
   
   public int updateByMailNo(GodownEntryCheckSku gec) {
     return ((GodownEntryCheckSkuDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckSkuDao.class)).updateByMailNo(gec);
   }
 
   
   public void delete(GodownEntryCheckSku godownEntryCheckSku) {
     ((GodownEntryCheckSkuDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckSkuDao.class)).delete(godownEntryCheckSku);
   }
 
   
   public void batchInsert(List<GodownEntryCheckSku> gecsList) {
     ((GodownEntryCheckSkuDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckSkuDao.class)).batchInsert(gecsList);
   }
 
 
   
   public int passCount(Map<String, Object> pageMap) {
     return ((GodownEntryCheckSkuDao)this.sqlSessionTemplate.getMapper(GodownEntryCheckSkuDao.class)).passCount(pageMap);
   }
 }


