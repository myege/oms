package com.what21.dao;

import com.what21.model.Item;
import com.what21.model.ItemForCk;
import java.util.List;
import java.util.Map;

public interface ItemForCkDao {
  List<ItemForCk> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  List<ItemForCk> findId(Map<String, Object> paramMap);
  
  List<ItemForCk> dateTime(Map<String, Object> paramMap);
  
  int deleteItem(String paramString);
  
  int insert(ItemForCk paramItemForCk);
  
  int updateItemForCk(ItemForCk paramItemForCk);
  
  Item findBySku(ItemForCk paramItemForCk);
  
  ItemForCk findByItemSku(String paramString);
  
  List<String> findByItemSkuList(List<String> paramList);
  
  List<ItemForCk> findByIdToBG(int paramInt);
  
  List<ItemForCk> findByToBG(Map<String, Object> paramMap);
  
  void insertBatch(List<ItemForCk> paramList);
}


