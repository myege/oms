 package com.what21.service.impl;
 
 import com.what21.dao.IjdxnDao;
 import com.what21.model.OnlineRecordingParamTo;
 import com.what21.service.IjdxnService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class IjdxnServiceImpl
   implements IjdxnService
 {
   @Autowired
   private IjdxnDao ijdxnDao;
   
   public List<OnlineRecordingParamTo> findAll(Map<String, Object> map) {
     return this.ijdxnDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return this.ijdxnDao.count(map);
   }
 }


