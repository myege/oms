 package com.what21.dao.impl;
 
 import com.what21.dao.OrderPhSkuDao;
 import com.what21.model.Item;
 import com.what21.model.OrderPhPushSku;
 import com.what21.model.OrderPhSku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class OrderPhSkuDaoImpl
   implements OrderPhSkuDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public int insert(OrderPhSku orderPhSku) {
     return ((OrderPhSkuDao)this.sqlSessionTemplate.getMapper(OrderPhSkuDao.class)).insert(orderPhSku);
   }
 
   
   public int delete(String txLogisticID) {
     return ((OrderPhSkuDao)this.sqlSessionTemplate.getMapper(OrderPhSkuDao.class)).delete(txLogisticID);
   }
 
   
   public List<OrderPhPushSku> findByTxLogisticID(String txLogisticID) {
     return ((OrderPhSkuDao)this.sqlSessionTemplate.getMapper(OrderPhSkuDao.class)).findByTxLogisticID(txLogisticID);
   }
 
   
   public List<OrderPhSku> findById(String txLogisticID) {
     return ((OrderPhSkuDao)this.sqlSessionTemplate.getMapper(OrderPhSkuDao.class)).findById(txLogisticID);
   }
 
   
   public List<OrderPhSku> Detailed(Map<String, Object> map) {
     return ((OrderPhSkuDao)this.sqlSessionTemplate.getMapper(OrderPhSkuDao.class)).Detailed(map);
   }
 
   
   public int count(String txLogisticID) {
     return ((OrderPhSkuDao)this.sqlSessionTemplate.getMapper(OrderPhSkuDao.class)).count(txLogisticID);
   }
 
   
   public void update(OrderPhSku orderPhSku) {
     ((OrderPhSkuDao)this.sqlSessionTemplate.getMapper(OrderPhSkuDao.class)).update(orderPhSku);
   }
 
 
   
   public Item findItem(String itemSku) {
     return ((OrderPhSkuDao)this.sqlSessionTemplate.getMapper(OrderPhSkuDao.class)).findItem(itemSku);
   }
 
   
   public int batchInsert(List<OrderPhSku> list) {
     return ((OrderPhSkuDao)this.sqlSessionTemplate.getMapper(OrderPhSkuDao.class)).batchInsert(list);
   }
 
   
   public List<OrderPhSku> skuupd(String txLogisticID) {
     return ((OrderPhSkuDao)this.sqlSessionTemplate.getMapper(OrderPhSkuDao.class)).skuupd(txLogisticID);
   }
 
   
   public void updods(OrderPhSku ou) {
     ((OrderPhSkuDao)this.sqlSessionTemplate.getMapper(OrderPhSkuDao.class)).updods(ou);
   }
 }


