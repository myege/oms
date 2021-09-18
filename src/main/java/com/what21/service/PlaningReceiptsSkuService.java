package com.what21.service;

import com.what21.model.PlaningReceiptsSku;
import java.util.List;
import java.util.Map;

public interface PlaningReceiptsSkuService {
  List<PlaningReceiptsSku> findAll(Map<String, Object> paramMap);
  
  int count(String paramString);
}


