package com.what21.dao;

import com.what21.model.Item;
import com.what21.model.OrderPhPushSku;
import com.what21.model.OrderPhSku;
import java.util.List;
import java.util.Map;

public interface OrderPhSkuDao {
  int insert(OrderPhSku paramOrderPhSku);
  
  int delete(String paramString);
  
  List<OrderPhPushSku> findByTxLogisticID(String paramString);
  
  List<OrderPhSku> findById(String paramString);
  
  List<OrderPhSku> Detailed(Map<String, Object> paramMap);
  
  int count(String paramString);
  
  void update(OrderPhSku paramOrderPhSku);
  
  Item findItem(String paramString);
  
  int batchInsert(List<OrderPhSku> paramList);
  
  List<OrderPhSku> skuupd(String paramString);
  
  void updods(OrderPhSku paramOrderPhSku);
}


