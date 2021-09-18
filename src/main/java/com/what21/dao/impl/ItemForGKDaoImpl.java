 package com.what21.dao.impl;
 
 import com.what21.dao.ItemForGKDao;
 import com.what21.model.ItemForGk;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class ItemForGKDaoImpl
   implements ItemForGKDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<ItemForGk> findAll(Map<String, Object> paramMap) {
     return ((ItemForGKDao)this.sqlSessionTemplate.getMapper(ItemForGKDao.class)).findAll(paramMap);
   }
   
   public int insertItem(ItemForGk ig) {
     return ((ItemForGKDao)this.sqlSessionTemplate.getMapper(ItemForGKDao.class)).insertItem(ig);
   }
 
 
   
   public int count(Map<String, Object> paramMap) {
     return ((ItemForGKDao)this.sqlSessionTemplate.getMapper(ItemForGKDao.class)).count(paramMap);
   }
 
 
   
   public int deleteItem(int[] ids) {
     return ((ItemForGKDao)this.sqlSessionTemplate.getMapper(ItemForGKDao.class)).deleteItem(ids);
   }
 
 
   
   public int updateForItem(ItemForGk ig) {
     return ((ItemForGKDao)this.sqlSessionTemplate.getMapper(ItemForGKDao.class)).updateForItem(ig);
   }
 
 
   
   public int findByItemSku(String itemSku) {
     return ((ItemForGKDao)this.sqlSessionTemplate.getMapper(ItemForGKDao.class)).findByItemSku(itemSku);
   }
 
 
   
   public void insertItems(List<ItemForGk> list) {
     ((ItemForGKDao)this.sqlSessionTemplate.getMapper(ItemForGKDao.class)).insertItems(list);
   }
 
 
   
   public List<ItemForGk> findByIDS(int[] ids) {
     return ((ItemForGKDao)this.sqlSessionTemplate.getMapper(ItemForGKDao.class)).findByIDS(ids);
   }
 
 
   
   public List<ItemForGk> findAllByEx() {
     return ((ItemForGKDao)this.sqlSessionTemplate.getMapper(ItemForGKDao.class)).findAllByEx();
   }
 }


