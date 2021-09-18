package com.what21.dao;

import com.what21.model.ChangeOdd;
import com.what21.model.GoodsChangeodd;
import java.util.List;

public interface GoodsChangeoddDao {
  void insertand(GoodsChangeodd paramGoodsChangeodd);
  
  List<GoodsChangeodd> findExpressNumber(String paramString);
  
  ChangeOdd updatecod(String paramString);
  
  GoodsChangeodd findByExpressNumber(String paramString);
  
  void deleteByPids(int[] paramArrayOfint);
  
  void insertAlls(List<GoodsChangeodd> paramList);
}


