package com.what21.service;

import com.what21.model.Company;
import com.what21.model.OrderBond;
import com.what21.model.OrderBondSku;
import com.what21.model.Users;
import java.util.List;
import java.util.Map;

public interface OrderBondService {
  int save(OrderBond paramOrderBond);
  
  int delete(int paramInt);
  
  OrderBond findObjectById(String paramString);
  
  List<OrderBond> findAll(Map<String, Object> paramMap);
  
  Integer count(Map<String, Object> paramMap);
  
  int update(OrderBond paramOrderBond);
  
  List<OrderBondSku> findBm(Map<String, Object> paramMap);
  
  int countBm(Map<String, Object> paramMap);
  
  int saveBm(OrderBondSku paramOrderBondSku);
  
  void updateSurplus(OrderBond paramOrderBond);
  
  int frostAndF(OrderBond paramOrderBond, double paramDouble);
  
  List<Company> findByCompany();
  
  Company findByCompanyBm(String paramString);
  
  List<Users> findByUserId();
  
  int deleteBm(String paramString);
}


