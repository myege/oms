 package com.what21.dao.impl;
 
 import com.what21.dao.ItemDao;
 import com.what21.dao.SzqgItemDao;
 import com.what21.model.Item;
 import com.what21.model.SzqgItem;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class SzqgItemDaoImpl
   implements SzqgItemDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<SzqgItem> findAll(Map<String, Object> map) {
     return ((SzqgItemDao)this.sqlSessionTemplate.getMapper(SzqgItemDao.class)).findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return ((SzqgItemDao)this.sqlSessionTemplate.getMapper(SzqgItemDao.class)).count(map);
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
 
   
   public List<SzqgItem> findBySku(SzqgItem i) {
     return ((SzqgItemDao)this.sqlSessionTemplate.getMapper(SzqgItemDao.class)).findBySku(i);
   }
 
   
   public int updateItme(Item item) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).updateItme(item);
   }
 
   
   public void insertBatch(List<SzqgItem> list) {
     ((SzqgItemDao)this.sqlSessionTemplate.getMapper(SzqgItemDao.class)).insertBatch(list);
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
   
   public List<SzqgItem> findgjz(String gzj) {
     return ((SzqgItemDao)this.sqlSessionTemplate.getMapper(SzqgItemDao.class)).findgjz(gzj);
   }
   
   public List<SzqgItem> findgjzforcd(String gzj) {
     return ((SzqgItemDao)this.sqlSessionTemplate.getMapper(SzqgItemDao.class)).findgjzforcd(gzj);
   }
   
   public List<SzqgItem> findgjzforcd2(String gzj) {
     return ((SzqgItemDao)this.sqlSessionTemplate.getMapper(SzqgItemDao.class)).findgjzforcd2(gzj);
   }
   
   public List<Item> findhzqd(String gzj) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).findhzqd(gzj);
   }
 
   
   public void updateWmsType2(int gjz) {
     ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).updateWmsType2(gjz);
   }
   
   public void updatebygjz(String sellerRecord, String notes, String status) {
     ((SzqgItemDao)this.sqlSessionTemplate.getMapper(SzqgItemDao.class)).updatebygjz(sellerRecord, notes, status);
   }
 
   
   public int batchInsert(List<SzqgItem> list) {
     return ((SzqgItemDao)this.sqlSessionTemplate.getMapper(SzqgItemDao.class)).batchInsert(list);
   }
 }


