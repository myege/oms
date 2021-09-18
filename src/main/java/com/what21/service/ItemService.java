package com.what21.service;

import com.what21.model.Item;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface ItemService {
  List<Item> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int insert(Item paramItem);
  
  List<Item> findId(Map<String, Object> paramMap);
  
  Item findById(String paramString);
  
  List<Item> condition(Map<String, Object> paramMap);
  
  List<Item> dateTime(Map<String, Object> paramMap);
  
  int deleteItem(String paramString);
  
  GeneralResult importOrder(String paramString, int paramInt);
  
  Item findBySku(Item paramItem);
  
  int updateItme(Item paramItem);
  
  GeneralResult importItem(int paramInt, String paramString);
  
  List<Item> finImpWms();
  
  List<Item> findArray(String[] paramArrayOfString);
  
  void updateWmsType(List<Map<String, String>> paramList);
  
  int updateById(Item paramItem);
  
  int save(Item paramItem);
  
  String CreateItem(String paramString) throws Exception;
}


