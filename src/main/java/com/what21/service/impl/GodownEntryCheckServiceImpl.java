 package com.what21.service.impl;
 
 import com.what21.dao.GodownEntryCheckDao;
 import com.what21.dao.GodownEntryCheckSkuDao;
 import com.what21.dao.OrderMailDao;
 import com.what21.model.GodownEntryCheck;
 import com.what21.model.GodownEntryCheckSku;
 import com.what21.model.OrderMail;
 import com.what21.service.GodownEntryCheckService;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class GodownEntryCheckServiceImpl
   implements GodownEntryCheckService
 {
   @Autowired
   private GodownEntryCheckDao godownEntryCheckDao;
   @Autowired
   private GodownEntryCheckSkuDao godownEntryCheckSkuDao;
   @Autowired
   private OrderMailDao orderMailDao;
   
   public List<GodownEntryCheck> getAll(Map<String, Object> map) {
     return this.godownEntryCheckDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return this.godownEntryCheckDao.count(pageMap);
   }
 
   
   public List<GodownEntryCheck> find(Map<String, Object> pageMap) {
     return this.godownEntryCheckDao.find(pageMap);
   }
 
   
   public int addGo(GodownEntryCheck g) {
     List<OrderMail> orderMailList = this.orderMailDao.findByTotalMailNo(g.getTotalMailNo());
 
     
     List<GodownEntryCheckSku> gecsList = new ArrayList<>();
     for (OrderMail orderMail : orderMailList) {
       GodownEntryCheckSku godownEntryCheckSku = new GodownEntryCheckSku();
       godownEntryCheckSku.setTotalMailNo(g.getTotalMailNo());
       godownEntryCheckSku.setBillcode(orderMail.getMailNo());
       godownEntryCheckSku.setPc(g.getPc());
       godownEntryCheckSku.setMailStatus(orderMail.getStatus());
       
       godownEntryCheckSku.setCreatedatatime(g.getCreatedatatime());
       godownEntryCheckSku.setInflag("1");
       gecsList.add(godownEntryCheckSku);
     } 
     
     this.godownEntryCheckSkuDao.batchInsert(gecsList);
     return this.godownEntryCheckDao.addGo(g);
   }
 
   
   public int deleteG(String id) {
     return this.godownEntryCheckDao.deleteG(id);
   }
 
   
   public int countGodownEntryCheck() {
     return this.godownEntryCheckDao.countGodownEntryCheck();
   }
 
   
   public GodownEntryCheck findByTotalMailNo(String totalMailNo) {
     return this.godownEntryCheckDao.findByTotalMailNo(totalMailNo);
   }
 
   
   public void update(GodownEntryCheck g) {
     this.godownEntryCheckDao.update(g);
   }
 
   
   public List<GodownEntryCheck> checkGo(Map<String, Object> map) {
     return this.godownEntryCheckDao.checkGo(map);
   }
 
   
   public GodownEntryCheck findByPc(GodownEntryCheck g) {
     return this.godownEntryCheckDao.findByPc(g);
   }
 
   
   public GodownEntryCheck findById(String id) {
     return this.godownEntryCheckDao.findById(id);
   }
 }


