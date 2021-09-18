 package com.what21.service.impl;
 
 import com.what21.dao.CostDao;
 import com.what21.dao.CostSkuDao;
 import com.what21.dao.OrderMailDao;
 import com.what21.model.CostSku;
 import com.what21.model.OrderMail;
 import com.what21.service.CostSkuService;
 import java.math.BigDecimal;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class CostSKUServiceImpl
   implements CostSkuService
 {
   @Autowired
   private CostDao costDao;
   @Autowired
   private CostSkuDao costSkuDao;
   @Autowired
   private OrderMailDao orderMailDao;
   
   public int addCostSku(CostSku cost) {
     return this.costSkuDao.addCostSku(cost);
   }
 
   
   public List<CostSku> findCostSku(Map<String, Object> map) {
     return this.costSkuDao.findCostSku(map);
   }
 
   
   public int count(int id) {
     return this.costSkuDao.count(id);
   }
 
   
   public int updateCostSku(CostSku cost) {
     return this.costSkuDao.updateCostSku(cost);
   }
 
   
   public int deleteCostSku(String costId) {
     return this.costSkuDao.deleteCostSku(costId);
   }
   
   public CostSku findAll(int id) {
     return this.costSkuDao.findAll(id);
   }
 
   
   public List<CostSku> findByIdxy(int id) {
     return this.costSkuDao.findByIdxy(id);
   }
 
   
   public List<CostSku> findByIddy(int id) {
     return this.costSkuDao.findByIddy(id);
   }
 
 
 
   
   public CostSku findById(String id, String totalMailNo) {
     BigDecimal allPrice = new BigDecimal("0");
     BigDecimal xy = new BigDecimal("0");
     BigDecimal orh = new BigDecimal("0");
     OrderMail orderMailList = this.orderMailDao.findTxLogisticIDByTotalMailNo(totalMailNo);
     OrderMail orderMaildy = this.orderMailDao.findOrderMailDy(totalMailNo);
     OrderMail orderMailxy = this.orderMailDao.findOrderMailXy(totalMailNo);
     xy = orderMailxy.getXy();
     orh = orderMaildy.getOhr();
     allPrice = orderMailList.getOjr();
     CostSku costSku = this.costSkuDao.findById(id);
     costSku.setIco(xy.multiply(new BigDecimal("3")));
     costSku.setIcou(orh);
     costSku.setWh(allPrice);
     costSku.setCons(allPrice.multiply(new BigDecimal("0.119")));
     return costSku;
   }
 }


