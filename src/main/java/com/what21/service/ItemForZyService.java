package com.what21.service;

import com.what21.model.Item;
import com.what21.model.ItemForZy;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface ItemForZyService {
  List<ItemForZy> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int insert(ItemForZy paramItemForZy);
  
  List<ItemForZy> findId(Map<String, Object> paramMap);
  
  List<ItemForZy> condition(Map<String, Object> paramMap);
  
  List<ItemForZy> dateTime(Map<String, Object> paramMap);
  
  int deleteItem(String paramString);
  
  GeneralResult importOrder(String paramString, int paramInt);
  
  int updateItemForZy(ItemForZy paramItemForZy);
  
  Item findBySku(ItemForZy paramItemForZy);
  
  ItemForZy findByItemSku(String paramString);
  
  List<ItemForZy> findByIdToBG(int paramInt);
  
  List<ItemForZy> findByToBG(Map<String, Object> paramMap);
  
  GeneralResult importItem(String paramString);
}


