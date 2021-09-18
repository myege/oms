 package com.what21.dao.impl;
 
 import com.what21.dao.PlaningReceiptsSkuDao;
 import com.what21.model.PlaningReceiptsSku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class PlaningReceiptsSkuDaoImpl
   implements PlaningReceiptsSkuDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
 
   
   public List<PlaningReceiptsSku> findAll(Map<String, Object> map) {
     return ((PlaningReceiptsSkuDao)this.sqlSessionTemplate.getMapper(PlaningReceiptsSkuDao.class)).findAll(map);
   }
 
   
   public int count(String stockDeleteId) {
     return ((PlaningReceiptsSkuDao)this.sqlSessionTemplate.getMapper(PlaningReceiptsSkuDao.class)).count(stockDeleteId);
   }
 
 
   
   public int insert(PlaningReceiptsSku planingReceiptsSku) {
     return ((PlaningReceiptsSkuDao)this.sqlSessionTemplate.getMapper(PlaningReceiptsSkuDao.class)).insert(planingReceiptsSku);
   }
 
 
   
   public int delete(String planingReceiptsId) {
     return ((PlaningReceiptsSkuDao)this.sqlSessionTemplate.getMapper(PlaningReceiptsSkuDao.class)).delete(planingReceiptsId);
   }
 
 
   
   public List<PlaningReceiptsSku> findByPlaningReceiptsId(String planingReceiptsId) {
     return ((PlaningReceiptsSkuDao)this.sqlSessionTemplate.getMapper(PlaningReceiptsSkuDao.class)).findByPlaningReceiptsId(planingReceiptsId);
   }
 }


