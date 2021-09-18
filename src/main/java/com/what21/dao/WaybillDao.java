package com.what21.dao;

import com.what21.model.Waybill;
import java.util.List;
import java.util.Map;

public interface WaybillDao {
  List<Waybill> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  void insert(Waybill paramWaybill);
  
  List<Waybill> findId(Map<String, Object> paramMap);
  
  List<Waybill> findSearch(Map<String, Object> paramMap);
  
  List<Waybill> findIsSign(Map<String, Object> paramMap);
  
  int delete(Map<String, Object> paramMap);
  
  Waybill findByExpressNumber(Map<String, Object> paramMap);
  
  List<Waybill> dateTime(Map<String, Object> paramMap);
  
  String[] getExpressNumber();
  
  void update(List<Waybill> paramList);
  
  void insertList(List<Waybill> paramList);
  
  List<Waybill> findIdArray(String[] paramArrayOfString);
}


