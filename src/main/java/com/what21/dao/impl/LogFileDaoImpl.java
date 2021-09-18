 package com.what21.dao.impl;
 
 import com.what21.dao.LogFileDao;
 import com.what21.model.LogFile;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class LogFileDaoImpl
   implements LogFileDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<LogFile> findAll(Map<String, Object> pageMap) {
     return ((LogFileDao)this.sqlSessionTemplate.getMapper(LogFileDao.class)).findAll(pageMap);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return ((LogFileDao)this.sqlSessionTemplate.getMapper(LogFileDao.class)).count(pageMap);
   }
 
   
   public int update(LogFile logFile) {
     return ((LogFileDao)this.sqlSessionTemplate.getMapper(LogFileDao.class)).update(logFile);
   }
 
   
   public LogFile findlog() {
     return ((LogFileDao)this.sqlSessionTemplate.getMapper(LogFileDao.class)).findlog();
   }
 
   
   public int addlogf(LogFile logFile) {
     return ((LogFileDao)this.sqlSessionTemplate.getMapper(LogFileDao.class)).addlogf(logFile);
   }
 
   
   public int logfdelete(String id) {
     return ((LogFileDao)this.sqlSessionTemplate.getMapper(LogFileDao.class)).logfdelete(id);
   }
 
   
   public int logfedit(LogFile logFile) {
     return ((LogFileDao)this.sqlSessionTemplate.getMapper(LogFileDao.class)).logfedit(logFile);
   }
 
   
   public LogFile findAllLogFile(int id) {
     return ((LogFileDao)this.sqlSessionTemplate.getMapper(LogFileDao.class)).findAllLogFile(id);
   }
 
   
   public LogFile getTax(LogFile logFile) {
     return ((LogFileDao)this.sqlSessionTemplate.getMapper(LogFileDao.class)).getTax(logFile);
   }
 
   
   public LogFile findlogzf(int id) {
     return ((LogFileDao)this.sqlSessionTemplate.getMapper(LogFileDao.class)).findlogzf(id);
   }
 
   
   public int frostMo(LogFile logFile) {
     return ((LogFileDao)this.sqlSessionTemplate.getMapper(LogFileDao.class)).frostMo(logFile);
   }
 
   
   public LogFile getUserId(LogFile logFile) {
     return ((LogFileDao)this.sqlSessionTemplate.getMapper(LogFileDao.class)).getUserId(logFile);
   }
 }


