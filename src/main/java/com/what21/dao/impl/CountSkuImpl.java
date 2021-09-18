 package com.what21.dao.impl;
 
 import com.what21.dao.CountSkuDao;
 import com.what21.model.TCountsku;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class CountSkuImpl
   implements CountSkuDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public Integer addSku(List<TCountsku> tt) throws Exception {
     return Integer.valueOf(this.sqlSessionTemplate.insert(String.valueOf(TCountsku.class.getName()) + ".addSku", tt));
   }
 
   
   public TCountsku findMailNo(String mailNo) throws Exception {
     return (TCountsku)this.sqlSessionTemplate.selectOne(String.valueOf(TCountsku.class.getName()) + ".findMailNo", mailNo);
   }
 
   
   public Integer delete(String[] allmaillno) throws Exception {
     return (Integer)this.sqlSessionTemplate.selectOne(String.valueOf(TCountsku.class.getName()) + ".delete", allmaillno);
   }
 }


