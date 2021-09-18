 package com.what21.dao.impl;
 
 import com.what21.dao.IjdlhdDao;
 import com.what21.model.Ijdlhd;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class IjdlhdDaoImpl
   implements IjdlhdDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<Ijdlhd> findAll(Map<String, Object> map) {
     return ((IjdlhdDao)this.sqlSessionTemplate.getMapper(IjdlhdDao.class)).findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return ((IjdlhdDao)this.sqlSessionTemplate.getMapper(IjdlhdDao.class)).count(map);
   }
 
 
   
   public void delete(String poNo) {
     ((IjdlhdDao)this.sqlSessionTemplate.getMapper(IjdlhdDao.class)).delete(poNo);
   }
 
 
 
   
   public void insertBatch(List<Ijdlhd> list) {
     ((IjdlhdDao)this.sqlSessionTemplate.getMapper(IjdlhdDao.class)).insertBatch(list);
   }
 }


