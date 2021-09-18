 package com.what21.dao.impl;
 
 import com.what21.dao.GylFinanceDao;
 import com.what21.model.GylFinance;
 import com.what21.model.OrderBonded;
 import com.what21.model.OrderBondedSku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class GylFinanceDaoImpl
   implements GylFinanceDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<GylFinance> findAll(Map<String, Object> map) {
     return ((GylFinanceDao)this.sqlSessionTemplate.getMapper(GylFinanceDao.class)).findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return ((GylFinanceDao)this.sqlSessionTemplate.getMapper(GylFinanceDao.class)).count(map);
   }
 
   
   public void insert(GylFinance gylFinance) {
     ((GylFinanceDao)this.sqlSessionTemplate.getMapper(GylFinanceDao.class)).insert(gylFinance);
   }
 
   
   public void batchInsertOrder(Object[] obArr) {
     ((GylFinanceDao)this.sqlSessionTemplate.getMapper(GylFinanceDao.class)).batchInsertOrder(obArr);
   }
 
   
   public void batchInsertOrderSku(List<OrderBondedSku> obsList) {
     ((GylFinanceDao)this.sqlSessionTemplate.getMapper(GylFinanceDao.class)).batchInsertOrderSku(obsList);
   }
 
   
   public List<OrderBonded> findByObArr(Object[] obArr) {
     return ((GylFinanceDao)this.sqlSessionTemplate.getMapper(GylFinanceDao.class)).findByObArr(obArr);
   }
 
   
   public List<OrderBonded> findOrderByNumber(Map<String, Object> map) {
     return ((GylFinanceDao)this.sqlSessionTemplate.getMapper(GylFinanceDao.class)).findOrderByNumber(map);
   }
 
   
   public int countByNumber(Map<String, Object> map) {
     return ((GylFinanceDao)this.sqlSessionTemplate.getMapper(GylFinanceDao.class)).countByNumber(map);
   }
 
   
   public int updateConfirmStatus(List<GylFinance> list) {
     return ((GylFinanceDao)this.sqlSessionTemplate.getMapper(GylFinanceDao.class)).updateConfirmStatus(list);
   }
 
   
   public List<OrderBonded> findOrder(List<GylFinance> list) {
     return ((GylFinanceDao)this.sqlSessionTemplate.getMapper(GylFinanceDao.class)).findOrder(list);
   }
 
 
   
   public List<OrderBondedSku> findOrderSku(List<GylFinance> list) {
     return ((GylFinanceDao)this.sqlSessionTemplate.getMapper(GylFinanceDao.class)).findOrderSku(list);
   }
 
   
   public int batchInsertToOrderBonded(List<OrderBonded> orderList) {
     return ((GylFinanceDao)this.sqlSessionTemplate.getMapper(GylFinanceDao.class)).batchInsertToOrderBonded(orderList);
   }
 
   
   public int batchInsertToOrderBondedSku(List<OrderBondedSku> orderSkuList) {
     return ((GylFinanceDao)this.sqlSessionTemplate.getMapper(GylFinanceDao.class)).batchInsertToOrderBondedSku(orderSkuList);
   }
 }


