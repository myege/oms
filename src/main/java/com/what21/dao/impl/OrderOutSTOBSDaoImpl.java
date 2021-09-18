 package com.what21.dao.impl;
 
 import com.what21.dao.OrderOutSTOBSDao;
 import com.what21.model.OrderOutSTOCustom;
 import com.what21.model.OrderOutSTOCustomBS;
 import com.what21.model.OrderOutSTOQueryVoBS;
 import com.what21.model.TOrderOutStoBS;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class OrderOutSTOBSDaoImpl
   implements OrderOutSTOBSDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<OrderOutSTOCustom> findAll(OrderOutSTOQueryVoBS orderOutSTOQueryVo) {
     return ((OrderOutSTOBSDao)this.sqlSessionTemplate.getMapper(OrderOutSTOBSDao.class)).findAll(orderOutSTOQueryVo);
   }
 
   
   public Integer countAll(OrderOutSTOQueryVoBS orderOutSTOQueryVo) {
     return ((OrderOutSTOBSDao)this.sqlSessionTemplate.getMapper(OrderOutSTOBSDao.class)).countAll(orderOutSTOQueryVo);
   }
 
   
   public void insertBatch(List<TOrderOutStoBS> list) {
     ((OrderOutSTOBSDao)this.sqlSessionTemplate.getMapper(OrderOutSTOBSDao.class)).insertBatch(list);
   }
 
   
   public List<OrderOutSTOCustomBS> findByIds(int[] ids) {
     return ((OrderOutSTOBSDao)this.sqlSessionTemplate.getMapper(OrderOutSTOBSDao.class)).findByIds(ids);
   }
 
   
   public void updateStatus(Integer id) {
     ((OrderOutSTOBSDao)this.sqlSessionTemplate.getMapper(OrderOutSTOBSDao.class)).updateStatus(id);
   }
 
 
   
   public void deleteByIds(int[] ids) {
     ((OrderOutSTOBSDao)this.sqlSessionTemplate.getMapper(OrderOutSTOBSDao.class)).deleteByIds(ids);
   }
 
 
   
   public void deleteByWayBillnos(List<String> waybillnos) {
     ((OrderOutSTOBSDao)this.sqlSessionTemplate.getMapper(OrderOutSTOBSDao.class)).deleteByWayBillnos(waybillnos);
   }
 
 
   
   public void deleteNoNeed() {
     ((OrderOutSTOBSDao)this.sqlSessionTemplate.getMapper(OrderOutSTOBSDao.class)).deleteNoNeed();
   }
 }


