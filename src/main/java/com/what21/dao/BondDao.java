package com.what21.dao;

import com.what21.model.Bond;
import java.util.List;
import java.util.Map;

public interface BondDao {
  List<Bond> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  List<Bond> findBond();
  
  Bond getTax(Bond paramBond);
  
  List<Bond> findbond(Map<String, Object> paramMap);
  
  int bonddelete(String paramString);
  
  int bondedit(Bond paramBond);
  
  int addbond(Bond paramBond);
  
  int addlogf(Bond paramBond);
}


