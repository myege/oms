package com.what21.service;

import com.what21.model.ReceiveHtData;
import java.util.List;
import java.util.Map;

public interface ReceiveHtDataService {
  String receiveData(String paramString);
  
  List<ReceiveHtData> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int update(String paramString);
}


