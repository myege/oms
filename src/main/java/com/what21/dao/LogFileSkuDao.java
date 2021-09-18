package com.what21.dao;

import com.what21.model.LogFileSku;
import java.util.List;
import java.util.Map;

public interface LogFileSkuDao {
  int saveLog(LogFileSku paramLogFileSku);
  
  List<LogFileSku> findAll(Map<String, Object> paramMap);
  
  int countLogSku(Map<String, Object> paramMap);
  
  int deleteSku(String paramString);
  
  LogFileSku findTxId(String paramString);
  
  LogFileSku findSkuCom(String paramString);
}


