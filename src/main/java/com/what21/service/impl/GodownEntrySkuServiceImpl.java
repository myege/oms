 package com.what21.service.impl;
 
 import com.what21.dao.GodownEntrySkuDao;
 import com.what21.model.GodownEntrySku;
 import com.what21.service.GodownEntrySkuService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class GodownEntrySkuServiceImpl
   implements GodownEntrySkuService
 {
   @Autowired
   private GodownEntrySkuDao godownEntrySkuDao;
   
   public List<GodownEntrySku> findBySku(Map<String, Object> pageMap) {
     return this.godownEntrySkuDao.findBySku(pageMap);
   }
 
 
   
   public int count(Map<String, Object> pageMap) {
     return this.godownEntrySkuDao.count(pageMap);
   }
 
 
   
   public void add(GodownEntrySku g) {
     this.godownEntrySkuDao.add(g);
   }
 
   
   public GodownEntrySku findByTotalMailNo(GodownEntrySku g) {
     return this.godownEntrySkuDao.findByTotalMailNo(g);
   }
 
   
   public List<GodownEntrySku> findByBill(String billcode) {
     return this.godownEntrySkuDao.findByBill(billcode);
   }
 
   
   public List<GodownEntrySku> printout(Map<String, Object> pageMap) {
     return this.godownEntrySkuDao.printout(pageMap);
   }
 
   
   public List<GodownEntrySku> findAll(Map<String, Object> pageMap) {
     return this.godownEntrySkuDao.findAll(pageMap);
   }
 
   
   public List<GodownEntrySku> findByMailNo(GodownEntrySku g) {
     return this.godownEntrySkuDao.findByMailNo(g);
   }
 
   
   public int delete(GodownEntrySku godownEntrySku) {
     return this.godownEntrySkuDao.delete(godownEntrySku);
   }
 
   
   public int updateByMailNo(GodownEntrySku godownEntrySku) {
     return this.godownEntrySkuDao.updateByMailNo(godownEntrySku);
   }
 }


