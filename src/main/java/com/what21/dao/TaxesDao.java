package com.what21.dao;

import com.what21.model.Taxes;
import java.util.List;
import java.util.Map;

public interface TaxesDao {
  List<Taxes> MByY(Map<String, Object> paramMap);
  
  List<Taxes> SJByY(Map<String, Object> paramMap);
  
  List<Taxes> ddsj(Map<String, Object> paramMap);
  
  void updatetjsj(Taxes paramTaxes);
  
  List<Taxes> ddfz(Map<String, Object> paramMap);
  
  List<Taxes> zsj(Map<String, Object> paramMap);
  
  void updatesj(Taxes paramTaxes);
}


