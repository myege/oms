 package com.what21.dao.impl;
 
 import com.what21.dao.ItemDao;
 import com.what21.dao.SzqgAgentDao;
 import com.what21.dao.SzqgAgreementDao;
 import com.what21.model.Item;
 import com.what21.model.SzqgAgent;
 import com.what21.model.SzqgAgreement;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class SzqgAgreementDaoImpl
   implements SzqgAgreementDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<SzqgAgent> findAll(Map<String, Object> map) {
     return ((SzqgAgentDao)this.sqlSessionTemplate.getMapper(SzqgAgentDao.class)).findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).count(map);
   }
 
   
   public List<Item> findId(Map<String, Object> map) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).findId(map);
   }
 
   
   public List<Item> dateTime(Map<String, Object> map) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).dateTime(map);
   }
 
   
   public int deleteItem(String id) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).deleteItem(id);
   }
 
   
   public int insert(Item item) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).insert(item);
   }
 
   
   public Item findBySku(Item i) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).findBySku(i);
   }
 
   
   public int updateItme(Item item) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).updateItme(item);
   }
 
   
   public void insertBatch(List<SzqgAgent> list) {
     ((SzqgAgentDao)this.sqlSessionTemplate.getMapper(SzqgAgentDao.class)).insertBatch(list);
   }
 
 
   
   public List<Item> finImpWms() {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).finImpWms();
   }
 
   
   public List<Item> findArray(String[] dis) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).findArray(dis);
   }
 
   
   public void updateWmsType(List<Map<String, String>> i) {
     ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).updateWmsType(i);
   }
 
 
   
   public Item findById(String id) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).findById(id);
   }
 
 
   
   public int updateById(Item item) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).updateById(item);
   }
 
 
   
   public int save(Item item) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).save(item);
   }
   
   public List<SzqgAgreement> findgjz(String gzj) {
     return ((SzqgAgreementDao)this.sqlSessionTemplate.getMapper(SzqgAgreementDao.class)).findgjz(gzj);
   }
   
   public List<Item> findhzqd(String gzj) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).findhzqd(gzj);
   }
 
   
   public void updateWmsType2(int gjz) {
     ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).updateWmsType2(gjz);
   }
 }


