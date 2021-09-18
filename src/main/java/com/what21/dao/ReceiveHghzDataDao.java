package com.what21.dao;

import com.what21.model.ReceiveHghzData;
import java.util.List;
import java.util.Map;

public interface ReceiveHghzDataDao {
  int insert(ReceiveHghzData paramReceiveHghzData);
  
  List<ReceiveHghzData> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int update(String[] paramArrayOfString);
  
  List<ReceiveHghzData> findByInvtNo(String paramString);
  
  List<ReceiveHghzData> find(Map<String, Object> paramMap);
}


