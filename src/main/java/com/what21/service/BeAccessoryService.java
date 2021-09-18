package com.what21.service;

import com.what21.model.BeAccessory;
import com.what21.result.ResultInfo;
import java.util.List;
import java.util.Map;

public interface BeAccessoryService {
  BeAccessory findById(Integer paramInteger) throws Exception;
  
  ResultInfo insert(BeAccessory paramBeAccessory) throws Exception;
  
  ResultInfo update(int paramInt, String paramString1, String paramString2, String paramString3) throws Exception;
  
  List<BeAccessory> findByLzdIdAndItemName(Map<String, Object> paramMap) throws Exception;
  
  int countByLzdIdAndItemName(Map<String, Object> paramMap);
  
  void deleteById(int paramInt) throws Exception;
  
  void deleteByIds(int[] paramArrayOfint) throws Exception;
  
  List<BeAccessory> findByLzdIdAndItemName2(Map<String, Object> paramMap);
  
  int countByLzdIdAndItemName2(Map<String, Object> paramMap);
}


