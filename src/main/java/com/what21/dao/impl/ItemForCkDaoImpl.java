 package com.what21.dao.impl;
 
 import com.what21.dao.ItemForCkDao;
 import com.what21.model.Item;
 import com.what21.model.ItemForCk;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class ItemForCkDaoImpl
   implements ItemForCkDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<ItemForCk> findAll(Map<String, Object> map) {
     return ((ItemForCkDao)this.sqlSessionTemplate.getMapper(ItemForCkDao.class)).findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return ((ItemForCkDao)this.sqlSessionTemplate.getMapper(ItemForCkDao.class)).count(map);
   }
 
   
   public List<ItemForCk> findId(Map<String, Object> map) {
     return ((ItemForCkDao)this.sqlSessionTemplate.getMapper(ItemForCkDao.class)).findId(map);
   }
 
   
   public List<ItemForCk> dateTime(Map<String, Object> map) {
     return ((ItemForCkDao)this.sqlSessionTemplate.getMapper(ItemForCkDao.class)).dateTime(map);
   }
 
   
   public int deleteItem(String id) {
     return ((ItemForCkDao)this.sqlSessionTemplate.getMapper(ItemForCkDao.class)).deleteItem(id);
   }
 
   
   public int insert(ItemForCk item) {
     return ((ItemForCkDao)this.sqlSessionTemplate.getMapper(ItemForCkDao.class)).insert(item);
   }
   
   public Item findBySku(ItemForCk i) {
     return ((ItemForCkDao)this.sqlSessionTemplate.getMapper(ItemForCkDao.class)).findBySku(i);
   }
 
   
   public int updateItemForCk(ItemForCk itemForCk) {
     return ((ItemForCkDao)this.sqlSessionTemplate.getMapper(ItemForCkDao.class)).updateItemForCk(itemForCk);
   }
 
   
   public ItemForCk findByItemSku(String itemSku) {
     return ((ItemForCkDao)this.sqlSessionTemplate.getMapper(ItemForCkDao.class)).findByItemSku(itemSku);
   }
 
   
   public List<String> findByItemSkuList(List<String> itemSkuList) {
     return ((ItemForCkDao)this.sqlSessionTemplate.getMapper(ItemForCkDao.class)).findByItemSkuList(itemSkuList);
   }
 
   
   public List<ItemForCk> findByIdToBG(int id) {
     return ((ItemForCkDao)this.sqlSessionTemplate.getMapper(ItemForCkDao.class)).findByIdToBG(id);
   }
 
   
   public List<ItemForCk> findByToBG(Map<String, Object> map) {
     return ((ItemForCkDao)this.sqlSessionTemplate.getMapper(ItemForCkDao.class)).findByToBG(map);
   }
 
   
   public void insertBatch(List<ItemForCk> list) {
     ((ItemForCkDao)this.sqlSessionTemplate.getMapper(ItemForCkDao.class)).insertBatch(list);
   }
 }


