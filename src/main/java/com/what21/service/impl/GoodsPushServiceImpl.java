 package com.what21.service.impl;
 
 import com.what21.dao.GoodsPushDao;
 import com.what21.model.GoodsPush;
 import com.what21.service.GoodsPushService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 @Service
 public class GoodsPushServiceImpl
   implements GoodsPushService
 {
   @Autowired
   private GoodsPushDao goodsDao;
   
   public void insert(GoodsPush goods) {
     this.goodsDao.insert(goods);
   }
 
   
   public List<GoodsPush> findByExpressNumber(String expressNumber) {
     return this.goodsDao.findByExpressNumber(expressNumber);
   }
 }


