package com.what21.dao;

import com.what21.model.Ijdtditem;
import java.util.List;

public interface IjdtditemDao {
  void insertBatch(List<Ijdtditem> paramList);
  
  void delete(String paramString);
  
  List<Ijdtditem> findbygjz(String paramString);
}


