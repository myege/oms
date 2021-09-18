package com.what21.service;

import com.alibaba.fastjson.JSONObject;
import com.what21.model.Collect;
import com.what21.model.OrderBonded;
import com.what21.model.OrderBondedForExport;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface OrderBondedService {
  List<OrderBonded> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  GeneralResult importOrder(String paramString, int paramInt);
  
  int deleteOrderBonded(String[] paramArrayOfString);
  
  int updateAuditstatus(String paramString) throws Exception;
  
  int updateAuditstatusByTxLogisticID(String paramString);
  
  int updateExpressParam(String paramString);
  
  int updateIspost(String paramString);
  
  String updateIsPushToWms(String paramString) throws Exception;
  
  String updateIsPushToWmsNew(String paramString) throws Exception;
  
  String updateIsCustoms(String paramString) throws Exception;
  
  int updateAuditstatusByIds(String paramString);
  
  int updateIsCustomsByTxLogisticID(String paramString);
  
  OrderBonded findByIdToBG(Integer paramInteger);
  
  List<OrderBondedForExport> exportOrder(Map<String, Object> paramMap);
  
  List<OrderBondedForExport> exportOrderBonded(OrderBondedForExport paramOrderBondedForExport);
  
  List<OrderBonded> findTxLogisticID();
  
  int payNumber(String paramString);
  
  int pushDd(String paramString);
  
  int pushQd(String paramString);
  
  OrderBonded findByMailNo(String paramString);
  
  int updateStatus(String paramString);
  
  GeneralResult importOrderNew(String paramString, int paramInt);
  
  int findaddress(OrderBonded paramOrderBonded);
  
  int updaddress(OrderBonded paramOrderBonded);
  
  List<OrderBonded> odbdateTime(Map<String, Object> paramMap);
  
  List<OrderBonded> findCs(String paramString);
  
  List<OrderBonded> findTxId(String paramString);
  
  int upm(OrderBonded paramOrderBonded);
  
  void deletetx(String paramString);
  
  int editodIdN(OrderBonded paramOrderBonded);
  
  void updateInvtno(OrderBonded paramOrderBonded);
  
  OrderBonded findOneById(int paramInt);
  
  int countByCollect(Collect paramCollect);
  
  List<OrderBonded> findAllByCollect(Collect paramCollect);
  
  int pushQdTest(String paramString);
  
  int editMatter(OrderBonded paramOrderBonded);
  
  int updateDSHKExpressParam(Integer[] paramArrayOfInteger);
  
  int push3(String paramString) throws Exception;
  
  void insert(OrderBonded paramOrderBonded);
  
  int insertAuditstatusByIds(String paramString);
  
  int updateIspostToYf(String paramString) throws Exception;
  
  int updateExpressParam2ForHTO(String paramString);
  
  void updateAuditstatus0(int[] paramArrayOfint);
  
  void updateCarrier(OrderBonded paramOrderBonded);
  
  int pushOrderToDsds(String paramString) throws Exception;
  
  int findException();
  
  List<String> getMailNoStraight(String paramString, int paramInt) throws Exception;
  
  List<String> cancleOrder(int[] paramArrayOfint);
  
  void deleteByIds(int[] paramArrayOfint);
  
  List<OrderBondedForExport> exportBondedByIds(String paramString);
  
  int updateExpressParam3ForHTO(String paramString);
  
  int payNumberTL(String paramString);
  
  void matchSDM(int[] paramArrayOfint);
  
  int payNumberKQ(String paramString);
  
  String pushBfb(String paramString1, String paramString2) throws Exception;
  
  int updatePushOrderZFB(String paramString);
  
  String pushForCFM(String paramString) throws Exception;
  
  String pushBfbUtil(String paramString) throws Exception;
  
  String checkIdCard(String paramString) throws Exception;
  
  OrderBonded findInfo(String paramString);
  
  int findInfoCount(String paramString);
  
  JSONObject queryOrderByParamInsert(String paramString) throws Exception;
  
  String getBfbUtil(String paramString) throws Exception;
  
  int backOrder(OrderBondedForExport paramOrderBondedForExport);
  
  String CreateOrder(String paramString) throws Exception;
}


