 package com.what21.service.impl;
 
 import com.what21.dao.End_waybillDao;
 import com.what21.model.EndCompany;
 import com.what21.model.End_waybill;
 import com.what21.service.End_waybillService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class End_waybillServiceImpl
   implements End_waybillService
 {
   @Autowired
   private End_waybillDao end_waybillDao;
   
   public List<End_waybill> getAll() {
     return this.end_waybillDao.getAll();
   }
 
 
   
   public List<End_waybill> findCompany(Map<String, Object> map) {
     return this.end_waybillDao.findCompany(map);
   }
   
   public int addEnd(End_waybill end_waybill) {
     return this.end_waybillDao.addEnd(end_waybill);
   }
   
   public int count(Map<String, Object> map) {
     return this.end_waybillDao.count(map);
   }
   
   public List<EndCompany> findCompany() {
     return this.end_waybillDao.findCompany();
   }
 }


