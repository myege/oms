package com.what21.dao;

import com.what21.model.Item;
import com.what21.model.ItemForZy;
import java.util.List;
import java.util.Map;

public interface ItemForZyDao {
  List<ItemForZy> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  List<ItemForZy> findId(Map<String, Object> paramMap);
  
  List<ItemForZy> dateTime(Map<String, Object> paramMap);
  
  int deleteItem(String paramString);
  
  int insert(ItemForZy paramItemForZy);
  
  int updateItemForZy(ItemForZy paramItemForZy);
  
  Item findBySku(ItemForZy paramItemForZy);
  
  ItemForZy findByItemSku(String paramString);
  
  List<String> findByItemSkuList(List<String> paramList);
  
  List<ItemForZy> findByIdToBG(int paramInt);
  
  List<ItemForZy> findByToBG(Map<String, Object> paramMap);
  
  void insertBatch(List<ItemForZy> paramList);
}


