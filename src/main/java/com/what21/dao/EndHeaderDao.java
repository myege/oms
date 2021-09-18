package com.what21.dao;

import com.what21.model.EndHeader;
import java.util.List;
import java.util.Map;

public interface EndHeaderDao {
  List<EndHeader> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int add(EndHeader paramEndHeader);
  
  int update(EndHeader paramEndHeader);
  
  int delete(int paramInt);
  
  List<EndHeader> findCondition(Map<String, Object> paramMap);
}


