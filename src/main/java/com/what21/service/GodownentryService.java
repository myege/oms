package com.what21.service;

import com.what21.model.Godownentry;
import java.util.List;
import java.util.Map;

public interface GodownentryService {
  int countGodownentry();
  
  List<Godownentry> getAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  List<Godownentry> find(Map<String, Object> paramMap);
  
  int addGo(Godownentry paramGodownentry);
  
  int deleteG(String paramString);
  
  Godownentry findByTotalMailNo(String paramString);
  
  void update(Godownentry paramGodownentry);
  
  List<Godownentry> checkGo(Map<String, Object> paramMap);
  
  Godownentry findByPc(Godownentry paramGodownentry);
  
  Godownentry findById(String paramString);
}


