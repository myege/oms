package com.what21.dao;

import com.what21.model.Merger;
import com.what21.model.MergerCustom;
import com.what21.model.MergerQueryVo;
import java.util.List;
import java.util.Map;

public interface MergerDao {
  List<Merger> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int merdelete(String paramString);
  
  List<Merger> cx(Map<String, Object> paramMap);
  
  void insert1(Merger paramMerger);
  
  Merger findByid(String paramString);
  
  int mets(String paramString);
  
  Merger meadd(Integer paramInteger);
  
  int mflag(Merger paramMerger);
  
  int count2(MergerQueryVo paramMergerQueryVo);
  
  List<MergerCustom> findAll2(MergerQueryVo paramMergerQueryVo);
}


