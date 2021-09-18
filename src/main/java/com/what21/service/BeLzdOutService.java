package com.what21.service;

import com.what21.model.BeLzdOut;
import com.what21.model.BeLzdOutCustom;
import com.what21.model.BeLzdOutQueryVo;
import com.what21.model.CountBeLzdOut;
import com.what21.result.ResultInfo;
import com.what21.util.GeneralResult;
import java.util.List;

public interface BeLzdOutService {
  List<BeLzdOut> findAll(BeLzdOutQueryVo paramBeLzdOutQueryVo) throws Exception;
  
  int countAll(BeLzdOutQueryVo paramBeLzdOutQueryVo) throws Exception;
  
  ResultInfo update(BeLzdOut paramBeLzdOut) throws Exception;
  
  BeLzdOut findById(Integer paramInteger) throws Exception;
  
  void deleteByIds(Integer[] paramArrayOfInteger) throws Exception;
  
  ResultInfo insert(BeLzdOutQueryVo paramBeLzdOutQueryVo) throws Exception;
  
  CountBeLzdOut count(BeLzdOutCustom paramBeLzdOutCustom) throws Exception;
  
  GeneralResult importBeLzdOut(String paramString) throws Exception;
  
  List<BeLzdOut> findByIds(int[] paramArrayOfint);
}


