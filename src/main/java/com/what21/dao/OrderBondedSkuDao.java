package com.what21.dao;

import com.what21.model.Item;
import com.what21.model.OrderBonded;
import com.what21.model.OrderBondedPushSku;
import com.what21.model.OrderBondedSku;
import java.util.List;
import java.util.Map;

public interface OrderBondedSkuDao {
  int insert(OrderBondedSku paramOrderBondedSku);
  
  int delete(String paramString);
  
  List<OrderBondedPushSku> findByTxLogisticID(String paramString);
  
  List<OrderBondedSku> findById(String paramString);
  
  List<OrderBondedSku> Detailed(Map<String, Object> paramMap);
  
  int count(String paramString);
  
  void update(OrderBondedSku paramOrderBondedSku);
  
  Item findItem(String paramString);
  
  int batchInsert(List<OrderBondedSku> paramList);
  
  List<OrderBondedSku> skuupd(String paramString);
  
  void updods(OrderBondedSku paramOrderBondedSku);
  
  void updodpc(OrderBondedSku paramOrderBondedSku);
  
  List<OrderBondedSku> findByTidList(List<OrderBonded> paramList);
}


