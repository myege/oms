package com.what21.dao;

import com.what21.model.Ijdlhd;
import java.util.List;
import java.util.Map;

public interface IjdlhdDao {
  List<Ijdlhd> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  void delete(String paramString);
  
  void insertBatch(List<Ijdlhd> paramList);
}


