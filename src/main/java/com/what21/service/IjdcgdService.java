package com.what21.service;

import com.what21.model.Ijdcgd;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface IjdcgdService {
  List<Ijdcgd> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  GeneralResult importCgd(int paramInt, String paramString);
}


