 package com.what21.service.impl;
 
 import com.what21.dao.OutPcDao;
 import com.what21.model.OutPc;
 import com.what21.service.OutPcService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class OutPcServiceImpl
   implements OutPcService
 {
   @Autowired
   private OutPcDao outPcDao;
   
   public List<OutPc> findAll(Map<String, Object> pageMap) {
     return this.outPcDao.findAll(pageMap);
   }
 
 
   
   public int count(Map<String, Object> pageMap) {
     return this.outPcDao.count(pageMap);
   }
 
 
   
   public int addO(OutPc o) {
     return this.outPcDao.addO(o);
   }
 
 
   
   public int deleteO(String id) {
     return this.outPcDao.deleteO(id);
   }
 }


