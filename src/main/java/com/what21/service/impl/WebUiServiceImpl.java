 package com.what21.service.impl;
 
 import com.what21.dao.WebUiDao;
 import com.what21.model.WebUi;
 import com.what21.service.WebUiService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 @Service
 public class WebUiServiceImpl
   implements WebUiService
 {
   @Autowired
   WebUiDao webUiDao;
   
   public List<WebUi> getAboutusMx() {
     return this.webUiDao.getAboutusMx();
   }
 
 
   
   public List<WebUi> findAll(WebUi webUi) {
     return this.webUiDao.findAll(webUi);
   }
 
 
   
   public Integer count(WebUi webUi) {
     return this.webUiDao.count(webUi);
   }
 
 
   
   public List<WebUi> findByCode(String code) {
     return this.webUiDao.findByCode(code);
   }
 
 
   
   public void update(List<WebUi> obj) {
     this.webUiDao.update(obj);
   }
 
 
 
   
   public List<WebUi> findByBegCode(String string) {
     return this.webUiDao.findByBegCode(string);
   }
 
   
   public List<WebUi> findByCodeThree(int id) {
     return this.webUiDao.findByCodeThree(id);
   }
 }


