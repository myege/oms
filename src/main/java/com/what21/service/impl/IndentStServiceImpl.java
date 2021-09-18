 package com.what21.service.impl;
 
 import com.what21.dao.IndentStDao;
 import com.what21.model.IndentState;
 import com.what21.service.IndentStService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class IndentStServiceImpl
   implements IndentStService
 {
   @Autowired
   IndentStDao indentStDao;
   
   public List<IndentState> findIndentId(String indentId) {
     return this.indentStDao.findIndentId(indentId);
   }
 }


