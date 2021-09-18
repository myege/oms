package com.what21.service;

import com.what21.model.OrderBondedSku;
import java.util.List;
import java.util.Map;

public interface OrderBondedSkuService {
  int insert(OrderBondedSku paramOrderBondedSku);
  
  List<OrderBondedSku> Detailed(Map<String, Object> paramMap);
  
  int count(String paramString);
  
  List<OrderBondedSku> findById(String paramString);
  
  void update(OrderBondedSku paramOrderBondedSku);
  
  List<OrderBondedSku> skuupd(String paramString);
  
  void updods(OrderBondedSku paramOrderBondedSku);
}


