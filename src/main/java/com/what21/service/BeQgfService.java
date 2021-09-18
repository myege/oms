package com.what21.service;

import com.what21.model.BeQgf;
import com.what21.model.Collect;
import java.util.List;
import java.util.Map;

public interface BeQgfService {
  List<BeQgf> findAll(Map<String, Object> paramMap) throws Exception;
  
  Integer count(Map<String, Object> paramMap) throws Exception;
  
  void insert(BeQgf paramBeQgf) throws Exception;
  
  void deleteById(Integer paramInteger) throws Exception;
  
  BeQgf findById(Integer paramInteger) throws Exception;
  
  BeQgf findByPramid(int paramInt) throws Exception;
  
  void update(BeQgf paramBeQgf) throws Exception;
  
  int update2(BeQgf paramBeQgf) throws Exception;
  
  int countByPramid(Map<String, Object> paramMap);
  
  List<BeQgf> findAllByPramid(Map<String, Object> paramMap);
  
  void insertByBsjs(Collect paramCollect);
  
  void deleteByPramid(int paramInt);
}


