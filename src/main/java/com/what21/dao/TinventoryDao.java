package com.what21.dao;

import com.what21.model.Tinventory;
import java.util.List;
import java.util.Map;

public interface TinventoryDao {
  List<Tinventory> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int updateTotality(Tinventory paramTinventory);
  
  List<Tinventory> findBySku(Tinventory paramTinventory);
  
  String findByPc(Tinventory paramTinventory);
  
  List<Tinventory> dateTime(Map<String, Object> paramMap);
  
  List<Tinventory> findeXport(Map<String, Object> paramMap);
  
  List<Tinventory> findByIdTinventory(Integer paramInteger);
  
  int updateTinventory(Tinventory paramTinventory);
  
  Tinventory findLR(Tinventory paramTinventory);
  
  int updateLR(Tinventory paramTinventory);
  
  int addTinventory(Tinventory paramTinventory);
  
  List<Tinventory> getAll(String paramString);
  
  int makeOver(Tinventory paramTinventory);
  
  Tinventory findSkuAndCode(Map<String, Object> paramMap);
  
  int upSurplus(Tinventory paramTinventory);
  
  Integer findSumBySku(Tinventory paramTinventory);
  
  void updatePreUsed(Tinventory paramTinventory);
  
  int addBig(List<Tinventory> paramList);
  
  void updateBig(List<Tinventory> paramList);
  
  int findStroage(String paramString);
  
  List<Tinventory> findInventory(String paramString1, String paramString2);
  
  void updateInventory(Tinventory paramTinventory);
}


