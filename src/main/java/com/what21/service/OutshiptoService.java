package com.what21.service;

import com.what21.model.Outshipto;
import com.what21.util.GeneralResult;
import java.util.List;

public interface OutshiptoService {
  List<Outshipto> findAll(Outshipto paramOutshipto);
  
  List<Outshipto> findId(String paramString);
  
  int findCount(Outshipto paramOutshipto);
  
  void delete(String paramString);
  
  void deleteSku(String paramString);
  
  int update(Outshipto paramOutshipto);
  
  void updateType(Outshipto paramOutshipto);
  
  GeneralResult implShip(String paramString);
  
  String geviShiup(String paramString) throws Exception;
}


