package com.what21.service;

import com.what21.model.ItemForGk;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface ItemForGKService {
  List<ItemForGk> findAll(Map<String, Object> paramMap);
  
  int insertItem(ItemForGk paramItemForGk);
  
  int count(Map<String, Object> paramMap);
  
  int deleteItem(int[] paramArrayOfint);
  
  ItemForGk queryOne(int paramInt);
  
  int updateForItem(ItemForGk paramItemForGk);
  
  GeneralResult importExcel(String paramString, int paramInt);
  
  List<ItemForGk> findByIDS(int[] paramArrayOfint);
  
  List<ItemForGk> findAllByEx();
  
  int findByItemSku(String paramString);
}


