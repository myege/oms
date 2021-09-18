package com.what21.service;

import com.what21.model.Ijdcgdys;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface IjdcgdysService {
  List<Ijdcgdys> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  GeneralResult importCgdys(int paramInt, String paramString);
}


