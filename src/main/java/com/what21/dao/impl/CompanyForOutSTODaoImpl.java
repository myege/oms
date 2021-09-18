 package com.what21.dao.impl;
 
 import com.what21.dao.CompanyForOutSTODao;
 import com.what21.model.CompanyforoutSto;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class CompanyForOutSTODaoImpl
   implements CompanyForOutSTODao {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public CompanyforoutSto findByItemcode(String itemcode) {
     return ((CompanyForOutSTODao)this.sqlSessionTemplate.getMapper(CompanyForOutSTODao.class)).findByItemcode(itemcode);
   }
 }


