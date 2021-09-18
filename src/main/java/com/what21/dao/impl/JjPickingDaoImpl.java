 package com.what21.dao.impl;
 
 import com.what21.dao.JjPickingDao;
 import com.what21.model.JjPicking;
 import com.what21.model.JjPickingCustom;
 import com.what21.model.JjPickingVo;
 import java.util.List;
 import org.apache.ibatis.annotations.Param;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class JjPickingDaoImpl
   implements JjPickingDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public int count(JjPickingVo jjPickingVo) {
     return ((JjPickingDao)this.sqlSessionTemplate.getMapper(JjPickingDao.class)).count(jjPickingVo);
   }
 
   
   public List<JjPicking> findAll(JjPickingVo jjPickingVo) {
     return ((JjPickingDao)this.sqlSessionTemplate.getMapper(JjPickingDao.class)).findAll(jjPickingVo);
   }
 
   
   public int distributeLeaflets(@Param("jjPickingCustom") JjPickingCustom jjPickingCustom) {
     return ((JjPickingDao)this.sqlSessionTemplate.getMapper(JjPickingDao.class)).distributeLeaflets(jjPickingCustom);
   }
 
 
   
   public List<JjPicking> findPick() {
     return ((JjPickingDao)this.sqlSessionTemplate.getMapper(JjPickingDao.class)).findPick();
   }
 
   
   public void insertBatch(List<JjPicking> list) {
     ((JjPickingDao)this.sqlSessionTemplate.getMapper(JjPickingDao.class)).insertBatch(list);
   }
   
   public List<JjPicking> findbygjz(String gzj) {
     return ((JjPickingDao)this.sqlSessionTemplate.getMapper(JjPickingDao.class)).findbygjz(gzj);
   }
   public List<JjPicking> findbygjz2(@Param("startTime") String startTime, @Param("endTime") String endTime) {
     return ((JjPickingDao)this.sqlSessionTemplate.getMapper(JjPickingDao.class)).findbygjz2(startTime, endTime);
   }
   public List<JjPicking> findbygjz3(@Param("startTime") String startTime, @Param("endTime") String endTime) {
     return ((JjPickingDao)this.sqlSessionTemplate.getMapper(JjPickingDao.class)).findbygjz3(startTime, endTime);
   }
   public List<JjPicking> findbyby(String gzj) {
     return ((JjPickingDao)this.sqlSessionTemplate.getMapper(JjPickingDao.class)).findbyby(gzj);
   }
   
   public List<JjPicking> findbybn(String gzj) {
     return ((JjPickingDao)this.sqlSessionTemplate.getMapper(JjPickingDao.class)).findbybn(gzj);
   }
 
 
   
   public JjPicking findById(String id) {
     return ((JjPickingDao)this.sqlSessionTemplate.getMapper(JjPickingDao.class)).findById(id);
   }
 
 
   
   public int updateById(JjPicking jjPicking) {
     return ((JjPickingDao)this.sqlSessionTemplate.getMapper(JjPickingDao.class)).updateById(jjPicking);
   }
 }


