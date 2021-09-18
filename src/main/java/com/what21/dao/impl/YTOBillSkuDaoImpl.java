 package com.what21.dao.impl;
 
 import com.what21.dao.YTOBillSkuDao;
 import com.what21.model.YTOBillSku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class YTOBillSkuDaoImpl
   implements YTOBillSkuDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<YTOBillSku> findByWaybillNo(Map<String, Object> map) {
     return ((YTOBillSkuDao)this.sqlSessionTemplate.getMapper(YTOBillSkuDao.class)).findByWaybillNo(map);
   }
 
 
 
   
   public int count(Map<String, Object> map) {
     return ((YTOBillSkuDao)this.sqlSessionTemplate.getMapper(YTOBillSkuDao.class)).count(map);
   }
 
 
 
   
   public int delete(int[] ids) {
     return ((YTOBillSkuDao)this.sqlSessionTemplate.getMapper(YTOBillSkuDao.class)).delete(ids);
   }
 
 
 
   
   public int updateById(YTOBillSku ytoBillSku) {
     return ((YTOBillSkuDao)this.sqlSessionTemplate.getMapper(YTOBillSkuDao.class)).updateById(ytoBillSku);
   }
 
 
   
   public List<YTOBillSku> odmTax(Map<String, Object> map) {
     return ((YTOBillSkuDao)this.sqlSessionTemplate.getMapper(YTOBillSkuDao.class)).odmTax(map);
   }
 
 
 
   
   public int insertYTO(YTOBillSku yto) {
     return ((YTOBillSkuDao)this.sqlSessionTemplate.getMapper(YTOBillSkuDao.class)).insertYTO(yto);
   }
 
 
 
   
   public List<YTOBillSku> findByOrderNo(YTOBillSku sku) {
     return ((YTOBillSkuDao)this.sqlSessionTemplate.getMapper(YTOBillSkuDao.class)).findByOrderNo(sku);
   }
 
 
 
   
   public List<YTOBillSku> findByWaybillNoAndOrderNo(Map<String, Object> map) {
     return ((YTOBillSkuDao)this.sqlSessionTemplate.getMapper(YTOBillSkuDao.class)).findByWaybillNoAndOrderNo(map);
   }
 
 
 
   
   public List<YTOBillSku> findbyId(YTOBillSku sku) {
     return ((YTOBillSkuDao)this.sqlSessionTemplate.getMapper(YTOBillSkuDao.class)).findbyId(sku);
   }
 
 
 
   
   public void insertSkuBatch(List<YTOBillSku> list) {
     ((YTOBillSkuDao)this.sqlSessionTemplate.getMapper(YTOBillSkuDao.class)).insertSkuBatch(list);
   }
 
 
 
   
   public int deleteAll() {
     return ((YTOBillSkuDao)this.sqlSessionTemplate.getMapper(YTOBillSkuDao.class)).deleteAll();
   }
 
 
 
   
   public int delete_1(int[] ids) {
     return ((YTOBillSkuDao)this.sqlSessionTemplate.getMapper(YTOBillSkuDao.class)).delete_1(ids);
   }
 
 
 
   
   public List<YTOBillSku> findByIds(int[] ids) {
     return ((YTOBillSkuDao)this.sqlSessionTemplate.getMapper(YTOBillSkuDao.class)).findByIds(ids);
   }
 
 
 
   
   public int updateByIds(int[] ids) {
     return ((YTOBillSkuDao)this.sqlSessionTemplate.getMapper(YTOBillSkuDao.class)).updateByIds(ids);
   }
 
 
 
   
   public List<YTOBillSku> findByWailNoFor(Map<String, Object> map) {
     return ((YTOBillSkuDao)this.sqlSessionTemplate.getMapper(YTOBillSkuDao.class)).findByWailNoFor(map);
   }
 
 
 
   
   public int updateByWayBillNo(Map<String, Object> map) {
     return ((YTOBillSkuDao)this.sqlSessionTemplate.getMapper(YTOBillSkuDao.class)).updateByWayBillNo(map);
   }
 
 
 
   
   public List<YTOBillSku> findAll() {
     return ((YTOBillSkuDao)this.sqlSessionTemplate.getMapper(YTOBillSkuDao.class)).findAll();
   }
 
 
 
   
   public int updateByWayBillNos(List<YTOBillSku> list) {
     return ((YTOBillSkuDao)this.sqlSessionTemplate.getMapper(YTOBillSkuDao.class)).updateByWayBillNos(list);
   }
 
 
 
   
   public int updateAOP() {
     return ((YTOBillSkuDao)this.sqlSessionTemplate.getMapper(YTOBillSkuDao.class)).updateAOP();
   }
 }


