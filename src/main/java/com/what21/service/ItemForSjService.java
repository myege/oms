package com.what21.service;

import com.what21.model.ItemForSj;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface ItemForSjService {
  List<ItemForSj> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  GeneralResult infor(String paramString, int paramInt);
  
  List<ItemForSj> findintfo(Map<String, Object> paramMap);
  
  ItemForSj findByInF(String paramString);
  
  void insertIn(ItemForSj paramItemForSj);
  
  int intfodelete(String paramString);
  
  int intfoedit(ItemForSj paramItemForSj);
  
  String ifsjsj(String paramString);
  
  List<ItemForSj> hyjStatus(String paramString);
  
  int updateStatus(String paramString);
  
  ItemForSj findById(String paramString);
  
  int updatefsj(ItemForSj paramItemForSj);
}


