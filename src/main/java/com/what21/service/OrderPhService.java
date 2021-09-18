package com.what21.service;

import com.what21.model.OrderPh;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface OrderPhService {
  List<OrderPh> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  GeneralResult importOrder(String paramString, int paramInt);
  
  int deleteOrderPh(String paramString);
  
  int updateAuditstatus(String paramString);
  
  int updateAuditstatusByTxLogisticID(String paramString);
  
  int updateExpressParam(String paramString);
  
  int updateIspost(String paramString);
  
  String updateIsPushToWms(String paramString) throws Exception;
  
  String updateIsCustoms(String paramString) throws Exception;
  
  int updateAuditstatusByIds(String paramString);
  
  int updateIsCustomsByTxLogisticID(String paramString);
  
  OrderPh findByIdToBG(Integer paramInteger);
  
  List<OrderPh> exportOrder(Map<String, Object> paramMap);
  
  List<OrderPh> findTxLogisticID();
  
  int payNumber(OrderPh paramOrderPh);
  
  int pushDd(String paramString);
  
  int pushQd(String paramString);
  
  OrderPh findByMailNo(String paramString);
  
  int updateStatus(String paramString);
  
  GeneralResult importOrderNew(String paramString, int paramInt);
  
  int findaddress(OrderPh paramOrderPh);
  
  int updaddress(OrderPh paramOrderPh);
  
  List<OrderPh> odbdateTime(Map<String, Object> paramMap);
  
  List<OrderPh> findCs(String paramString);
  
  List<OrderPh> findTxId(String paramString);
  
  int upm(OrderPh paramOrderPh);
  
  void deletetx(String paramString);
  
  int editodIdN(OrderPh paramOrderPh);
  
  void updateInvtno(OrderPh paramOrderPh);
  
  OrderPh findOneById(int paramInt);
}


