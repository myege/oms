 package com.what21.dao;
 
 import com.what21.model.BeAccessory;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class BeAccessoryDaoImpl
   implements BeAccessoryDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public BeAccessory findById(Integer id) {
     return ((BeAccessoryDao)this.sqlSessionTemplate.getMapper(BeAccessoryDao.class)).findById(id);
   }
 
   
   public List<BeAccessory> findByLzdIdAndItemName(Map<String, Object> map) {
     return ((BeAccessoryDao)this.sqlSessionTemplate.getMapper(BeAccessoryDao.class)).findByLzdIdAndItemName(map);
   }
 
   
   public void insert(BeAccessory beAccessory) {
     ((BeAccessoryDao)this.sqlSessionTemplate.getMapper(BeAccessoryDao.class)).insert(beAccessory);
   }
 
 
   
   public void update(BeAccessory beAccessory) {
     ((BeAccessoryDao)this.sqlSessionTemplate.getMapper(BeAccessoryDao.class)).update(beAccessory);
   }
 
 
   
   public int countByLzdIdAndItemName(Map<String, Object> map) {
     return ((BeAccessoryDao)this.sqlSessionTemplate.getMapper(BeAccessoryDao.class)).countByLzdIdAndItemName(map);
   }
 
   
   public void deleteById(int id) {
     ((BeAccessoryDao)this.sqlSessionTemplate.getMapper(BeAccessoryDao.class)).deleteById(id);
   }
 
 
   
   public List<BeAccessory> findByLzdIdAndItemName2(Map<String, Object> map) {
     return ((BeAccessoryDao)this.sqlSessionTemplate.getMapper(BeAccessoryDao.class)).findByLzdIdAndItemName2(map);
   }
 
   
   public int countByLzdIdAndItemName2(Map<String, Object> map) {
     return ((BeAccessoryDao)this.sqlSessionTemplate.getMapper(BeAccessoryDao.class)).countByLzdIdAndItemName2(map);
   }
 
   
   public void deleteByLzdId(int id) {
     ((BeAccessoryDao)this.sqlSessionTemplate.getMapper(BeAccessoryDao.class)).deleteByLzdId(id);
   }
 }


