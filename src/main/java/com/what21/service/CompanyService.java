package com.what21.service;

import com.what21.model.Company;
import com.what21.model.CompanyQueryVo;
import java.util.List;

public interface CompanyService {
  int count(CompanyQueryVo paramCompanyQueryVo);
  
  List<Company> findAll(CompanyQueryVo paramCompanyQueryVo);
  
  int save(Company paramCompany);
  
  int updateById(Company paramCompany);
  
  Company findById(Integer paramInteger);
  
  int changeAuto(Company paramCompany);
  
  int deleteCompanyByIds(String[] paramArrayOfString);
  
  List<Company> findCompanybmAndName();
  
  Company findCompanyNameByCompanybm(String paramString);
}


