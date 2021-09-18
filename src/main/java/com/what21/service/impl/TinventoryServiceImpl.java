 package com.what21.service.impl;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.TinventoryDao;
 import com.what21.model.Tinventory;
 import com.what21.service.TinventoryService;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class TinventoryServiceImpl
   implements TinventoryService
 {
   @Autowired
   private TinventoryDao tinventoryDao;
   
   public List<Tinventory> findAll(Map<String, Object> map) {
     return this.tinventoryDao.findAll(map);
   }
   
   public int count(Map<String, Object> map) {
     return this.tinventoryDao.count(map);
   }
   public List<Tinventory> dateTime(Map<String, Object> map) {
     return this.tinventoryDao.dateTime(map);
   }
 
   
   public int updateTotality(Tinventory t) {
     return this.tinventoryDao.updateTotality(t);
   }
 
 
   
   public String findByPc(Tinventory t) {
     return this.tinventoryDao.findByPc(t);
   }
 
 
   
   public List<Tinventory> findBySku(Tinventory t) {
     return this.tinventoryDao.findBySku(t);
   }
 
   
   public List<Tinventory> findeXport(Map<String, Object> map) {
     return this.tinventoryDao.findeXport(map);
   }
 
   
   public List<Tinventory> findByIdTinventory(Integer id) {
     return this.tinventoryDao.findByIdTinventory(id);
   }
 
   
   public int updateTinventory(Tinventory id) {
     return this.tinventoryDao.updateTinventory(id);
   }
 
 
   
   public Tinventory findLR(Tinventory t) {
     return this.tinventoryDao.findLR(t);
   }
 
 
   
   public int updateLR(Tinventory t) {
     return this.tinventoryDao.updateLR(t);
   }
 
   
   public int addTinventory(Tinventory tinventory) {
     return this.tinventoryDao.addTinventory(tinventory);
   }
 
   
   public List<Tinventory> getAll(String merchant) {
     return this.tinventoryDao.getAll(merchant);
   }
 
   
   public int makeOver(Tinventory tinventory) {
     return this.tinventoryDao.makeOver(tinventory);
   }
 
   
   public Tinventory findSkuAndCode(Map<String, Object> map) {
     return this.tinventoryDao.findSkuAndCode(map);
   }
 
   
   public int upSurplus(Tinventory tinventory) {
     return this.tinventoryDao.upSurplus(tinventory);
   }
 
   
   public int addBig(List<Tinventory> list) {
     return this.tinventoryDao.addBig(list);
   }
   
   public void updateBig(List<Tinventory> list) {
     this.tinventoryDao.updateBig(list);
   }
 
   
   public int findStorage(String stoage) {
     return this.tinventoryDao.findStroage(stoage);
   }
   
   public String CreateInventory(String str) throws Exception {
     List<Tinventory> list = new ArrayList<>();
     JSONObject ret = JSONObject.parseObject(str);
     String skuReserveId = ret.getString("skuReserveId");
     System.out.println("skuReserveId=====" + skuReserveId);
     String reserveCount = ret.getString("reserveCount");
     String orderCount = ret.getString("orderCount");
     String outPutCount = ret.getString("outPutCount");
     
     return "";
   }
 }


