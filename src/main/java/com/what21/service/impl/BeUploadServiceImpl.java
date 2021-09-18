 package com.what21.service.impl;
 
 import com.what21.dao.BeUploadDao;
 import com.what21.model.BeUpload;
 import com.what21.service.BeUploadService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 
 @Transactional
 @Service
 public class BeUploadServiceImpl
   implements BeUploadService {
   @Autowired
   private BeUploadDao beUploadDao;
   
   public BeUpload findById(String id) {
     return this.beUploadDao.findById(id);
   }
 }


