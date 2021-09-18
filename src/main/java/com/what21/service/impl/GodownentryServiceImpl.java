 package com.what21.service.impl;
 
 import com.what21.dao.GodownEntrySkuDao;
 import com.what21.dao.GodownentryDao;
 import com.what21.dao.OrderMailDao;
 import com.what21.model.GodownEntrySku;
 import com.what21.model.Godownentry;
 import com.what21.model.OrderMail;
 import com.what21.service.GodownentryService;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class GodownentryServiceImpl
   implements GodownentryService
 {
   @Autowired
   private GodownentryDao godownentryDao;
   @Autowired
   private GodownEntrySkuDao godownEntrySkuDao;
   @Autowired
   private OrderMailDao orderMailDao;
   
   public List<Godownentry> getAll(Map<String, Object> map) {
     return this.godownentryDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return this.godownentryDao.count(pageMap);
   }
 
   
   public List<Godownentry> find(Map<String, Object> pageMap) {
     return this.godownentryDao.find(pageMap);
   }
 
   
   public int addGo(Godownentry g) {
     List<OrderMail> orderMailList = this.orderMailDao.findByTotalMailNo(g.getTotalMailNo());
 
     
     List<GodownEntrySku> gesList = new ArrayList<>();
     if (orderMailList != null && orderMailList.size() > 0) {
       for (OrderMail orderMail : orderMailList) {
         GodownEntrySku godownEntrySku = new GodownEntrySku();
         godownEntrySku.setTotalMailNo(g.getTotalMailNo());
         godownEntrySku.setBillcode(orderMail.getMailNo());
         godownEntrySku.setPc(g.getPc());
         
         godownEntrySku.setCreatedatatime(g.getCreatedatatime());
         godownEntrySku.setInflag("1");
         gesList.add(godownEntrySku);
       } 
       
       this.godownEntrySkuDao.batchInsert(gesList);
       return this.godownentryDao.addGo(g);
     } 
     return 9;
   }
 
   
   public int deleteG(String id) {
     return this.godownentryDao.deleteG(id);
   }
 
   
   public int countGodownentry() {
     return this.godownentryDao.countGodownentry();
   }
 
   
   public Godownentry findByTotalMailNo(String totalMailNo) {
     return this.godownentryDao.findByTotalMailNo(totalMailNo);
   }
 
   
   public void update(Godownentry g) {
     this.godownentryDao.update(g);
   }
 
   
   public List<Godownentry> checkGo(Map<String, Object> map) {
     return this.godownentryDao.checkGo(map);
   }
 
   
   public Godownentry findByPc(Godownentry g) {
     return this.godownentryDao.findByPc(g);
   }
 
   
   public Godownentry findById(String id) {
     return this.godownentryDao.findById(id);
   }
 }


