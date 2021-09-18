 package com.what21.dao.impl;
 
 import com.what21.dao.CollectDao;
 import com.what21.model.Collect;
 import com.what21.model.CollectQueryVo;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class CollectDaoImpl
   implements CollectDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<Collect> findAll(CollectQueryVo collectQueryVo) {
     return ((CollectDao)this.sqlSessionTemplate.getMapper(CollectDao.class)).findAll(collectQueryVo);
   }
 
   
   public int count(CollectQueryVo collectQueryVo) {
     return ((CollectDao)this.sqlSessionTemplate.getMapper(CollectDao.class)).count(collectQueryVo);
   }
 
   
   public void insert(Collect collect) {
     ((CollectDao)this.sqlSessionTemplate.getMapper(CollectDao.class)).insert(collect);
   }
 
 
   
   public void delete(int id) {
     ((CollectDao)this.sqlSessionTemplate.getMapper(CollectDao.class)).delete(id);
   }
 
 
   
   public Collect findById(int id) {
     return ((CollectDao)this.sqlSessionTemplate.getMapper(CollectDao.class)).findById(id);
   }
 
 
   
   public int countByTydh(String tydh) {
     return ((CollectDao)this.sqlSessionTemplate.getMapper(CollectDao.class)).countByTydh(tydh);
   }
 
   
   public void update(Collect collect) {
     ((CollectDao)this.sqlSessionTemplate.getMapper(CollectDao.class)).update(collect);
   }
 
 
   
   public Collect findByTydh(String tydh) {
     return ((CollectDao)this.sqlSessionTemplate.getMapper(CollectDao.class)).findByTydh(tydh);
   }
 
   
   public List<Collect> findAllBs(CollectQueryVo collectQueryVo) {
     return ((CollectDao)this.sqlSessionTemplate.getMapper(CollectDao.class)).findAllBs(collectQueryVo);
   }
 
   
   public int countBs(CollectQueryVo collectQueryVo) {
     return ((CollectDao)this.sqlSessionTemplate.getMapper(CollectDao.class)).countBs(collectQueryVo);
   }
 
   
   public List<Collect> findBySjAndSj2(Collect collect) {
     return ((CollectDao)this.sqlSessionTemplate.getMapper(CollectDao.class)).findBySjAndSj2(collect);
   }
 
   
   public int countBySjAndSj2(Collect collect) {
     return ((CollectDao)this.sqlSessionTemplate.getMapper(CollectDao.class)).countBySjAndSj2(collect);
   }
 
   
   public Collect findByYwbh(String ywbh) {
     return ((CollectDao)this.sqlSessionTemplate.getMapper(CollectDao.class)).findByYwbh(ywbh);
   }
 }


