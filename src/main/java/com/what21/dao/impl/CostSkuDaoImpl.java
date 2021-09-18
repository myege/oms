 package com.what21.dao.impl;
 
 import com.what21.dao.CostSkuDao;
 import com.what21.model.CostSku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class CostSkuDaoImpl
   implements CostSkuDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public int addCostSku(CostSku cost) {
     return ((CostSkuDao)this.sqlSessionTemplate.getMapper(CostSkuDao.class)).addCostSku(cost);
   }
 
   
   public List<CostSku> findCostSku(Map<String, Object> map) {
     return ((CostSkuDao)this.sqlSessionTemplate.getMapper(CostSkuDao.class)).findCostSku(map);
   }
 
   
   public int count(int id) {
     return ((CostSkuDao)this.sqlSessionTemplate.getMapper(CostSkuDao.class)).count(id);
   }
 
   
   public int updateCostSku(CostSku cost) {
     return ((CostSkuDao)this.sqlSessionTemplate.getMapper(CostSkuDao.class)).updateCostSku(cost);
   }
 
   
   public int deleteCostSku(String costId) {
     return ((CostSkuDao)this.sqlSessionTemplate.getMapper(CostSkuDao.class)).deleteCostSku(costId);
   }
 
   
   public CostSku findById(String id) {
     return ((CostSkuDao)this.sqlSessionTemplate.getMapper(CostSkuDao.class)).findById(id);
   }
 
   
   public CostSku findAll(int id) {
     return ((CostSkuDao)this.sqlSessionTemplate.getMapper(CostSkuDao.class)).findAll(id);
   }
 
   
   public List<CostSku> findByIdxy(int id) {
     return ((CostSkuDao)this.sqlSessionTemplate.getMapper(CostSkuDao.class)).findByIdxy(id);
   }
 
   
   public List<CostSku> findByIddy(int id) {
     return ((CostSkuDao)this.sqlSessionTemplate.getMapper(CostSkuDao.class)).findByIddy(id);
   }
 }


