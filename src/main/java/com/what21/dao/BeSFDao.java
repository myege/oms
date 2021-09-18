package com.what21.dao;

import com.what21.model.BeSF;
import java.util.List;
import java.util.Map;

public interface BeSFDao {
  List<BeSF> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  void insert(BeSF paramBeSF);
  
  void deleteById(int paramInt);
  
  BeSF findById(int paramInt);
  
  List<BeSF> findByPramid(Map<String, Object> paramMap);
  
  int countByPramid(Map<String, Object> paramMap);
  
  void update(BeSF paramBeSF);
  
  void deleteByPramid(Map<String, Object> paramMap);
}


