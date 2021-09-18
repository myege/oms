package com.what21.dao;

import com.what21.model.CostSku;
import java.util.List;
import java.util.Map;

public interface CostSkuDao {
  int addCostSku(CostSku paramCostSku);
  
  List<CostSku> findCostSku(Map<String, Object> paramMap);
  
  int count(int paramInt);
  
  int updateCostSku(CostSku paramCostSku);
  
  int deleteCostSku(String paramString);
  
  CostSku findById(String paramString);
  
  CostSku findAll(int paramInt);
  
  List<CostSku> findByIdxy(int paramInt);
  
  List<CostSku> findByIddy(int paramInt);
}


