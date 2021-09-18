 package com.what21.dao.impl;
 
 import com.what21.dao.StojsDao;
 import com.what21.model.PWC;
 import com.what21.model.Stojs;
 import com.what21.model.StojsBb;
 import com.what21.model.StojsQd;
 import com.what21.model.StojsQueryVo;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class StojsDaoImpl
   implements StojsDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<Stojs> findAll(StojsQueryVo stojsQueryVo) {
     return ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).findAll(stojsQueryVo);
   }
 
   
   public void deleteQdsByIds(Integer[] ids) {
     ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).deleteQdsByIds(ids);
   }
 
 
   
   public void deleteByIds(Integer[] ids) {
     ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).deleteByIds(ids);
   }
 
 
   
   public void insert(Stojs stojs) {
     ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).insert(stojs);
   }
 
 
   
   public Integer count(StojsQueryVo stojsQueryVo) {
     return ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).count(stojsQueryVo);
   }
   
   public List<StojsQd> findAllQd(StojsQueryVo stojsQueryVo) {
     return ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).findAllQd(stojsQueryVo);
   }
   
   public Integer countQd(StojsQueryVo stojsQueryVo) {
     return ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).countQd(stojsQueryVo);
   }
 
   
   public Stojs findById(Integer pid) {
     return ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).findById(pid);
   }
 
   
   public Integer countByMailNo(String mailno) {
     return ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).countByMailNo(mailno);
   }
 
   
   public void insertQdBatch(List<StojsQd> list) {
     ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).insertQdBatch(list);
   }
 
 
   
   public void updateQdztByIds(List<Integer> stojsids) {
     ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).updateQdztByIds(stojsids);
   }
 
 
   
   public List<PWC> getPWCs1(Integer id) {
     return ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).getPWCs1(id);
   }
 
   
   public List<PWC> getPWCs2(Integer id) {
     return ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).getPWCs2(id);
   }
 
   
   public List<PWC> getPWCs3(Integer id) {
     return ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).getPWCs3(id);
   }
 
   
   public void inserBbBatch(List<StojsBb> bbs) {
     ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).inserBbBatch(bbs);
   }
 
 
   
   public void updateBbztById(Integer id) {
     ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).updateBbztById(id);
   }
 
 
   
   public void deleteBbsByIds(Integer[] ids) {
     ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).deleteBbsByIds(ids);
   }
 
 
   
   public Integer countBb(StojsQueryVo stojsQueryVo) {
     return ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).countBb(stojsQueryVo);
   }
 
   
   public List<StojsBb> findAllBb(StojsQueryVo stojsQueryVo) {
     return ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).findAllBb(stojsQueryVo);
   }
 
   
   public PWC getPWCs1NotGroup(Integer id) {
     return ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).getPWCs1NotGroup(id);
   }
 
   
   public PWC getPWCs2NotGroup(Integer id) {
     return ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).getPWCs2NotGroup(id);
   }
 
   
   public PWC getPWCs3NotGroup(Integer id) {
     return ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).getPWCs3NotGroup(id);
   }
 
   
   public void updateQdztById(Integer id) {
     ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).updateQdztById(id);
   }
 
 
   
   public PWC getPWCs4NotGroup(Integer id) {
     return ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).getPWCs4NotGroup(id);
   }
 
   
   public PWC getPWCs5NotGroup(Integer id) {
     return ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).getPWCs5NotGroup(id);
   }
 
   
   public List<PWC> getPWCs4(Integer id) {
     return ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).getPWCs4(id);
   }
 
   
   public List<PWC> getPWCs5(Integer id) {
     return ((StojsDao)this.sqlSessionTemplate.getMapper(StojsDao.class)).getPWCs5(id);
   }
 }


