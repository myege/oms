 package com.what21.dao.impl;
 
 import com.what21.dao.ItemForSjDao;
 import com.what21.model.ItemForSj;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class ItemForSjDaoImpl
   implements ItemForSjDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<ItemForSj> findAll(Map<String, Object> map) {
     return ((ItemForSjDao)this.sqlSessionTemplate.getMapper(ItemForSjDao.class)).findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return ((ItemForSjDao)this.sqlSessionTemplate.getMapper(ItemForSjDao.class)).count(map);
   }
 
   
   public List<ItemForSj> findintfo(Map<String, Object> pageMap) {
     return ((ItemForSjDao)this.sqlSessionTemplate.getMapper(ItemForSjDao.class)).findintfo(pageMap);
   }
 
   
   public ItemForSj findByInF(String goods_sn) {
     return ((ItemForSjDao)this.sqlSessionTemplate.getMapper(ItemForSjDao.class)).findByInF(goods_sn);
   }
 
   
   public void insertIn(ItemForSj itemForSj) {
     ((ItemForSjDao)this.sqlSessionTemplate.getMapper(ItemForSjDao.class)).insertIn(itemForSj);
   }
 
   
   public int intfodelete(String id) {
     return ((ItemForSjDao)this.sqlSessionTemplate.getMapper(ItemForSjDao.class)).intfodelete(id);
   }
 
   
   public int intfoedit(ItemForSj itemForSj) {
     return ((ItemForSjDao)this.sqlSessionTemplate.getMapper(ItemForSjDao.class)).intfoedit(itemForSj);
   }
 
   
   public String ifsjsj(String ids) {
     return ((ItemForSjDao)this.sqlSessionTemplate.getMapper(ItemForSjDao.class)).ifsjsj(ids);
   }
 
   
   public void infflag(ItemForSj inou) {
     ((ItemForSjDao)this.sqlSessionTemplate.getMapper(ItemForSjDao.class)).infflag(inou);
   }
 
 
   
   public List<ItemForSj> ifadd() {
     return ((ItemForSjDao)this.sqlSessionTemplate.getMapper(ItemForSjDao.class)).ifadd();
   }
 
   
   public List<ItemForSj> hyjStatus(String merchantNum) {
     return ((ItemForSjDao)this.sqlSessionTemplate.getMapper(ItemForSjDao.class)).hyjStatus(merchantNum);
   }
 
   
   public int updateStatus(String ids) {
     return ((ItemForSjDao)this.sqlSessionTemplate.getMapper(ItemForSjDao.class)).updateStatus(ids);
   }
 
   
   public ItemForSj findById(String id) {
     return ((ItemForSjDao)this.sqlSessionTemplate.getMapper(ItemForSjDao.class)).findById(id);
   }
 
   
   public int updatefsj(ItemForSj itorSj) {
     return ((ItemForSjDao)this.sqlSessionTemplate.getMapper(ItemForSjDao.class)).updatefsj(itorSj);
   }
 }


