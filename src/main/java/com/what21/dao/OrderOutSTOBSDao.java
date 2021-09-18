package com.what21.dao;

import com.what21.model.OrderOutSTOCustom;
import com.what21.model.OrderOutSTOCustomBS;
import com.what21.model.OrderOutSTOQueryVoBS;
import com.what21.model.TOrderOutStoBS;
import java.util.List;

public interface OrderOutSTOBSDao {
  List<OrderOutSTOCustom> findAll(OrderOutSTOQueryVoBS paramOrderOutSTOQueryVoBS);
  
  Integer countAll(OrderOutSTOQueryVoBS paramOrderOutSTOQueryVoBS);
  
  void insertBatch(List<TOrderOutStoBS> paramList);
  
  List<OrderOutSTOCustomBS> findByIds(int[] paramArrayOfint);
  
  void updateStatus(Integer paramInteger);
  
  void deleteByIds(int[] paramArrayOfint);
  
  void deleteByWayBillnos(List<String> paramList);
  
  void deleteNoNeed();
}


