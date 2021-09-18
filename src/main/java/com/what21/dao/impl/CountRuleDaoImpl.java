 package com.what21.dao.impl;
 
 import com.what21.dao.CountRuleDao;
 import com.what21.model.CountRule;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class CountRuleDaoImpl
   implements CountRuleDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<CountRule> findAll(Map<String, Object> map) throws Exception {
     return this.sqlSessionTemplate.selectList(String.valueOf(CountRule.class.getName()) + ".findAll", map);
   }
 
   
   public Integer count(Map<String, Object> map) throws Exception {
     return (Integer)this.sqlSessionTemplate.selectOne(String.valueOf(CountRule.class.getName()) + ".count", map);
   }
 
   
   public Integer add(CountRule countRule) throws Exception {
     return Integer.valueOf(this.sqlSessionTemplate.insert(String.valueOf(CountRule.class.getName()) + ".add", countRule));
   }
 
   
   public Integer update(CountRule countRule) throws Exception {
     return Integer.valueOf(this.sqlSessionTemplate.update(String.valueOf(CountRule.class.getName()) + ".update", countRule));
   }
 
   
   public Integer delate(String[] ids) throws Exception {
     return Integer.valueOf(this.sqlSessionTemplate.delete(String.valueOf(CountRule.class.getName()) + ".datele", ids));
   }
 
   
   public List<CountRule> findCombobox(Integer id) throws Exception {
     return this.sqlSessionTemplate.selectList(String.valueOf(CountRule.class.getName()) + ".findCombobox", id);
   }
 }


