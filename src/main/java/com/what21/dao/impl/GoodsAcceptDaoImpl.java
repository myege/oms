 package com.what21.dao.impl;
 
 import com.what21.dao.GoodsAcceptDao;
 import com.what21.model.GoodsAccept;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class GoodsAcceptDaoImpl
   implements GoodsAcceptDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public int insert(GoodsAccept goods) {
     return ((GoodsAcceptDao)this.sqlSessionTemplate.getMapper(GoodsAcceptDao.class)).insert(goods);
   }
 
   
   public List<GoodsAccept> findByExpressNumber(String expressNumber) {
     return ((GoodsAcceptDao)this.sqlSessionTemplate.getMapper(GoodsAcceptDao.class)).findByExpressNumber(expressNumber);
   }
 
   
   public int delete(String[] expressNumbers) {
     return ((GoodsAcceptDao)this.sqlSessionTemplate.getMapper(GoodsAcceptDao.class)).delete(expressNumbers);
   }
 }


