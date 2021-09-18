 package com.what21.service.impl;
 
 import com.what21.dao.OrderCheckDao;
 import com.what21.model.OrderCheck;
 import com.what21.service.OrderCheckService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 @Service
 public class OrderCheckServiceImpl
   implements OrderCheckService
 {
   @Autowired
   private OrderCheckDao orderCheckDao;
   
   public List<OrderCheck> getAll(Map<String, Object> map) {
     return this.orderCheckDao.findAll(map);
   }
 
   
   public int countOrderCheck() {
     return this.orderCheckDao.countOrderCheck();
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return this.orderCheckDao.count(pageMap);
   }
 
   
   public List<OrderCheck> find(Map<String, Object> pageMap) {
     return this.orderCheckDao.find(pageMap);
   }
 
   
   public int addO(OrderCheck o) {
     return this.orderCheckDao.addO(o);
   }
 
   
   public int deleteO(String id) {
     return this.orderCheckDao.deleteO(id);
   }
 
 
   
   public OrderCheck findByPc(OrderCheck o) {
     return this.orderCheckDao.findByPc(o);
   }
 }


