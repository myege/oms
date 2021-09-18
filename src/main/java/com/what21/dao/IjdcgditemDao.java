package com.what21.dao;

import com.what21.model.Ijdcgditem;
import java.util.List;

public interface IjdcgditemDao {
  void insertBatch(List<Ijdcgditem> paramList);
  
  void delete(String paramString);
  
  List<Ijdcgditem> findbygjz(String paramString);
}


