package com.what21.service;

import com.what21.model.OrderCheck;
import java.util.List;
import java.util.Map;

public interface OrderCheckService {
  List<OrderCheck> getAll(Map<String, Object> paramMap);
  
  int countOrderCheck();
  
  int count(Map<String, Object> paramMap);
  
  List<OrderCheck> find(Map<String, Object> paramMap);
  
  int addO(OrderCheck paramOrderCheck);
  
  int deleteO(String paramString);
  
  OrderCheck findByPc(OrderCheck paramOrderCheck);
}


