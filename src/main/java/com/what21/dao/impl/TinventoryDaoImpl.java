 package com.what21.dao.impl;
 
 import com.what21.dao.TinventoryDao;
 import com.what21.model.Tinventory;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class TinventoryDaoImpl
   implements TinventoryDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
   
   public List<Tinventory> findAll(Map<String, Object> map) {
     return ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).findAll(map);
   }
   
   public int count(Map<String, Object> map) {
     return ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).count(map);
   }
 
   
   public List<Tinventory> dateTime(Map<String, Object> map) {
     return ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).dateTime(map);
   }
 
 
   
   public int updateTotality(Tinventory t) {
     return ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).updateTotality(t);
   }
 
 
   
   public String findByPc(Tinventory t) {
     return ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).findByPc(t);
   }
 
 
   
   public List<Tinventory> findBySku(Tinventory t) {
     return ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).findBySku(t);
   }
 
   
   public List<Tinventory> findeXport(Map<String, Object> map) {
     return ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).findeXport(map);
   }
 
   
   public List<Tinventory> findByIdTinventory(Integer id) {
     return ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).findByIdTinventory(id);
   }
 
 
   
   public int updateTinventory(Tinventory id) {
     return ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).updateTinventory(id);
   }
 
 
   
   public Tinventory findLR(Tinventory t) {
     return ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).findLR(t);
   }
 
 
   
   public int updateLR(Tinventory t) {
     return ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).updateLR(t);
   }
 
   
   public int addTinventory(Tinventory tinventory) {
     return ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).addTinventory(tinventory);
   }
 
   
   public List<Tinventory> getAll(String merchant) {
     return ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).getAll(merchant);
   }
 
   
   public int makeOver(Tinventory tinventory) {
     return ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).makeOver(tinventory);
   }
 
   
   public Tinventory findSkuAndCode(Map<String, Object> map) {
     return ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).findSkuAndCode(map);
   }
 
   
   public int upSurplus(Tinventory tinventory) {
     return ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).upSurplus(tinventory);
   }
 
   
   public Integer findSumBySku(Tinventory tinventory) {
     return ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).findSumBySku(tinventory);
   }
 
   
   public void updatePreUsed(Tinventory tinventory) {
     ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).updatePreUsed(tinventory);
   }
 
   
   public int addBig(List<Tinventory> list) {
     return ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).addBig(list);
   }
 
   
   public void updateBig(List<Tinventory> list) {
     ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).updateBig(list);
   }
 
   
   public int findStroage(String storage) {
     return ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).findStroage(storage);
   }
 
   
   public List<Tinventory> findInventory(String merchantNum, String itemsku) {
     return ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).findInventory(merchantNum, itemsku);
   }
 
   
   public void updateInventory(Tinventory tinventory) {
     ((TinventoryDao)this.sqlSessionTemplate.getMapper(TinventoryDao.class)).updateInventory(tinventory);
   }
 }


