 package com.what21.service.impl;
 
 import com.what21.dao.EndCompanyDao;
 import com.what21.model.EndCompany;
 import com.what21.service.EndCompanyService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 @Service
 public class EndCompanyServiceImpl
   implements EndCompanyService
 {
   @Autowired
   private EndCompanyDao companyDao;
   
   public List<EndCompany> findAll(Map<String, Object> map) {
     return this.companyDao.findAll(map);
   }
   
   public int count(Map<String, Object> map) {
     return this.companyDao.count(map);
   }
 
   
   public List<EndCompany> findCond(Map<String, Object> map) {
     return this.companyDao.findCond(map);
   }
 
   
   public int addCom(EndCompany end) {
     return this.companyDao.addCom(end);
   }
 
   
   public int delete(int id) {
     return this.companyDao.delete(id);
   }
 
   
   public int update(EndCompany end) {
     return this.companyDao.update(end);
   }
 }


