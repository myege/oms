 package com.what21.dao.impl;
 
 import com.what21.dao.IjdcgdysDao;
 import com.what21.model.Ijdcgdys;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class IjdcgdysDaoImpl
   implements IjdcgdysDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<Ijdcgdys> findAll(Map<String, Object> map) {
     return ((IjdcgdysDao)this.sqlSessionTemplate.getMapper(IjdcgdysDao.class)).findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return ((IjdcgdysDao)this.sqlSessionTemplate.getMapper(IjdcgdysDao.class)).count(map);
   }
 
   
   public void insertBatch(List<Ijdcgdys> list) {
     ((IjdcgdysDao)this.sqlSessionTemplate.getMapper(IjdcgdysDao.class)).insertBatch(list);
   }
 
   
   public void delete(String poNo) {
     ((IjdcgdysDao)this.sqlSessionTemplate.getMapper(IjdcgdysDao.class)).delete(poNo);
   }
 
   
   public List<Ijdcgdys> findbygjz(String id) {
     return ((IjdcgdysDao)this.sqlSessionTemplate.getMapper(IjdcgdysDao.class)).findbygjz(id);
   }
 
   
   public void updatebygjz(String poNo, String notes, String status) {
     ((IjdcgdysDao)this.sqlSessionTemplate.getMapper(IjdcgdysDao.class)).updatebygjz(poNo, notes, status);
   }
 }


