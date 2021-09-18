 package com.what21.service.impl;
 
 import com.what21.dao.CostDao;
 import com.what21.dao.OrderMailDao;
 import com.what21.model.Cost;
 import com.what21.model.OrderMail;
 import com.what21.service.CostService;
 import java.math.BigDecimal;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class CostServiceImpl
   implements CostService
 {
   @Autowired
   private CostDao costDao;
   @Autowired
   private OrderMailDao orderMailDao;
   
   public int count(Map<String, Object> map) {
     return this.costDao.count(map);
   }
 
   
   public List<Cost> findAll(Map<String, Object> map) {
     return this.costDao.findAll(map);
   }
 
   
   public int addCost(Cost cost) {
     return this.costDao.addCost(cost);
   }
 
   
   public int deleteCost(String id) {
     return this.costDao.deleteCost(id);
   }
 
   
   public int updateCost(Cost cost) {
     return this.costDao.updateCost(cost);
   }
 
   
   public int updateCostSku(Cost cost) {
     return this.costDao.updateCostSku(cost);
   }
 
   
   public int updateCostisTj(int id) {
     return this.costDao.updateCostisTj(id);
   }
 
   
   public Cost findCosts(String id) {
     return this.costDao.findCosts(id);
   }
 
   
   public int updateCostxg(String id) {
     return this.costDao.updateCostxg(id);
   }
 
   
   public int updateCosthl(String id) {
     return this.costDao.updateCosthl(id);
   }
 
   
   public int updateCostdb(String id) {
     return this.costDao.updateCostdb(id);
   }
 
   
   public int isPayment(int id) {
     return this.costDao.isPayment(id);
   }
 
   
   public Cost findTotanumber(String id) {
     return this.costDao.findTotanumber(id);
   }
 
   
   public Cost findCost(String id, String totalMailNo) {
     BigDecimal allPrice = new BigDecimal("0");
     BigDecimal xxy = new BigDecimal("0");
     BigDecimal xy = new BigDecimal("0");
     BigDecimal ddy = new BigDecimal("0");
     BigDecimal orh = new BigDecimal("0");
     BigDecimal xdy = new BigDecimal("0");
     
     OrderMail orderMailList = this.orderMailDao.findTxLogisticIDByTotalMailNo(totalMailNo);
     allPrice = orderMailList.getOjr();
     OrderMail orderMaildy = this.orderMailDao.findOrderMailDy(totalMailNo);
     OrderMail orderMailxy = this.orderMailDao.findOrderMailXy(totalMailNo);
     ddy = orderMaildy.getDy();
     xy = orderMailxy.getXy();
     orh = orderMaildy.getOhr();
     Cost cost = this.costDao.findCost(id);
     if (cost == null) {
       Cost costs = this.costDao.findById(id);
       costs.setIcount(xy.multiply(new BigDecimal("3")));
       costs.setInts(xy);
       costs.setIntss(ddy);
       costs.setIcounts(orh);
       costs.setWhy(allPrice);
       costs.setZd(ddy.add(xy));
       costs.setCon(allPrice.multiply(new BigDecimal("0.119")));
       BigDecimal af = costs.getIcount();
       BigDecimal aj = costs.getIcounts();
       xxy = af.add(aj);
       costs.setZh(xxy);
     } else {
       BigDecimal gm = cost.getGm();
       BigDecimal total = cost.getTt();
       cost.setIcount(xy.multiply(new BigDecimal("3")));
       cost.setInts(xy);
       cost.setIntss(ddy);
       cost.setIcounts(orh);
       cost.setWhy(allPrice);
       cost.setZd(ddy.add(xy));
       cost.setCon(allPrice.multiply(new BigDecimal("0.119")));
       BigDecimal af = cost.getIcount();
       BigDecimal aj = cost.getIcounts();
       
       xxy = af.add(aj);
       xdy = xxy.add(gm);
       BigDecimal hdb = cost.getCon();
       BigDecimal okm = xdy.add(hdb);
       cost.setTt(total);
       cost.setZh(xxy);
     } 
     return cost;
   }
 
   
   public int updateCostldt(String id) {
     return this.costDao.updateCostldt(id);
   }
 
   
   public int updateCosthkzy(String id) {
     return this.costDao.updateCosthkzy(id);
   }
 
   
   public int updateCosthdtx(String id) {
     return this.costDao.updateCosthdtx(id);
   }
 
   
   public int updateCostmg(String id) {
     return this.costDao.updateCostmg(id);
   }
 
   
   public Cost findCostAll(int id) {
     return this.costDao.findCostAll(id);
   }
 
   
   public int updateCostxq(Cost cost) {
     return this.costDao.updateCostxq(cost);
   }
 
   
   public List<Cost> viewDetails(int userId) {
     return this.costDao.viewDetails(userId);
   }
 }


