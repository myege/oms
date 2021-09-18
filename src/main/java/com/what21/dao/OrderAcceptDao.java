package com.what21.dao;

import com.what21.model.OrderAccept;
import java.util.List;
import java.util.Map;

public interface OrderAcceptDao {
  OrderAccept findByExpressNumber(String paramString);
  
  void insert(OrderAccept paramOrderAccept);
  
  int count(Map<String, Object> paramMap);
  
  List<OrderAccept> findAll(Map<String, Object> paramMap);
  
  OrderAccept findById(int paramInt);
  
  void update(String paramString);
  
  void updatePrintType(int paramInt);
  
  List<OrderAccept> findExpressNumber(Map<String, Object> paramMap);
  
  int updates(String paramString);
  
  OrderAccept findByIdToM(Integer paramInteger);
  
  int updateExpressmatch(String paramString1, String paramString2);
  
  int updateExpressM(OrderAccept paramOrderAccept);
  
  List<OrderAccept> findByOrderNumber(String paramString);
  
  void delete(String[] paramArrayOfString);
}


