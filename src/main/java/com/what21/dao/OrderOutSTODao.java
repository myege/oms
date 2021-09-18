package com.what21.dao;

import com.what21.model.OrderOutSTOCustom;
import com.what21.model.OrderOutSTOQueryVo;
import com.what21.model.TOrderOutSto;
import java.util.List;

public interface OrderOutSTODao {
  List<OrderOutSTOCustom> findAll(OrderOutSTOQueryVo paramOrderOutSTOQueryVo);
  
  Integer countAll(OrderOutSTOQueryVo paramOrderOutSTOQueryVo);
  
  void insertBatch(List<TOrderOutSto> paramList);
  
  List<OrderOutSTOCustom> findByIds(int[] paramArrayOfint);
  
  void updateStatus(Integer paramInteger);
  
  void deleteByIds(int[] paramArrayOfint);
  
  void deleteByWayBillnos(List<String> paramList);
  
  void deleteNoNeed();
  
  List<OrderOutSTOCustom> findByBillno(String paramString);
}


