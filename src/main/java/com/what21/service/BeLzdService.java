package com.what21.service;

import com.what21.model.BeLzd;
import com.what21.model.BeLzdQueryVo;
import com.what21.result.ResultInfo;
import java.util.List;

public interface BeLzdService {
  List<BeLzd> findAll(BeLzdQueryVo paramBeLzdQueryVo) throws Exception;
  
  int countAll(BeLzdQueryVo paramBeLzdQueryVo) throws Exception;
  
  void update(BeLzd paramBeLzd) throws Exception;
  
  BeLzd findById(Integer paramInteger) throws Exception;
  
  void deleteById(Integer paramInteger) throws Exception;
  
  ResultInfo insert(BeLzdQueryVo paramBeLzdQueryVo) throws Exception;
  
  void insertZgxx(BeLzdQueryVo paramBeLzdQueryVo) throws Exception;
  
  void updateFlagByIds(String[] paramArrayOfString, int paramInt) throws Exception;
  
  void updateLzd(BeLzdQueryVo paramBeLzdQueryVo) throws Exception;
  
  void deleteByIds(int[] paramArrayOfint) throws Exception;
}


