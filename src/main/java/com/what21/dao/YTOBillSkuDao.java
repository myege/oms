package com.what21.dao;

import com.what21.model.YTOBillSku;
import java.util.List;
import java.util.Map;

public interface YTOBillSkuDao {
  List<YTOBillSku> findByWaybillNo(Map<String, Object> paramMap);
  
  List<YTOBillSku> findByWailNoFor(Map<String, Object> paramMap);
  
  List<YTOBillSku> findbyId(YTOBillSku paramYTOBillSku);
  
  int count(Map<String, Object> paramMap);
  
  List<YTOBillSku> findByOrderNo(YTOBillSku paramYTOBillSku);
  
  int delete(int[] paramArrayOfint);
  
  int delete_1(int[] paramArrayOfint);
  
  int updateById(YTOBillSku paramYTOBillSku);
  
  int updateByIds(int[] paramArrayOfint);
  
  int updateByWayBillNo(Map<String, Object> paramMap);
  
  int updateAOP();
  
  int insertYTO(YTOBillSku paramYTOBillSku);
  
  List<YTOBillSku> odmTax(Map<String, Object> paramMap);
  
  List<YTOBillSku> findByWaybillNoAndOrderNo(Map<String, Object> paramMap);
  
  void insertSkuBatch(List<YTOBillSku> paramList);
  
  int deleteAll();
  
  List<YTOBillSku> findByIds(int[] paramArrayOfint);
  
  List<YTOBillSku> findAll();
  
  int updateByWayBillNos(List<YTOBillSku> paramList);
}


