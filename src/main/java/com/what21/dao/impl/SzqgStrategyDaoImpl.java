 package com.what21.dao.impl;
 
 import com.what21.dao.SzqgStrategyDao;
 import com.what21.model.SzqgStrategy;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class SzqgStrategyDaoImpl
   implements SzqgStrategyDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<SzqgStrategy> findgjz(String gzj) {
     return ((SzqgStrategyDao)this.sqlSessionTemplate.getMapper(SzqgStrategyDao.class)).findgjz(gzj);
   }
 
 
   
   public SzqgStrategy findBySt(String St) {
     return ((SzqgStrategyDao)this.sqlSessionTemplate.getMapper(SzqgStrategyDao.class)).findBySt(St);
   }
 }


