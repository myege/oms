package com.what21.dao;

import com.what21.model.OrderOutBS;
import com.what21.model.OrderOutForExportBS;
import com.what21.model.OrderOutQueryVoBS;
import com.what21.model.OrderOutSkuBS;
import java.util.List;

public interface OrderOutBSDao {
  List<OrderOutBS> find(OrderOutQueryVoBS paramOrderOutQueryVoBS);
  
  Integer count(OrderOutQueryVoBS paramOrderOutQueryVoBS);
  
  void insert(OrderOutBS paramOrderOutBS);
  
  OrderOutBS findById(Integer paramInteger);
  
  void deleteById(Integer paramInteger);
  
  void update(OrderOutBS paramOrderOutBS);
  
  List<OrderOutSkuBS> findSku(OrderOutQueryVoBS paramOrderOutQueryVoBS);
  
  Integer countSku(OrderOutQueryVoBS paramOrderOutQueryVoBS);
  
  void insertSku(OrderOutSkuBS paramOrderOutSkuBS);
  
  void deleteByOrderno(String paramString);
  
  OrderOutBS findByOrderno(String paramString);
  
  List<OrderOutBS> findByiIds(int[] paramArrayOfint);
  
  void updateStatus1(int paramInt);
  
  String findSumPriceByOrderno(String paramString);
  
  void updateSumPriceByOrderno(List<OrderOutBS> paramList);
  
  Integer findNumByOrderno(String paramString);
  
  List<OrderOutSkuBS> findSkusByOrderno(String paramString);
  
  void insertBatch(List<OrderOutBS> paramList);
  
  void insertSkuBatch(List<OrderOutSkuBS> paramList);
  
  Integer sumByOrderno(String paramString);
  
  void updateEnd(int[] paramArrayOfint);
  
  OrderOutSkuBS findSkuById(Integer paramInteger);
  
  void updateSku(OrderOutSkuBS paramOrderOutSkuBS);
  
  String findCompanycodeByUserid(Integer paramInteger);
  
  void updateStatus(int paramInt, String paramString);
  
  void updateGoodsno(String paramString1, String paramString2);
  
  Integer countSkuByGoodsno(String paramString);
  
  void deleteSkuByIds(int[] paramArrayOfint);
  
  void deleteByIds(int[] paramArrayOfint);
  
  void updateSto1(List<Integer> paramList);
  
  void updateWeight(int paramInt, String paramString1, String paramString2);
  
  List<OrderOutForExportBS> findExportByIds(int[] paramArrayOfint);
  
  List<OrderOutForExportBS> findExport(OrderOutQueryVoBS paramOrderOutQueryVoBS);
}


