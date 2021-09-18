 package com.what21.service.impl;
 
 import com.what21.dao.JjPickingSkuDao;
 import com.what21.model.JjPickingSku;
 import com.what21.model.JjPickingSkuVo;
 import com.what21.service.JjPickingSkuService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class JjPickingSkuServiceImpl
   implements JjPickingSkuService
 {
   @Autowired
   private JjPickingSkuDao jjPickingSkuDao;
   
   public int count(JjPickingSkuVo jjPickingSkuVo) {
     return this.jjPickingSkuDao.count(jjPickingSkuVo);
   }
 
   
   public List<JjPickingSku> findAll(JjPickingSkuVo jjPickingSkuVo) {
     return this.jjPickingSkuDao.findAll(jjPickingSkuVo);
   }
 }


