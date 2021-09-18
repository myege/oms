 package com.what21.service.impl;
 
 import com.what21.dao.CountRuleDao;
 import com.what21.model.CountRule;
 import com.what21.service.CountRuleService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 @Service
 public class CountRuleServiceImpl
   implements CountRuleService
 {
   @Autowired
   private CountRuleDao countRuleDao;
   
   public List<CountRule> findAll(Map<String, Object> map) throws Exception {
     return this.countRuleDao.findAll(map);
   }
 
   
   public Integer count(Map<String, Object> map) throws Exception {
     return this.countRuleDao.count(map);
   }
 
   
   public Integer add(CountRule countRule) throws Exception {
     return this.countRuleDao.add(countRule);
   }
 
   
   public Integer update(CountRule countRule) throws Exception {
     return this.countRuleDao.update(countRule);
   }
 
   
   public Integer delate(String[] ids) throws Exception {
     return this.countRuleDao.delate(ids);
   }
 
   
   public List<CountRule> findCombobox(Integer id) throws Exception {
     return this.countRuleDao.findCombobox(id);
   }
 }


