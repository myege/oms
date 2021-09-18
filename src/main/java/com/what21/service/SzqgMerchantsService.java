package com.what21.service;

import com.what21.model.SzqgMerchants;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface SzqgMerchantsService {
  List<SzqgMerchants> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  GeneralResult importAgentMerchants(int paramInt, String paramString);
}


