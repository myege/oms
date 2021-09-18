package com.what21.service;

import com.what21.model.BeAccessoryOut;
import com.what21.model.BeAccessoryOutQueryVo;
import com.what21.result.ResultInfo;
import java.util.List;

public interface BeAccessoryOutService {
  List<BeAccessoryOut> findAll(BeAccessoryOutQueryVo paramBeAccessoryOutQueryVo);
  
  Integer countAll(BeAccessoryOutQueryVo paramBeAccessoryOutQueryVo);
  
  ResultInfo insert(BeAccessoryOut paramBeAccessoryOut);
  
  BeAccessoryOut findById(Integer paramInteger);
  
  void deleteByIds(Integer[] paramArrayOfInteger) throws Exception;
  
  void deleteById(int paramInt) throws Exception;
  
  ResultInfo update(int paramInt, String paramString1, String paramString2, String paramString3);
}


