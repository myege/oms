 package com.what21.dao.impl;
 
 import com.what21.dao.OrderCheckSkuDao;
 import com.what21.model.OrderCheckSku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class OrderCheckSkuDaoImpl
   implements OrderCheckSkuDao {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
 
   
   public List<OrderCheckSku> findBySku(Map<String, Object> pageMap) {
     return ((OrderCheckSkuDao)this.sqlSessionTemplate.getMapper(OrderCheckSkuDao.class)).findBySku(pageMap);
   }
 
 
   
   public int count(Map<String, Object> pageMap) {
     return ((OrderCheckSkuDao)this.sqlSessionTemplate.getMapper(OrderCheckSkuDao.class)).count(pageMap);
   }
 
 
   
   public void add(OrderCheckSku o) {
     ((OrderCheckSkuDao)this.sqlSessionTemplate.getMapper(OrderCheckSkuDao.class)).add(o);
   }
 
 
 
   
   public OrderCheckSku findId(Map<String, Object> pageMap) {
     return ((OrderCheckSkuDao)this.sqlSessionTemplate.getMapper(OrderCheckSkuDao.class)).findId(pageMap);
   }
 
   
   public List<OrderCheckSku> findAll(Map<String, Object> pageMap) {
     return ((OrderCheckSkuDao)this.sqlSessionTemplate.getMapper(OrderCheckSkuDao.class)).findAll(pageMap);
   }
 
   
   public List<OrderCheckSku> findByBillcode(String billcode) {
     return ((OrderCheckSkuDao)this.sqlSessionTemplate.getMapper(OrderCheckSkuDao.class)).findByBillcode(billcode);
   }
 }


