package com.what21.dao;

import com.what21.model.Godownentry;
import java.util.List;
import java.util.Map;

public interface GodownentryDao {
  int countGodownentry();
  
  int count(Map<String, Object> paramMap);
  
  List<Godownentry> findAll(Map<String, Object> paramMap);
  
  List<Godownentry> find(Map<String, Object> paramMap);
  
  int addGo(Godownentry paramGodownentry);
  
  int deleteG(String paramString);
  
  Godownentry findByTotalMailNo(String paramString);
  
  void update(Godownentry paramGodownentry);
  
  List<Godownentry> checkGo(Map<String, Object> paramMap);
  
  Godownentry findByPc(Godownentry paramGodownentry);
  
  Godownentry findById(String paramString);
}


