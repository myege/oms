 package com.what21.dao.impl;
 
 import com.what21.dao.OrderMailSkuDao;
 import com.what21.model.CompanyZyAndOrderMail;
 import com.what21.model.ItemForZy;
 import com.what21.model.OrderMail;
 import com.what21.model.OrderMailAndChangeOdd;
 import com.what21.model.OrderMailPushSku;
 import com.what21.model.OrderMailSku;
 import com.what21.model.OrderMailSkuAll;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class OrderMailSkuDaoImpl
   implements OrderMailSkuDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public int insert(OrderMailSku orderMailSku) {
     return ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).insert(orderMailSku);
   }
 
   
   public int delete(String txLogisticID) {
     return ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).delete(txLogisticID);
   }
 
   
   public List<OrderMailPushSku> findByTxLogisticID(String txLogisticID) {
     return ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).findByTxLogisticID(txLogisticID);
   }
 
   
   public List<OrderMailSku> findById(String txLogisticID) {
     return ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).findById(txLogisticID);
   }
 
   
   public List<OrderMailSku> detailedMailSku(Map<String, Object> map) {
     return ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).detailedMailSku(map);
   }
 
   
   public int count(String txLogisticID) {
     return ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).count(txLogisticID);
   }
 
   
   public ItemForZy findItemForZy(String itemSku) {
     return ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).findItemForZy(itemSku);
   }
 
   
   public int batchInsert(List<OrderMailSku> omsList) {
     return ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).batchInsert(omsList);
   }
 
   
   public void updateData(OrderMailSku orderMailSku) {
     ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).updateData(orderMailSku);
   }
 
   
   public List<OrderMailSku> findAllPrice(String totalMailNo) {
     return ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).findAllPrice(totalMailNo);
   }
   
   public List<OrderMailSku> findCostxy(String id) {
     return ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).findCostxy(id);
   }
 
   
   public List<OrderMailSku> findCostdy(String id) {
     return ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).findCostdy(id);
   }
 
   
   public List<OrderMailSku> findAllPriceByTxLogisticID(List<OrderMail> orderMailList) {
     return ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).findAllPriceByTxLogisticID(orderMailList);
   }
 
   
   public String odmTax(int id) {
     return ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).odmTax(id);
   }
 
   
   public int upodm(OrderMailSku orderMailSku) {
     return ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).upodm(orderMailSku);
   }
 
   
   public List<OrderMailSku> findByIdArrToCz(String[] idArr) {
     return ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).findByIdArrToCz(idArr);
   }
 
   
   public List<OrderMailSku> findByTidList(List<OrderMail> orderMailList) {
     return ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).findByTidList(orderMailList);
   }
 
   
   public List<OrderMailAndChangeOdd> findByTxLogisticID2(List<CompanyZyAndOrderMail> txLogisticID) {
     return ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).findByTxLogisticID2(txLogisticID);
   }
 
 
   
   public List<OrderMailSkuAll> findALLaboutSku() {
     return ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).findALLaboutSku();
   }
 
 
   
   public List<OrderMailSkuAll> findALLaboutSku_byIDS(String value) {
     return ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).findALLaboutSku_byIDS(value);
   }
 
 
   
   public List<OrderMail> findTxloginst(String value) {
     return ((OrderMailSkuDao)this.sqlSessionTemplate.getMapper(OrderMailSkuDao.class)).findTxloginst(value);
   }
 }


