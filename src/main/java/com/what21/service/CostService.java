package com.what21.service;

import com.what21.model.Cost;
import java.util.List;
import java.util.Map;

public interface CostService {
  int count(Map<String, Object> paramMap);
  
  List<Cost> findAll(Map<String, Object> paramMap);
  
  int addCost(Cost paramCost);
  
  int deleteCost(String paramString);
  
  int updateCost(Cost paramCost);
  
  Cost findCost(String paramString1, String paramString2);
  
  int updateCostSku(Cost paramCost);
  
  int updateCostisTj(int paramInt);
  
  Cost findCosts(String paramString);
  
  int updateCostxg(String paramString);
  
  int updateCosthl(String paramString);
  
  int updateCostdb(String paramString);
  
  int isPayment(int paramInt);
  
  Cost findTotanumber(String paramString);
  
  int updateCostldt(String paramString);
  
  int updateCosthkzy(String paramString);
  
  int updateCosthdtx(String paramString);
  
  int updateCostmg(String paramString);
  
  Cost findCostAll(int paramInt);
  
  int updateCostxq(Cost paramCost);
  
  List<Cost> viewDetails(int paramInt);
}


