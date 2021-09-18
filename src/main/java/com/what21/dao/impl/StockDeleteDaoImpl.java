 package com.what21.dao.impl;
 
 import com.what21.dao.StockDeleteDao;
 import com.what21.model.StockDelete;
 import com.what21.model.StockDeleteCustom;
 import com.what21.model.StockDeleteQueryVo;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class StockDeleteDaoImpl
   implements StockDeleteDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
 
   
   public List<StockDelete> findAll(Map<String, Object> map) {
     return ((StockDeleteDao)this.sqlSessionTemplate.getMapper(StockDeleteDao.class)).findAll(map);
   }
 
 
   
   public int count(Map<String, Object> map) {
     return ((StockDeleteDao)this.sqlSessionTemplate.getMapper(StockDeleteDao.class)).count(map);
   }
 
 
   
   public int delete(String stockDeleteId) {
     return ((StockDeleteDao)this.sqlSessionTemplate.getMapper(StockDeleteDao.class)).delete(stockDeleteId);
   }
 
 
   
   public List<StockDelete> sd(Map<String, Object> map) {
     return ((StockDeleteDao)this.sqlSessionTemplate.getMapper(StockDeleteDao.class)).sd(map);
   }
 
   
   public int insert(StockDelete stockDelete) {
     return ((StockDeleteDao)this.sqlSessionTemplate.getMapper(StockDeleteDao.class)).insert(stockDelete);
   }
 
   
   public StockDelete findByStockDeleteId(String stockDeleteId) {
     return ((StockDeleteDao)this.sqlSessionTemplate.getMapper(StockDeleteDao.class)).findByStockDeleteId(stockDeleteId);
   }
 
 
   
   public int pushSd(StockDelete stockDelete) {
     return ((StockDeleteDao)this.sqlSessionTemplate.getMapper(StockDeleteDao.class)).pushSd(stockDelete);
   }
 
 
   
   public StockDelete findByIdToSD(Integer id) {
     return ((StockDeleteDao)this.sqlSessionTemplate.getMapper(StockDeleteDao.class)).findByIdToSD(id);
   }
 
 
   
   public int insertFlagSD(StockDelete stockDelete) {
     return ((StockDeleteDao)this.sqlSessionTemplate.getMapper(StockDeleteDao.class)).insertFlagSD(stockDelete);
   }
 
   
   public void updateFlag(StockDelete stockDelete) {
     ((StockDeleteDao)this.sqlSessionTemplate.getMapper(StockDeleteDao.class)).updateFlag(stockDelete);
   }
 
   
   public int count2(StockDeleteQueryVo stockDeleteQueryVo) {
     return ((StockDeleteDao)this.sqlSessionTemplate.getMapper(StockDeleteDao.class)).count2(stockDeleteQueryVo);
   }
 
   
   public List<StockDeleteCustom> findAll2(StockDeleteQueryVo stockDeleteQueryVo) {
     return ((StockDeleteDao)this.sqlSessionTemplate.getMapper(StockDeleteDao.class)).findAll2(stockDeleteQueryVo);
   }
 }


