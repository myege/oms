 package com.what21.dao.impl;
 
 import com.what21.dao.ExpressOptionsDao;
 import com.what21.model.ExpressOptions;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class ExpressOptionsDaoImpl
   implements ExpressOptionsDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public ExpressOptions findByKey(String key) {
     return ((ExpressOptionsDao)this.sqlSessionTemplate.getMapper(ExpressOptionsDao.class)).findByKey(key);
   }
 
   
   public void insert(ExpressOptions options) {
     ((ExpressOptionsDao)this.sqlSessionTemplate.getMapper(ExpressOptionsDao.class)).insert(options);
   }
 
   
   public void updateByKey(ExpressOptions options) {
     ((ExpressOptionsDao)this.sqlSessionTemplate.getMapper(ExpressOptionsDao.class)).updateByKey(options);
   }
 
   
   public List<ExpressOptions> selectList() {
     return ((ExpressOptionsDao)this.sqlSessionTemplate.getMapper(ExpressOptionsDao.class)).selectList();
   }
 }


