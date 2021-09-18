package com.what21.service;

import com.what21.model.StockDelete;
import com.what21.model.StockDeleteCustom;
import com.what21.model.StockDeleteQueryVo;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface StockDeleteService {
  List<StockDelete> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int delete(String paramString);
  
  List<StockDelete> sd(Map<String, Object> paramMap);
  
  GeneralResult importSd(String paramString, int paramInt);
  
  int pushSd(String paramString);
  
  int count2(StockDeleteQueryVo paramStockDeleteQueryVo);
  
  List<StockDeleteCustom> findAll2(StockDeleteQueryVo paramStockDeleteQueryVo);
}


