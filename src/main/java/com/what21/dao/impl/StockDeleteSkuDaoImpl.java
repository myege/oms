 package com.what21.dao.impl;
 
 import com.what21.dao.StockDeleteSkuDao;
 import com.what21.model.StockDeleteSku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class StockDeleteSkuDaoImpl
   implements StockDeleteSkuDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
 
   
   public List<StockDeleteSku> findAll(Map<String, Object> map) {
     return ((StockDeleteSkuDao)this.sqlSessionTemplate.getMapper(StockDeleteSkuDao.class)).findAll(map);
   }
 
   
   public int count(String stockDeleteId) {
     return ((StockDeleteSkuDao)this.sqlSessionTemplate.getMapper(StockDeleteSkuDao.class)).count(stockDeleteId);
   }
 
 
   
   public int insert(StockDeleteSku stockDeleteSku) {
     return ((StockDeleteSkuDao)this.sqlSessionTemplate.getMapper(StockDeleteSkuDao.class)).insert(stockDeleteSku);
   }
 
 
   
   public int delete(String stockDeleteId) {
     return ((StockDeleteSkuDao)this.sqlSessionTemplate.getMapper(StockDeleteSkuDao.class)).delete(stockDeleteId);
   }
 
 
   
   public List<StockDeleteSku> findByStockDeleteId(String stockDeleteId) {
     return ((StockDeleteSkuDao)this.sqlSessionTemplate.getMapper(StockDeleteSkuDao.class)).findByStockDeleteId(stockDeleteId);
   }
 }


