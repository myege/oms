 package com.what21.dao.impl;
 
 import com.what21.dao.OrderPhDao;
 import com.what21.model.OrderPh;
 import com.what21.model.OrderPhPush;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class OrderPhDaoImpl
   implements OrderPhDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<OrderPh> findAll(Map<String, Object> map) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).findAll(map);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).count(pageMap);
   }
 
   
   public OrderPh findByTxLogisticID(String txLogisticID) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).findByTxLogisticID(txLogisticID);
   }
 
   
   public OrderPhPush findById(Integer id) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).findById(id);
   }
 
   
   public OrderPh findByIdToBG(Integer id) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).findByIdToBG(id);
   }
 
   
   public OrderPh findByIdToWJ(Integer id) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).findByIdToWJ(id);
   }
 
   
   public OrderPh findByIdToWms(Integer id) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).findByIdToWms(id);
   }
 
   
   public int insert(OrderPh orderPh) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).insert(orderPh);
   }
 
   
   public int delete(String txLogisticID) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).delete(txLogisticID);
   }
 
   
   public int updateAuditstatus(OrderPh orderPh) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).updateAuditstatus(orderPh);
   }
 
   
   public int updateAuditstatusByTxLogisticID(OrderPh orderPh) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).updateAuditstatusByTxLogisticID(orderPh);
   }
 
   
   public int updateExpressParam(OrderPh orderPh) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).updateExpressParam(orderPh);
   }
 
   
   public int updateIspost(OrderPh orderPh) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).updateIspost(orderPh);
   }
 
   
   public int updateIsCustoms(OrderPh orderPh) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).updateIsCustoms(orderPh);
   }
 
   
   public void updateCountAndWeight(OrderPh orderPh) {
     ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).updateCountAndWeight(orderPh);
   }
 
   
   public int updateIsCustomsByTxLogisticID(OrderPh orderPh) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).updateIsCustomsByTxLogisticID(orderPh);
   }
 
   
   public List<OrderPh> exportOrder(Map<String, Object> paramMap) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).exportOrder(paramMap);
   }
 
   
   public int updateIsPushToWms(String[] idArr) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).updateIsPushToWms(idArr);
   }
 
   
   public List<OrderPh> findAreaByName(String paramName) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).findAreaByName(paramName);
   }
 
   
   public List<OrderPh> findTxLogisticID() {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).findTxLogisticID();
   }
 
   
   public int payNumber(OrderPh orderPh) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).payNumber(orderPh);
   }
 
   
   public int updateIsPushDd(OrderPh orderPh) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).updateIsPushDd(orderPh);
   }
 
   
   public int updateIsPushQd(OrderPh orderPh) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).updateIsPushQd(orderPh);
   }
 
   
   public OrderPh findByMailNo(String mailNo) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).findByMailNo(mailNo);
   }
 
   
   public List<OrderPh> hyjStatus(String merchantNum) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).hyjStatus(merchantNum);
   }
 
   
   public int updateStatus(String merchantNum) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).updateStatus(merchantNum);
   }
 
   
   public int batchInsert(Object[] ob) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).batchInsert(ob);
   }
 
   
   public List<OrderPh> findOrderStatus(String[] idArr) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).findOrderStatus(idArr);
   }
 
   
   public List<OrderPh> findByObArr(Object[] obArr) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).findByObArr(obArr);
   }
 
   
   public int findaddress(OrderPh orderPh) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).findaddress(orderPh);
   }
 
   
   public int updaddress(OrderPh orderPh) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).updaddress(orderPh);
   }
 
   
   public List<OrderPh> odbdateTime(Map<String, Object> pageMap) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).odbdateTime(pageMap);
   }
 
   
   public void deletetx(String id) {
     ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).deletetx(id);
   }
 
   
   public int upm(OrderPh ob) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).upm(ob);
   }
 
   
   public List<OrderPh> findTxId(String id) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).findTxId(id);
   }
 
   
   public List<OrderPh> findCs(String idd) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).findCs(idd);
   }
 
   
   public int editodIdN(OrderPh orderPh) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).editodIdN(orderPh);
   }
 
   
   public void updateInvtno(OrderPh orderPh) {
     ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).updateInvtno(orderPh);
   }
 
 
   
   public OrderPh findOneById(int id) {
     return ((OrderPhDao)this.sqlSessionTemplate.getMapper(OrderPhDao.class)).findOneById(id);
   }
 }


