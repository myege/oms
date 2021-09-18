package com.what21.dao;

import com.what21.model.Item;
import com.what21.model.SzqgOrder;
import com.what21.model.SzqgOrderExpot;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface SzqgOrderDao {
  List<SzqgOrder> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  List<Item> findId(Map<String, Object> paramMap);
  
  Item findById(String paramString);
  
  List<Item> dateTime(Map<String, Object> paramMap);
  
  int deleteItem(String paramString);
  
  int insert(Item paramItem);
  
  List<SzqgOrder> findBySku(SzqgOrder paramSzqgOrder);
  
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
  
  SzqgOrder findByIdToBG(Integer paramInteger);
  
  SzqgOrder findByOrderNo(String paramString);
  
  void updatebygjz(@Param("sellerRecord") String paramString1, @Param("notes") String paramString2, @Param("status") String paramString3);
  
  void updateAtB(@Param("A") String paramString1, @Param("B") String paramString2);
  
  int batchInsert(List<SzqgOrder> paramList);
  
  int updateAuditstatus3(SzqgOrder paramSzqgOrder);
  
  int updateYdzt(SzqgOrder paramSzqgOrder);
  
  List<SzqgOrderExpot> exportszqg(SzqgOrderExpot paramSzqgOrderExpot);
}


