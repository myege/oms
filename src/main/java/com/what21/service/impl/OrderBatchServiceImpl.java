 package com.what21.service.impl;
 
 import com.what21.dao.OrderBatchDao;
 import com.what21.model.OrderBatch;
 import com.what21.service.OrderBatchService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 
 
 
 @Service
 @Transactional
 public class OrderBatchServiceImpl
   implements OrderBatchService
 {
   @Autowired
   private OrderBatchDao orderBacthDao;
   
   public List<OrderBatch> findAll(Map<String, Object> paramMap) {
     return this.orderBacthDao.findAll(paramMap);
   }
 
   
   public int count(Map<String, Object> map) {
     return this.orderBacthDao.count(map);
   }
 
   
   public int insertBatch(OrderBatch orderBatch) {
     return this.orderBacthDao.insertBatch(orderBatch);
   }
 
   
   public int updateid(OrderBatch orderBatch) {
     return this.orderBacthDao.updateid(orderBatch);
   }
 
   
   public OrderBatch findBarchBond(int id) {
     return this.orderBacthDao.findBarchBond(id);
   }
 }


