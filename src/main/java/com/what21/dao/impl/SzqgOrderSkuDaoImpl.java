 package com.what21.dao.impl;
 
 import com.what21.dao.SzqgOrderSkuDao;
 import com.what21.model.Item;
 import com.what21.model.SzqgOrderSku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class SzqgOrderSkuDaoImpl
   implements SzqgOrderSkuDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public int insert(SzqgOrderSku szqgOrderSku) {
     return ((SzqgOrderSkuDao)this.sqlSessionTemplate.getMapper(SzqgOrderSkuDao.class)).insert(szqgOrderSku);
   }
 
   
   public int delete(String txLogisticID) {
     return ((SzqgOrderSkuDao)this.sqlSessionTemplate.getMapper(SzqgOrderSkuDao.class)).delete(txLogisticID);
   }
 
 
   
   public List<SzqgOrderSku> findById(String txLogisticID) {
     return ((SzqgOrderSkuDao)this.sqlSessionTemplate.getMapper(SzqgOrderSkuDao.class)).findById(txLogisticID);
   }
 
   
   public List<SzqgOrderSku> Detailed(Map<String, Object> map) {
     return ((SzqgOrderSkuDao)this.sqlSessionTemplate.getMapper(SzqgOrderSkuDao.class)).Detailed(map);
   }
 
   
   public int count(String txLogisticID) {
     return ((SzqgOrderSkuDao)this.sqlSessionTemplate.getMapper(SzqgOrderSkuDao.class)).count(txLogisticID);
   }
 
   
   public void update(SzqgOrderSku szqgOrderSku) {
     ((SzqgOrderSkuDao)this.sqlSessionTemplate.getMapper(SzqgOrderSkuDao.class)).update(szqgOrderSku);
   }
 
 
   
   public Item findItem(String itemSku) {
     return ((SzqgOrderSkuDao)this.sqlSessionTemplate.getMapper(SzqgOrderSkuDao.class)).findItem(itemSku);
   }
 
   
   public int batchInsert(List<SzqgOrderSku> list) {
     return ((SzqgOrderSkuDao)this.sqlSessionTemplate.getMapper(SzqgOrderSkuDao.class)).batchInsert(list);
   }
 
   
   public List<SzqgOrderSku> skuupd(String txLogisticID) {
     return ((SzqgOrderSkuDao)this.sqlSessionTemplate.getMapper(SzqgOrderSkuDao.class)).skuupd(txLogisticID);
   }
 
   
   public void updods(SzqgOrderSku ou) {
     ((SzqgOrderSkuDao)this.sqlSessionTemplate.getMapper(SzqgOrderSkuDao.class)).updods(ou);
   }
 
   
   public void updodpc(SzqgOrderSku ou) {
     ((SzqgOrderSkuDao)this.sqlSessionTemplate.getMapper(SzqgOrderSkuDao.class)).updodpc(ou);
   }
   
   public int batchUpdate(List<SzqgOrderSku> list) {
     return ((SzqgOrderSkuDao)this.sqlSessionTemplate.getMapper(SzqgOrderSkuDao.class)).batchInsert(list);
   }
 }


