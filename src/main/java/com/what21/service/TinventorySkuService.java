package com.what21.service;

import com.what21.model.TinventorySku;
import java.util.List;
import java.util.Map;

public interface TinventorySkuService {
  int addTinSku(TinventorySku paramTinventorySku);
  
  List<TinventorySku> findtinSku(Map<String, Object> paramMap);
  
  int countSku(Map<String, Object> paramMap);
}


