package com.what21.dao;

import com.what21.model.BeQgf;
import java.util.List;
import java.util.Map;

public interface BeQgfDao {
  List<BeQgf> findAll(Map<String, Object> paramMap);
  
  Integer count(Map<String, Object> paramMap);
  
  void insert(BeQgf paramBeQgf);
  
  void deleteById(Integer paramInteger);
  
  BeQgf findById(Integer paramInteger);
  
  BeQgf findByPramid(int paramInt);
  
  void update(BeQgf paramBeQgf);
  
  int countByPramid(Map<String, Object> paramMap);
  
  List<BeQgf> findAllByPramid(Map<String, Object> paramMap);
  
  void deleteByPramid(int paramInt);
}


