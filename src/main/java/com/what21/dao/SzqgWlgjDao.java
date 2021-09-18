package com.what21.dao;

import com.what21.model.SzqgwlGj;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SzqgWlgjDao {
  List<SzqgwlGj> findgjz(@Param("gjz") String paramString);
}


