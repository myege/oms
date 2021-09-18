 package com.what21.dao.impl;
 
 import com.what21.dao.BeYfDao;
 import com.what21.model.BeYf;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class BeYfDaoImpl
   implements BeYfDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<BeYf> findAll(Map<String, Object> map) {
     return ((BeYfDao)this.sqlSessionTemplate.getMapper(BeYfDao.class)).findAll(map);
   }
 
   
   public Integer count(Map<String, Object> map) {
     return ((BeYfDao)this.sqlSessionTemplate.getMapper(BeYfDao.class)).count(map);
   }
 
   
   public void insert(BeYf beYf) {
     ((BeYfDao)this.sqlSessionTemplate.getMapper(BeYfDao.class)).insert(beYf);
   }
 
 
   
   public void deleteById(Integer id) {
     ((BeYfDao)this.sqlSessionTemplate.getMapper(BeYfDao.class)).deleteById(id);
   }
 
 
   
   public BeYf findById(Integer id) {
     return ((BeYfDao)this.sqlSessionTemplate.getMapper(BeYfDao.class)).findById(id);
   }
 
   
   public List<BeYf> findByPramid(Map<String, Object> map) {
     return ((BeYfDao)this.sqlSessionTemplate.getMapper(BeYfDao.class)).findByPramid(map);
   }
 
   
   public Integer countByPramid(Map<String, Object> map) {
     return ((BeYfDao)this.sqlSessionTemplate.getMapper(BeYfDao.class)).countByPramid(map);
   }
 
   
   public void update(BeYf beYf) {
     ((BeYfDao)this.sqlSessionTemplate.getMapper(BeYfDao.class)).update(beYf);
   }
 
 
   
   public void deleteByPramid(Map<String, Object> map) {
     ((BeYfDao)this.sqlSessionTemplate.getMapper(BeYfDao.class)).deleteByPramid(map);
   }
 }


