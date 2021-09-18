package com.what21.dao;

import com.what21.model.BfbModel;
import com.what21.model.EchattsBonded;
import com.what21.model.FindBytxLogisticIDForYtoExcel;
import com.what21.model.OrderMail;
import com.what21.model.OrderMailForExport;
import com.what21.model.OrderMailPush;
import java.util.List;
import java.util.Map;

public interface OrderMailDao {
  List<OrderMail> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int insert(OrderMail paramOrderMail);
  
  OrderMail findByTxLogisticID(String paramString);
  
  OrderMailPush findById(Integer paramInteger);
  
  List<OrderMail> findByIds(int[] paramArrayOfint);
  
  List<FindBytxLogisticIDForYtoExcel> findBytxLogisticIDForYtoExcelById(int[] paramArrayOfint);
  
  List<OrderMail> findtxLogisticID(int[] paramArrayOfint);
  
  OrderMail findByIdToBG(Integer paramInteger);
  
  OrderMail findByIdToWJ(Integer paramInteger);
  
  int updateByIds(int[] paramArrayOfint);
  
  void updateToal(OrderMail paramOrderMail);
  
  int delete(String[] paramArrayOfString);
  
  Integer deleteMailNos(String[] paramArrayOfString);
  
  int updateAuditstatus(OrderMail paramOrderMail);
  
  int updateExpressParam(OrderMail paramOrderMail);
  
  int updateIspost(OrderMail paramOrderMail);
  
  int updateIsCustoms(OrderMail paramOrderMail);
  
  OrderMail findByMailNo(OrderMail paramOrderMail);
  
  List<OrderMail> findMailNo(Map<String, Object> paramMap);
  
  void updateCountAndWeight(OrderMail paramOrderMail);
  
  int updateIsPushCz(Integer paramInteger);
  
  List<OrderMail> findTxLogisticID();
  
  int payNumber(OrderMail paramOrderMail);
  
  int updateIsPushDd(OrderMail paramOrderMail);
  
  int updateIsPushQd(OrderMail paramOrderMail);
  
  List<OrderMail> findByTotalMailNo(String paramString);
  
  List<OrderMail> findAllmx(int paramInt);
  
  int countmx(int paramInt);
  
  List<OrderMail> findeXport(Map<String, Object> paramMap);
  
  List<OrderMail> findeXportId(Integer paramInteger);
  
  int batchInsert(Object[] paramArrayOfObject);
  
  OrderMail getTax(OrderMail paramOrderMail);
  
  List<OrderMail> findBybatchNumber(Map<String, Object> paramMap);
  
  int conbatchNumber(String paramString);
  
  List<OrderMail> deriveddetail(String paramString);
  
  List<OrderMailForExport> exportMoil(Map<String, Object> paramMap);
  
  List<OrderMail> exportMoilid(Integer paramInteger);
  
  OrderMail findTxLogisticIDByTotalMailNo(String paramString);
  
  OrderMail findOrderMailDy(String paramString);
  
  OrderMail findOrderMailXy(String paramString);
  
  OrderMail findOrderMai(String paramString);
  
  List<OrderMail> storageOrderMai(String paramString);
  
  List<OrderMail> odmdateTime(Map<String, Object> paramMap);
  
  int editIdN(OrderMail paramOrderMail);
  
  void updateInvtno(OrderMail paramOrderMail);
  
  OrderMail findOneById(int paramInt);
  
  int countByTydh(String paramString);
  
  int updateIsMailNo(OrderMail paramOrderMail);
  
  void updateValueAddedTax(OrderMail paramOrderMail);
  
  List<OrderMail> findByTydh(Map<String, Object> paramMap);
  
  int updateExpressParamsf(String paramString);
  
  List<OrderMail> iadd();
  
  OrderMail findByIdTosf(int paramInt);
  
  void inztsf(OrderMail paramOrderMail);
  
  int updateIsPrint(OrderMail paramOrderMail);
  
  int editMatterm(OrderMail paramOrderMail);
  
  int updateMailNoById(OrderMail paramOrderMail);
  
  Map<String, String> findByTotalMailNoS(String paramString);
  
  List<OrderMail> findByOmArr(Object[] paramArrayOfObject);
  
  int updateAuditstatus2(OrderMail paramOrderMail);
  
  List<OrderMail> findByIdArrToCz(String[] paramArrayOfString);
  
  List<OrderMail> findByTotalMailNoToCz(String paramString);
  
  void batchUpdateIsPushCz(List<OrderMail> paramList);
  
  List<OrderMail> findReturnCode(String paramString);
  
  List<OrderMail> findByIdArr(String[] paramArrayOfString);
  
  int findByIdToalOrderMailNo(String paramString);
  
  int updateIspostForYF(OrderMail paramOrderMail);
  
  int updateReturnCode(OrderMail paramOrderMail);
  
  void transferExcep(Integer paramInteger);
  
  int findException();
  
  void updateAuditstatus0(int[] paramArrayOfint);
  
  List<OrderMailForExport> exportMailByIds(String paramString);
  
  List<FindBytxLogisticIDForYtoExcel> findBytxLogisticIDForYtoExcels(String paramString);
  
  void updateSerialNumber(List<Map<String, String>> paramList);
  
  BfbModel pushBfb(String paramString);
  
  void updateByBfb(OrderMail paramOrderMail);
  
  Integer findOrderToday();
  
  int findOrderYear();
  
  int findExceptionOrderYear();
  
  int findLastYearOrderNumber();
  
  int findLastMonthOrderNumber();
  
  String findLastMonthTotalSum();
  
  String findTotalSumYear();
  
  List<EchattsBonded> findbonded();
  
  List<EchattsBonded> findMail();
  
  List<OrderMailForExport> exportGD(OrderMail paramOrderMail);
  
  List<Map> neworderzycx(String paramString);
  
  int updateddzt_2(String paramString);
  
  int updateptzt_2(String paramString);
  
  int updateddhzzt_1(String paramString);
  
  int goBackOrder(String[] paramArrayOfString);
}


