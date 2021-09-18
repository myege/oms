package com.what21.dao;

import com.what21.model.ItemForSj;
import java.util.List;
import java.util.Map;

public interface ItemForSjDao {
  List<ItemForSj> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  List<ItemForSj> findintfo(Map<String, Object> paramMap);
  
  ItemForSj findByInF(String paramString);
  
  void insertIn(ItemForSj paramItemForSj);
  
  int intfodelete(String paramString);
  
  int intfoedit(ItemForSj paramItemForSj);
  
  String ifsjsj(String paramString);
  
  void infflag(ItemForSj paramItemForSj);
  
  List<ItemForSj> ifadd();
  
  List<ItemForSj> hyjStatus(String paramString);
  
  int updateStatus(String paramString);
  
  ItemForSj findById(String paramString);
  
  int updatefsj(ItemForSj paramItemForSj);
}


