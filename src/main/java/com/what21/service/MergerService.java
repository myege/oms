package com.what21.service;

import com.what21.model.Merger;
import com.what21.model.MergerCustom;
import com.what21.model.MergerQueryVo;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface MergerService {
  List<Merger> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int merdelete(String paramString);
  
  List<Merger> cx(Map<String, Object> paramMap);
  
  GeneralResult inmerm(String paramString, int paramInt);
  
  int mets(String paramString);
  
  Merger meadd(Integer paramInteger);
  
  int mflag(Merger paramMerger);
  
  int count2(MergerQueryVo paramMergerQueryVo);
  
  List<MergerCustom> findAll2(MergerQueryVo paramMergerQueryVo);
}


