package com.what21.dao;

import com.what21.model.OrderBatch;
import java.util.List;
import java.util.Map;

public interface OrderBatchDao {
  List<OrderBatch> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int insertBatch(OrderBatch paramOrderBatch);
  
  int updateid(OrderBatch paramOrderBatch);
  
  OrderBatch findBarchBond(int paramInt);
}


