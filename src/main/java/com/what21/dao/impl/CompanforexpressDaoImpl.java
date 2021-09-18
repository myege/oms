 package com.what21.dao.impl;
 
 import com.what21.dao.CompanforexpressDao;
 import com.what21.model.Companforexpress;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class CompanforexpressDaoImpl
   implements CompanforexpressDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<Companforexpress> findAll() {
     return this.sqlSessionTemplate.selectList(String.valueOf(Companforexpress.class.getName()) + ".findAll");
   }
 
   
   public Companforexpress findByBm(String bm) {
     return (Companforexpress)this.sqlSessionTemplate.selectOne(String.valueOf(Companforexpress.class.getName()) + ".findByBm", bm);
   }
 }


