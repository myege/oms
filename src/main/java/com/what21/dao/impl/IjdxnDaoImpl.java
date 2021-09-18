 package com.what21.dao.impl;
 
 import com.what21.dao.IjdxnDao;
 import com.what21.model.OnlineRecordingParamTo;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class IjdxnDaoImpl
   implements IjdxnDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<OnlineRecordingParamTo> findAll(Map<String, Object> map) {
     return ((IjdxnDao)this.sqlSessionTemplate.getMapper(IjdxnDao.class)).findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return ((IjdxnDao)this.sqlSessionTemplate.getMapper(IjdxnDao.class)).count(map);
   }
 
   
   public List<OnlineRecordingParamTo> findbygjz(String sellerRecord) {
     return ((IjdxnDao)this.sqlSessionTemplate.getMapper(IjdxnDao.class)).findbygjz(sellerRecord);
   }
 
   
   public void updatebygjz(String sellerRecord, String notes, String status) {
     ((IjdxnDao)this.sqlSessionTemplate.getMapper(IjdxnDao.class)).updatebygjz(sellerRecord, notes, status);
   }
 
 
   
   public void insertBatch(List<OnlineRecordingParamTo> list) {
     ((IjdxnDao)this.sqlSessionTemplate.getMapper(IjdxnDao.class)).insertBatch(list);
   }
 
 
   
   public List<OnlineRecordingParamTo> findgjz(String gzj) {
     return ((IjdxnDao)this.sqlSessionTemplate.getMapper(IjdxnDao.class)).findgjz(gzj);
   }
 }


