 package com.what21.dao.impl;
 
 import com.what21.dao.YTOBillDao;
 import com.what21.model.YTOBillSku;
 import com.what21.model.YtoBill;
 import com.what21.model.YtoBillEx;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class YTOBillDaoImpl
   implements YTOBillDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public int insertYTO(YtoBill yto) {
     return ((YTOBillDao)this.sqlSessionTemplate.getMapper(YTOBillDao.class)).insertYTO(yto);
   }
 
 
   
   public List<YtoBill> findAllOfYTO(Map<String, Object> map) {
     return ((YTOBillDao)this.sqlSessionTemplate.getMapper(YTOBillDao.class)).findAllOfYTO(map);
   }
 
 
   
   public int delete(int[] ids) {
     return ((YTOBillDao)this.sqlSessionTemplate.getMapper(YTOBillDao.class)).delete(ids);
   }
 
 
   
   public int updateByID(YtoBill yto) {
     return ((YTOBillDao)this.sqlSessionTemplate.getMapper(YTOBillDao.class)).updateByID(yto);
   }
 
 
   
   public int count(Map<String, Object> map) {
     return ((YTOBillDao)this.sqlSessionTemplate.getMapper(YTOBillDao.class)).count(map);
   }
 
 
   
   public List<YtoBill> findByWaybillNo(Map<String, Object> map) {
     return ((YTOBillDao)this.sqlSessionTemplate.getMapper(YTOBillDao.class)).findByWaybillNo(map);
   }
 
 
   
   public List<YtoBill> findById(int[] ids) {
     return ((YTOBillDao)this.sqlSessionTemplate.getMapper(YTOBillDao.class)).findById(ids);
   }
 
 
   
   public void insertList(List<YtoBill> subList) {
     ((YTOBillDao)this.sqlSessionTemplate.getMapper(YTOBillDao.class)).insertList(subList);
   }
 
 
   
   public YtoBill findId(Map<String, Object> map) {
     return null;
   }
 
 
   
   public List<YtoBill> findByOrderNo(YtoBill yto) {
     return ((YTOBillDao)this.sqlSessionTemplate.getMapper(YTOBillDao.class)).findByOrderNo(yto);
   }
 
 
   
   public int updateForIs(YtoBill yto) {
     return ((YTOBillDao)this.sqlSessionTemplate.getMapper(YTOBillDao.class)).updateForIs(yto);
   }
 
 
   
   public List<YtoBillEx> leftJoin(int[] ids) {
     return ((YTOBillDao)this.sqlSessionTemplate.getMapper(YTOBillDao.class)).leftJoin(ids);
   }
 
 
   
   public List<YtoBill> findByWay_1(Map<String, Object> map) {
     return ((YTOBillDao)this.sqlSessionTemplate.getMapper(YTOBillDao.class)).findByWay_1(map);
   }
 
 
   
   public void insertYtoBatch(List<YtoBill> list) {
     ((YTOBillDao)this.sqlSessionTemplate.getMapper(YTOBillDao.class)).insertYtoBatch(list);
   }
 
 
   
   public int deleteAll(String isSigned) {
     return ((YTOBillDao)this.sqlSessionTemplate.getMapper(YTOBillDao.class)).deleteAll(isSigned);
   }
 
 
   
   public List<YtoBillEx> findExport() {
     return ((YTOBillDao)this.sqlSessionTemplate.getMapper(YTOBillDao.class)).findExport();
   }
 
 
   
   public List<YtoBill> findByWaybillNo_createTime(Map<String, Object> map) {
     return ((YTOBillDao)this.sqlSessionTemplate.getMapper(YTOBillDao.class)).findByWaybillNo_createTime(map);
   }
 
 
   
   public List<YtoBill> findByWaybillNo_deliveryTime(Map<String, Object> map) {
     return ((YTOBillDao)this.sqlSessionTemplate.getMapper(YTOBillDao.class)).findByWaybillNo_deliveryTime(map);
   }
 
 
   
   public List<YTOBillSku> findByIdsForSku(List<YtoBill> list) {
     return ((YTOBillDao)this.sqlSessionTemplate.getMapper(YTOBillDao.class)).findByIdsForSku(list);
   }
 }


