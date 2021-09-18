 package com.what21.service.impl;
 
 import com.what21.dao.OrderPhSkuDao;
 import com.what21.model.OrderPhSku;
 import com.what21.service.OrderPhSkuService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 @Service
 public class OrderPhSkuServiceImpl
   implements OrderPhSkuService
 {
   @Autowired
   private OrderPhSkuDao OrderPhSkuDao;
   
   public int insert(OrderPhSku orderPhSku) {
     return this.OrderPhSkuDao.insert(orderPhSku);
   }
 
   
   public List<OrderPhSku> Detailed(Map<String, Object> map) {
     return this.OrderPhSkuDao.Detailed(map);
   }
 
   
   public int count(String txLogisticID) {
     return this.OrderPhSkuDao.count(txLogisticID);
   }
 
 
   
   public List<OrderPhSku> findById(String txLogisticID) {
     return this.OrderPhSkuDao.findById(txLogisticID);
   }
 
 
   
   public void update(OrderPhSku orderPhSku) {
     this.OrderPhSkuDao.update(orderPhSku);
   }
 
   
   public List<OrderPhSku> skuupd(String txLogisticID) {
     return this.OrderPhSkuDao.skuupd(txLogisticID);
   }
 
   
   public void updods(OrderPhSku ou) {
     this.OrderPhSkuDao.updods(ou);
   }
 }


