package com.what21.service;

import com.what21.model.EndCompany;
import java.util.List;
import java.util.Map;

public interface EndCompanyService {
  List<EndCompany> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  List<EndCompany> findCond(Map<String, Object> paramMap);
  
  int addCom(EndCompany paramEndCompany);
  
  int delete(int paramInt);
  
  int update(EndCompany paramEndCompany);
}


