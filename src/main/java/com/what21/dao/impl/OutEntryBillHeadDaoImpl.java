 package com.what21.dao.impl;
 
 import com.what21.dao.OutEntryBillHeadDao;
 import com.what21.model.OutEntryBillHead;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class OutEntryBillHeadDaoImpl
   implements OutEntryBillHeadDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
 
   
   public List<OutEntryBillHead> findAll(Map<String, Object> pageMap) {
     return ((OutEntryBillHeadDao)this.sqlSessionTemplate.getMapper(OutEntryBillHeadDao.class)).findAll(pageMap);
   }
 
 
   
   public int count(Map<String, Object> pageMap) {
     return ((OutEntryBillHeadDao)this.sqlSessionTemplate.getMapper(OutEntryBillHeadDao.class)).count(pageMap);
   }
 
 
   
   public int delete(String id) {
     return ((OutEntryBillHeadDao)this.sqlSessionTemplate.getMapper(OutEntryBillHeadDao.class)).delete(id);
   }
 
 
   
   public int add(OutEntryBillHead oeb) {
     return ((OutEntryBillHeadDao)this.sqlSessionTemplate.getMapper(OutEntryBillHeadDao.class)).add(oeb);
   }
 }


