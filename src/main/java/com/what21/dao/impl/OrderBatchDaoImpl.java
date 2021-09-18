 package com.what21.dao.impl;
 
 import com.what21.dao.OrderBatchDao;
 import com.what21.model.OrderBatch;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class OrderBatchDaoImpl
   implements OrderBatchDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<OrderBatch> findAll(Map<String, Object> paramMap) {
     return ((OrderBatchDao)this.sqlSessionTemplate.getMapper(OrderBatchDao.class)).findAll(paramMap);
   }
 
   
   public int count(Map<String, Object> map) {
     return ((OrderBatchDao)this.sqlSessionTemplate.getMapper(OrderBatchDao.class)).count(map);
   }
 
   
   public int insertBatch(OrderBatch orderBatch) {
     return ((OrderBatchDao)this.sqlSessionTemplate.getMapper(OrderBatchDao.class)).insertBatch(orderBatch);
   }
 
   
   public int updateid(OrderBatch orderBatch) {
     return ((OrderBatchDao)this.sqlSessionTemplate.getMapper(OrderBatchDao.class)).updateid(orderBatch);
   }
 
   
   public OrderBatch findBarchBond(int id) {
     return ((OrderBatchDao)this.sqlSessionTemplate.getMapper(OrderBatchDao.class)).findBarchBond(id);
   }
 }


