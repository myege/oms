 package com.what21.service.impl;
 
 import com.what21.dao.BePttsfDao;
 import com.what21.dao.CollectDao;
 import com.what21.dao.OrderBondedDao;
 import com.what21.dao.OrderMailDao;
 import com.what21.model.BePttsf;
 import com.what21.model.Collect;
 import com.what21.model.OrderBonded;
 import com.what21.model.OrderMail;
 import com.what21.service.BePttsfService;
 import com.what21.tools.Tools;
 import java.text.DecimalFormat;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 @Service
 @Transactional
 public class BePttsfServiceImpl
   implements BePttsfService
 {
   @Autowired
   private BePttsfDao bePttsfDao;
   @Autowired
   private CollectDao collectDao;
   @Autowired
   private OrderMailDao orderMailDao;
   @Autowired
   private OrderBondedDao orderBondedDao;
   
   public List<BePttsf> findAll(Map<String, Object> map) {
     return this.bePttsfDao.findAll(map);
   }
 
   
   public Integer count(Map<String, Object> map) {
     return this.bePttsfDao.count(map);
   }
 
   
   public void insert(BePttsf bePttsf) {
     this.bePttsfDao.insert(bePttsf);
   }
 
   
   public void deleteById(Integer id) {
     this.bePttsfDao.deleteById(id);
   }
 
   
   public BePttsf findById(Integer id) {
     return this.bePttsfDao.findById(id);
   }
 
   
   public List<BePttsf> findByPramid(Map<String, Object> map) {
     return this.bePttsfDao.findByPramid(map);
   }
 
   
   public Integer countByPramid(Map<String, Object> map) {
     return this.bePttsfDao.countByPramid(map);
   }
 
   
   public void findByTydh(Map<String, Object> map) {
     this.bePttsfDao.deleteByPramid(map);
     DecimalFormat df = new DecimalFormat("0.00");
     List<OrderMail> mails = this.orderMailDao.findByTydh(map);
     double pttsf = 0.0D;
     for (int i = 0; i < mails.size(); i++) {
       OrderMail orderMail = mails.get(i);
       BePttsf bePttsf = new BePttsf();
       bePttsf.setKhddh(orderMail.getTxLogisticID());
       bePttsf.setSjr(orderMail.getReceiveMan());
       bePttsf.setSpsl(orderMail.getItemCount());
       bePttsf.setSpzj(orderMail.getWorth().toString());
       bePttsf.setSpmc(orderMail.getItemName());
       bePttsf.setKddh(orderMail.getMailNo());
       double price = orderMail.getWorth().doubleValue();
       if (orderMail.getWorth().doubleValue() <= 300.0D) {
         bePttsf.setPtsjsyf("3");
         pttsf += 3.0D;
       } else {
         price *= 0.01D;
         pttsf += price;
         String str_ptsjsyf = df.format(price);
         bePttsf.setPtsjsyf(str_ptsjsyf);
       } 
       String drsj = Tools.format("yyyy-MM-dd HH:mm:ss", new Date());
       bePttsf.setDrsj(drsj);
       bePttsf.setPramid((Integer)map.get("pramid"));
       this.bePttsfDao.insert(bePttsf);
     } 
     String str_pttsf = df.format(pttsf);
     Collect collect = this.collectDao.findById(((Integer)map.get("pramid")).intValue());
     collect.setPttsf(str_pttsf);
     Tools.saveHj(collect);
     this.collectDao.update(collect);
   }
 
 
   
   public void insertByBsjs(Map<String, Object> map) {
     this.bePttsfDao.deleteByPramid(map);
     DecimalFormat df = new DecimalFormat("0.00");
     int pramid = ((Integer)map.get("pramid")).intValue();
     Collect collect = this.collectDao.findById(pramid);
     List<OrderBonded> bondeds = this.orderBondedDao.findAllByCollect(collect);
     double pttsf = 0.0D;
     for (int i = 0; i < bondeds.size(); i++) {
       OrderBonded orderBonded = bondeds.get(i);
       BePttsf bePttsf = new BePttsf();
       bePttsf.setKhddh(orderBonded.getTxLogisticID());
       bePttsf.setSjr(orderBonded.getReceiveMan());
       bePttsf.setSpsl(orderBonded.getItemCount());
       bePttsf.setSpzj(orderBonded.getWorth().toString());
       bePttsf.setSpmc(orderBonded.getItemName());
       bePttsf.setKddh(orderBonded.getMailNo());
       double price = orderBonded.getWorth().doubleValue();
       if (orderBonded.getWorth().doubleValue() <= 300.0D) {
         bePttsf.setPtsjsyf("3");
         pttsf += 3.0D;
       } else {
         price *= 0.01D;
         pttsf += price;
         String str_ptsjsyf = df.format(price);
         bePttsf.setPtsjsyf(str_ptsjsyf);
       } 
       String drsj = Tools.format("yyyy-MM-dd HH:mm:ss", new Date());
       bePttsf.setDrsj(drsj);
       bePttsf.setPramid((Integer)map.get("pramid"));
       this.bePttsfDao.insert(bePttsf);
     } 
     String str_pttsf = df.format(pttsf);
     collect.setPttsf(str_pttsf);
     Tools.saveHj(collect);
     this.collectDao.update(collect);
   }
 
 
   
   public void deleteByPramid(Map<String, Object> map) {
     this.bePttsfDao.deleteByPramid(map);
   }
 }


