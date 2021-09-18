 package com.what21.dao.impl;
 
 import com.what21.dao.GoodsPushDao;
 import com.what21.model.GoodsPush;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class GoodsDaoPushImpl
   implements GoodsPushDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void insert(GoodsPush goods) {
     ((GoodsPushDao)this.sqlSessionTemplate.getMapper(GoodsPushDao.class)).insert(goods);
   }
 
   
   public List<GoodsPush> findByExpressNumber(String expressNumber) {
     return ((GoodsPushDao)this.sqlSessionTemplate.getMapper(GoodsPushDao.class)).findByExpressNumber(expressNumber);
   }
 }


