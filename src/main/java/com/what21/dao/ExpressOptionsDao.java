package com.what21.dao;

import com.what21.model.ExpressOptions;
import java.util.List;

public interface ExpressOptionsDao {
  ExpressOptions findByKey(String paramString);
  
  void insert(ExpressOptions paramExpressOptions);
  
  void updateByKey(ExpressOptions paramExpressOptions);
  
  List<ExpressOptions> selectList();
}


