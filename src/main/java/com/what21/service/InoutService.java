package com.what21.service;

import com.what21.model.Inout;
import com.what21.model.InoutCustom;
import com.what21.model.InoutPageQueryVo;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface InoutService {
  List<Inout> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int indelete(String paramString);
  
  List<Inout> findin(Map<String, Object> paramMap);
  
  GeneralResult inmeri(String paramString, int paramInt);
  
  String ints(int paramInt);
  
  int inflag(Inout paramInout);
  
  List<Inout> findinId(String paramString);
  
  List<Inout> inadd();
  
  List<Inout> findByIds(Map<String, Object> paramMap);
  
  Inout findBySelect(Integer paramInteger);
  
  int count2(InoutPageQueryVo paramInoutPageQueryVo);
  
  List<InoutCustom> findAll2(InoutPageQueryVo paramInoutPageQueryVo);
  
  List<Inout> findByPageQuery(InoutPageQueryVo paramInoutPageQueryVo);
}


