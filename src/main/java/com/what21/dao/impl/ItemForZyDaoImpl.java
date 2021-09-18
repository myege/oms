 package com.what21.dao.impl;
 
 import com.what21.dao.ItemForZyDao;
 import com.what21.model.Item;
 import com.what21.model.ItemForZy;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class ItemForZyDaoImpl
   implements ItemForZyDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<ItemForZy> findAll(Map<String, Object> map) {
     return ((ItemForZyDao)this.sqlSessionTemplate.getMapper(ItemForZyDao.class)).findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return ((ItemForZyDao)this.sqlSessionTemplate.getMapper(ItemForZyDao.class)).count(map);
   }
 
   
   public List<ItemForZy> findId(Map<String, Object> map) {
     return ((ItemForZyDao)this.sqlSessionTemplate.getMapper(ItemForZyDao.class)).findId(map);
   }
 
   
   public List<ItemForZy> dateTime(Map<String, Object> map) {
     return ((ItemForZyDao)this.sqlSessionTemplate.getMapper(ItemForZyDao.class)).dateTime(map);
   }
 
   
   public int deleteItem(String id) {
     return ((ItemForZyDao)this.sqlSessionTemplate.getMapper(ItemForZyDao.class)).deleteItem(id);
   }
 
   
   public int insert(ItemForZy item) {
     return ((ItemForZyDao)this.sqlSessionTemplate.getMapper(ItemForZyDao.class)).insert(item);
   }
   
   public Item findBySku(ItemForZy i) {
     return ((ItemForZyDao)this.sqlSessionTemplate.getMapper(ItemForZyDao.class)).findBySku(i);
   }
 
   
   public int updateItemForZy(ItemForZy itemForZy) {
     return ((ItemForZyDao)this.sqlSessionTemplate.getMapper(ItemForZyDao.class)).updateItemForZy(itemForZy);
   }
 
   
   public ItemForZy findByItemSku(String itemSku) {
     return ((ItemForZyDao)this.sqlSessionTemplate.getMapper(ItemForZyDao.class)).findByItemSku(itemSku);
   }
 
   
   public List<String> findByItemSkuList(List<String> itemSkuList) {
     return ((ItemForZyDao)this.sqlSessionTemplate.getMapper(ItemForZyDao.class)).findByItemSkuList(itemSkuList);
   }
 
   
   public List<ItemForZy> findByIdToBG(int id) {
     return ((ItemForZyDao)this.sqlSessionTemplate.getMapper(ItemForZyDao.class)).findByIdToBG(id);
   }
 
   
   public List<ItemForZy> findByToBG(Map<String, Object> map) {
     return ((ItemForZyDao)this.sqlSessionTemplate.getMapper(ItemForZyDao.class)).findByToBG(map);
   }
 
   
   public void insertBatch(List<ItemForZy> list) {
     ((ItemForZyDao)this.sqlSessionTemplate.getMapper(ItemForZyDao.class)).insertBatch(list);
   }
 }


