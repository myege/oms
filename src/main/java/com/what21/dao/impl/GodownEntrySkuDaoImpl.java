 package com.what21.dao.impl;
 
 import com.what21.dao.GodownEntrySkuDao;
 import com.what21.model.GodownEntrySku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class GodownEntrySkuDaoImpl
   implements GodownEntrySkuDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
 
   
   public List<GodownEntrySku> findBySku(Map<String, Object> pageMap) {
     return ((GodownEntrySkuDao)this.sqlSessionTemplate.getMapper(GodownEntrySkuDao.class)).findBySku(pageMap);
   }
 
 
   
   public int count(Map<String, Object> pageMap) {
     return ((GodownEntrySkuDao)this.sqlSessionTemplate.getMapper(GodownEntrySkuDao.class)).count(pageMap);
   }
 
 
 
   
   public void add(GodownEntrySku g) {
     ((GodownEntrySkuDao)this.sqlSessionTemplate.getMapper(GodownEntrySkuDao.class)).add(g);
   }
 
 
   
   public GodownEntrySku findByTotalMailNo(GodownEntrySku g) {
     return ((GodownEntrySkuDao)this.sqlSessionTemplate.getMapper(GodownEntrySkuDao.class)).findByTotalMailNo(g);
   }
 
   
   public List<GodownEntrySku> findByBill(String billcode) {
     return ((GodownEntrySkuDao)this.sqlSessionTemplate.getMapper(GodownEntrySkuDao.class)).findByBill(billcode);
   }
 
   
   public List<GodownEntrySku> printout(Map<String, Object> pageMap) {
     return ((GodownEntrySkuDao)this.sqlSessionTemplate.getMapper(GodownEntrySkuDao.class)).printout(pageMap);
   }
 
   
   public List<GodownEntrySku> findAll(Map<String, Object> pageMap) {
     return ((GodownEntrySkuDao)this.sqlSessionTemplate.getMapper(GodownEntrySkuDao.class)).findAll(pageMap);
   }
 
   
   public List<GodownEntrySku> findByMailNo(GodownEntrySku g) {
     return ((GodownEntrySkuDao)this.sqlSessionTemplate.getMapper(GodownEntrySkuDao.class)).findByMailNo(g);
   }
 
   
   public void batchInsert(List<GodownEntrySku> gesList) {
     ((GodownEntrySkuDao)this.sqlSessionTemplate.getMapper(GodownEntrySkuDao.class)).batchInsert(gesList);
   }
 
   
   public int delete(GodownEntrySku godownEntrySku) {
     return ((GodownEntrySkuDao)this.sqlSessionTemplate.getMapper(GodownEntrySkuDao.class)).delete(godownEntrySku);
   }
 
   
   public int updateByMailNo(GodownEntrySku godownEntrySku) {
     return ((GodownEntrySkuDao)this.sqlSessionTemplate.getMapper(GodownEntrySkuDao.class)).updateByMailNo(godownEntrySku);
   }
 }


