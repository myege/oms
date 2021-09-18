 package com.what21.dao.impl;
 
 import com.what21.dao.TimedTaskDao;
 import com.what21.model.OrderPush3;
 import com.what21.model.TimedTask;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class TimedTaskDaoImpl
   implements TimedTaskDao
 {
   @Autowired
   SqlSessionTemplate sqlSessionTemplate;
   
   public List<TimedTask> findAll(Map<String, Object> map) throws Exception {
     return this.sqlSessionTemplate.selectList(String.valueOf(TimedTask.class.getName()) + ".findAll", map);
   }
 
   
   public Integer count(Map<String, Object> map) throws Exception {
     return (Integer)this.sqlSessionTemplate.selectOne(String.valueOf(TimedTask.class.getName()) + ".count", map);
   }
 
   
   public int add(TimedTask timedTask) throws Exception {
     return this.sqlSessionTemplate.insert(String.valueOf(TimedTask.class.getName()) + ".add", timedTask);
   }
 
   
   public int delete(String ids) throws Exception {
     return this.sqlSessionTemplate.delete(String.valueOf(TimedTask.class.getName()) + ".delete", ids);
   }
 
   
   public int update(TimedTask timedTask) throws Exception {
     return this.sqlSessionTemplate.update(String.valueOf(TimedTask.class.getName()) + ".update", timedTask);
   }
 
 
   
   public List<Map<String, Object>> findTimeTj(Map<String, String> map) throws Exception {
     return this.sqlSessionTemplate.selectList(String.valueOf(TimedTask.class.getName()) + ".findTimeTj", map);
   }
 
   
   public List<OrderPush3> findPush3(String[] ids) throws Exception {
     return this.sqlSessionTemplate.selectList(String.valueOf(TimedTask.class.getName()) + ".findPush3", ids);
   }
 }


