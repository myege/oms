package com.what21.service;

import com.what21.model.BeHc;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface BeHcService {
  List<BeHc> findAll(Map<String, Object> paramMap) throws Exception;
  
  Integer count(Map<String, Object> paramMap) throws Exception;
  
  void insert(BeHc paramBeHc) throws Exception;
  
  void deleteById(Integer paramInteger) throws Exception;
  
  BeHc findById(Integer paramInteger) throws Exception;
  
  List<BeHc> findByPramid(Map<String, Object> paramMap) throws Exception;
  
  Integer countByPramid(Map<String, Object> paramMap) throws Exception;
  
  GeneralResult importOrderNew(String paramString, int paramInt) throws Exception;
  
  void deleteByPramid(Map<String, Object> paramMap);
}


