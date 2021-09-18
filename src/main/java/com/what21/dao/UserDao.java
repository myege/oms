package com.what21.dao;

import com.what21.model.Users;
import java.util.List;
import java.util.Map;

public interface UserDao {
  int countUsers();
  
  List<Users> findAll(Map<String, Object> paramMap);
  
  List<Users> findAll_1(int paramInt);
  
  int addUsers(Users paramUsers);
  
  int updateUsers(Users paramUsers);
  
  int deleteUsers(String paramString);
  
  Users findUser(Users paramUsers);
  
  int count(Map<String, Object> paramMap);
  
  List<Users> query(Map<String, Object> paramMap);
  
  int power(Users paramUsers);
  
  List<Users> uname(Map<String, Object> paramMap);
  
  int cleanPower(Users paramUsers);
  
  List<String> fingUserId();
  
  List<Users> findByUserId();
}


