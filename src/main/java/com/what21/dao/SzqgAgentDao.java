package com.what21.dao;

import com.what21.model.Item;
import com.what21.model.SzqgAgent;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface SzqgAgentDao {
  List<SzqgAgent> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  List<Item> findId(Map<String, Object> paramMap);
  
  Item findById(String paramString);
  
  List<Item> dateTime(Map<String, Object> paramMap);
  
  int deleteItem(String paramString);
  
  int insert(Item paramItem);
  
  Item findBySku(Item paramItem);
  
  int updateItme(Item paramItem);
  
  void insertBatch(List<SzqgAgent> paramList);
  
  List<Item> finImpWms();
  
  List<Item> findArray(String[] paramArrayOfString);
  
  void updateWmsType(List<Map<String, String>> paramList);
  
  int updateById(Item paramItem);
  
  int save(Item paramItem);
  
  List<SzqgAgent> findgjz(@Param("gjz") String paramString);
  
  List<Item> findhzqd(@Param("gjz") String paramString);
  
  void updateWmsType2(@Param("gjz") int paramInt);
  
  void updatebygjz(@Param("sellerRecord") String paramString1, @Param("notes") String paramString2, @Param("status") String paramString3);
}


