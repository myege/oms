package com.what21.service;

import com.what21.model.Actualreceive;
import com.what21.model.ActualreceiveCustom;
import com.what21.model.ActualreceiveQueryVo;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface ActualreceiveService {
  List<Actualreceive> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int countSku(Map<String, Object> paramMap);
  
  int actdelete(String paramString);
  
  List<Actualreceive> findact(Map<String, Object> paramMap);
  
  GeneralResult inmera(String paramString, int paramInt);
  
  String actts(String paramString);
  
  int aflag(Actualreceive paramActualreceive);
  
  List<Actualreceive> findacId(String paramString);
  
  List<Actualreceive> actadd(Map<String, Object> paramMap);
  
  int count2(ActualreceiveQueryVo paramActualreceiveQueryVo);
  
  List<ActualreceiveCustom> findAll2(ActualreceiveQueryVo paramActualreceiveQueryVo);
}


