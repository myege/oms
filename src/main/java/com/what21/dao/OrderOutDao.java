package com.what21.dao;

import com.what21.model.OrderOut;
import com.what21.model.OrderOutForExport;
import com.what21.model.OrderOutQueryVo;
import com.what21.model.OrderOutSku;
import java.util.List;
import java.util.Map;

public interface OrderOutDao {
  List<OrderOut> find(OrderOutQueryVo paramOrderOutQueryVo);
  
  Integer count(OrderOutQueryVo paramOrderOutQueryVo);
  
  void insert(OrderOut paramOrderOut);
  
  OrderOut findById(Integer paramInteger);
  
  void deleteById(Integer paramInteger);
  
  void update(OrderOut paramOrderOut);
  
  List<OrderOutSku> findSku(OrderOutQueryVo paramOrderOutQueryVo);
  
  Integer countSku(OrderOutQueryVo paramOrderOutQueryVo);
  
  void insertSku(OrderOutSku paramOrderOutSku);
  
  void deleteByOrderno(String paramString);
  
  OrderOut findByOrderno(String paramString);
  
  List<OrderOut> findByiIds(int[] paramArrayOfint);
  
  void updateStatus1(int paramInt);
  
  String findSumPriceByOrderno(String paramString);
  
  void updateSumPriceByOrderno(List<OrderOut> paramList);
  
  Integer findNumByOrderno(String paramString);
  
  List<OrderOutSku> findSkusByOrderno(String paramString);
  
  void insertBatch(List<OrderOut> paramList);
  
  void insertSkuBatch(List<OrderOutSku> paramList);
  
  Integer sumByOrderno(String paramString);
  
  Integer sumByWayBillNo(String paramString);
  
  void updateEnd(int[] paramArrayOfint);
  
  OrderOutSku findSkuById(Integer paramInteger);
  
  void updateSku(OrderOutSku paramOrderOutSku);
  
  String findCompanycodeByUserid(Integer paramInteger);
  
  void updateStatus(int paramInt, String paramString);
  
  void updateGoodsno(String paramString1, String paramString2);
  
  Integer countSkuByGoodsno(String paramString);
  
  void deleteSkuByIds(int[] paramArrayOfint);
  
  void deleteByIds(int[] paramArrayOfint);
  
  void updateSto1(List<Integer> paramList);
  
  void updateWeight(int paramInt, String paramString1, String paramString2);
  
  List<OrderOutForExport> findExportByIds(int[] paramArrayOfint);
  
  List<OrderOutForExport> findExport(OrderOutQueryVo paramOrderOutQueryVo);
  
  List<OrderOut> findByTotalWayBill(String paramString);
  
  List<OrderOutSku> findSkuByIdS(int[] paramArrayOfint);
  
  List<OrderOut> findByiIdsList(List<Integer> paramList);
  
  List<OrderOutSku> findSkuByIdSList(List<Integer> paramList);
  
  void updateDD(List<OrderOut> paramList);
  
  void updateQD(List<OrderOut> paramList);
  
  void updateZF(List<OrderOut> paramList);
  
  List<Map<String, Object>> jxsh(String paramString);
}


