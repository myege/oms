package com.what21.service;

import com.what21.model.YTOBillSku;
import java.util.List;
import java.util.Map;

public interface YTOBillSkuService {
  List<YTOBillSku> findByWaybillNo(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int delete(int[] paramArrayOfint);
  
  int delete_1(int[] paramArrayOfint);
  
  int updateById(YTOBillSku paramYTOBillSku);
  
  List<YTOBillSku> odmTax(Map<String, Object> paramMap);
  
  int insertYTO(YTOBillSku paramYTOBillSku);
  
  List<YTOBillSku> findByOrderNo(YTOBillSku paramYTOBillSku);
  
  List<YTOBillSku> findByWaybillNoAndOrderNo(Map<String, Object> paramMap);
  
  List<YTOBillSku> findbyId(YTOBillSku paramYTOBillSku);
  
  int deleteAll();
  
  int updateByIds(int[] paramArrayOfint);
  
  List<YTOBillSku> findByWailNoFor(Map<String, Object> paramMap);
  
  List<YTOBillSku> findByIds(int[] paramArrayOfint);
  
  int updateByWayBillNo(Map<String, Object> paramMap);
  
  int updateAOP();
  
  List<YTOBillSku> findAll();
  
  int updateByWayBillNos(List<YTOBillSku> paramList);
}


