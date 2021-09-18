 package com.what21.dao.impl;
 
 import com.what21.dao.IndentStDao;
 import com.what21.model.IndentState;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class IndentStDaoImpl
   implements IndentStDao
 {
   @Autowired
   SqlSessionTemplate sqlSessionTemplate;
   
   public List<IndentState> findIndentId(String indentId) {
     return this.sqlSessionTemplate.selectList(String.valueOf(IndentState.class.getName()) + ".findIndentId", indentId);
   }
 }


