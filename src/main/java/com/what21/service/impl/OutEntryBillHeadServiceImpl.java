 package com.what21.service.impl;
 
 import com.what21.dao.OutEntryBillHeadDao;
 import com.what21.model.OutEntryBillHead;
 import com.what21.service.OutEntryBillHeadService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class OutEntryBillHeadServiceImpl
   implements OutEntryBillHeadService
 {
   @Autowired
   private OutEntryBillHeadDao outEntryBillHeadDao;
   
   public List<OutEntryBillHead> findAll(Map<String, Object> pageMap) {
     return this.outEntryBillHeadDao.findAll(pageMap);
   }
 
 
   
   public int count(Map<String, Object> pageMap) {
     return this.outEntryBillHeadDao.count(pageMap);
   }
 
 
   
   public int delete(String id) {
     return this.outEntryBillHeadDao.delete(id);
   }
 
 
   
   public int add(OutEntryBillHead oeb) {
     return this.outEntryBillHeadDao.add(oeb);
   }
 }


