package com.what21.dao;

import com.what21.model.CompanyZyAndOrderMail;
import com.what21.model.Companyforzy;
import com.what21.model.OrderMail;
import java.util.List;

public interface CompanyforzyDao {
  Companyforzy findByZy(String paramString);
  
  List<Companyforzy> findOver();
  
  List<CompanyZyAndOrderMail> findAllByCompanyCode(List<OrderMail> paramList);
}


