package com.what21.service;

import com.what21.model.Materiel;
import java.util.List;
import java.util.Map;

public interface MaterielService {
  List<Materiel> findAll(Map<String, Object> paramMap);
  
  int Mateadd(Materiel paramMateriel);
  
  int count(Map<String, Object> paramMap);
  
  int Matedelete(String paramString);
  
  List<Materiel> mTime(Map<String, Object> paramMap);
  
  List<Materiel> inquiry(Map<String, Object> paramMap);
  
  List<Materiel> validate(Map<String, Object> paramMap);
  
  List<Materiel> port(Map<String, Object> paramMap);
  
  List<Materiel> findByIdMateriel(Integer paramInteger);
  
  int pullCommotidie() throws Exception;
  
  int updateInventory() throws Exception;
  
  int updateTotality(Materiel paramMateriel);
}


