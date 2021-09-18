package com.what21.service;

import com.what21.model.BeYf;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface BeYfService {
  List<BeYf> findAll(Map<String, Object> paramMap);
  
  Integer count(Map<String, Object> paramMap);
  
  void insert(BeYf paramBeYf);
  
  void deleteById(Integer paramInteger);
  
  BeYf findById(Integer paramInteger);
  
  List<BeYf> findByPramid(Map<String, Object> paramMap);
  
  Integer countByPramid(Map<String, Object> paramMap);
  
  GeneralResult importOrderNew(String paramString, int paramInt);
  
  void deleteByPramid(Map<String, Object> paramMap);
}


