 package com.what21.dao.impl;
 
 import com.what21.dao.CountOrderDao;
 import com.what21.model.TCountorder;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class CountOrderDaoImpl
   implements CountOrderDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<TCountorder> findAll(TCountorder map) throws Exception {
     return this.sqlSessionTemplate.selectList(String.valueOf(TCountorder.class.getName()) + ".findAll", map);
   }
 
   
   public Integer count(TCountorder map) throws Exception {
     return (Integer)this.sqlSessionTemplate.selectOne(String.valueOf(TCountorder.class.getName()) + ".count", map);
   }
 
   
   public Integer add(TCountorder countOrder) throws Exception {
     return Integer.valueOf(this.sqlSessionTemplate.insert(String.valueOf(TCountorder.class.getName()) + ".add", countOrder));
   }
 
   
   public TCountorder findMailNo(String mailNo) throws Exception {
     return (TCountorder)this.sqlSessionTemplate.selectOne(String.valueOf(TCountorder.class.getName()) + ".findMailNo", mailNo);
   }
 
   
   public Integer updateState(List<TCountorder> oo) throws Exception {
     return (Integer)this.sqlSessionTemplate.selectOne(String.valueOf(TCountorder.class.getName()) + ".updateState", oo);
   }
 
   
   public Integer delete(String[] allmailno) throws Exception {
     return (Integer)this.sqlSessionTemplate.selectOne(String.valueOf(TCountorder.class.getName()) + ".delete", allmailno);
   }
 
   
   public List<TCountorder> findMailNoOne(String mailNo) throws Exception {
     return this.sqlSessionTemplate.selectList(String.valueOf(TCountorder.class.getName()) + ".findMailNoOne", mailNo);
   }
 }


