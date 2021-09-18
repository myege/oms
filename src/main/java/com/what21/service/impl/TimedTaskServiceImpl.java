 package com.what21.service.impl;
 
 import com.what21.dao.TimedTaskDao;
 import com.what21.model.TimedTask;
 import com.what21.service.TimedTaskService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 @Service
 public class TimedTaskServiceImpl
   implements TimedTaskService
 {
   @Autowired
   private TimedTaskDao timedTaskDao;
   
   public List<TimedTask> findAll(Map<String, Object> map) throws Exception {
     return this.timedTaskDao.findAll(map);
   }
 
   
   public Integer count(Map<String, Object> map) throws Exception {
     return this.timedTaskDao.count(map);
   }
 
   
   public int add(TimedTask timedTask) throws Exception {
     return this.timedTaskDao.add(timedTask);
   }
 
   
   public int delete(String ids) throws Exception {
     return this.timedTaskDao.delete(ids);
   }
 
   
   public int update(TimedTask timedTask) throws Exception {
     return this.timedTaskDao.update(timedTask);
   }
 }


