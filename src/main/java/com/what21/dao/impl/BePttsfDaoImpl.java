 package com.what21.dao.impl;
 
 import com.what21.dao.BePttsfDao;
 import com.what21.model.BePttsf;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class BePttsfDaoImpl
   implements BePttsfDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<BePttsf> findAll(Map<String, Object> map) {
     return ((BePttsfDao)this.sqlSessionTemplate.getMapper(BePttsfDao.class)).findAll(map);
   }
 
 
   
   public Integer count(Map<String, Object> map) {
     return ((BePttsfDao)this.sqlSessionTemplate.getMapper(BePttsfDao.class)).count(map);
   }
 
   
   public void insert(BePttsf bePttsf) {
     ((BePttsfDao)this.sqlSessionTemplate.getMapper(BePttsfDao.class)).insert(bePttsf);
   }
 
 
   
   public void deleteById(Integer id) {
     ((BePttsfDao)this.sqlSessionTemplate.getMapper(BePttsfDao.class)).deleteById(id);
   }
 
 
   
   public BePttsf findById(Integer id) {
     return ((BePttsfDao)this.sqlSessionTemplate.getMapper(BePttsfDao.class)).findById(id);
   }
 
   
   public List<BePttsf> findByPramid(Map<String, Object> map) {
     return ((BePttsfDao)this.sqlSessionTemplate.getMapper(BePttsfDao.class)).findByPramid(map);
   }
 
   
   public Integer countByPramid(Map<String, Object> map) {
     return ((BePttsfDao)this.sqlSessionTemplate.getMapper(BePttsfDao.class)).countByPramid(map);
   }
 
   
   public void deleteByPramid(Map<String, Object> map) {
     ((BePttsfDao)this.sqlSessionTemplate.getMapper(BePttsfDao.class)).deleteByPramid(map);
   }
 }


