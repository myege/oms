package com.what21.dao;

import com.what21.model.ReceiveHtData;
import java.util.List;
import java.util.Map;

public interface ReceiveHtDataDao {
  int insert(ReceiveHtData paramReceiveHtData);
  
  List<ReceiveHtData> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int update(String[] paramArrayOfString);
}


