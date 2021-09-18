package com.what21.service;

import com.what21.model.OutPc;
import java.util.List;
import java.util.Map;

public interface OutPcService {
  List<OutPc> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int addO(OutPc paramOutPc);
  
  int deleteO(String paramString);
}


