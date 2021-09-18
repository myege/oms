package com.what21.service;

import com.what21.model.ReceiveHghzData;
import java.util.List;
import java.util.Map;

public interface ReceiveHghzDataService {
  String receiveData(String paramString);
  
  List<ReceiveHghzData> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int update(String paramString);
  
  List<ReceiveHghzData> findByInvtNo(String paramString);
  
  List<ReceiveHghzData> find(Map<String, Object> paramMap);
}


