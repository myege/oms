 package com.what21.dao.impl;
 
 import com.what21.dao.EndCompanyDao;
 import com.what21.model.EndCompany;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class EndCompanyDaoImpl
   implements EndCompanyDao
 {
   @Autowired
   SqlSessionTemplate sqlSessionTemplate;
   
   public List<EndCompany> findAll(Map<String, Object> map) {
     return this.sqlSessionTemplate.selectList(String.valueOf(EndCompany.class.getName()) + ".findAll", map);
   }
 
   
   public int count(Map<String, Object> map) {
     return ((Integer)this.sqlSessionTemplate.selectOne(String.valueOf(EndCompany.class.getName()) + ".count", map)).intValue();
   }
   
   public List<EndCompany> findCond(Map<String, Object> map) {
     return this.sqlSessionTemplate.selectList(String.valueOf(EndCompany.class.getName()) + ".findCond", map);
   }
 
   
   public int addCom(EndCompany end) {
     return this.sqlSessionTemplate.insert(String.valueOf(EndCompany.class.getName()) + ".addCom", end);
   }
 
   
   public int delete(int id) {
     return this.sqlSessionTemplate.delete(String.valueOf(EndCompany.class.getName()) + ".delete", Integer.valueOf(id));
   }
 
   
   public int update(EndCompany end) {
     return this.sqlSessionTemplate.update(String.valueOf(EndCompany.class.getName()) + ".update", end);
   }
 }


