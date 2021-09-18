package com.what21.service;

import com.what21.model.GoodsAccept;
import java.util.List;

public interface GoodsAcceptService {
  void insert(GoodsAccept paramGoodsAccept);
  
  List<GoodsAccept> findByExpressNumber(String paramString);
}


