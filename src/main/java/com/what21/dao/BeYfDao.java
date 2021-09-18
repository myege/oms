package com.what21.dao;

import com.what21.model.BeYf;
import java.util.List;
import java.util.Map;

public interface BeYfDao {
  List<BeYf> findAll(Map<String, Object> paramMap);
  
  Integer count(Map<String, Object> paramMap);
  
  void insert(BeYf paramBeYf);
  
  void deleteById(Integer paramInteger);
  
  BeYf findById(Integer paramInteger);
  
  List<BeYf> findByPramid(Map<String, Object> paramMap);
  
  Integer countByPramid(Map<String, Object> paramMap);
  
  void update(BeYf paramBeYf);
  
  void deleteByPramid(Map<String, Object> paramMap);
}


