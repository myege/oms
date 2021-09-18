package com.what21.dao;

import com.what21.model.Item;
import com.what21.model.SzqgItem;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface SzqgItemDao {
  List<SzqgItem> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  List<Item> findId(Map<String, Object> paramMap);
  
  Item findById(String paramString);
  
  List<Item> dateTime(Map<String, Object> paramMap);
  
  int deleteItem(String paramString);
  
  int insert(Item paramItem);
  
  List<SzqgItem> findBySku(SzqgItem paramSzqgItem);
  
  int updateItme(Item paramItem);
  
  void insertBatch(List<SzqgItem> paramList);
  
  List<Item> finImpWms();
  
  List<Item> findArray(String[] paramArrayOfString);
  
  void updateWmsType(List<Map<String, String>> paramList);
  
  int updateById(Item paramItem);
  
  int save(Item paramItem);
  
  List<SzqgItem> findgjz(@Param("gjz") String paramString);
  
  List<SzqgItem> findgjzforcd(@Param("gjz") String paramString);
  
  List<SzqgItem> findgjzforcd2(@Param("gjz") String paramString);
  
  List<Item> findhzqd(@Param("gjz") String paramString);
  
  void updateWmsType2(@Param("gjz") int paramInt);
  
  void updatebygjz(@Param("sellerRecord") String paramString1, @Param("notes") String paramString2, @Param("status") String paramString3);
  
  int batchInsert(List<SzqgItem> paramList);
}


