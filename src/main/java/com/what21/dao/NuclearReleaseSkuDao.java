package com.what21.dao;

import com.what21.model.NuclearReleaseSku;
import java.util.List;
import java.util.Map;

public interface NuclearReleaseSkuDao {
  List<NuclearReleaseSku> findBySku(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  void add(NuclearReleaseSku paramNuclearReleaseSku);
  
  NuclearReleaseSku findId(Map<String, Object> paramMap);
  
  List<NuclearReleaseSku> findAll(Map<String, Object> paramMap);
  
  List<NuclearReleaseSku> findByB(String paramString);
}


