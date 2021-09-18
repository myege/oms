package com.what21.dao;

import com.what21.model.PlaningReceiptsSku;
import java.util.List;
import java.util.Map;

public interface PlaningReceiptsSkuDao {
  List<PlaningReceiptsSku> findAll(Map<String, Object> paramMap);
  
  int count(String paramString);
  
  int insert(PlaningReceiptsSku paramPlaningReceiptsSku);
  
  int delete(String paramString);
  
  List<PlaningReceiptsSku> findByPlaningReceiptsId(String paramString);
}


