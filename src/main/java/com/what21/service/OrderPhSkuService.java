package com.what21.service;

import com.what21.model.OrderPhSku;
import java.util.List;
import java.util.Map;

public interface OrderPhSkuService {
  int insert(OrderPhSku paramOrderPhSku);
  
  List<OrderPhSku> Detailed(Map<String, Object> paramMap);
  
  int count(String paramString);
  
  List<OrderPhSku> findById(String paramString);
  
  void update(OrderPhSku paramOrderPhSku);
  
  List<OrderPhSku> skuupd(String paramString);
  
  void updods(OrderPhSku paramOrderPhSku);
}


