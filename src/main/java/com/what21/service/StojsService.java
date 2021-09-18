package com.what21.service;

import com.what21.model.PWC;
import com.what21.model.Stojs;
import com.what21.model.StojsBb;
import com.what21.model.StojsQd;
import com.what21.model.StojsQueryVo;
import com.what21.util.GeneralResult;
import java.util.List;

public interface StojsService {
  List<Stojs> findAll(StojsQueryVo paramStojsQueryVo);
  
  Integer count(StojsQueryVo paramStojsQueryVo);
  
  List<StojsQd> findAllQd(StojsQueryVo paramStojsQueryVo);
  
  Integer countQd(StojsQueryVo paramStojsQueryVo);
  
  void insert(Stojs paramStojs);
  
  void deleteByIds(Integer[] paramArrayOfInteger);
  
  GeneralResult importOrderNew(Integer paramInteger, String paramString);
  
  void geneBb(Integer paramInteger);
  
  Integer countBb(StojsQueryVo paramStojsQueryVo);
  
  List<StojsBb> findAllBb(StojsQueryVo paramStojsQueryVo);
  
  PWC getPWCs1NotGroup(Integer paramInteger);
  
  PWC getPWCs2NotGroup(Integer paramInteger);
  
  PWC getPWCs3NotGroup(Integer paramInteger);
  
  PWC getPWCs4NotGroup(Integer paramInteger);
  
  PWC getPWCs5NotGroup(Integer paramInteger);
}


