package com.what21.dao;

import com.what21.model.OrderCheck;
import java.util.List;
import java.util.Map;

public interface OrderCheckDao {
  List<OrderCheck> findAll(Map<String, Object> paramMap);
  
  int countOrderCheck();
  
  int deleteO(String paramString);
  
  int addO(OrderCheck paramOrderCheck);
  
  List<OrderCheck> find(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  OrderCheck findByPc(OrderCheck paramOrderCheck);
}


