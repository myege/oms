 package com.what21.dao.impl;
 
 import com.what21.dao.OrderCommoditieDao;
 import com.what21.model.OrderCommoditie;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class OrderCommoditieDaoImpl
   implements OrderCommoditieDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<OrderCommoditie> findAll(Map<String, Object> map) {
     return ((OrderCommoditieDao)this.sqlSessionTemplate.getMapper(OrderCommoditieDao.class)).findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return ((OrderCommoditieDao)this.sqlSessionTemplate.getMapper(OrderCommoditieDao.class)).count(map);
   }
 
   
   public List<OrderCommoditie> findeXport(Integer id) {
     return ((OrderCommoditieDao)this.sqlSessionTemplate.getMapper(OrderCommoditieDao.class)).findeXport(id);
   }
 
 
   
   public List<OrderCommoditie> dateTime(Map<String, Object> map) {
     return ((OrderCommoditieDao)this.sqlSessionTemplate.getMapper(OrderCommoditieDao.class)).dateTime(map);
   }
 }


