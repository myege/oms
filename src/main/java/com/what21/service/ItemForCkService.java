package com.what21.service;

import com.what21.model.Item;
import com.what21.model.ItemForCk;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface ItemForCkService {
  List<ItemForCk> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int insert(ItemForCk paramItemForCk);
  
  List<ItemForCk> findId(Map<String, Object> paramMap);
  
  List<ItemForCk> condition(Map<String, Object> paramMap);
  
  List<ItemForCk> dateTime(Map<String, Object> paramMap);
  
  int deleteItem(String paramString);
  
  GeneralResult importOrder(String paramString, int paramInt);
  
  int updateItemForCk(ItemForCk paramItemForCk);
  
  Item findBySku(ItemForCk paramItemForCk);
  
  ItemForCk findByItemSku(String paramString);
  
  List<ItemForCk> findByIdToBG(int paramInt);
  
  List<ItemForCk> findByToBG(Map<String, Object> paramMap);
  
  GeneralResult importItem(String paramString);
}


