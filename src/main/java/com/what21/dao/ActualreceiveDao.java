package com.what21.dao;

import com.what21.model.Actualreceive;
import com.what21.model.ActualreceiveCustom;
import com.what21.model.ActualreceiveQueryVo;
import com.what21.model.ActualreceiveSku;
import java.util.List;
import java.util.Map;

public interface ActualreceiveDao {
  List<Actualreceive> findAll(Map<String, Object> paramMap);
  
  List<ActualreceiveSku> findAllSku(Map<String, Object> paramMap);
  
  int countSku(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int actdelete(String paramString);
  
  List<Actualreceive> findact(Map<String, Object> paramMap);
  
  Actualreceive findByA(String paramString);
  
  void inserta(Actualreceive paramActualreceive);
  
  int actts(String paramString);
  
  int aflag(Actualreceive paramActualreceive);
  
  List<Actualreceive> findacId(String paramString);
  
  List<Actualreceive> actadd(Map<String, Object> paramMap);
  
  void aInserta(List<Actualreceive> paramList);
  
  int count2(ActualreceiveQueryVo paramActualreceiveQueryVo);
  
  List<ActualreceiveCustom> findAll2(ActualreceiveQueryVo paramActualreceiveQueryVo);
}


