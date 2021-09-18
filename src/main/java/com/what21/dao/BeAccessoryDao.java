package com.what21.dao;

import com.what21.model.BeAccessory;
import java.util.List;
import java.util.Map;

public interface BeAccessoryDao {
  BeAccessory findById(Integer paramInteger);
  
  void insert(BeAccessory paramBeAccessory);
  
  void update(BeAccessory paramBeAccessory);
  
  List<BeAccessory> findByLzdIdAndItemName(Map<String, Object> paramMap);
  
  int countByLzdIdAndItemName(Map<String, Object> paramMap);
  
  void deleteById(int paramInt);
  
  List<BeAccessory> findByLzdIdAndItemName2(Map<String, Object> paramMap);
  
  int countByLzdIdAndItemName2(Map<String, Object> paramMap);
  
  void deleteByLzdId(int paramInt);
}


