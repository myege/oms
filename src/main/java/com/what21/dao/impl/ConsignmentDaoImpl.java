 package com.what21.dao.impl;
 
 import com.what21.dao.ConsignmentDao;
 import com.what21.model.ConsignmentQueryVo;
 import com.what21.model.JjDelivery;
 import com.what21.model.JjPickingSku;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class ConsignmentDaoImpl
   implements ConsignmentDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<JjDelivery> findAll(ConsignmentQueryVo consignmentQueryVo) {
     return ((ConsignmentDao)this.sqlSessionTemplate.getMapper(ConsignmentDao.class)).findAll(consignmentQueryVo);
   }
 
 
   
   public int findCount() {
     return ((ConsignmentDao)this.sqlSessionTemplate.getMapper(ConsignmentDao.class)).findCount();
   }
 
 
   
   public JjPickingSku checkBillInPickingSku(JjDelivery delivery) {
     return ((ConsignmentDao)this.sqlSessionTemplate.getMapper(ConsignmentDao.class)).checkBillInPickingSku(delivery);
   }
 
 
   
   public int insertJjDelivery(ConsignmentQueryVo consignmentQueryVo) {
     return ((ConsignmentDao)this.sqlSessionTemplate.getMapper(ConsignmentDao.class)).insertJjDelivery(consignmentQueryVo);
   }
 
 
   
   public int checkBillInDelivery(JjDelivery delivery) {
     return ((ConsignmentDao)this.sqlSessionTemplate.getMapper(ConsignmentDao.class)).checkBillInDelivery(delivery);
   }
 }


