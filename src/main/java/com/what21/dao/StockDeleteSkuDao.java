package com.what21.dao;

import com.what21.model.StockDeleteSku;
import java.util.List;
import java.util.Map;

public interface StockDeleteSkuDao {
  List<StockDeleteSku> findAll(Map<String, Object> paramMap);
  
  int count(String paramString);
  
  int insert(StockDeleteSku paramStockDeleteSku);
  
  int delete(String paramString);
  
  List<StockDeleteSku> findByStockDeleteId(String paramString);
}


