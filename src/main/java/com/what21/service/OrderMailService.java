package com.what21.service;

import com.what21.model.EchattsBonded;
import com.what21.model.FindBytxLogisticIDForYtoExcel;
import com.what21.model.OrderMail;
import com.what21.model.OrderMailForExport;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface OrderMailService {
  List<OrderMail> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  GeneralResult importOrder(String paramString, int paramInt);
  
  int deleteOrderMail(String[] paramArrayOfString);
  
  List<OrderMail> findtxLogisticID(int[] paramArrayOfint);
  
  int updateAuditstatus(String paramString) throws Exception;
  
  int updateExpressParam(String paramString);
  
  int updateIspost(String paramString);
  
  int updateIsPushCz(String paramString);
  
  void updateToal(OrderMail paramOrderMail);
  
  int updateByIds(int[] paramArrayOfint);
  
  String updateIsPushCzNew(String paramString1, String paramString2) throws Exception;
  
  int updateIsCustoms(String paramString);
  
  int updateAuditstatusByIds(String paramString);
  
  OrderMail findByMailNo(OrderMail paramOrderMail);
  
  List<OrderMail> findMailNo(Map<String, Object> paramMap);
  
  int findByIdToalOrderMailNo(String paramString);
  
  List<OrderMail> findTxLogisticID();
  
  int payNumber(OrderMail paramOrderMail);
  
  int pushDd(String paramString);
  
  int storage(String paramString);
  
  int pushQd(String paramString);
  
  List<OrderMail> findAllmx(int paramInt);
  
  int countmx(int paramInt);
  
  List<OrderMail> findeXport(Map<String, Object> paramMap);
  
  List<OrderMail> findeXportId(Integer paramInteger);
  
  GeneralResult importOrderNew(String paramString, int paramInt);
  
  String getTax(int paramInt);
  
  List<OrderMail> findBybatchNumber(Map<String, Object> paramMap);
  
  int conbatchNumber(String paramString);
  
  List<OrderMail> deriveddetail(String paramString);
  
  List<OrderMailForExport> exportMoil(Map<String, Object> paramMap);
  
  List<OrderMail> exportMoilid(Integer paramInteger);
  
  OrderMail findOrderMailDy(String paramString);
  
  OrderMail findOrderMailXy(String paramString);
  
  OrderMail findOrderMai(String paramString);
  
  String getMailBill(String paramString) throws Exception;
  
  List<OrderMail> odmdateTime(Map<String, Object> paramMap);
  
  int editIdN(OrderMail paramOrderMail);
  
  void updateInvtno(OrderMail paramOrderMail);
  
  OrderMail findOneById(int paramInt);
  
  int countByTydh(String paramString);
  
  int updateExpressParamsf(String paramString);
  
  void inztsf(OrderMail paramOrderMail);
  
  int pushQdTest(String paramString);
  
  List<FindBytxLogisticIDForYtoExcel> findBytxLogisticIDForYtoExcelById(int[] paramArrayOfint);
  
  List<FindBytxLogisticIDForYtoExcel> findBytxLogisticIDForYtoExcels(String paramString);
  
  int editMatterm(OrderMail paramOrderMail);
  
  int push3(String paramString);
  
  String pushCzStatus(String paramString);
  
  int updateIspostToYf(String paramString) throws Exception;
  
  int updateReturnCode(OrderMail paramOrderMail);
  
  int pushOrderToDsds(String paramString) throws Exception;
  
  int findException();
  
  void updateAuditstatus0(int[] paramArrayOfint);
  
  int payNumberTL(String paramString);
  
  List<OrderMailForExport> exportMailByIds(String paramString);
  
  List<OrderMailForExport> exportGD(OrderMail paramOrderMail);
  
  List<OrderMail> findByIds(int[] paramArrayOfint);
  
  String pushBfb(String paramString1, String paramString2) throws Exception;
  
  Integer findOrderToday();
  
  int findOrderYear();
  
  int findExceptionOrderYear();
  
  String findTotalSumYear();
  
  int findLastYearOrderNumber();
  
  int findLastMonthOrderNumber();
  
  String findLastMonthTotalSum();
  
  List<EchattsBonded> findbonded();
  
  List<EchattsBonded> findMail();
  
  String pushBfbUtil(String paramString1, String paramString2) throws Exception;
  
  String getBfbUtil(String paramString) throws Exception;
  
  int goBackOrder(String[] paramArrayOfString);
}


