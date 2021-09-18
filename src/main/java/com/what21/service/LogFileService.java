package com.what21.service;

import com.what21.model.LogFile;
import java.util.List;
import java.util.Map;

public interface LogFileService {
  List<LogFile> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int update(LogFile paramLogFile);
  
  LogFile findlog();
  
  int addlogf(LogFile paramLogFile);
  
  int logfdelete(String paramString);
  
  int logfedit(LogFile paramLogFile);
  
  LogFile findAllLogFile(int paramInt);
  
  String getTax(int paramInt);
  
  LogFile findlogzf(int paramInt);
  
  int frostMo(LogFile paramLogFile);
}


