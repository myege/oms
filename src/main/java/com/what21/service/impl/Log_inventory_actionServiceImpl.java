 package com.what21.service.impl;
 
 import com.what21.dao.Log_inventory_actionDao;
 import com.what21.model.Log_inventory_action;
 import com.what21.service.Log_inventory_actionService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class Log_inventory_actionServiceImpl
   implements Log_inventory_actionService
 {
   @Autowired
   private Log_inventory_actionDao log_inventory_actionDao;
   
   public void insert(Log_inventory_action log) {
     this.log_inventory_actionDao.insert(log);
   }
 }


