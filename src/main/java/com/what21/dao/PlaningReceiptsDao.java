package com.what21.dao;

import com.what21.model.PlaningReceipts;
import com.what21.model.PlaningReceiptsQueryVo;
import java.util.List;
import java.util.Map;

public interface PlaningReceiptsDao {
  List<PlaningReceipts> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int delete(String paramString);
  
  List<PlaningReceipts> pr(Map<String, Object> paramMap);
  
  int insert(PlaningReceipts paramPlaningReceipts);
  
  PlaningReceipts findByPlaningReceiptsId(String paramString);
  
  int pushPr(PlaningReceipts paramPlaningReceipts);
  
  PlaningReceipts findByIdToPR(Integer paramInteger);
  
  int insertFlag(PlaningReceipts paramPlaningReceipts);
  
  int count2(PlaningReceiptsQueryVo paramPlaningReceiptsQueryVo);
  
  List<PlaningReceipts> findAll2(PlaningReceiptsQueryVo paramPlaningReceiptsQueryVo);
}


