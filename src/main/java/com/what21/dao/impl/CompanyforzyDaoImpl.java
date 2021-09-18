 package com.what21.dao.impl;
 
 import com.what21.dao.CompanyforzyDao;
 import com.what21.model.CompanyZyAndOrderMail;
 import com.what21.model.Companyforzy;
 import com.what21.model.OrderMail;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class CompanyforzyDaoImpl
   implements CompanyforzyDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public Companyforzy findByZy(String bm) {
     return (Companyforzy)this.sqlSessionTemplate.selectOne(String.valueOf(Companyforzy.class.getName()) + ".findByZy", bm);
   }
 
 
 
   
   public List<CompanyZyAndOrderMail> findAllByCompanyCode(List<OrderMail> list) {
     return ((CompanyforzyDao)this.sqlSessionTemplate.getMapper(CompanyforzyDao.class)).findAllByCompanyCode(list);
   }
 
 
   
   public List<Companyforzy> findOver() {
     return this.sqlSessionTemplate.selectList(String.valueOf(Companyforzy.class.getName()) + ".findOver");
   }
 }


