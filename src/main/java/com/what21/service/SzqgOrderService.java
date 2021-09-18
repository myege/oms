package com.what21.service;

import com.what21.model.SzqgOrder;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface SzqgOrderService {
  List<SzqgOrder> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  String updateIsCustoms(String paramString) throws Exception;
  
  String ddpush(String paramString) throws Exception;
  
  String hctbpush(String paramString) throws Exception;
  
  String qdpush(String paramString) throws Exception;
  
  String ctddpush(String paramString) throws Exception;
  
  String ctqdpush(String paramString) throws Exception;
  
  String fydpush(String paramString) throws Exception;
  
  String zydpush(String paramString) throws Exception;
  
  String CCqdpush(String paramString) throws Exception;
  
  String wlgjpush(String paramString) throws Exception;
  
  String ptpush(String paramString) throws Exception;
  
  String shpush(String paramString) throws Exception;
  
  String CreateOrder(String paramString) throws Exception;
  
  String GetMailno(String paramString) throws Exception;
  
  GeneralResult importOrderNew(String paramString, int paramInt);
}


