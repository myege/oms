 package com.what21.dao.impl;
 
 import com.what21.dao.CompanyForOutDao;
 import com.what21.model.CompanyForOut;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class CompanyForOutDaoImpl
   implements CompanyForOutDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public CompanyForOut findByItemcode(String itemcode) {
     return ((CompanyForOutDao)this.sqlSessionTemplate.getMapper(CompanyForOutDao.class)).findByItemcode(itemcode);
   }
 }


