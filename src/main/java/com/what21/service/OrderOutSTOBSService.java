package com.what21.service;

import com.what21.model.OrderOutSTOCustom;
import com.what21.model.OrderOutSTOQueryVoBS;
import com.what21.model.TOrderOutStoBS;
import com.what21.util.GeneralResult;
import java.util.List;

public interface OrderOutSTOBSService {
  List<OrderOutSTOCustom> findAll(OrderOutSTOQueryVoBS paramOrderOutSTOQueryVoBS);
  
  Integer countAll(OrderOutSTOQueryVoBS paramOrderOutSTOQueryVoBS);
  
  void insertBatch(List<TOrderOutStoBS> paramList);
  
  GeneralResult importOrderNew(Integer paramInteger, String paramString);
  
  List<String> pushSTO(int[] paramArrayOfint);
  
  void deleteByIds(int[] paramArrayOfint);
  
  List<String> pushkouAn(int[] paramArrayOfint);
  
  void deleteNotNeed();
}


