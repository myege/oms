package com.what21.service;

import com.what21.model.GodownEntryCheckSku;
import java.util.List;
import java.util.Map;

public interface GodownEntryCheckSkuService {
  List<GodownEntryCheckSku> findBySku(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int passCount(Map<String, Object> paramMap);
  
  void add(GodownEntryCheckSku paramGodownEntryCheckSku);
  
  GodownEntryCheckSku findByTotalMailNo(GodownEntryCheckSku paramGodownEntryCheckSku);
  
  List<GodownEntryCheckSku> findByBill(String paramString);
  
  List<GodownEntryCheckSku> printout(Map<String, Object> paramMap);
  
  List<GodownEntryCheckSku> findAll(Map<String, Object> paramMap);
  
  GodownEntryCheckSku findByMailNo(GodownEntryCheckSku paramGodownEntryCheckSku);
  
  int updateByMailNo(GodownEntryCheckSku paramGodownEntryCheckSku);
  
  void delete(GodownEntryCheckSku paramGodownEntryCheckSku);
}


