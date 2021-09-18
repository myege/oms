 package com.what21.dao.impl;
 
 import com.what21.dao.WebZuDao;
 import com.what21.model.Webzu;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class WebZuDaoImpl
   implements WebZuDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<Map<String, String>> findByElec(Webzu map) {
     return this.sqlSessionTemplate.selectList(String.valueOf(Webzu.class.getName()) + ".findByElec", map);
   }
 
   
   public Webzu findById(String code) {
     return (Webzu)this.sqlSessionTemplate.selectOne(String.valueOf(Webzu.class.getName()) + ".findById", code);
   }
 
   
   public List<Webzu> findAll(Map<String, Object> map) {
     return this.sqlSessionTemplate.selectList(String.valueOf(Webzu.class.getName()) + ".findAll", map);
   }
 
 
   
   public Integer count(Map<String, Object> map) {
     return (Integer)this.sqlSessionTemplate.selectOne(String.valueOf(Webzu.class.getName()) + ".count", map);
   }
 
   
   public int add(Webzu webzu) {
     return this.sqlSessionTemplate.insert(String.valueOf(Webzu.class.getName()) + ".add", webzu);
   }
 
 
   
   public int update(Webzu webzu) {
     return this.sqlSessionTemplate.update(String.valueOf(Webzu.class.getName()) + ".update", webzu);
   }
 
   
   public int delete(Integer id) {
     return this.sqlSessionTemplate.delete(String.valueOf(Webzu.class.getName()) + ".delete", id);
   }
 }


