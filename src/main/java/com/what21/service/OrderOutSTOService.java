package com.what21.service;

import com.what21.model.OrderOutSTOCustom;
import com.what21.model.OrderOutSTOQueryVo;
import com.what21.model.TOrderOutSto;
import com.what21.util.GeneralResult;
import java.util.List;

public interface OrderOutSTOService {
  List<OrderOutSTOCustom> findAll(OrderOutSTOQueryVo paramOrderOutSTOQueryVo);
  
  Integer countAll(OrderOutSTOQueryVo paramOrderOutSTOQueryVo);
  
  void insertBatch(List<TOrderOutSto> paramList);
  
  GeneralResult importOrderNew(Integer paramInteger, String paramString);
  
  List<String> pushSTO(int[] paramArrayOfint);
  
  void deleteByIds(int[] paramArrayOfint);
  
  List<String> pushkouAn(int[] paramArrayOfint, String paramString);
  
  void deleteNotNeed();
}


