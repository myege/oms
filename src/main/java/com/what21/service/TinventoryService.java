package com.what21.service;

import com.what21.model.Tinventory;
import java.util.List;
import java.util.Map;

public interface TinventoryService {
  List<Tinventory> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int updateTotality(Tinventory paramTinventory);
  
  String findByPc(Tinventory paramTinventory);
  
  List<Tinventory> findBySku(Tinventory paramTinventory);
  
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
  
  int addBig(List<Tinventory> paramList);
  
  void updateBig(List<Tinventory> paramList);
  
  int findStorage(String paramString);
  
  String CreateInventory(String paramString) throws Exception;
}


