package com.what21.service;

import com.what21.model.StockDeleteSku;
import java.util.List;
import java.util.Map;

public interface StockDeleteSkuService {
  List<StockDeleteSku> findAll(Map<String, Object> paramMap);
  
  int count(String paramString);
}


