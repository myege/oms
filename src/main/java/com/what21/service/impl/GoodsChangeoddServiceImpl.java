 package com.what21.service.impl;
 
 import com.what21.dao.GoodsChangeoddDao;
 import com.what21.model.ChangeOdd;
 import com.what21.model.GoodsChangeodd;
 import com.what21.service.GoodsChangeoddService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class GoodsChangeoddServiceImpl
   implements GoodsChangeoddService
 {
   @Autowired
   private GoodsChangeoddDao goodsChangeoddDao;
   
   public void insertand(GoodsChangeodd goods) {
     this.goodsChangeoddDao.insertand(goods);
   }
 
 
   
   public GoodsChangeodd findByExpressNumber(String expressNumber) {
     return this.goodsChangeoddDao.findByExpressNumber(expressNumber);
   }
 
   
   public List<GoodsChangeodd> findExpressNumber(String expressNumber) {
     return this.goodsChangeoddDao.findExpressNumber(expressNumber);
   }
 
   
   public ChangeOdd updatecod(String expressNumber) {
     return this.goodsChangeoddDao.updatecod(expressNumber);
   }
 
   
   public void deleteByPids(int[] ids) {
     this.goodsChangeoddDao.deleteByPids(ids);
   }
 
 
 
   
   public void insertAlls(List<GoodsChangeodd> list) {
     this.goodsChangeoddDao.insertAlls(list);
   }
 }


