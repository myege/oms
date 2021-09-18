package com.what21.service;

import com.what21.model.SzqgAgent;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface SzqgAgentService {
  List<SzqgAgent> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  GeneralResult importAgent(int paramInt, String paramString);
}


