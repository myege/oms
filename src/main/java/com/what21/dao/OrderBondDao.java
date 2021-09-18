package com.what21.dao;

import com.what21.model.OrderBond;
import com.what21.model.OrderBondSku;
import java.util.List;
import java.util.Map;

public interface OrderBondDao {
  int save(OrderBond paramOrderBond);
  
  int delete(int paramInt);
  
  OrderBond findObjectById(String paramString);
  
  List<OrderBond> findAll(Map<String, Object> paramMap);
  
  Integer count(Map<String, Object> paramMap);
  
  int updete(OrderBond paramOrderBond);
  
  OrderBond findCom(String paramString);
  
  String findManey(String paramString);
  
  List<OrderBondSku> findBm(Map<String, Object> paramMap);
  
  int deleteBm(String paramString);
  
  int countBm(Map<String, Object> paramMap);
  
  int saveBm(OrderBondSku paramOrderBondSku);
  
  void updateSurplus(OrderBond paramOrderBond);
  
  int frostAndF(OrderBond paramOrderBond);
  
  OrderBondSku findMailNo(String paramString);
  
  OrderBondSku findSku(String paramString);
  
  void deleteSku(int paramInt);
  
  List<Map> newordercx(String paramString);
  
  int updateddzt(String paramString);
  
  int updateddzt_1(String paramString);
}


