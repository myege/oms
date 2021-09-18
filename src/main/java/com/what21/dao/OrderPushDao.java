package com.what21.dao;

import com.what21.model.OrderPush;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface OrderPushDao {
  OrderPush findByExpressNumber(String paramString);
  
  void insert(OrderPush paramOrderPush);
  
  int count(@Param("userId") int paramInt);
  
  List<OrderPush> findAll(@Param("map") Map<String, Object> paramMap);
  
  OrderPush findById(int paramInt);
  
  List<OrderPush> findByPushType(int paramInt);
  
  void update(String paramString);
}


