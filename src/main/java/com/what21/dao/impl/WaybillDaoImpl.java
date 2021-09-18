 package com.what21.dao.impl;
 
 import com.what21.dao.WaybillDao;
 import com.what21.model.Waybill;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class WaybillDaoImpl
   implements WaybillDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<Waybill> findAll(Map<String, Object> map) {
     return ((WaybillDao)this.sqlSessionTemplate.getMapper(WaybillDao.class)).findAll(map);
   }
 
 
   
   public int count(Map<String, Object> map) {
     return ((WaybillDao)this.sqlSessionTemplate.getMapper(WaybillDao.class)).count(map);
   }
 
 
   
   public void insert(Waybill waybill) {
     ((WaybillDao)this.sqlSessionTemplate.getMapper(WaybillDao.class)).insert(waybill);
   }
 
 
   
   public List<Waybill> findId(Map<String, Object> map) {
     return ((WaybillDao)this.sqlSessionTemplate.getMapper(WaybillDao.class)).findId(map);
   }
 
 
   
   public List<Waybill> findSearch(Map<String, Object> map) {
     return ((WaybillDao)this.sqlSessionTemplate.getMapper(WaybillDao.class)).findSearch(map);
   }
 
 
   
   public List<Waybill> findIsSign(Map<String, Object> map) {
     return ((WaybillDao)this.sqlSessionTemplate.getMapper(WaybillDao.class)).findIsSign(map);
   }
 
 
   
   public int delete(Map<String, Object> map) {
     return ((WaybillDao)this.sqlSessionTemplate.getMapper(WaybillDao.class)).delete(map);
   }
 
 
   
   public Waybill findByExpressNumber(Map<String, Object> map) {
     return ((WaybillDao)this.sqlSessionTemplate.getMapper(WaybillDao.class)).findByExpressNumber(map);
   }
 
 
   
   public List<Waybill> dateTime(Map<String, Object> map) {
     return ((WaybillDao)this.sqlSessionTemplate.getMapper(WaybillDao.class)).dateTime(map);
   }
 
   
   public String[] getExpressNumber() {
     return ((WaybillDao)this.sqlSessionTemplate.getMapper(WaybillDao.class)).getExpressNumber();
   }
 
   
   public void update(List<Waybill> way) {
     ((WaybillDao)this.sqlSessionTemplate.getMapper(WaybillDao.class)).update(way);
   }
 
   
   public void insertList(List<Waybill> subList) {
     ((WaybillDao)this.sqlSessionTemplate.getMapper(WaybillDao.class)).insertList(subList);
   }
 
   
   public List<Waybill> findIdArray(String[] arr) {
     return ((WaybillDao)this.sqlSessionTemplate.getMapper(WaybillDao.class)).findIdArray(arr);
   }
 }


