package com.what21.service;

import com.what21.model.Log_inventory;
import java.util.List;
import java.util.Map;

public interface Log_inventoryService {
  void insert(Log_inventory paramLog_inventory);
  
  List<Log_inventory> findtoTinven(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
}

