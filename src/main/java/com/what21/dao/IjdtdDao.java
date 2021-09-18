package com.what21.dao;

import com.what21.model.Ijdtd;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface IjdtdDao {
  List<Ijdtd> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  void insertBatch(List<Ijdtd> paramList);
  
  void delete(String paramString);
  
  List<Ijdtd> findbygjz(String paramString);
  
  void updatebygjz(@Param("ladNo") String paramString1, @Param("notes") String paramString2, @Param("status") String paramString3);
}


