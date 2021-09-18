package com.what21.service;

import com.what21.model.GylFinance;
import com.what21.model.OrderBonded;
import java.util.List;
import java.util.Map;

public interface GylFinanceService {
  List<GylFinance> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  List<OrderBonded> findOrderByNumber(Map<String, Object> paramMap);
  
  int countByNumber(Map<String, Object> paramMap);
  
  int updateConfirm(String paramString1, String paramString2);
}


