package com.what21.dao;

import com.what21.model.BeLzd;
import com.what21.model.BeLzdQueryVo;
import java.util.List;

public interface BeLzdDao {
  List<BeLzd> findAll(BeLzdQueryVo paramBeLzdQueryVo) throws Exception;
  
  int countAll(BeLzdQueryVo paramBeLzdQueryVo) throws Exception;
  
  void update(BeLzd paramBeLzd) throws Exception;
  
  void insert(BeLzd paramBeLzd) throws Exception;
  
  BeLzd findById(Integer paramInteger) throws Exception;
  
  void deleteById(Integer paramInteger) throws Exception;
  
  String getMaxYwbh(String paramString) throws Exception;
  
  BeLzd findByTydh(String paramString) throws Exception;
}


