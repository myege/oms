package com.what21.dao;

import com.what21.model.SzqgStrategy;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SzqgStrategyDao {
  List<SzqgStrategy> findgjz(@Param("gjz") String paramString);
  
  SzqgStrategy findBySt(String paramString);
}


