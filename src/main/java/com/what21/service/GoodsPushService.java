package com.what21.service;

import com.what21.model.GoodsPush;
import java.util.List;

public interface GoodsPushService {
  void insert(GoodsPush paramGoodsPush);
  
  List<GoodsPush> findByExpressNumber(String paramString);
}


