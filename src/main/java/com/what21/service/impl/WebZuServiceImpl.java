 package com.what21.service.impl;
 
 import com.what21.dao.WebZuDao;
 import com.what21.model.Webzu;
 import com.what21.service.WebZuService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class WebZuServiceImpl
   implements WebZuService
 {
   @Autowired
   private WebZuDao webZuDao;
   
   public List<Map<String, String>> findByElec(Webzu code) {
     Webzu findById = this.webZuDao.findById(code.getCode());
 
     
     return this.webZuDao.findByElec(findById);
   }
 
   
   public List<Webzu> findAll(Map<String, Object> map) {
     return this.webZuDao.findAll(map);
   }
 
   
   public Integer count(Map<String, Object> map) {
     return this.webZuDao.count(map);
   }
 
   
   public int add(Webzu webzu) {
     return this.webZuDao.add(webzu);
   }
 
   
   public int update(Webzu webzu) {
     return this.webZuDao.update(webzu);
   }
 
   
   public int delete(Integer id) {
     return this.webZuDao.delete(id);
   }
 }


