 package com.what21.dao.impl;
 
 import com.what21.dao.CostDao;
 import com.what21.model.Cost;
 import com.what21.model.CostSku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class CostDaoImpl
   implements CostDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public int count(Map<String, Object> map) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).count(map);
   }
 
   
   public List<Cost> findAll(Map<String, Object> map) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).findAll(map);
   }
 
 
   
   public int addCost(Cost cost) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).addCost(cost);
   }
 
   
   public int deleteCost(String id) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).deleteCost(id);
   }
 
   
   public int updateCost(Cost cost) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).updateCost(cost);
   }
 
   
   public Cost findCost(String id) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).findCost(id);
   }
 
   
   public int updateCostSku(Cost cost) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).updateCostSku(cost);
   }
 
   
   public int updateCostisTj(int id) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).updateCostisTj(id);
   }
 
   
   public Cost findCosts(String id) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).findCosts(id);
   }
 
   
   public int updateCostxg(String id) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).updateCostxg(id);
   }
 
   
   public int updateCosthl(String id) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).updateCosthl(id);
   }
 
   
   public int updateCostdb(String id) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).updateCostdb(id);
   }
 
   
   public int isPayment(int id) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).isPayment(id);
   }
 
   
   public Cost findTotanumber(String id) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).findTotanumber(id);
   }
 
   
   public Cost findById(String id) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).findById(id);
   }
 
   
   public int updateCostldt(String id) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).updateCostldt(id);
   }
 
   
   public int updateCosthkzy(String id) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).updateCosthkzy(id);
   }
 
   
   public int updateCosthdtx(String id) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).updateCosthdtx(id);
   }
 
   
   public int updateCostmg(String id) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).updateCostmg(id);
   }
 
   
   public int updateCostTotalTax(Cost cost) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).updateCostTotalTax(cost);
   }
 
   
   public CostSku findBycostId(int costId) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).findBycostId(costId);
   }
 
   
   public Cost findCostAll(int id) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).findCostAll(id);
   }
 
   
   public int updateCostxq(Cost cost) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).updateCostxq(cost);
   }
 
   
   public List<Cost> viewDetails(int userId) {
     return ((CostDao)this.sqlSessionTemplate.getMapper(CostDao.class)).viewDetails(userId);
   }
 }


