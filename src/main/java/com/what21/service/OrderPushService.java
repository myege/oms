package com.what21.service;

import com.what21.model.GoodsPush;
import com.what21.model.OrderPush;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface OrderPushService {
  Boolean findByExpressNumber(String paramString);
  
  void insert(OrderPush paramOrderPush);
  
  List<OrderPush> findAll(Map<String, Object> paramMap);
  
  int count(int paramInt);
  
  OrderPush findById(int paramInt);
  
  List<OrderPush> findByPushType(int paramInt);
  
  void update(String paramString);
  
  String push(OrderPush paramOrderPush, List<GoodsPush> paramList);
  
  GeneralResult importOrder(String paramString, int paramInt);
}


