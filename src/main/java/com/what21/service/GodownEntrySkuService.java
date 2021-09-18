package com.what21.service;

import com.what21.model.GodownEntrySku;
import java.util.List;
import java.util.Map;

public interface GodownEntrySkuService {
  List<GodownEntrySku> findBySku(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  void add(GodownEntrySku paramGodownEntrySku);
  
  GodownEntrySku findByTotalMailNo(GodownEntrySku paramGodownEntrySku);
  
  List<GodownEntrySku> findByBill(String paramString);
  
  List<GodownEntrySku> printout(Map<String, Object> paramMap);
  
  List<GodownEntrySku> findAll(Map<String, Object> paramMap);
  
  List<GodownEntrySku> findByMailNo(GodownEntrySku paramGodownEntrySku);
  
  int delete(GodownEntrySku paramGodownEntrySku);
  
  int updateByMailNo(GodownEntrySku paramGodownEntrySku);
}


