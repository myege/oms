 package com.what21.dao.impl;
 
 import com.what21.dao.OrderBondedSkuDao;
 import com.what21.model.Item;
 import com.what21.model.OrderBonded;
 import com.what21.model.OrderBondedPushSku;
 import com.what21.model.OrderBondedSku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class OrderBondedSkuDaoImpl
   implements OrderBondedSkuDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public int insert(OrderBondedSku orderBondedSku) {
     return ((OrderBondedSkuDao)this.sqlSessionTemplate.getMapper(OrderBondedSkuDao.class)).insert(orderBondedSku);
   }
 
   
   public int delete(String txLogisticID) {
     return ((OrderBondedSkuDao)this.sqlSessionTemplate.getMapper(OrderBondedSkuDao.class)).delete(txLogisticID);
   }
 
   
   public List<OrderBondedPushSku> findByTxLogisticID(String txLogisticID) {
     return ((OrderBondedSkuDao)this.sqlSessionTemplate.getMapper(OrderBondedSkuDao.class)).findByTxLogisticID(txLogisticID);
   }
 
   
   public List<OrderBondedSku> findById(String txLogisticID) {
     return ((OrderBondedSkuDao)this.sqlSessionTemplate.getMapper(OrderBondedSkuDao.class)).findById(txLogisticID);
   }
 
   
   public List<OrderBondedSku> Detailed(Map<String, Object> map) {
     return ((OrderBondedSkuDao)this.sqlSessionTemplate.getMapper(OrderBondedSkuDao.class)).Detailed(map);
   }
 
   
   public int count(String txLogisticID) {
     return ((OrderBondedSkuDao)this.sqlSessionTemplate.getMapper(OrderBondedSkuDao.class)).count(txLogisticID);
   }
 
   
   public void update(OrderBondedSku orderBondedSku) {
     ((OrderBondedSkuDao)this.sqlSessionTemplate.getMapper(OrderBondedSkuDao.class)).update(orderBondedSku);
   }
 
 
   
   public Item findItem(String itemSku) {
     return ((OrderBondedSkuDao)this.sqlSessionTemplate.getMapper(OrderBondedSkuDao.class)).findItem(itemSku);
   }
 
   
   public int batchInsert(List<OrderBondedSku> list) {
     return ((OrderBondedSkuDao)this.sqlSessionTemplate.getMapper(OrderBondedSkuDao.class)).batchInsert(list);
   }
 
   
   public List<OrderBondedSku> skuupd(String txLogisticID) {
     return ((OrderBondedSkuDao)this.sqlSessionTemplate.getMapper(OrderBondedSkuDao.class)).skuupd(txLogisticID);
   }
 
   
   public void updods(OrderBondedSku ou) {
     ((OrderBondedSkuDao)this.sqlSessionTemplate.getMapper(OrderBondedSkuDao.class)).updods(ou);
   }
 
   
   public void updodpc(OrderBondedSku ou) {
     ((OrderBondedSkuDao)this.sqlSessionTemplate.getMapper(OrderBondedSkuDao.class)).updodpc(ou);
   }
 
   
   public List<OrderBondedSku> findByTidList(List<OrderBonded> orderBondedList) {
     return ((OrderBondedSkuDao)this.sqlSessionTemplate.getMapper(OrderBondedSkuDao.class)).findByTidList(orderBondedList);
   }
 }


