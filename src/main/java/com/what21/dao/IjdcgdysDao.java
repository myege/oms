package com.what21.dao;

import com.what21.model.Ijdcgdys;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface IjdcgdysDao {
  List<Ijdcgdys> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  void insertBatch(List<Ijdcgdys> paramList);
  
  void delete(String paramString);
  
  List<Ijdcgdys> findbygjz(String paramString);
  
  void updatebygjz(@Param("poNo") String paramString1, @Param("notes") String paramString2, @Param("status") String paramString3);
}


