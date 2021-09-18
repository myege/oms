package com.what21.dao;

import com.what21.model.BeHc;
import java.util.List;
import java.util.Map;

public interface BeHcDao {
  List<BeHc> findAll(Map<String, Object> paramMap);
  
  Integer count(Map<String, Object> paramMap);
  
  void insert(BeHc paramBeHc);
  
  void deleteById(Integer paramInteger);
  
  BeHc findById(Integer paramInteger);
  
  List<BeHc> findByPramid(Map<String, Object> paramMap);
  
  Integer countByPramid(Map<String, Object> paramMap);
  
  void deleteByPramid(Map<String, Object> paramMap);
}


