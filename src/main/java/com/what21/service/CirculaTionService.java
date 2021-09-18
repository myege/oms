package com.what21.service;

import com.what21.model.CirculaTion;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface CirculaTionService {
  int count(Map<String, Object> paramMap);
  
  List<CirculaTion> findAll(Map<String, Object> paramMap);
  
  int inserta(CirculaTion paramCirculaTion);
  
  int deletecir(String paramString);
  
  GeneralResult incir(String paramString, int paramInt);
  
  int insertc(CirculaTion paramCirculaTion);
  
  int insertb(CirculaTion paramCirculaTion);
  
  List<CirculaTion> findByBill(String paramString);
  
  List<CirculaTion> findcirAll(Map<String, Object> paramMap);
  
  int insertall(CirculaTion paramCirculaTion);
  
  int insertqd(CirculaTion paramCirculaTion);
  
  int upcirsb(CirculaTion paramCirculaTion);
  
  int insertaa(CirculaTion paramCirculaTion);
  
  int insciraa(CirculaTion paramCirculaTion);
}


