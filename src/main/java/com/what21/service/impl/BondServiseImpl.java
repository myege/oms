 package com.what21.service.impl;
 
 import com.what21.dao.BondDao;
 import com.what21.model.Bond;
 import com.what21.service.BondService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class BondServiseImpl
   implements BondService
 {
   @Autowired
   private BondDao bondDao;
   
   public int count(Map<String, Object> map) {
     return this.bondDao.count(map);
   }
 
   
   public List<Bond> findAll(Map<String, Object> paramMap) {
     return this.bondDao.findAll(paramMap);
   }
   
   public List<Bond> findBond() {
     return this.bondDao.findBond();
   }
   
   public Bond getTax(Bond bond) {
     return this.bondDao.getTax(bond);
   }
 
   
   public List<Bond> findbond(Map<String, Object> pageMap) {
     return this.bondDao.findbond(pageMap);
   }
 
   
   public int bonddelete(String id) {
     return this.bondDao.bonddelete(id);
   }
 
   
   public int bondedit(Bond bond) {
     return this.bondDao.bondedit(bond);
   }
 
   
   public int addbond(Bond bond) {
     return this.bondDao.addbond(bond);
   }
 
   
   public int addlogf(Bond bond) {
     return this.bondDao.addlogf(bond);
   }
 }


