 package com.what21.dao.impl;
 
 import com.what21.dao.CompanyForOutBSDao;
 import com.what21.model.CompanyForOutBS;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class CompanyForOutBSDaoImpl
   implements CompanyForOutBSDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public CompanyForOutBS findByItemcode(String itemcode) {
     return ((CompanyForOutBSDao)this.sqlSessionTemplate.getMapper(CompanyForOutBSDao.class)).findByItemcode(itemcode);
   }
 }


