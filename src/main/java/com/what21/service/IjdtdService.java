package com.what21.service;

import com.what21.model.Ijdtd;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface IjdtdService {
  List<Ijdtd> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  GeneralResult importTd(int paramInt, String paramString);
  
  int deleteTd(String[] paramArrayOfString);
}


