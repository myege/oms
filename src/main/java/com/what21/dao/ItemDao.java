package com.what21.dao;

import com.what21.model.Item;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ItemDao {
  List<Item> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  List<Item> findId(Map<String, Object> paramMap);
  
  Item findById(String paramString);
  
  List<Item> dateTime(Map<String, Object> paramMap);
  
  int deleteItem(String paramString);
  
  int insert(Item paramItem);
  
  Item findBySku(Item paramItem);
  
  int updateItme(Item paramItem);
  
  void insertBatch(List<Item> paramList);
  
  List<Item> finImpWms();
  
  List<Item> findArray(String[] paramArrayOfString);
  
  void updateWmsType(List<Map<String, String>> paramList);
  
  int updateById(Item paramItem);
  
  int save(Item paramItem);
  
  List<Item> findgjz(@Param("gjz") String paramString);
  
  List<Item> findhzqd(@Param("gjz") String paramString);
  
  void updateWmsType2(@Param("gjz") int paramInt);
}


