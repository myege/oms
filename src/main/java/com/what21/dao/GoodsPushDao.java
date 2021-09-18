package com.what21.dao;

import com.what21.model.GoodsPush;
import java.util.List;

public interface GoodsPushDao {
  void insert(GoodsPush paramGoodsPush);
  
  List<GoodsPush> findByExpressNumber(String paramString);
}


