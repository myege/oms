package com.what21.dao;

import com.what21.model.Outshipto;
import java.util.List;
import java.util.Map;

public interface OutshiptoDao {
  List<Outshipto> findAll(Outshipto paramOutshipto);
  
  List<Outshipto> findId(String paramString);
  
  int findCount(Outshipto paramOutshipto);
  
  void delete(String paramString);
  
  void deleteSku(String paramString);
  
  void update(Outshipto paramOutshipto);
  
  void insert(List<Outshipto> paramList);
  
  List<String> findBillNo(Map<String, String> paramMap);
  
  List<String> findLogisticsNo(Map<String, String> paramMap);
  
  void huizUpdate(Outshipto paramOutshipto);
  
  void updateType(String paramString);
}


