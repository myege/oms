 package com.what21.dao.impl;
 
 import com.what21.dao.BeSFDao;
 import com.what21.model.BeSF;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class BeSFDaoImpl
   implements BeSFDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<BeSF> findAll(Map<String, Object> map) {
     return ((BeSFDao)this.sqlSessionTemplate.getMapper(BeSFDao.class)).findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return ((BeSFDao)this.sqlSessionTemplate.getMapper(BeSFDao.class)).count(map);
   }
 
   
   public void insert(BeSF beSF) {
     ((BeSFDao)this.sqlSessionTemplate.getMapper(BeSFDao.class)).insert(beSF);
   }
 
   
   public void deleteById(int id) {
     ((BeSFDao)this.sqlSessionTemplate.getMapper(BeSFDao.class)).deleteById(id);
   }
 
   
   public BeSF findById(int id) {
     return ((BeSFDao)this.sqlSessionTemplate.getMapper(BeSFDao.class)).findById(id);
   }
 
   
   public List<BeSF> findByPramid(Map<String, Object> map) {
     return ((BeSFDao)this.sqlSessionTemplate.getMapper(BeSFDao.class)).findByPramid(map);
   }
 
   
   public int countByPramid(Map<String, Object> map) {
     return ((BeSFDao)this.sqlSessionTemplate.getMapper(BeSFDao.class)).countByPramid(map);
   }
 
   
   public void update(BeSF beSF) {
     ((BeSFDao)this.sqlSessionTemplate.getMapper(BeSFDao.class)).update(beSF);
   }
 
 
   
   public void deleteByPramid(Map<String, Object> map) {
     ((BeSFDao)this.sqlSessionTemplate.getMapper(BeSFDao.class)).deleteByPramid(map);
   }
 }


