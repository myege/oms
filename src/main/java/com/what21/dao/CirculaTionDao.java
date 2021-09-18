package com.what21.dao;

import com.what21.model.CirculaTion;
import java.util.List;
import java.util.Map;

public interface CirculaTionDao {
  int count(Map<String, Object> paramMap);
  
  List<CirculaTion> findAll(Map<String, Object> paramMap);
  
  int inserta(CirculaTion paramCirculaTion);
  
  int deletecir(String paramString);
  
  CirculaTion findBycir(String paramString);
  
  void incir(CirculaTion paramCirculaTion);
  
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


