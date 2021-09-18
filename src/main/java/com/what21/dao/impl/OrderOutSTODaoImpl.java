 package com.what21.dao.impl;
 
 import com.what21.dao.OrderOutSTODao;
 import com.what21.model.OrderOutSTOCustom;
 import com.what21.model.OrderOutSTOQueryVo;
 import com.what21.model.TOrderOutSto;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class OrderOutSTODaoImpl
   implements OrderOutSTODao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<OrderOutSTOCustom> findAll(OrderOutSTOQueryVo orderOutSTOQueryVo) {
     return ((OrderOutSTODao)this.sqlSessionTemplate.getMapper(OrderOutSTODao.class)).findAll(orderOutSTOQueryVo);
   }
 
   
   public Integer countAll(OrderOutSTOQueryVo orderOutSTOQueryVo) {
     return ((OrderOutSTODao)this.sqlSessionTemplate.getMapper(OrderOutSTODao.class)).countAll(orderOutSTOQueryVo);
   }
 
   
   public void insertBatch(List<TOrderOutSto> list) {
     ((OrderOutSTODao)this.sqlSessionTemplate.getMapper(OrderOutSTODao.class)).insertBatch(list);
   }
 
   
   public List<OrderOutSTOCustom> findByIds(int[] ids) {
     return ((OrderOutSTODao)this.sqlSessionTemplate.getMapper(OrderOutSTODao.class)).findByIds(ids);
   }
 
   
   public void updateStatus(Integer id) {
     ((OrderOutSTODao)this.sqlSessionTemplate.getMapper(OrderOutSTODao.class)).updateStatus(id);
   }
 
 
   
   public void deleteByIds(int[] ids) {
     ((OrderOutSTODao)this.sqlSessionTemplate.getMapper(OrderOutSTODao.class)).deleteByIds(ids);
   }
 
 
   
   public void deleteByWayBillnos(List<String> waybillnos) {
     ((OrderOutSTODao)this.sqlSessionTemplate.getMapper(OrderOutSTODao.class)).deleteByWayBillnos(waybillnos);
   }
 
 
   
   public void deleteNoNeed() {
     ((OrderOutSTODao)this.sqlSessionTemplate.getMapper(OrderOutSTODao.class)).deleteNoNeed();
   }
 
 
   
   public List<OrderOutSTOCustom> findByBillno(String totalWayBill) {
     return ((OrderOutSTODao)this.sqlSessionTemplate.getMapper(OrderOutSTODao.class)).findByBillno(totalWayBill);
   }
 }


