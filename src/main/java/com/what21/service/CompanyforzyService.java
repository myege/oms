package com.what21.service;

import com.what21.model.CompanyZyAndOrderMail;
import com.what21.model.OrderMail;
import java.util.List;

public interface CompanyforzyService {
  List<CompanyZyAndOrderMail> findAllByCompanyCode(List<OrderMail> paramList);
}


