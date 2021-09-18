 package com.what21.dao.impl;
 
 import com.what21.dao.End_waybillDao;
 import com.what21.model.EndCompany;
 import com.what21.model.End_waybill;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class End_waybillDaoImpl
   implements End_waybillDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
 
   
   public List<End_waybill> getAll() {
     return this.sqlSessionTemplate.selectList(String.valueOf(End_waybill.class.getName()) + ".findAll");
   }
 
 
 
   
   public List<End_waybill> findCompany(Map<String, Object> map) {
     return this.sqlSessionTemplate.selectList(String.valueOf(End_waybill.class.getName()) + ".findCompany", map);
   }
 
   
   public int addEnd(End_waybill end_waybill) {
     return this.sqlSessionTemplate.insert(String.valueOf(End_waybill.class.getName()) + ".addEnd", end_waybill);
   }
   
   public int count(Map<String, Object> map) {
     return ((Integer)this.sqlSessionTemplate.selectOne(String.valueOf(End_waybill.class.getName()) + ".count", map)).intValue();
   }
   
   public List<EndCompany> findCompany() {
     return this.sqlSessionTemplate.selectList(String.valueOf(End_waybill.class.getName()) + ".findCom");
   }
 }


