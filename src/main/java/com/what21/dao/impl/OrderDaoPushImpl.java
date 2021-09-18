 package com.what21.dao.impl;
 
 import com.what21.dao.OrderPushDao;
 import com.what21.model.OrderPush;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class OrderDaoPushImpl
   implements OrderPushDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public OrderPush findByExpressNumber(String expressNumber) {
     return ((OrderPushDao)this.sqlSessionTemplate.getMapper(OrderPushDao.class)).findByExpressNumber(expressNumber);
   }
 
   
   public void insert(OrderPush order) {
     ((OrderPushDao)this.sqlSessionTemplate.getMapper(OrderPushDao.class)).insert(order);
   }
 
   
   public int count(int userId) {
     return ((OrderPushDao)this.sqlSessionTemplate.getMapper(OrderPushDao.class)).count(userId);
   }
 
   
   public List<OrderPush> findAll(Map<String, Object> map) {
     return ((OrderPushDao)this.sqlSessionTemplate.getMapper(OrderPushDao.class)).findAll(map);
   }
 
   
   public OrderPush findById(int id) {
     return ((OrderPushDao)this.sqlSessionTemplate.getMapper(OrderPushDao.class)).findById(id);
   }
 
   
   public List<OrderPush> findByPushType(int pushType) {
     return ((OrderPushDao)this.sqlSessionTemplate.getMapper(OrderPushDao.class)).findByPushType(pushType);
   }
 
   
   public void update(String expressNumber) {
     ((OrderPushDao)this.sqlSessionTemplate.getMapper(OrderPushDao.class)).update(expressNumber);
   }
 }


