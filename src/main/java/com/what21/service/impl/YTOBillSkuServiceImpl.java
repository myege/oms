 package com.what21.service.impl;
 
 import com.what21.dao.YTOBillSkuDao;
 import com.what21.model.YTOBillSku;
 import com.what21.service.YTOBillSkuService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class YTOBillSkuServiceImpl
   implements YTOBillSkuService
 {
   @Autowired
   private YTOBillSkuDao dao;
   
   public List<YTOBillSku> findByWaybillNo(Map<String, Object> map) {
     return this.dao.findByWaybillNo(map);
   }
 
 
   
   public int count(Map<String, Object> map) {
     return this.dao.count(map);
   }
 
 
   
   public int delete(int[] ids) {
     return this.dao.delete(ids);
   }
 
 
   
   public int updateById(YTOBillSku ytoBillSku) {
     return this.dao.updateById(ytoBillSku);
   }
 
 
   
   public List<YTOBillSku> odmTax(Map<String, Object> map) {
     return this.dao.odmTax(map);
   }
 
 
   
   public int insertYTO(YTOBillSku yto) {
     return this.dao.insertYTO(yto);
   }
 
 
   
   public List<YTOBillSku> findByOrderNo(YTOBillSku sku) {
     return this.dao.findByOrderNo(sku);
   }
 
 
   
   public List<YTOBillSku> findByWaybillNoAndOrderNo(Map<String, Object> map) {
     return this.dao.findByWaybillNoAndOrderNo(map);
   }
 
 
   
   public List<YTOBillSku> findbyId(YTOBillSku sku) {
     return this.dao.findbyId(sku);
   }
   
   public int deleteAll() {
     return this.dao.deleteAll();
   }
 
 
   
   public int delete_1(int[] ids) {
     return this.dao.delete_1(ids);
   }
 
 
   
   public List<YTOBillSku> findByIds(int[] ids) {
     return this.dao.findByIds(ids);
   }
 
 
   
   public int updateByIds(int[] ids) {
     return this.dao.updateByIds(ids);
   }
 
 
   
   public List<YTOBillSku> findByWailNoFor(Map<String, Object> map) {
     return this.dao.findByWailNoFor(map);
   }
 
 
   
   public int updateByWayBillNo(Map<String, Object> map) {
     return this.dao.updateByWayBillNo(map);
   }
 
 
   
   public List<YTOBillSku> findAll() {
     return this.dao.findAll();
   }
 
 
   
   public int updateByWayBillNos(List<YTOBillSku> list) {
     return this.dao.updateByWayBillNos(list);
   }
 
 
   
   public int updateAOP() {
     return this.dao.updateAOP();
   }
 }


