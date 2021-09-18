package com.what21.dao;

import com.what21.model.OrderPh;
import com.what21.model.OrderPhPush;
import java.util.List;
import java.util.Map;

public interface OrderPhDao {
  List<OrderPh> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int insert(OrderPh paramOrderPh);
  
  OrderPh findByTxLogisticID(String paramString);
  
  OrderPhPush findById(Integer paramInteger);
  
  OrderPh findByIdToBG(Integer paramInteger);
  
  OrderPh findByIdToWJ(Integer paramInteger);
  
  OrderPh findByIdToWms(Integer paramInteger);
  
  int delete(String paramString);
  
  int updateAuditstatus(OrderPh paramOrderPh);
  
  int updateAuditstatusByTxLogisticID(OrderPh paramOrderPh);
  
  int updateExpressParam(OrderPh paramOrderPh);
  
  int updateIspost(OrderPh paramOrderPh);
  
  int updateIsCustoms(OrderPh paramOrderPh);
  
  void updateCountAndWeight(OrderPh paramOrderPh);
  
  int updateIsCustomsByTxLogisticID(OrderPh paramOrderPh);
  
  List<OrderPh> exportOrder(Map<String, Object> paramMap);
  
  int updateIsPushToWms(String[] paramArrayOfString);
  
  List<OrderPh> findAreaByName(String paramString);
  
  List<OrderPh> findTxLogisticID();
  
  int payNumber(OrderPh paramOrderPh);
  
  int updateIsPushDd(OrderPh paramOrderPh);
  
  int updateIsPushQd(OrderPh paramOrderPh);
  
  OrderPh findByMailNo(String paramString);
  
  List<OrderPh> hyjStatus(String paramString);
  
  int updateStatus(String paramString);
  
  int batchInsert(Object[] paramArrayOfObject);
  
  List<OrderPh> findOrderStatus(String[] paramArrayOfString);
  
  List<OrderPh> findByObArr(Object[] paramArrayOfObject);
  
  int findaddress(OrderPh paramOrderPh);
  
  int updaddress(OrderPh paramOrderPh);
  
  List<OrderPh> odbdateTime(Map<String, Object> paramMap);
  
  void deletetx(String paramString);
  
  int upm(OrderPh paramOrderPh);
  
  List<OrderPh> findTxId(String paramString);
  
  List<OrderPh> findCs(String paramString);
  
  int editodIdN(OrderPh paramOrderPh);
  
  void updateInvtno(OrderPh paramOrderPh);
  
  OrderPh findOneById(int paramInt);
}


