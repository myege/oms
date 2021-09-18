 package com.what21.service.impl;
 
 import com.what21.dao.TaxesDao;
 import com.what21.model.Taxes;
 import com.what21.service.TaxesService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class TaxesServiceImpl
   implements TaxesService
 {
   @Autowired
   private TaxesDao taxesDao;
   
   public List<Taxes> MByY(Map<String, Object> map) {
     return this.taxesDao.MByY(map);
   }
 
   
   public List<Taxes> SJByY(Map<String, Object> map) {
     return this.taxesDao.SJByY(map);
   }
 
   
   public List<Taxes> ddsj(Map<String, Object> map) {
     return this.taxesDao.ddsj(map);
   }
 
 
 
   
   public List<Taxes> ddfz(Map<String, Object> map) {
     return this.taxesDao.ddfz(map);
   }
 
   
   public void updatetjsj(Taxes taxes) {
     this.taxesDao.updatetjsj(taxes);
   }
 
 
   
   public List<Taxes> zsj(Map<String, Object> map) {
     return this.taxesDao.zsj(map);
   }
 
   
   public void updatesj(Taxes taxes) {
     this.taxesDao.updatesj(taxes);
   }
 }


