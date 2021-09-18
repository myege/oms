 package com.what21.dao.impl;
 
 import com.what21.dao.OrderCheckDao;
 import com.what21.model.OrderCheck;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class OrderCheckDaoImpl
   implements OrderCheckDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
   
   public List<OrderCheck> findAll(Map<String, Object> map) {
     return ((OrderCheckDao)this.sqlSessionTemplate.getMapper(OrderCheckDao.class)).findAll(map);
   }
   
   public int countOrderCheck() {
     return ((OrderCheckDao)this.sqlSessionTemplate.getMapper(OrderCheckDao.class)).countOrderCheck();
   }
   
   public int deleteO(String id) {
     return ((OrderCheckDao)this.sqlSessionTemplate.getMapper(OrderCheckDao.class)).deleteO(id);
   }
   
   public int addO(OrderCheck o) {
     return ((OrderCheckDao)this.sqlSessionTemplate.getMapper(OrderCheckDao.class)).addO(o);
   }
   
   public List<OrderCheck> find(Map<String, Object> pageMap) {
     return ((OrderCheckDao)this.sqlSessionTemplate.getMapper(OrderCheckDao.class)).find(pageMap);
   }
   
   public int count(Map<String, Object> pageMap) {
     return ((OrderCheckDao)this.sqlSessionTemplate.getMapper(OrderCheckDao.class)).count(pageMap);
   }
 
   
   public OrderCheck findByPc(OrderCheck o) {
     return ((OrderCheckDao)this.sqlSessionTemplate.getMapper(OrderCheckDao.class)).findByPc(o);
   }
 }


