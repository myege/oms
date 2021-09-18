 package com.what21.service.impl;
 
 import com.what21.dao.OrderBondedSkuDao;
 import com.what21.model.OrderBondedSku;
 import com.what21.service.OrderBondedSkuService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 @Service
 public class OrderBondedSkuServiceImpl
   implements OrderBondedSkuService
 {
   @Autowired
   private OrderBondedSkuDao orderBondedSkuDao;
   
   public int insert(OrderBondedSku orderBondedSku) {
     return this.orderBondedSkuDao.insert(orderBondedSku);
   }
 
   
   public List<OrderBondedSku> Detailed(Map<String, Object> map) {
     return this.orderBondedSkuDao.Detailed(map);
   }
 
   
   public int count(String txLogisticID) {
     return this.orderBondedSkuDao.count(txLogisticID);
   }
 
 
   
   public List<OrderBondedSku> findById(String txLogisticID) {
     return this.orderBondedSkuDao.findById(txLogisticID);
   }
 
 
   
   public void update(OrderBondedSku orderBondedSku) {
     this.orderBondedSkuDao.update(orderBondedSku);
   }
 
   
   public List<OrderBondedSku> skuupd(String txLogisticID) {
     return this.orderBondedSkuDao.skuupd(txLogisticID);
   }
 
   
   public void updods(OrderBondedSku ou) {
     this.orderBondedSkuDao.updods(ou);
   }
 }


