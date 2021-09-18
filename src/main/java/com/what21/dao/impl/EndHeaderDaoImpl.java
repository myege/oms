 package com.what21.dao.impl;
 
 import com.what21.dao.EndHeaderDao;
 import com.what21.model.EndHeader;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class EndHeaderDaoImpl
   implements EndHeaderDao
 {
   @Autowired
   SqlSessionTemplate sqlSessionTemplate;
   
   public List<EndHeader> findAll(Map<String, Object> map) {
     return this.sqlSessionTemplate.selectList(String.valueOf(EndHeader.class.getName()) + ".findAll", map);
   }
   
   public int count(Map<String, Object> map) {
     return ((Integer)this.sqlSessionTemplate.selectOne(String.valueOf(EndHeader.class.getName()) + ".count", map)).intValue();
   }
   
   public int add(EndHeader end) {
     return this.sqlSessionTemplate.insert(String.valueOf(EndHeader.class.getName()) + ".add", end);
   }
   
   public int update(EndHeader end) {
     return this.sqlSessionTemplate.update(String.valueOf(EndHeader.class.getName()) + ".update", end);
   }
   
   public int delete(int id) {
     return this.sqlSessionTemplate.delete(String.valueOf(EndHeader.class.getName()) + ".delete", Integer.valueOf(id));
   }
   
   public List<EndHeader> findCondition(Map<String, Object> map) {
     return this.sqlSessionTemplate.selectList(String.valueOf(EndHeader.class.getName()) + ".findCondition", map);
   }
 }


