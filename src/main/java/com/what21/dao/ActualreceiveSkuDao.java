package com.what21.dao;

import com.what21.model.ActualreceiveSku;
import java.util.List;
import java.util.Map;

public interface ActualreceiveSkuDao {
  List<ActualreceiveSku> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  List<ActualreceiveSku> findact(Map<String, Object> paramMap);
  
  void insert(ActualreceiveSku paramActualreceiveSku);
  
  ActualreceiveSku findByA(String paramString);
  
  int actdelete(String paramString);
  
  void aflag(ActualreceiveSku paramActualreceiveSku);
  
  void asInsert(Object[] paramArrayOfObject);
}


