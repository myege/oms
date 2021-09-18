package com.what21.dao;

import com.what21.model.Idcheck;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IdcheckDao {
  void insertBatch(List<Idcheck> paramList);
  
  List<Idcheck> findgjz(@Param("gjz") String paramString);
}


