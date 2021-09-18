 package com.what21.dao.impl;
 
 import com.what21.dao.BuyerLimitDao;
 import com.what21.model.BuyerLimit;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class BuyerLimitDaoImpl
   implements BuyerLimitDao {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public BuyerLimit findByNameAndNameId(String name, String nameId) {
     return ((BuyerLimitDao)this.sqlSessionTemplate.getMapper(BuyerLimitDao.class)).findByNameAndNameId(name, nameId);
   }
 
   
   public void insert(BuyerLimit buyerLimit) {
     ((BuyerLimitDao)this.sqlSessionTemplate.getMapper(BuyerLimitDao.class)).insert(buyerLimit);
   }
 
 
   
   public void update(BuyerLimit buyerLimit) {
     ((BuyerLimitDao)this.sqlSessionTemplate.getMapper(BuyerLimitDao.class)).update(buyerLimit);
   }
 }


