 package com.what21.service.impl;
 
 import com.what21.dao.GoodsAcceptDao;
 import com.what21.model.GoodsAccept;
 import com.what21.service.GoodsAcceptService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 @Service
 public class GoodsAcceptServiceImpl
   implements GoodsAcceptService
 {
   @Autowired
   private GoodsAcceptDao goodsAcceptDao;
   
   public void insert(GoodsAccept goods) {
     this.goodsAcceptDao.insert(goods);
   }
 
   
   public List<GoodsAccept> findByExpressNumber(String expressNumber) {
     return this.goodsAcceptDao.findByExpressNumber(expressNumber);
   }
 }


