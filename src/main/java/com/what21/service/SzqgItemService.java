package com.what21.service;

import com.what21.model.SzqgItem;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface SzqgItemService {
  List<SzqgItem> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  GeneralResult importAgentItem(int paramInt, String paramString);
  
  String CreateItem(String paramString) throws Exception;
}


