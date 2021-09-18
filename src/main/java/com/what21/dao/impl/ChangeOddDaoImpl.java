 package com.what21.dao.impl;
 
 import com.what21.dao.ChangeOddDao;
 import com.what21.model.ChangeOdd;
 import com.what21.model.ExportChangeOdd;
 import com.what21.model.OrderMail;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class ChangeOddDaoImpl
   implements ChangeOddDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<ChangeOdd> findAll(Map<String, Object> paramMap) {
     return ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).findAll(paramMap);
   }
 
   
   public int count(Map<String, Object> map) {
     return ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).count(map);
   }
 
 
 
   
   public void insert(ChangeOdd changeOdd) {
     ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).insert(changeOdd);
   }
 
   
   public ChangeOdd findById(int paramInt) {
     return ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).findById(paramInt);
   }
 
 
   
   public void update(String paramString) {
     ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).update(paramString);
   }
 
   
   public List<ChangeOdd> findExpressNumber(Map<String, Object> paramMap) {
     return ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).findExpressNumber(paramMap);
   }
 
   
   public int updates(String paramString) {
     return ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).updates(paramString);
   }
 
 
 
   
   public List<ChangeOdd> findexpn(Map<String, Object> pageMap) {
     return ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).findexpn(pageMap);
   }
 
 
   
   public void updatePrintType(String intValue) {
     ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).updatePrintType(intValue);
   }
 
 
   
   public ChangeOdd updateepn(String expressNumber) {
     return ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).updateepn(expressNumber);
   }
 
   
   public List<ChangeOdd> allfind() {
     return ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).allfind();
   }
 
   
   public void insertand(ChangeOdd changeOdd) {
     ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).insertand(changeOdd);
   }
 
 
   
   public void updatechangeodd(ChangeOdd changeOdd) {
     ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).updatechangeodd(changeOdd);
   }
 
 
   
   public void deleteByIds(int[] ids) {
     ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).deleteByIds(ids);
   }
 
 
   
   public List<ExportChangeOdd> exportByIds(int[] ids) {
     return ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).exportByIds(ids);
   }
 
   
   public List<ExportChangeOdd> exportByCons(ExportChangeOdd exportChangeOdd2) {
     return ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).exportByCons(exportChangeOdd2);
   }
 
 
   
   public int inserts(List<ChangeOdd> change) {
     return ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).inserts(change);
   }
 
 
   
   public int insert_int(ChangeOdd changeOdd) {
     return ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).insert_int(changeOdd);
   }
 
 
   
   public List<ChangeOdd> findByExpressNumberCount(List<OrderMail> o) {
     return ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).findByExpressNumberCount(o);
   }
 
 
   
   public void deleteById(int id) {
     ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).deleteById(id);
   }
 
 
   
   public ChangeOdd findByExpressNumber(String paremString) {
     return ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).findByExpressNumber(paremString);
   }
 
 
   
   public List<ChangeOdd> findByExpressNumberForAlls(List<ChangeOdd> list) {
     return ((ChangeOddDao)this.sqlSessionTemplate.getMapper(ChangeOddDao.class)).findByExpressNumberForAlls(list);
   }
 }


