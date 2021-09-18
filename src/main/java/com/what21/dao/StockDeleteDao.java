package com.what21.dao;

import com.what21.model.StockDelete;
import com.what21.model.StockDeleteCustom;
import com.what21.model.StockDeleteQueryVo;
import java.util.List;
import java.util.Map;

public interface StockDeleteDao {
  List<StockDelete> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int delete(String paramString);
  
  List<StockDelete> sd(Map<String, Object> paramMap);
  
  int insert(StockDelete paramStockDelete);
  
  StockDelete findByStockDeleteId(String paramString);
  
  int pushSd(StockDelete paramStockDelete);
  
  StockDelete findByIdToSD(Integer paramInteger);
  
  int insertFlagSD(StockDelete paramStockDelete);
  
  void updateFlag(StockDelete paramStockDelete);
  
  int count2(StockDeleteQueryVo paramStockDeleteQueryVo);
  
  List<StockDeleteCustom> findAll2(StockDeleteQueryVo paramStockDeleteQueryVo);
}


