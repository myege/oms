 package com.what21.dao.impl;
 
 import com.what21.dao.CirculaTionDao;
 import com.what21.model.CirculaTion;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class CirculaTionDaoImpl
   implements CirculaTionDao {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return ((CirculaTionDao)this.sqlSessionTemplate.getMapper(CirculaTionDao.class)).count(pageMap);
   }
 
   
   public List<CirculaTion> findAll(Map<String, Object> pageMap) {
     return ((CirculaTionDao)this.sqlSessionTemplate.getMapper(CirculaTionDao.class)).findAll(pageMap);
   }
 
   
   public int inserta(CirculaTion u) {
     return ((CirculaTionDao)this.sqlSessionTemplate.getMapper(CirculaTionDao.class)).inserta(u);
   }
 
   
   public int deletecir(String id) {
     return ((CirculaTionDao)this.sqlSessionTemplate.getMapper(CirculaTionDao.class)).deletecir(id);
   }
 
   
   public CirculaTion findBycir(String id) {
     return ((CirculaTionDao)this.sqlSessionTemplate.getMapper(CirculaTionDao.class)).findBycir(id);
   }
 
   
   public void incir(CirculaTion ct) {
     ((CirculaTionDao)this.sqlSessionTemplate.getMapper(CirculaTionDao.class)).incir(ct);
   }
 
   
   public int insertc(CirculaTion circulaTion) {
     return ((CirculaTionDao)this.sqlSessionTemplate.getMapper(CirculaTionDao.class)).insertc(circulaTion);
   }
 
   
   public int insertb(CirculaTion circulaTion) {
     return ((CirculaTionDao)this.sqlSessionTemplate.getMapper(CirculaTionDao.class)).insertb(circulaTion);
   }
 
   
   public List<CirculaTion> findByBill(String id) {
     return ((CirculaTionDao)this.sqlSessionTemplate.getMapper(CirculaTionDao.class)).findByBill(id);
   }
 
   
   public List<CirculaTion> findcirAll(Map<String, Object> pageMap) {
     return ((CirculaTionDao)this.sqlSessionTemplate.getMapper(CirculaTionDao.class)).findcirAll(pageMap);
   }
 
   
   public int insertall(CirculaTion u) {
     return ((CirculaTionDao)this.sqlSessionTemplate.getMapper(CirculaTionDao.class)).insertall(u);
   }
 
   
   public int insertqd(CirculaTion u) {
     return ((CirculaTionDao)this.sqlSessionTemplate.getMapper(CirculaTionDao.class)).insertqd(u);
   }
 
   
   public int upcirsb(CirculaTion circulaTion) {
     return ((CirculaTionDao)this.sqlSessionTemplate.getMapper(CirculaTionDao.class)).upcirsb(circulaTion);
   }
 
 
   
   public int insertaa(CirculaTion circulaTion) {
     return ((CirculaTionDao)this.sqlSessionTemplate.getMapper(CirculaTionDao.class)).insertaa(circulaTion);
   }
 
   
   public int insciraa(CirculaTion circulaTion) {
     return ((CirculaTionDao)this.sqlSessionTemplate.getMapper(CirculaTionDao.class)).insciraa(circulaTion);
   }
 }


