 package com.what21.dao.impl;
 
 import com.what21.dao.NuclearReleaseSkuDao;
 import com.what21.model.NuclearReleaseSku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class NuclearReleaseSkuDaoImpl
   implements NuclearReleaseSkuDao {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
 
   
   public List<NuclearReleaseSku> findBySku(Map<String, Object> pageMap) {
     return ((NuclearReleaseSkuDao)this.sqlSessionTemplate.getMapper(NuclearReleaseSkuDao.class)).findBySku(pageMap);
   }
 
 
   
   public int count(Map<String, Object> pageMap) {
     return ((NuclearReleaseSkuDao)this.sqlSessionTemplate.getMapper(NuclearReleaseSkuDao.class)).count(pageMap);
   }
 
 
   
   public void add(NuclearReleaseSku n) {
     ((NuclearReleaseSkuDao)this.sqlSessionTemplate.getMapper(NuclearReleaseSkuDao.class)).add(n);
   }
 
 
 
   
   public NuclearReleaseSku findId(Map<String, Object> pageMap) {
     return ((NuclearReleaseSkuDao)this.sqlSessionTemplate.getMapper(NuclearReleaseSkuDao.class)).findId(pageMap);
   }
 
   
   public List<NuclearReleaseSku> findAll(Map<String, Object> pageMap) {
     return ((NuclearReleaseSkuDao)this.sqlSessionTemplate.getMapper(NuclearReleaseSkuDao.class)).findAll(pageMap);
   }
 
   
   public List<NuclearReleaseSku> findByB(String billcode) {
     return ((NuclearReleaseSkuDao)this.sqlSessionTemplate.getMapper(NuclearReleaseSkuDao.class)).findByB(billcode);
   }
 }


