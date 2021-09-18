 package com.what21.dao.impl;
 
 import com.what21.dao.OrderAcceptDao;
 import com.what21.model.OrderAccept;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class OrderAcceptDaoImpl
   implements OrderAcceptDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public OrderAccept findByExpressNumber(String expressNumber) {
     return ((OrderAcceptDao)this.sqlSessionTemplate.getMapper(OrderAcceptDao.class)).findByExpressNumber(expressNumber);
   }
   
   public void insert(OrderAccept order) {
     ((OrderAcceptDao)this.sqlSessionTemplate.getMapper(OrderAcceptDao.class)).insert(order);
   }
   
   public List<OrderAccept> findAll(Map<String, Object> map) {
     return ((OrderAcceptDao)this.sqlSessionTemplate.getMapper(OrderAcceptDao.class)).findAll(map);
   }
   
   public OrderAccept findById(int id) {
     return ((OrderAcceptDao)this.sqlSessionTemplate.getMapper(OrderAcceptDao.class)).findById(id);
   }
   
   public void update(String expressNumber) {
     ((OrderAcceptDao)this.sqlSessionTemplate.getMapper(OrderAcceptDao.class)).update(expressNumber);
   }
   
   public void updatePrintType(int id) {
     ((OrderAcceptDao)this.sqlSessionTemplate.getMapper(OrderAcceptDao.class)).updatePrintType(id);
   }
   
   public List<OrderAccept> findExpressNumber(Map<String, Object> map) {
     return ((OrderAcceptDao)this.sqlSessionTemplate.getMapper(OrderAcceptDao.class)).findExpressNumber(map);
   }
   
   public int updates(String expressNumber) {
     return ((OrderAcceptDao)this.sqlSessionTemplate.getMapper(OrderAcceptDao.class)).updates(expressNumber);
   }
   
   public int count(Map<String, Object> map) {
     return ((OrderAcceptDao)this.sqlSessionTemplate.getMapper(OrderAcceptDao.class)).count(map);
   }
 
   
   public OrderAccept findByIdToM(Integer id) {
     return ((OrderAcceptDao)this.sqlSessionTemplate.getMapper(OrderAcceptDao.class)).findByIdToM(id);
   }
 
   
   public int updateExpressmatch(String ids, String name) {
     return ((OrderAcceptDao)this.sqlSessionTemplate.getMapper(OrderAcceptDao.class)).updateExpressmatch(ids, name);
   }
 
   
   public int updateExpressM(OrderAccept ocp) {
     return ((OrderAcceptDao)this.sqlSessionTemplate.getMapper(OrderAcceptDao.class)).updateExpressM(ocp);
   }
 
   
   public List<OrderAccept> findByOrderNumber(String txLogisticID) {
     return ((OrderAcceptDao)this.sqlSessionTemplate.getMapper(OrderAcceptDao.class)).findByOrderNumber(txLogisticID);
   }
 
   
   public void delete(String[] expressNumbers) {
     ((OrderAcceptDao)this.sqlSessionTemplate.getMapper(OrderAcceptDao.class)).delete(expressNumbers);
   }
 }


