 package com.what21.service.impl;
 
 import com.what21.dao.PlaningReceiptsSkuDao;
 import com.what21.model.PlaningReceiptsSku;
 import com.what21.service.PlaningReceiptsSkuService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 @Service
 public class PlaningReceiptsSkuServiceImpl
   implements PlaningReceiptsSkuService
 {
   @Autowired
   private PlaningReceiptsSkuDao planingReceiptsSkuDao;
   
   public List<PlaningReceiptsSku> findAll(Map<String, Object> map) {
     return this.planingReceiptsSkuDao.findAll(map);
   }
 
 
   
   public int count(String planingReceiptsId) {
     return this.planingReceiptsSkuDao.count(planingReceiptsId);
   }
 }


