package com.what21.dao;

import com.what21.model.OrderPush3;
import com.what21.model.TimedTask;
import java.util.List;
import java.util.Map;

public interface TimedTaskDao {
  List<TimedTask> findAll(Map<String, Object> paramMap) throws Exception;
  
  Integer count(Map<String, Object> paramMap) throws Exception;
  
  int add(TimedTask paramTimedTask) throws Exception;
  
  int delete(String paramString) throws Exception;
  
  int update(TimedTask paramTimedTask) throws Exception;
  
  List<Map<String, Object>> findTimeTj(Map<String, String> paramMap) throws Exception;
  
  List<OrderPush3> findPush3(String[] paramArrayOfString) throws Exception;
}


