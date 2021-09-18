 package com.what21.dao.impl;
 
 import com.what21.dao.OrderDsDao;
 import com.what21.model.OrderDs;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class OrderDsDaoImpl
   implements OrderDsDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void insert(OrderDs map) {
     this.sqlSessionTemplate.selectList(String.valueOf(OrderDs.class.getName()) + ".insert", map);
   }
 
 
   
   public OrderDs findByOrder(String ds) {
     return (OrderDs)this.sqlSessionTemplate.selectOne(String.valueOf(OrderDs.class.getName()) + ".findByOrder", ds);
   }
 }


