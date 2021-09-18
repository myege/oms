 package com.what21.service.impl;
 
 import com.what21.dao.OutEntryBillListDao;
 import com.what21.model.OutEntryBillList;
 import com.what21.service.OutEntryBillListService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 @Service
 public class OutEntryBillListServiceImpl
   implements OutEntryBillListService
 {
   @Autowired
   private OutEntryBillListDao outEntryBillListDao;
   
   public int add(OutEntryBillList oel) {
     return this.outEntryBillListDao.add(oel);
   }
 
 
   
   public int delete(String id) {
     return this.outEntryBillListDao.delete(id);
   }
 }


