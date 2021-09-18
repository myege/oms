 package com.what21.service.impl;
 
 import com.what21.dao.IjdlhdDao;
 import com.what21.model.Ijdlhd;
 import com.what21.service.IjdlhdService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class IjdlhdServiceImpl
   implements IjdlhdService
 {
   @Autowired
   private IjdlhdDao ijdlhdDao;
   
   public List<Ijdlhd> findAll(Map<String, Object> map) {
     return this.ijdlhdDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return this.ijdlhdDao.count(map);
   }
 }


