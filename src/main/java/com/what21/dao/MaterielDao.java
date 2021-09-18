package com.what21.dao;

import com.what21.model.Materiel;
import com.what21.model.OrderCommoditie;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface MaterielDao {
  List<Materiel> findAll(Map<String, Object> paramMap);
  
  int Mateadd(Materiel paramMateriel);
  
  int count(Map<String, Object> paramMap);
  
  int Matedelete(String paramString);
  
  List<Materiel> mTime(Map<String, Object> paramMap);
  
  List<Materiel> inquiry(Map<String, Object> paramMap);
  
  List<Materiel> validate(Map<String, Object> paramMap);
  
  List<Materiel> port(Map<String, Object> paramMap);
  
  List<Materiel> findByIdMateriel(Integer paramInteger);
  
  void insertOrderCommoditie(@Param("list") List<OrderCommoditie> paramList);
  
  List<OrderCommoditie> findInventory();
  
  int updateInventory(OrderCommoditie paramOrderCommoditie);
  
  int updateTotality(Materiel paramMateriel);
}


