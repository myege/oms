 package com.what21.dao.impl;
 
 import com.what21.dao.WinventoryDao;
 import com.what21.model.Winventory;
 import com.what21.model.WinventoryQueryVo;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class WinventoryDaoImpl
   implements WinventoryDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
 
   
   public List<Winventory> findAll(Map<String, Object> pageMap) {
     return ((WinventoryDao)this.sqlSessionTemplate.getMapper(WinventoryDao.class)).findAll(pageMap);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return ((WinventoryDao)this.sqlSessionTemplate.getMapper(WinventoryDao.class)).count(pageMap);
   }
 
   
   public int wdelete(String id) {
     return ((WinventoryDao)this.sqlSessionTemplate.getMapper(WinventoryDao.class)).wdelete(id);
   }
 
   
   public List<Winventory> findw(Map<String, Object> pageMap) {
     return ((WinventoryDao)this.sqlSessionTemplate.getMapper(WinventoryDao.class)).findw(pageMap);
   }
 
 
   
   public Winventory findByW(String id) {
     return ((WinventoryDao)this.sqlSessionTemplate.getMapper(WinventoryDao.class)).findByW(id);
   }
 
 
   
   public void insertw(Winventory winventory) {
     ((WinventoryDao)this.sqlSessionTemplate.getMapper(WinventoryDao.class)).insertw(winventory);
   }
 
 
 
   
   public int wints() {
     return ((WinventoryDao)this.sqlSessionTemplate.getMapper(WinventoryDao.class)).wints();
   }
   
   public int wflag(Winventory winventory) {
     return ((WinventoryDao)this.sqlSessionTemplate.getMapper(WinventoryDao.class)).wflag(winventory);
   }
 
 
   
   public List<Winventory> findwId(String manualId) {
     return ((WinventoryDao)this.sqlSessionTemplate.getMapper(WinventoryDao.class)).findwId(manualId);
   }
 
 
   
   public List<Winventory> wadd() {
     return ((WinventoryDao)this.sqlSessionTemplate.getMapper(WinventoryDao.class)).wadd();
   }
 
 
   
   public int count2(WinventoryQueryVo winventoryQueryVo) {
     return ((WinventoryDao)this.sqlSessionTemplate.getMapper(WinventoryDao.class)).count2(winventoryQueryVo);
   }
 
 
   
   public List<Winventory> findAll2(WinventoryQueryVo winventoryQueryVo) {
     return ((WinventoryDao)this.sqlSessionTemplate.getMapper(WinventoryDao.class)).findAll2(winventoryQueryVo);
   }
 }


