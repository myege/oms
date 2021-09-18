 package com.what21.dao.impl;
 
 import com.what21.dao.BeUploadDao;
 import com.what21.model.BeUpload;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class BeUploadDaoImpl
   implements BeUploadDao {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public BeUpload findById(String id) {
     return ((BeUploadDao)this.sqlSessionTemplate.getMapper(BeUploadDao.class)).findById(id);
   }
 }


