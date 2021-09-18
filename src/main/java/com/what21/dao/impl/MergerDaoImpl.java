 package com.what21.dao.impl;
 
 import com.what21.dao.MergerDao;
 import com.what21.model.Merger;
 import com.what21.model.MergerCustom;
 import com.what21.model.MergerQueryVo;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class MergerDaoImpl
   implements MergerDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<Merger> findAll(Map<String, Object> pageMap) {
     return ((MergerDao)this.sqlSessionTemplate.getMapper(MergerDao.class)).findAll(pageMap);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return ((MergerDao)this.sqlSessionTemplate.getMapper(MergerDao.class)).count(pageMap);
   }
 
   
   public int merdelete(String id) {
     return ((MergerDao)this.sqlSessionTemplate.getMapper(MergerDao.class)).merdelete(id);
   }
 
   
   public List<Merger> cx(Map<String, Object> pageMap) {
     return ((MergerDao)this.sqlSessionTemplate.getMapper(MergerDao.class)).cx(pageMap);
   }
 
   
   public void insert1(Merger merger) {
     ((MergerDao)this.sqlSessionTemplate.getMapper(MergerDao.class)).insert1(merger);
   }
 
 
   
   public Merger findByid(String id) {
     return ((MergerDao)this.sqlSessionTemplate.getMapper(MergerDao.class)).findByid(id);
   }
 
   
   public int mets(String ids) {
     return ((MergerDao)this.sqlSessionTemplate.getMapper(MergerDao.class)).mets(ids);
   }
 
   
   public Merger meadd(Integer id) {
     return ((MergerDao)this.sqlSessionTemplate.getMapper(MergerDao.class)).meadd(id);
   }
 
   
   public int mflag(Merger merger) {
     return ((MergerDao)this.sqlSessionTemplate.getMapper(MergerDao.class)).mflag(merger);
   }
 
   
   public int count2(MergerQueryVo mergerQueryVo) {
     return ((MergerDao)this.sqlSessionTemplate.getMapper(MergerDao.class)).count2(mergerQueryVo);
   }
 
   
   public List<MergerCustom> findAll2(MergerQueryVo mergerQueryVo) {
     return ((MergerDao)this.sqlSessionTemplate.getMapper(MergerDao.class)).findAll2(mergerQueryVo);
   }
 }


