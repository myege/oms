package com.what21.service;

import com.what21.model.Users;
import java.util.List;
import java.util.Map;

public interface UsersService {
  List<Users> getAll(Map<String, Object> paramMap);
  
  List<Users> findAll_1(int paramInt);
  
  int countUsers();
  
  int addUsers(Users paramUsers);
  
  int updateUsers(Users paramUsers);
  
  int deleteUsers(String paramString);
  
  Users findUser(Users paramUsers);
  
  List<Users> query(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int power(Users paramUsers);
  
  List<Users> uname(Map<String, Object> paramMap);
  
  int cleanPower(Users paramUsers);
}


