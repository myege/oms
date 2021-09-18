 package com.what21.service.impl;
 
 import com.what21.dao.OrderMailSkuDao;
 import com.what21.model.CompanyZyAndOrderMail;
 import com.what21.model.OrderMail;
 import com.what21.model.OrderMailAndChangeOdd;
 import com.what21.model.OrderMailSku;
 import com.what21.model.OrderMailSkuAll;
 import com.what21.service.OrderMailSkuService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class OrderMailSkuServiceImpl
   implements OrderMailSkuService
 {
   @Autowired
   private OrderMailSkuDao orderMailSkuDao;
   
   public int insert(OrderMailSku orderMailSku) {
     return this.orderMailSkuDao.insert(orderMailSku);
   }
 
   
   public List<OrderMailSku> detailedMailSku(Map<String, Object> map) {
     return this.orderMailSkuDao.detailedMailSku(map);
   }
 
   
   public int count(String txLogisticID) {
     return this.orderMailSkuDao.count(txLogisticID);
   }
 
   
   public List<OrderMailSku> findAllPrice(String totalMailNo) {
     return this.orderMailSkuDao.findAllPrice(totalMailNo);
   }
 
   
   public List<OrderMailSku> findCostxy(String id) {
     return this.orderMailSkuDao.findCostxy(id);
   }
 
   
   public List<OrderMailSku> findCostdy(String id) {
     return this.orderMailSkuDao.findCostdy(id);
   }
 
 
 
   
   public String odmTax(int id) {
     return this.orderMailSkuDao.odmTax(id);
   }
 
   
   public int upodm(OrderMailSku orderMailSku) {
     return this.orderMailSkuDao.upodm(orderMailSku);
   }
   
   public List<OrderMailAndChangeOdd> findByTxLogisticID2(List<CompanyZyAndOrderMail> txLogisticID) {
     return this.orderMailSkuDao.findByTxLogisticID2(txLogisticID);
   }
 
 
   
   public List<OrderMailSkuAll> findALLaboutSku() {
     return this.orderMailSkuDao.findALLaboutSku();
   }
 
 
   
   public List<OrderMailSkuAll> findALLaboutSku_byIDS(String value) {
     return this.orderMailSkuDao.findALLaboutSku_byIDS(value);
   }
 
 
   
   public List<OrderMail> findTxloginst(String value) {
     return this.orderMailSkuDao.findTxloginst(value);
   }
 }


