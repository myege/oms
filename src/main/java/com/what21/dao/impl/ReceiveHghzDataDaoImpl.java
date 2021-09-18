 package com.what21.dao.impl;
 
 import com.what21.dao.ReceiveHghzDataDao;
 import com.what21.model.ReceiveHghzData;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class ReceiveHghzDataDaoImpl
   implements ReceiveHghzDataDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public int insert(ReceiveHghzData receiveHghzData) {
     return ((ReceiveHghzDataDao)this.sqlSessionTemplate.getMapper(ReceiveHghzDataDao.class)).insert(receiveHghzData);
   }
 
   
   public List<ReceiveHghzData> findAll(Map<String, Object> pageMap) {
     return ((ReceiveHghzDataDao)this.sqlSessionTemplate.getMapper(ReceiveHghzDataDao.class)).findAll(pageMap);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return ((ReceiveHghzDataDao)this.sqlSessionTemplate.getMapper(ReceiveHghzDataDao.class)).count(pageMap);
   }
 
   
   public int update(String[] idArr) {
     return ((ReceiveHghzDataDao)this.sqlSessionTemplate.getMapper(ReceiveHghzDataDao.class)).update(idArr);
   }
 
   
   public List<ReceiveHghzData> findByInvtNo(String invtNo) {
     return ((ReceiveHghzDataDao)this.sqlSessionTemplate.getMapper(ReceiveHghzDataDao.class)).findByInvtNo(invtNo);
   }
 
   
   public List<ReceiveHghzData> find(Map<String, Object> map) {
     return ((ReceiveHghzDataDao)this.sqlSessionTemplate.getMapper(ReceiveHghzDataDao.class)).find(map);
   }
 }


