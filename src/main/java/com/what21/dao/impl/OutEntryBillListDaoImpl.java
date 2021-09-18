 package com.what21.dao.impl;
 
 import com.what21.dao.OutEntryBillListDao;
 import com.what21.model.OutEntryBillList;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class OutEntryBillListDaoImpl
   implements OutEntryBillListDao {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public int add(OutEntryBillList oel) {
     return ((OutEntryBillListDao)this.sqlSessionTemplate.getMapper(OutEntryBillListDao.class)).add(oel);
   }
 
   
   public int delete(String id) {
     return ((OutEntryBillListDao)this.sqlSessionTemplate.getMapper(OutEntryBillListDao.class)).delete(id);
   }
 }


