 package com.what21.service.impl;
 
 import com.what21.dao.CompanyforzyDao;
 import com.what21.model.CompanyZyAndOrderMail;
 import com.what21.model.OrderMail;
 import com.what21.service.CompanyforzyService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class CompanyforzyServiceImpl
   implements CompanyforzyService
 {
   @Autowired
   CompanyforzyDao dao;
   
   public List<CompanyZyAndOrderMail> findAllByCompanyCode(List<OrderMail> list) {
     return this.dao.findAllByCompanyCode(list);
   }
 }


