package com.what21.service;

import com.what21.model.TimedTask;
import java.util.List;
import java.util.Map;

public interface TimedTaskService {
  List<TimedTask> findAll(Map<String, Object> paramMap) throws Exception;
  
  Integer count(Map<String, Object> paramMap) throws Exception;
  
  int add(TimedTask paramTimedTask) throws Exception;
  
  int delete(String paramString) throws Exception;
  
  int update(TimedTask paramTimedTask) throws Exception;
}


