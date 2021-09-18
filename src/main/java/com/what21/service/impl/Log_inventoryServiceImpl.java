 package com.what21.service.impl;
 
 import com.what21.dao.Log_inventoryDao;
 import com.what21.model.Log_inventory;
 import com.what21.service.Log_inventoryService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class Log_inventoryServiceImpl
   implements Log_inventoryService
 {
   @Autowired
   private Log_inventoryDao log_inventoryDao;
   
   public void insert(Log_inventory log) {
     this.log_inventoryDao.insert(log);
   }
 
 
 
   
   public List<Log_inventory> findtoTinven(Map<String, Object> pageMap) {
     return this.log_inventoryDao.findtoTinven(pageMap);
   }
 
 
 
   
   public int count(Map<String, Object> pageMap) {
     return this.log_inventoryDao.count(pageMap);
   }
 }


