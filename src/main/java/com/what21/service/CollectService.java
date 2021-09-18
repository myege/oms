package com.what21.service;

import com.what21.model.Collect;
import com.what21.model.CollectQueryVo;
import java.util.List;

public interface CollectService {
  List<Collect> findAll(CollectQueryVo paramCollectQueryVo);
  
  int count(CollectQueryVo paramCollectQueryVo);
  
  void insert(Collect paramCollect);
  
  void delete(int paramInt);
  
  Collect findById(int paramInt);
  
  int countByTydh(String paramString);
  
  void update(Collect paramCollect);
  
  Collect findByTydh(String paramString);
  
  List<Collect> findAllBs(CollectQueryVo paramCollectQueryVo);
  
  int countBs(CollectQueryVo paramCollectQueryVo);
  
  void insertBsjs(CollectQueryVo paramCollectQueryVo);
  
  List<Collect> findBySjAndSj2(Collect paramCollect);
  
  int countBySjAndSj2(Collect paramCollect);
  
  Collect findByYwbh(String paramString);
  
  void deleteByIds(int[] paramArrayOfint);
}


