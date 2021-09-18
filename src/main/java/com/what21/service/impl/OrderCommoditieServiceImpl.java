 package com.what21.service.impl;
 
 import com.what21.dao.OrderCommoditieDao;
 import com.what21.model.OrderCommoditie;
 import com.what21.service.OrderCommoditieService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 
 
 @Service
 @Transactional
 public class OrderCommoditieServiceImpl
   implements OrderCommoditieService
 {
   @Autowired
   public OrderCommoditieDao orderCommoditieDao;
   
   public List<OrderCommoditie> findAll(Map<String, Object> map) {
     return this.orderCommoditieDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return this.orderCommoditieDao.count(map);
   }
 
   
   public List<OrderCommoditie> findeXport(Integer id) {
     return this.orderCommoditieDao.findeXport(id);
   }
 
   
   public List<OrderCommoditie> dateTime(Map<String, Object> map) {
     return this.orderCommoditieDao.dateTime(map);
   }
 }


