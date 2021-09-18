 package com.what21.service.impl;
 
 import com.what21.dao.UserDao;
 import com.what21.model.Users;
 import com.what21.service.UsersService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 @Service
 public class UsersServiceImpl
   implements UsersService
 {
   @Autowired
   private UserDao userDao;
   
   public void setUserDao(UserDao userDao) {
     this.userDao = userDao;
   }
 
   
   public Users findUser(Users u) {
     return this.userDao.findUser(u);
   }
 
   
   public List<Users> getAll(Map<String, Object> map) {
     return this.userDao.findAll(map);
   }
 
   
   public int countUsers() {
     return this.userDao.countUsers();
   }
 
   
   public int addUsers(Users u) {
     return this.userDao.addUsers(u);
   }
 
   
   public int updateUsers(Users u) {
     return this.userDao.updateUsers(u);
   }
 
   
   public int deleteUsers(String id) {
     return this.userDao.deleteUsers(id);
   }
 
   
   public List<Users> query(Map<String, Object> pageMap) {
     return this.userDao.query(pageMap);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return this.userDao.count(pageMap);
   }
 
   
   public int power(Users u) {
     return this.userDao.power(u);
   }
 
 
   
   public List<Users> uname(Map<String, Object> map) {
     return this.userDao.uname(map);
   }
 
   
   public int cleanPower(Users u) {
     return this.userDao.cleanPower(u);
   }
 
 
   
   public List<Users> findAll_1(int changeOdd) {
     return this.userDao.findAll_1(changeOdd);
   }
 }


