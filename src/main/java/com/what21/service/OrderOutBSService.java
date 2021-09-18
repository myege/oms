package com.what21.service;

import com.what21.model.OrderOutBS;
import com.what21.model.OrderOutForExportBS;
import com.what21.model.OrderOutLog;
import com.what21.model.OrderOutQueryVoBS;
import com.what21.model.OrderOutSkuBS;
import com.what21.util.GeneralResult;
import java.util.List;

public interface OrderOutBSService {
  List<OrderOutBS> find(OrderOutQueryVoBS paramOrderOutQueryVoBS);
  
  Integer count(OrderOutQueryVoBS paramOrderOutQueryVoBS);
  
  List<OrderOutSkuBS> findSku(OrderOutQueryVoBS paramOrderOutQueryVoBS);
  
  Integer countSku(OrderOutQueryVoBS paramOrderOutQueryVoBS);
  
  void deleteByIds(int[] paramArrayOfint);
  
  GeneralResult importOrderNew(String paramString, Integer paramInteger);
  
  List<String> pushQd(int[] paramArrayOfint);
  
  List<OrderOutBS> findByIds(int[] paramArrayOfint);
  
  List<OrderOutSkuBS> findSkusByPid(int paramInt);
  
  void endOrders(int[] paramArrayOfint);
  
  void updateSku(OrderOutSkuBS paramOrderOutSkuBS);
  
  OrderOutSkuBS findSkuById(Integer paramInteger);
  
  OrderOutLog findLogByWaybillno(String paramString);
  
  void updateGoodsno(String paramString1, String paramString2);
  
  Integer countSkuByGoodsno(String paramString);
  
  OrderOutBS findById(Integer paramInteger);
  
  List<String> pushSTO2(int[] paramArrayOfint) throws Exception;
  
  List<String> updateWeight(int paramInt, String paramString1, String paramString2);
  
  List<OrderOutForExportBS> findExportByIds(int[] paramArrayOfint);
  
  List<OrderOutForExportBS> findExport(OrderOutQueryVoBS paramOrderOutQueryVoBS);
}


