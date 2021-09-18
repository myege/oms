 package com.what21.service.impl;
 
 import com.what21.dao.OrderCheckSkuDao;
 import com.what21.model.OrderCheckSku;
 import com.what21.service.OrderCheckSkuService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class OrderCheckSkuServiceImpl
   implements OrderCheckSkuService
 {
   @Autowired
   private OrderCheckSkuDao orderCheckSkuDao;
   
   public List<OrderCheckSku> findBySku(Map<String, Object> pageMap) {
     return this.orderCheckSkuDao.findBySku(pageMap);
   }
 
 
   
   public int count(Map<String, Object> pageMap) {
     return this.orderCheckSkuDao.count(pageMap);
   }
 
 
   
   public void add(OrderCheckSku o) {
     this.orderCheckSkuDao.add(o);
   }
 
 
   
   public OrderCheckSku findId(Map<String, Object> pageMap) {
     return this.orderCheckSkuDao.findId(pageMap);
   }
 
   
   public List<OrderCheckSku> findAll(Map<String, Object> pageMap) {
     return this.orderCheckSkuDao.findAll(pageMap);
   }
 
   
   public List<OrderCheckSku> findByBillcode(String billcode) {
     return this.orderCheckSkuDao.findByBillcode(billcode);
   }
 }


