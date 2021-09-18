package com.what21.service;

import com.what21.model.OrderAccept;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface OrderAcceptService {
  Boolean findByExpressNumber(String paramString);
  
  void insert(OrderAccept paramOrderAccept);
  
  List<OrderAccept> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  OrderAccept findById(int paramInt);
  
  void update(String paramString);
  
  void updatePrintType(int paramInt);
  
  List<OrderAccept> findExpressNumber(Map<String, Object> paramMap);
  
  GeneralResult importOrder(String paramString, int paramInt);
  
  int updates(String paramString);
  
  int updateExpressmatch(String paramString1, String paramString2);
  
  int updateExpressM(OrderAccept paramOrderAccept);
  
  int delete(String[] paramArrayOfString);
}


