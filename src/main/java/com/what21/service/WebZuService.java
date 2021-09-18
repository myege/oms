package com.what21.service;

import com.what21.model.Webzu;
import java.util.List;
import java.util.Map;

public interface WebZuService {
  List<Map<String, String>> findByElec(Webzu paramWebzu);
  
  List<Webzu> findAll(Map<String, Object> paramMap);
  
  Integer count(Map<String, Object> paramMap);
  
  int add(Webzu paramWebzu);
  
  int update(Webzu paramWebzu);
  
  int delete(Integer paramInteger);
}


