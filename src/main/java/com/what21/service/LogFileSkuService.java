package com.what21.service;

import com.what21.model.LogFileSku;
import java.util.List;
import java.util.Map;

public interface LogFileSkuService {
  int saveLog(LogFileSku paramLogFileSku);
  
  List<LogFileSku> findAll(Map<String, Object> paramMap);
  
  int countLogSku(Map<String, Object> paramMap);
  
  int deleteSku(String paramString);
  
  LogFileSku findTxId(String paramString);
  
  LogFileSku findSkuCom(String paramString);
}


