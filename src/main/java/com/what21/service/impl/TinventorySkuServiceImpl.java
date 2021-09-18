 package com.what21.service.impl;
 
 import com.what21.dao.TinventorySkuDao;
 import com.what21.model.TinventorySku;
 import com.what21.service.TinventorySkuService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 @Service
 public class TinventorySkuServiceImpl
   implements TinventorySkuService
 {
   @Autowired
   private TinventorySkuDao tinventorySkuDao;
   
   public int addTinSku(TinventorySku tinventorySku) {
     return this.tinventorySkuDao.addTinSku(tinventorySku);
   }
 
   
   public List<TinventorySku> findtinSku(Map<String, Object> map) {
     return this.tinventorySkuDao.findtinSku(map);
   }
 
   
   public int countSku(Map<String, Object> map) {
     return this.tinventorySkuDao.countSku(map);
   }
 }


