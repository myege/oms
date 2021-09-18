package com.what21.service;

import com.what21.model.Winventory;
import com.what21.model.WinventoryQueryVo;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface WinventoryService {
  List<Winventory> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int wdelete(String paramString);
  
  List<Winventory> findw(Map<String, Object> paramMap);
  
  GeneralResult inmerw(String paramString, int paramInt);
  
  String wints();
  
  int wflag(Winventory paramWinventory);
  
  List<Winventory> findwId(String paramString);
  
  List<Winventory> wadd();
  
  int count2(WinventoryQueryVo paramWinventoryQueryVo);
  
  List<Winventory> findAll2(WinventoryQueryVo paramWinventoryQueryVo);
}


