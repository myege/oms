package com.what21.dao;

import com.what21.model.Inout;
import com.what21.model.InoutCustom;
import com.what21.model.InoutPageQueryVo;
import com.what21.model.LogInout;
import java.util.List;
import java.util.Map;

public interface InoutDao {
  List<Inout> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int indelete(String paramString);
  
  List<Inout> findin(Map<String, Object> paramMap);
  
  Inout findByI(String paramString);
  
  void inserti(Inout paramInout);
  
  int ints(String paramString);
  
  int inflag(Inout paramInout);
  
  List<Inout> findinId(String paramString);
  
  List<Inout> inadd();
  
  List<Inout> findByIds(Map<String, Object> paramMap);
  
  Inout findBySelect(Integer paramInteger);
  
  void insertLog(LogInout paramLogInout);
  
  int count2(InoutPageQueryVo paramInoutPageQueryVo);
  
  List<InoutCustom> findAll2(InoutPageQueryVo paramInoutPageQueryVo);
  
  List<Inout> findByPageQuery(InoutPageQueryVo paramInoutPageQueryVo);
}


