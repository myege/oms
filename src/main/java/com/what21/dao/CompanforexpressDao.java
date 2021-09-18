package com.what21.dao;

import com.what21.model.Companforexpress;
import java.util.List;

public interface CompanforexpressDao {
  List<Companforexpress> findAll();
  
  Companforexpress findByBm(String paramString);
}


