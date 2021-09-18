 package com.what21.service.impl;
 
 import com.what21.dao.NuclearReleaseSkuDao;
 import com.what21.model.NuclearReleaseSku;
 import com.what21.service.NuclearReleaseSkuService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class NuclearReleaseSkuServiceImpl
   implements NuclearReleaseSkuService
 {
   @Autowired
   private NuclearReleaseSkuDao nuclearReleaseSkuDao;
   
   public List<NuclearReleaseSku> findBySku(Map<String, Object> pageMap) {
     return this.nuclearReleaseSkuDao.findBySku(pageMap);
   }
 
 
   
   public int count(Map<String, Object> pageMap) {
     return this.nuclearReleaseSkuDao.count(pageMap);
   }
 
 
   
   public void add(NuclearReleaseSku n) {
     this.nuclearReleaseSkuDao.add(n);
   }
 
 
 
   
   public NuclearReleaseSku findId(Map<String, Object> pageMap) {
     return this.nuclearReleaseSkuDao.findId(pageMap);
   }
 
   
   public List<NuclearReleaseSku> findAll(Map<String, Object> pageMap) {
     return this.nuclearReleaseSkuDao.findAll(pageMap);
   }
 
   
   public List<NuclearReleaseSku> findByB(String billcode) {
     return this.nuclearReleaseSkuDao.findByB(billcode);
   }
 }


