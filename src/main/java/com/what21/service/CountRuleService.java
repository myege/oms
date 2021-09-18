package com.what21.service;

import com.what21.model.CountRule;
import java.util.List;
import java.util.Map;

public interface CountRuleService {
  List<CountRule> findAll(Map<String, Object> paramMap) throws Exception;
  
  Integer count(Map<String, Object> paramMap) throws Exception;
  
  Integer add(CountRule paramCountRule) throws Exception;
  
  Integer update(CountRule paramCountRule) throws Exception;
  
  Integer delate(String[] paramArrayOfString) throws Exception;
  
  List<CountRule> findCombobox(Integer paramInteger) throws Exception;
}


