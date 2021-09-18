package com.what21.service;

import com.what21.model.OutEntryBillHead;
import java.util.List;
import java.util.Map;

public interface OutEntryBillHeadService {
  List<OutEntryBillHead> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int delete(String paramString);
  
  int add(OutEntryBillHead paramOutEntryBillHead);
}


