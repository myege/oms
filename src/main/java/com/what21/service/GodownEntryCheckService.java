package com.what21.service;

import com.what21.model.GodownEntryCheck;
import java.util.List;
import java.util.Map;

public interface GodownEntryCheckService {
  int countGodownEntryCheck();
  
  List<GodownEntryCheck> getAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  List<GodownEntryCheck> find(Map<String, Object> paramMap);
  
  int addGo(GodownEntryCheck paramGodownEntryCheck);
  
  int deleteG(String paramString);
  
  GodownEntryCheck findByTotalMailNo(String paramString);
  
  void update(GodownEntryCheck paramGodownEntryCheck);
  
  List<GodownEntryCheck> checkGo(Map<String, Object> paramMap);
  
  GodownEntryCheck findByPc(GodownEntryCheck paramGodownEntryCheck);
  
  GodownEntryCheck findById(String paramString);
}


