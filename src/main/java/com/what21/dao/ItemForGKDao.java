package com.what21.dao;

import com.what21.model.ItemForGk;
import java.util.List;
import java.util.Map;

public interface ItemForGKDao {
  List<ItemForGk> findAll(Map<String, Object> paramMap);
  
  int insertItem(ItemForGk paramItemForGk);
  
  int count(Map<String, Object> paramMap);
  
  int deleteItem(int[] paramArrayOfint);
  
  int updateForItem(ItemForGk paramItemForGk);
  
  int findByItemSku(String paramString);
  
  void insertItems(List<ItemForGk> paramList);
  
  List<ItemForGk> findByIDS(int[] paramArrayOfint);
  
  List<ItemForGk> findAllByEx();
}


