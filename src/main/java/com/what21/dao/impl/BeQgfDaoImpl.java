 package com.what21.dao.impl;
 
 import com.what21.dao.BeQgfDao;
 import com.what21.model.BeQgf;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class BeQgfDaoImpl
   implements BeQgfDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<BeQgf> findAll(Map<String, Object> map) {
     return ((BeQgfDao)this.sqlSessionTemplate.getMapper(BeQgfDao.class)).findAll(map);
   }
 
 
   
   public Integer count(Map<String, Object> map) {
     return ((BeQgfDao)this.sqlSessionTemplate.getMapper(BeQgfDao.class)).count(map);
   }
 
   
   public void insert(BeQgf beQgf) {
     ((BeQgfDao)this.sqlSessionTemplate.getMapper(BeQgfDao.class)).insert(beQgf);
   }
 
 
   
   public void deleteById(Integer id) {
     ((BeQgfDao)this.sqlSessionTemplate.getMapper(BeQgfDao.class)).deleteById(id);
   }
 
 
   
   public BeQgf findById(Integer id) {
     return ((BeQgfDao)this.sqlSessionTemplate.getMapper(BeQgfDao.class)).findById(id);
   }
 
   
   public BeQgf findByPramid(int pramid) {
     return ((BeQgfDao)this.sqlSessionTemplate.getMapper(BeQgfDao.class)).findByPramid(pramid);
   }
 
 
   
   public void update(BeQgf beQgf) {
     ((BeQgfDao)this.sqlSessionTemplate.getMapper(BeQgfDao.class)).update(beQgf);
   }
 
 
   
   public int countByPramid(Map<String, Object> map) {
     return ((BeQgfDao)this.sqlSessionTemplate.getMapper(BeQgfDao.class)).countByPramid(map);
   }
 
   
   public List<BeQgf> findAllByPramid(Map<String, Object> map) {
     return ((BeQgfDao)this.sqlSessionTemplate.getMapper(BeQgfDao.class)).findAllByPramid(map);
   }
 
   
   public void deleteByPramid(int pramid) {
     ((BeQgfDao)this.sqlSessionTemplate.getMapper(BeQgfDao.class)).deleteByPramid(pramid);
   }
 }


