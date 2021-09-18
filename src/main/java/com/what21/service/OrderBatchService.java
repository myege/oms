package com.what21.service;

import com.what21.model.OrderBatch;
import java.util.List;
import java.util.Map;

public interface OrderBatchService {
  List<OrderBatch> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int insertBatch(OrderBatch paramOrderBatch);
  
  int updateid(OrderBatch paramOrderBatch);
  
  OrderBatch findBarchBond(int paramInt);
}


