package com.what21.service;

import com.what21.model.ExpressOptions;
import java.util.List;
import java.util.Map;

public interface ExpressOptionsService {
  void save(Map<String, String> paramMap);
  
  List<ExpressOptions> selectList();
}


