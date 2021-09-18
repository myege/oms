package com.what21.service;

import com.what21.model.ChangeOdd;
import com.what21.model.GoodsChangeodd;
import java.util.List;

public interface GoodsChangeoddService {
  void insertand(GoodsChangeodd paramGoodsChangeodd);
  
  GoodsChangeodd findByExpressNumber(String paramString);
  
  List<GoodsChangeodd> findExpressNumber(String paramString);
  
  ChangeOdd updatecod(String paramString);
  
  void deleteByPids(int[] paramArrayOfint);
  
  void insertAlls(List<GoodsChangeodd> paramList);
}


