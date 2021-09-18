package com.what21.dao;

import com.what21.model.Item;
import com.what21.model.SzqgOrderSku;
import java.util.List;
import java.util.Map;

public interface SzqgOrderSkuDao {
  int insert(SzqgOrderSku paramSzqgOrderSku);
  
  int delete(String paramString);
  
  List<SzqgOrderSku> findById(String paramString);
  
  List<SzqgOrderSku> Detailed(Map<String, Object> paramMap);
  
  int count(String paramString);
  
  void update(SzqgOrderSku paramSzqgOrderSku);
  
  Item findItem(String paramString);
  
  int batchInsert(List<SzqgOrderSku> paramList);
  
  List<SzqgOrderSku> skuupd(String paramString);
  
  void updods(SzqgOrderSku paramSzqgOrderSku);
  
  void updodpc(SzqgOrderSku paramSzqgOrderSku);
  
  int batchUpdate(List<SzqgOrderSku> paramList);
}


