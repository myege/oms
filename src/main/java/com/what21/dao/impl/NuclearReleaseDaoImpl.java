 package com.what21.dao.impl;
 
 import com.what21.dao.NuclearReleaseDao;
 import com.what21.model.NuclearRelease;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class NuclearReleaseDaoImpl
   implements NuclearReleaseDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
 
   
   public int countNuclearRelease() {
     return ((NuclearReleaseDao)this.sqlSessionTemplate.getMapper(NuclearReleaseDao.class)).countNuclearRelease();
   }
 
 
   
   public List<NuclearRelease> findAll(Map<String, Object> map) {
     return ((NuclearReleaseDao)this.sqlSessionTemplate.getMapper(NuclearReleaseDao.class)).findAll(map);
   }
 
 
   
   public int count(Map<String, Object> pageMap) {
     return ((NuclearReleaseDao)this.sqlSessionTemplate.getMapper(NuclearReleaseDao.class)).count(pageMap);
   }
 
 
   
   public List<NuclearRelease> find(Map<String, Object> pageMap) {
     return ((NuclearReleaseDao)this.sqlSessionTemplate.getMapper(NuclearReleaseDao.class)).find(pageMap);
   }
 
 
   
   public int addNu(NuclearRelease n) {
     return ((NuclearReleaseDao)this.sqlSessionTemplate.getMapper(NuclearReleaseDao.class)).addNu(n);
   }
 
 
   
   public int deleteN(String id) {
     return ((NuclearReleaseDao)this.sqlSessionTemplate.getMapper(NuclearReleaseDao.class)).deleteN(id);
   }
 
 
 
   
   public NuclearRelease findByPc(NuclearRelease n) {
     return ((NuclearReleaseDao)this.sqlSessionTemplate.getMapper(NuclearReleaseDao.class)).findByPc(n);
   }
 }


