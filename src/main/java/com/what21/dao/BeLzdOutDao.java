package com.what21.dao;

import com.what21.model.BeLzdOut;
import com.what21.model.BeLzdOutCustom;
import com.what21.model.BeLzdOutQueryVo;
import com.what21.model.CountBeLzdOut;
import java.util.List;

public interface BeLzdOutDao {
  List<BeLzdOut> findAll(BeLzdOutQueryVo paramBeLzdOutQueryVo) throws Exception;
  
  int countAll(BeLzdOutQueryVo paramBeLzdOutQueryVo) throws Exception;
  
  void update(BeLzdOut paramBeLzdOut) throws Exception;
  
  void insert(BeLzdOut paramBeLzdOut) throws Exception;
  
  BeLzdOut findById(Integer paramInteger) throws Exception;
  
  void deleteByIds(Integer[] paramArrayOfInteger) throws Exception;
  
  BeLzdOut findByTydh(String paramString) throws Exception;
  
  CountBeLzdOut count(BeLzdOutCustom paramBeLzdOutCustom);
  
  void insertBatch(List<BeLzdOut> paramList);
  
  List<String> findByTdhs(Object[] paramArrayOfObject);
  
  List<BeLzdOut> findByIds(int[] paramArrayOfint);
}


