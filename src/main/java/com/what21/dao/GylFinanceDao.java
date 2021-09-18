package com.what21.dao;

import com.what21.model.GylFinance;
import com.what21.model.OrderBonded;
import com.what21.model.OrderBondedSku;
import java.util.List;
import java.util.Map;

public interface GylFinanceDao {
  List<GylFinance> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  void insert(GylFinance paramGylFinance);
  
  void batchInsertOrder(Object[] paramArrayOfObject);
  
  void batchInsertOrderSku(List<OrderBondedSku> paramList);
  
  List<OrderBonded> findByObArr(Object[] paramArrayOfObject);
  
  List<OrderBonded> findOrderByNumber(Map<String, Object> paramMap);
  
  int countByNumber(Map<String, Object> paramMap);
  
  int updateConfirmStatus(List<GylFinance> paramList);
  
  List<OrderBonded> findOrder(List<GylFinance> paramList);
  
  List<OrderBondedSku> findOrderSku(List<GylFinance> paramList);
  
  int batchInsertToOrderBonded(List<OrderBonded> paramList);
  
  int batchInsertToOrderBondedSku(List<OrderBondedSku> paramList);
}


