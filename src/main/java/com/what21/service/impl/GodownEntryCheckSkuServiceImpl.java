 package com.what21.service.impl;
 
 import com.what21.dao.GodownEntryCheckSkuDao;
 import com.what21.model.GodownEntryCheckSku;
 import com.what21.service.GodownEntryCheckSkuService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class GodownEntryCheckSkuServiceImpl
   implements GodownEntryCheckSkuService
 {
   @Autowired
   private GodownEntryCheckSkuDao godownEntryCheckSkuDao;
   
   public List<GodownEntryCheckSku> findBySku(Map<String, Object> pageMap) {
     return this.godownEntryCheckSkuDao.findBySku(pageMap);
   }
 
 
   
   public int count(Map<String, Object> pageMap) {
     return this.godownEntryCheckSkuDao.count(pageMap);
   }
 
 
   
   public void add(GodownEntryCheckSku g) {
     this.godownEntryCheckSkuDao.add(g);
   }
 
   
   public GodownEntryCheckSku findByTotalMailNo(GodownEntryCheckSku g) {
     return this.godownEntryCheckSkuDao.findByTotalMailNo(g);
   }
 
   
   public List<GodownEntryCheckSku> findByBill(String billcode) {
     return this.godownEntryCheckSkuDao.findByBill(billcode);
   }
 
   
   public List<GodownEntryCheckSku> printout(Map<String, Object> pageMap) {
     return this.godownEntryCheckSkuDao.printout(pageMap);
   }
 
   
   public List<GodownEntryCheckSku> findAll(Map<String, Object> pageMap) {
     return this.godownEntryCheckSkuDao.findAll(pageMap);
   }
 
   
   public GodownEntryCheckSku findByMailNo(GodownEntryCheckSku g) {
     return this.godownEntryCheckSkuDao.findByMailNo(g);
   }
 
   
   public int updateByMailNo(GodownEntryCheckSku gec) {
     return this.godownEntryCheckSkuDao.updateByMailNo(gec);
   }
 
   
   public void delete(GodownEntryCheckSku godownEntryCheckSku) {
     this.godownEntryCheckSkuDao.delete(godownEntryCheckSku);
   }
 
 
   
   public int passCount(Map<String, Object> pageMap) {
     return this.godownEntryCheckSkuDao.passCount(pageMap);
   }
 }


