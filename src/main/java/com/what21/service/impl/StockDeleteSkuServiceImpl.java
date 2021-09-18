 package com.what21.service.impl;
 
 import com.what21.dao.StockDeleteSkuDao;
 import com.what21.model.StockDeleteSku;
 import com.what21.service.StockDeleteSkuService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class StockDeleteSkuServiceImpl
   implements StockDeleteSkuService
 {
   @Autowired
   private StockDeleteSkuDao stockDeleteSkuDao;
   
   public List<StockDeleteSku> findAll(Map<String, Object> map) {
     return this.stockDeleteSkuDao.findAll(map);
   }
 
 
   
   public int count(String stockDeleteId) {
     return this.stockDeleteSkuDao.count(stockDeleteId);
   }
 }


