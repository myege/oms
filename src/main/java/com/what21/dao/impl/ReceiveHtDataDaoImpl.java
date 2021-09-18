 package com.what21.dao.impl;
 
 import com.what21.dao.ReceiveHtDataDao;
 import com.what21.model.ReceiveHtData;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class ReceiveHtDataDaoImpl
   implements ReceiveHtDataDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public int insert(ReceiveHtData receiveHghzData) {
     return ((ReceiveHtDataDao)this.sqlSessionTemplate.getMapper(ReceiveHtDataDao.class)).insert(receiveHghzData);
   }
 
   
   public List<ReceiveHtData> findAll(Map<String, Object> pageMap) {
     return ((ReceiveHtDataDao)this.sqlSessionTemplate.getMapper(ReceiveHtDataDao.class)).findAll(pageMap);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return ((ReceiveHtDataDao)this.sqlSessionTemplate.getMapper(ReceiveHtDataDao.class)).count(pageMap);
   }
 
   
   public int update(String[] idArr) {
     return ((ReceiveHtDataDao)this.sqlSessionTemplate.getMapper(ReceiveHtDataDao.class)).update(idArr);
   }
 }


