package com.what21.service;

import com.what21.model.EndCompany;
import com.what21.model.End_waybill;
import java.util.List;
import java.util.Map;

public interface End_waybillService {
  List<End_waybill> getAll();
  
  List<End_waybill> findCompany(Map<String, Object> paramMap);
  
  int addEnd(End_waybill paramEnd_waybill);
  
  int count(Map<String, Object> paramMap);
  
  List<EndCompany> findCompany();
}


