package com.what21.dao;

import com.what21.model.Webzu;
import java.util.List;
import java.util.Map;

public interface WebZuDao {
  List<Map<String, String>> findByElec(Webzu paramWebzu);
  
  Webzu findById(String paramString);
  
  List<Webzu> findAll(Map<String, Object> paramMap);
  
  Integer count(Map<String, Object> paramMap);
  
  int add(Webzu paramWebzu);
  
  int update(Webzu paramWebzu);
  
  int delete(Integer paramInteger);
}


