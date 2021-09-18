 package com.what21.dao.impl;
 
 import com.what21.dao.BeHcDao;
 import com.what21.model.BeHc;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class BeHcDaoImpl
   implements BeHcDao {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<BeHc> findAll(Map<String, Object> map) {
     return ((BeHcDao)this.sqlSessionTemplate.getMapper(BeHcDao.class)).findAll(map);
   }
 
   
   public Integer count(Map<String, Object> map) {
     return ((BeHcDao)this.sqlSessionTemplate.getMapper(BeHcDao.class)).count(map);
   }
 
   
   public void insert(BeHc beHc) {
     ((BeHcDao)this.sqlSessionTemplate.getMapper(BeHcDao.class)).insert(beHc);
   }
 
 
   
   public void deleteById(Integer id) {
     ((BeHcDao)this.sqlSessionTemplate.getMapper(BeHcDao.class)).deleteById(id);
   }
 
 
   
   public BeHc findById(Integer id) {
     return ((BeHcDao)this.sqlSessionTemplate.getMapper(BeHcDao.class)).findById(id);
   }
 
   
   public List<BeHc> findByPramid(Map<String, Object> map) {
     return ((BeHcDao)this.sqlSessionTemplate.getMapper(BeHcDao.class)).findByPramid(map);
   }
 
   
   public Integer countByPramid(Map<String, Object> map) {
     return ((BeHcDao)this.sqlSessionTemplate.getMapper(BeHcDao.class)).countByPramid(map);
   }
 
   
   public void deleteByPramid(Map<String, Object> map) {
     ((BeHcDao)this.sqlSessionTemplate.getMapper(BeHcDao.class)).deleteByPramid(map);
   }
 }


