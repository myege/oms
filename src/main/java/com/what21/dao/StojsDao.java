package com.what21.dao;

import com.what21.model.PWC;
import com.what21.model.Stojs;
import com.what21.model.StojsBb;
import com.what21.model.StojsQd;
import com.what21.model.StojsQueryVo;
import java.util.List;

public interface StojsDao {
  List<Stojs> findAll(StojsQueryVo paramStojsQueryVo);
  
  Integer count(StojsQueryVo paramStojsQueryVo);
  
  void deleteQdsByIds(Integer[] paramArrayOfInteger);
  
  void deleteByIds(Integer[] paramArrayOfInteger);
  
  void insert(Stojs paramStojs);
  
  List<StojsQd> findAllQd(StojsQueryVo paramStojsQueryVo);
  
  Integer countQd(StojsQueryVo paramStojsQueryVo);
  
  Stojs findById(Integer paramInteger);
  
  Integer countByMailNo(String paramString);
  
  void insertQdBatch(List<StojsQd> paramList);
  
  void updateQdztByIds(List<Integer> paramList);
  
  List<PWC> getPWCs1(Integer paramInteger);
  
  List<PWC> getPWCs2(Integer paramInteger);
  
  List<PWC> getPWCs3(Integer paramInteger);
  
  void inserBbBatch(List<StojsBb> paramList);
  
  void updateBbztById(Integer paramInteger);
  
  void deleteBbsByIds(Integer[] paramArrayOfInteger);
  
  Integer countBb(StojsQueryVo paramStojsQueryVo);
  
  List<StojsBb> findAllBb(StojsQueryVo paramStojsQueryVo);
  
  PWC getPWCs1NotGroup(Integer paramInteger);
  
  PWC getPWCs2NotGroup(Integer paramInteger);
  
  PWC getPWCs3NotGroup(Integer paramInteger);
  
  PWC getPWCs4NotGroup(Integer paramInteger);
  
  PWC getPWCs5NotGroup(Integer paramInteger);
  
  void updateQdztById(Integer paramInteger);
  
  List<PWC> getPWCs4(Integer paramInteger);
  
  List<PWC> getPWCs5(Integer paramInteger);
}


