 package com.what21.dao.impl;
 
 import com.what21.dao.UserDao;
 import com.what21.model.Users;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class UserDaoImpl
   implements UserDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<Users> findAll(Map<String, Object> map) {
     return ((UserDao)this.sqlSessionTemplate.getMapper(UserDao.class)).findAll(map);
   }
 
   
   public int countUsers() {
     return ((UserDao)this.sqlSessionTemplate.getMapper(UserDao.class)).countUsers();
   }
 
   
   public int addUsers(Users u) {
     return ((UserDao)this.sqlSessionTemplate.getMapper(UserDao.class)).addUsers(u);
   }
 
   
   public int updateUsers(Users u) {
     return ((UserDao)this.sqlSessionTemplate.getMapper(UserDao.class)).updateUsers(u);
   }
 
   
   public int deleteUsers(String id) {
     return ((UserDao)this.sqlSessionTemplate.getMapper(UserDao.class)).deleteUsers(id);
   }
 
   
   public Users findUser(Users u) {
     return ((UserDao)this.sqlSessionTemplate.getMapper(UserDao.class)).findUser(u);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return ((UserDao)this.sqlSessionTemplate.getMapper(UserDao.class)).count(pageMap);
   }
 
   
   public List<Users> query(Map<String, Object> pageMap) {
     return ((UserDao)this.sqlSessionTemplate.getMapper(UserDao.class)).query(pageMap);
   }
 
   
   public int power(Users u) {
     return ((UserDao)this.sqlSessionTemplate.getMapper(UserDao.class)).power(u);
   }
 
   
   public List<Users> uname(Map<String, Object> map) {
     return ((UserDao)this.sqlSessionTemplate.getMapper(UserDao.class)).uname(map);
   }
 
   
   public int cleanPower(Users u) {
     return ((UserDao)this.sqlSessionTemplate.getMapper(UserDao.class)).cleanPower(u);
   }
 
   
   public List<String> fingUserId() {
     return ((UserDao)this.sqlSessionTemplate.getMapper(UserDao.class)).fingUserId();
   }
 
   
   public List<Users> findByUserId() {
     return ((UserDao)this.sqlSessionTemplate.getMapper(UserDao.class)).findByUserId();
   }
 
 
   
   public List<Users> findAll_1(int changeOdd) {
     return ((UserDao)this.sqlSessionTemplate.getMapper(UserDao.class)).findAll_1(changeOdd);
   }
 }


