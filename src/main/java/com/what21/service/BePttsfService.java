package com.what21.service;

import com.what21.model.BePttsf;
import java.util.List;
import java.util.Map;

public interface BePttsfService {
  List<BePttsf> findAll(Map<String, Object> paramMap);
  
  Integer count(Map<String, Object> paramMap);
  
  void insert(BePttsf paramBePttsf);
  
  void deleteById(Integer paramInteger);
  
  BePttsf findById(Integer paramInteger);
  
  List<BePttsf> findByPramid(Map<String, Object> paramMap);
  
  Integer countByPramid(Map<String, Object> paramMap);
  
  void findByTydh(Map<String, Object> paramMap);
  
  void insertByBsjs(Map<String, Object> paramMap);
  
  void deleteByPramid(Map<String, Object> paramMap);
}


