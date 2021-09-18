package com.what21.service;

import com.what21.model.Ijdlhd;
import java.util.List;
import java.util.Map;

public interface IjdlhdService {
  List<Ijdlhd> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
}


