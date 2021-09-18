 package com.what21.service.impl;
 
 import com.what21.dao.CompanyDao;
 import com.what21.model.Company;
 import com.what21.model.CompanyQueryVo;
 import com.what21.service.CompanyService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class CompanyServiceImpl
   implements CompanyService
 {
   @Autowired
   private CompanyDao companyDao;
   
   public int count(CompanyQueryVo companyQueryVo) {
     return this.companyDao.count(companyQueryVo);
   }
 
   
   public List<Company> findAll(CompanyQueryVo companyQueryVo) {
     return this.companyDao.findAll(companyQueryVo);
   }
 
   
   public int save(Company company) {
     return this.companyDao.save(company);
   }
 
   
   public int updateById(Company company) {
     return this.companyDao.updateById(company);
   }
 
   
   public Company findById(Integer id) {
     return this.companyDao.findById(id);
   }
 
   
   public int changeAuto(Company company) {
     return this.companyDao.changeAuto(company);
   }
 
   
   public int deleteCompanyByIds(String[] ids) {
     return this.companyDao.deleteCompanyByIds(ids);
   }
 
   
   public List<Company> findCompanybmAndName() {
     return this.companyDao.findCompanybmAndName();
   }
 
   
   public Company findCompanyNameByCompanybm(String companybm) {
     return this.companyDao.findCompanyNameByCompanybm(companybm);
   }
 }


