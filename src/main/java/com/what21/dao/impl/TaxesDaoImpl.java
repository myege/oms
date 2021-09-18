 package com.what21.dao.impl;
 
 import com.what21.dao.TaxesDao;
 import com.what21.model.Taxes;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class TaxesDaoImpl
   implements TaxesDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<Taxes> MByY(Map<String, Object> map) {
     return ((TaxesDao)this.sqlSessionTemplate.getMapper(TaxesDao.class)).MByY(map);
   }
 
   
   public List<Taxes> SJByY(Map<String, Object> map) {
     return ((TaxesDao)this.sqlSessionTemplate.getMapper(TaxesDao.class)).SJByY(map);
   }
 
   
   public List<Taxes> ddsj(Map<String, Object> map) {
     return ((TaxesDao)this.sqlSessionTemplate.getMapper(TaxesDao.class)).ddsj(map);
   }
 
 
   
   public void updatetjsj(Taxes taxes) {
     ((TaxesDao)this.sqlSessionTemplate.getMapper(TaxesDao.class)).updatetjsj(taxes);
   }
 
   
   public List<Taxes> ddfz(Map<String, Object> map) {
     return ((TaxesDao)this.sqlSessionTemplate.getMapper(TaxesDao.class)).ddfz(map);
   }
 
   
   public List<Taxes> zsj(Map<String, Object> map) {
     return ((TaxesDao)this.sqlSessionTemplate.getMapper(TaxesDao.class)).zsj(map);
   }
 
   
   public void updatesj(Taxes taxes) {
     ((TaxesDao)this.sqlSessionTemplate.getMapper(TaxesDao.class)).updatesj(taxes);
   }
 }


