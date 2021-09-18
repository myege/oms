 package com.what21.dao.impl;
 
 import com.what21.dao.InoutDao;
 import com.what21.model.Inout;
 import com.what21.model.InoutCustom;
 import com.what21.model.InoutPageQueryVo;
 import com.what21.model.LogInout;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class InoutDaoImpl
   implements InoutDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
 
   
   public List<Inout> findAll(Map<String, Object> pageMap) {
     return ((InoutDao)this.sqlSessionTemplate.getMapper(InoutDao.class)).findAll(pageMap);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return ((InoutDao)this.sqlSessionTemplate.getMapper(InoutDao.class)).count(pageMap);
   }
 
   
   public int indelete(String id) {
     return ((InoutDao)this.sqlSessionTemplate.getMapper(InoutDao.class)).indelete(id);
   }
 
   
   public List<Inout> findin(Map<String, Object> pageMap) {
     return ((InoutDao)this.sqlSessionTemplate.getMapper(InoutDao.class)).findin(pageMap);
   }
 
   
   public Inout findByI(String id) {
     return ((InoutDao)this.sqlSessionTemplate.getMapper(InoutDao.class)).findByI(id);
   }
 
   
   public void inserti(Inout inout) {
     ((InoutDao)this.sqlSessionTemplate.getMapper(InoutDao.class)).inserti(inout);
   }
 
 
   
   public int ints(String ids) {
     return ((InoutDao)this.sqlSessionTemplate.getMapper(InoutDao.class)).ints(ids);
   }
 
 
 
   
   public int inflag(Inout inout) {
     return ((InoutDao)this.sqlSessionTemplate.getMapper(InoutDao.class)).inflag(inout);
   }
 
   
   public List<Inout> findinId(String jobFormId) {
     return ((InoutDao)this.sqlSessionTemplate.getMapper(InoutDao.class)).findinId(jobFormId);
   }
 
   
   public List<Inout> inadd() {
     return ((InoutDao)this.sqlSessionTemplate.getMapper(InoutDao.class)).inadd();
   }
 
   
   public List<Inout> findByIds(Map<String, Object> pageMap) {
     return ((InoutDao)this.sqlSessionTemplate.getMapper(InoutDao.class)).findByIds(pageMap);
   }
 
   
   public Inout findBySelect(Integer id) {
     return ((InoutDao)this.sqlSessionTemplate.getMapper(InoutDao.class)).findBySelect(id);
   }
 
   
   public void insertLog(LogInout logInout) {
     ((InoutDao)this.sqlSessionTemplate.getMapper(InoutDao.class)).insertLog(logInout);
   }
 
 
   
   public int count2(InoutPageQueryVo inoutPageQueryVo) {
     return ((InoutDao)this.sqlSessionTemplate.getMapper(InoutDao.class)).count2(inoutPageQueryVo);
   }
 
 
   
   public List<InoutCustom> findAll2(InoutPageQueryVo inoutPageQueryVo) {
     return ((InoutDao)this.sqlSessionTemplate.getMapper(InoutDao.class)).findAll2(inoutPageQueryVo);
   }
 
   
   public List<Inout> findByPageQuery(InoutPageQueryVo inoutPageQueryVo) {
     return ((InoutDao)this.sqlSessionTemplate.getMapper(InoutDao.class)).findByPageQuery(inoutPageQueryVo);
   }
 }


