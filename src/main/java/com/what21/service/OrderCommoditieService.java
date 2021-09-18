package com.what21.service;

import com.what21.model.OrderCommoditie;
import java.util.List;
import java.util.Map;

public interface OrderCommoditieService {
  List<OrderCommoditie> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  List<OrderCommoditie> findeXport(Integer paramInteger);
  
  List<OrderCommoditie> dateTime(Map<String, Object> paramMap);
}


