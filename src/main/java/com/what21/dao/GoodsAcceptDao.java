package com.what21.dao;

import com.what21.model.GoodsAccept;
import java.util.List;

public interface GoodsAcceptDao {
  int insert(GoodsAccept paramGoodsAccept);
  
  List<GoodsAccept> findByExpressNumber(String paramString);
  
  int delete(String[] paramArrayOfString);
}


