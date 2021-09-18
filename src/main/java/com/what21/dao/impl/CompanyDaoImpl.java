 package com.what21.dao.impl;
 
 import com.what21.dao.CompanyDao;
 import com.what21.model.Company;
 import com.what21.model.CompanyQueryVo;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class CompanyDaoImpl
   implements CompanyDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public Company findByCompanyBm(String companyBm) {
     return ((CompanyDao)this.sqlSessionTemplate.getMapper(CompanyDao.class)).findByCompanyBm(companyBm);
   }
   
   public List<Company> findByCompany() {
     return ((CompanyDao)this.sqlSessionTemplate.getMapper(CompanyDao.class)).findByCompany();
   }
   
   public int count(CompanyQueryVo companyQueryVo) {
     return ((CompanyDao)this.sqlSessionTemplate.getMapper(CompanyDao.class)).count(companyQueryVo);
   }
   
   public List<Company> findAll(CompanyQueryVo companyQueryVo) {
     return ((CompanyDao)this.sqlSessionTemplate.getMapper(CompanyDao.class)).findAll(companyQueryVo);
   }
   
   public int save(Company company) {
     return ((CompanyDao)this.sqlSessionTemplate.getMapper(CompanyDao.class)).save(company);
   }
   
   public int updateById(Company company) {
     return ((CompanyDao)this.sqlSessionTemplate.getMapper(CompanyDao.class)).updateById(company);
   }
   
   public Company findById(Integer id) {
     return ((CompanyDao)this.sqlSessionTemplate.getMapper(CompanyDao.class)).findById(id);
   }
   
   public int changeAuto(Company company) {
     return ((CompanyDao)this.sqlSessionTemplate.getMapper(CompanyDao.class)).changeAuto(company);
   }
   
   public int deleteCompanyByIds(String[] ids) {
     return ((CompanyDao)this.sqlSessionTemplate.getMapper(CompanyDao.class)).deleteCompanyByIds(ids);
   }
   
   public List<Company> findCompanybmAndName() {
     return ((CompanyDao)this.sqlSessionTemplate.getMapper(CompanyDao.class)).findCompanybmAndName();
   }
   
   public Company findCompanyNameByCompanybm(String companybm) {
     return ((CompanyDao)this.sqlSessionTemplate.getMapper(CompanyDao.class)).findCompanyNameByCompanybm(companybm);
   }
 }


