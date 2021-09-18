package com.what21.service;

import com.what21.model.BeSF;
import java.util.List;
import java.util.Map;

public interface BeSFService {
  List<BeSF> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  void insert(BeSF paramBeSF);
  
  void deleteById(int paramInt);
  
  BeSF findById(int paramInt);
  
  List<BeSF> findByPramid(Map<String, Object> paramMap);
  
  int countByPramid(Map<String, Object> paramMap);
  
  void findByTydh(Map<String, Object> paramMap);
  
  void update(String[] paramArrayOfString, String paramString1, String paramString2);
  
  void insertByBsjs(Map<String, Object> paramMap);
  
  void update(String[] paramArrayOfString, String paramString, int paramInt);
  
  void deleteByPramid(Map<String, Object> paramMap);
  
  void updateAll(int paramInt);
}


