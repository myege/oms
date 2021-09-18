 package com.what21.service.impl;
 
 import com.what21.dao.EndHeaderDao;
 import com.what21.model.EndHeader;
 import com.what21.service.EndHeaderService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 @Service
 public class EndHeaderServiceImpl
   implements EndHeaderService
 {
   @Autowired
   private EndHeaderDao dao;
   
   public List<EndHeader> findAll(Map<String, Object> map) {
     return this.dao.findAll(map);
   }
   
   public int count(Map<String, Object> map) {
     return this.dao.count(map);
   }
   
   public int add(EndHeader end) {
     return this.dao.add(end);
   }
   
   public int update(EndHeader end) {
     return this.dao.update(end);
   }
   
   public int delete(int id) {
     return this.dao.delete(id);
   }
   
   public List<EndHeader> findCondition(Map<String, Object> map) {
     return this.dao.findCondition(map);
   }
 }


