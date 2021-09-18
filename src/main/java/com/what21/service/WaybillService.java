package com.what21.service;

import com.what21.model.Waybill;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface WaybillService {
  List<Waybill> findAll(Map<String, Object> paramMap) throws Exception;
  
  int count(Map<String, Object> paramMap);
  
  void insert(Waybill paramWaybill);
  
  List<Waybill> findId(Map<String, Object> paramMap);
  
  List<Waybill> findSearch(Map<String, Object> paramMap);
  
  List<Waybill> findIsSign(Map<String, Object> paramMap);
  
  int delete(Map<String, Object> paramMap);
  
  Waybill findByExpressNumber(Map<String, Object> paramMap);
  
  GeneralResult importOrder(String paramString, int paramInt);
  
  List<Waybill> dateTime(Map<String, Object> paramMap);
  
  List<Waybill> findIdArray(String[] paramArrayOfString);
}


