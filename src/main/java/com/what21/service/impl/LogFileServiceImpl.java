 package com.what21.service.impl;
 
 import com.what21.dao.LogFileDao;
 import com.what21.model.LogFile;
 import com.what21.service.LogFileService;
 import java.math.BigDecimal;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class LogFileServiceImpl
   implements LogFileService
 {
   @Autowired
   private LogFileDao logFileDao;
   
   public List<LogFile> findAll(Map<String, Object> pageMap) {
     return this.logFileDao.findAll(pageMap);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return this.logFileDao.count(pageMap);
   }
 
   
   public int update(LogFile logFile) {
     return this.logFileDao.update(logFile);
   }
 
   
   public LogFile findlog() {
     return this.logFileDao.findlog();
   }
 
   
   public int addlogf(LogFile logFile) {
     if (this.logFileDao.getUserId(logFile) == null) {
       return this.logFileDao.addlogf(logFile);
     }
     return 2;
   }
 
   
   public int logfdelete(String id) {
     return this.logFileDao.logfdelete(id);
   }
 
   
   public int logfedit(LogFile logFile) {
     return this.logFileDao.logfedit(logFile);
   }
 
   
   public LogFile findAllLogFile(int id) {
     return this.logFileDao.findAllLogFile(id);
   }
 
   
   public String getTax(int userId) {
     String ret = "";
     BigDecimal bzjs = new BigDecimal(0);
     if (userId != 0) {
       LogFile logFile = new LogFile();
       logFile.setUserId(userId);
       logFile = this.logFileDao.getTax(logFile);
       if (logFile != null && 
         logFile.getResidualMargin() != null) {
         String bzj = logFile.getResidualMargin();
         bzjs = new BigDecimal(bzj);
       } 
       
       ret = "保证金：" + bzjs;
     } else {
       
       ret = " ";
     } 
     return ret;
   }
   
   public LogFile findlogzf(int id) {
     return this.logFileDao.findlogzf(id);
   }
 
   
   public int frostMo(LogFile logFile) {
     return this.logFileDao.frostMo(logFile);
   }
 }


