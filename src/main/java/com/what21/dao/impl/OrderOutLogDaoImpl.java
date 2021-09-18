 package com.what21.dao.impl;
 
 import com.what21.dao.OrderOutLogDao;
 import com.what21.model.OrderOutLog;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class OrderOutLogDaoImpl
   implements OrderOutLogDao {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void insert(OrderOutLog orderOutLog) {
     ((OrderOutLogDao)this.sqlSessionTemplate.getMapper(OrderOutLogDao.class)).insert(orderOutLog);
   }
 
   
   public OrderOutLog findLogByWaybillno(String waybillno) {
     return ((OrderOutLogDao)this.sqlSessionTemplate.getMapper(OrderOutLogDao.class)).findLogByWaybillno(waybillno);
   }
 }


