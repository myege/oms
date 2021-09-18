 package com.what21.service.impl;
 
 import com.what21.dao.ConsignmentDao;
 import com.what21.model.ConsignmentQueryVo;
 import com.what21.model.JjDelivery;
 import com.what21.model.JjPickingSku;
 import com.what21.service.ConsignmentService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 @Service
 @Transactional
 public class ConsignmentServiceImpl
   implements ConsignmentService
 {
   @Autowired
   private ConsignmentDao consignmentDao;
   
   public List<JjDelivery> findAll(ConsignmentQueryVo consignmentQueryVo) {
     return this.consignmentDao.findAll(consignmentQueryVo);
   }
 
 
   
   public int findCount() {
     return this.consignmentDao.findCount();
   }
 
 
   
   public JjPickingSku checkBillInPickingSku(JjDelivery delivery) {
     return this.consignmentDao.checkBillInPickingSku(delivery);
   }
 
 
   
   public int insertJjDelivery(ConsignmentQueryVo consignmentQueryVo) {
     return this.consignmentDao.insertJjDelivery(consignmentQueryVo);
   }
 
 
   
   public int checkBillInDelivery(JjDelivery delivery) {
     return this.consignmentDao.checkBillInDelivery(delivery);
   }
 }


