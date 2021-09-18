package com.what21.dao;

import com.what21.model.BeAccessoryOut;
import com.what21.model.BeAccessoryOutQueryVo;
import java.util.List;

public interface BeAccessoryOutDao {
  List<BeAccessoryOut> findAll(BeAccessoryOutQueryVo paramBeAccessoryOutQueryVo);
  
  Integer countAll(BeAccessoryOutQueryVo paramBeAccessoryOutQueryVo);
  
  void insert(BeAccessoryOut paramBeAccessoryOut);
  
  void deleteByIds(Integer[] paramArrayOfInteger);
  
  BeAccessoryOut findById(Integer paramInteger);
  
  Integer countByItemname(Integer paramInteger, String paramString);
  
  void deleteById(int paramInt);
  
  void update(BeAccessoryOut paramBeAccessoryOut);
}


