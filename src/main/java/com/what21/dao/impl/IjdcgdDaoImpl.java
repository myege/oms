 package com.what21.dao.impl;
 
 import com.what21.dao.IjdcgdDao;
 import com.what21.model.Ijdcgd;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class IjdcgdDaoImpl
   implements IjdcgdDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<Ijdcgd> findAll(Map<String, Object> map) {
     return ((IjdcgdDao)this.sqlSessionTemplate.getMapper(IjdcgdDao.class)).findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return ((IjdcgdDao)this.sqlSessionTemplate.getMapper(IjdcgdDao.class)).count(map);
   }
 
   
   public void insertBatch(List<Ijdcgd> list) {
     ((IjdcgdDao)this.sqlSessionTemplate.getMapper(IjdcgdDao.class)).insertBatch(list);
   }
 
 
   
   public void delete(String poNo) {
     ((IjdcgdDao)this.sqlSessionTemplate.getMapper(IjdcgdDao.class)).delete(poNo);
   }
 
   
   public List<Ijdcgd> findbygjz(String id) {
     return ((IjdcgdDao)this.sqlSessionTemplate.getMapper(IjdcgdDao.class)).findbygjz(id);
   }
 
   
   public void updatebygjz(String poNo, String notes, String status) {
     ((IjdcgdDao)this.sqlSessionTemplate.getMapper(IjdcgdDao.class)).updatebygjz(poNo, notes, status);
   }
 }


