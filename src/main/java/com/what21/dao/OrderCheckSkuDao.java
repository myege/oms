package com.what21.dao;

import com.what21.model.OrderCheckSku;
import java.util.List;
import java.util.Map;

public interface OrderCheckSkuDao {
  List<OrderCheckSku> findBySku(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  void add(OrderCheckSku paramOrderCheckSku);
  
  OrderCheckSku findId(Map<String, Object> paramMap);
  
  List<OrderCheckSku> findAll(Map<String, Object> paramMap);
  
  List<OrderCheckSku> findByBillcode(String paramString);
}


