package com.what21.service;

import com.what21.model.PlaningReceipts;
import com.what21.model.PlaningReceiptsQueryVo;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface PlaningReceiptsService {
  List<PlaningReceipts> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int delete(String paramString);
  
  List<PlaningReceipts> pr(Map<String, Object> paramMap);
  
  GeneralResult importPr(String paramString, int paramInt);
  
  String pushPr(String paramString);
  
  int count2(PlaningReceiptsQueryVo paramPlaningReceiptsQueryVo);
  
  List<PlaningReceipts> findAll2(PlaningReceiptsQueryVo paramPlaningReceiptsQueryVo);
}


