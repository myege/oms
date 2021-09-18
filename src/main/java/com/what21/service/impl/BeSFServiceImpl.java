 package com.what21.service.impl;
 
 import com.what21.dao.BeSFDao;
 import com.what21.dao.CollectDao;
 import com.what21.dao.OrderBondedDao;
 import com.what21.dao.OrderMailDao;
 import com.what21.model.BeSF;
 import com.what21.model.Collect;
 import com.what21.model.OrderBonded;
 import com.what21.model.OrderMail;
 import com.what21.service.BeSFService;
 import com.what21.tools.Tools;
 import java.text.DecimalFormat;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 @Service
 @Transactional
 public class BeSFServiceImpl
   implements BeSFService
 {
   @Autowired
   private BeSFDao beSFDao;
   @Autowired
   private CollectDao collectDao;
   @Autowired
   private OrderMailDao orderMailDao;
   @Autowired
   private OrderBondedDao orderBondedDao;
   
   public List<BeSF> findAll(Map<String, Object> map) {
     return this.beSFDao.findAll(map);
   }
 
 
   
   public int count(Map<String, Object> map) {
     return this.beSFDao.count(map);
   }
 
   
   public void insert(BeSF beSF) {
     this.beSFDao.insert(beSF);
   }
 
 
   
   public void deleteById(int id) {
     this.beSFDao.deleteById(id);
   }
   
   public List<BeSF> findByPramid(Map<String, Object> map) {
     return this.beSFDao.findByPramid(map);
   }
 
   
   public BeSF findById(int id) {
     return this.beSFDao.findById(id);
   }
 
   
   public int countByPramid(Map<String, Object> map) {
     return this.beSFDao.countByPramid(map);
   }
 
   
   public void findByTydh(Map<String, Object> map) {
     this.beSFDao.deleteByPramid(map);
     List<OrderMail> mails = this.orderMailDao.findByTydh(map);
     float sf = 0.0F;
     for (int i = 0; i < mails.size(); i++) {
       float f1 = 0.0F;
       OrderMail orderMail = mails.get(i);
       if (StringUtils.isNotEmpty(orderMail.getValueAddedTax())) {
         f1 = Float.valueOf(orderMail.getValueAddedTax()).floatValue();
       }
       sf += f1;
       BeSF beSF = new BeSF();
       beSF.setPramid(((Integer)map.get("pramid")).intValue());
 
       
       beSF.setDdh(orderMail.getOrdercode());
       beSF.setSjbm(orderMail.getMerchantNum());
       beSF.setSbje(orderMail.getWorth().toString());
       beSF.setZzs((new StringBuilder(String.valueOf(f1))).toString());
       beSF.setFydh(orderMail.getMailNo());
       beSF.setZfrzjh(orderMail.getBuyerIdNumber());
       beSF.setZfrxm(orderMail.getBuyerName());
       String drsj = Tools.format("yyyy-MM-dd HH-mm-ss", new Date());
       beSF.setDrsj(drsj);
       beSF.setId(null);
       this.beSFDao.insert(beSF);
     } 
     DecimalFormat df = new DecimalFormat("0.00");
     String str_sf = df.format(sf);
     Collect collect = this.collectDao.findById(((Integer)map.get("pramid")).intValue());
     collect.setSf(str_sf);
     Tools.saveHj(collect);
     this.collectDao.update(collect);
   }
 
 
 
   
   public void update(String[] ids, String zzs, String tydh) {
     int pramid = 0; byte b; int i; String[] arrayOfString;
     for (i = (arrayOfString = ids).length, b = 0; b < i; ) { String id = arrayOfString[b];
       BeSF beSF = this.beSFDao.findById(Integer.parseInt(id));
       beSF.setZzs(zzs);
       pramid = beSF.getPramid();
       this.beSFDao.update(beSF); b++; }
     
     Collect collect = this.collectDao.findById(pramid);
     Map<String, Object> map = new HashMap<>();
     map.put("pramid", Integer.valueOf(pramid));
     List<BeSF> list = this.beSFDao.findByPramid(map);
     float sf = 0.0F;
     for (BeSF beSF2 : list) {
       sf += Float.valueOf(beSF2.getZzs()).floatValue();
     }
     DecimalFormat df = new DecimalFormat("0.00");
     String str_sf = df.format(sf);
     collect.setSf(str_sf);
     Tools.saveHj(collect);
     this.collectDao.update(collect);
   }
 
 
   
   public void insertByBsjs(Map<String, Object> map) {
     this.beSFDao.deleteByPramid(map);
     int pramid = ((Integer)map.get("pramid")).intValue();
     Collect collect = this.collectDao.findById(pramid);
     List<OrderBonded> bondeds = this.orderBondedDao.findAllByCollect(collect);
     
     float sf = 0.0F;
     for (int i = 0; i < bondeds.size(); i++) {
       float f1 = 0.0F;
       OrderBonded orderBonded = bondeds.get(i);
       if (StringUtils.isNotEmpty(orderBonded.getValueAddedTax())) {
         f1 = Float.valueOf(orderBonded.getValueAddedTax()).floatValue();
       }
       sf += f1;
       BeSF beSF = new BeSF();
       beSF.setPramid(((Integer)map.get("pramid")).intValue());
       
       beSF.setBscjsj(orderBonded.getCreateTime());
       beSF.setDdh(orderBonded.getOrdercode());
       beSF.setSjbm(orderBonded.getMerchantNum());
       beSF.setSbje(orderBonded.getWorth().toString());
       beSF.setZzs((new StringBuilder(String.valueOf(f1))).toString());
       beSF.setFydh(orderBonded.getMailNo());
       beSF.setZfrzjh(orderBonded.getBuyerIdNumber());
       beSF.setZfrxm(orderBonded.getBuyerName());
       String drsj = Tools.format("yyyy-MM-dd HH-mm-ss", new Date());
       beSF.setDrsj(drsj);
       this.beSFDao.insert(beSF);
     } 
     DecimalFormat df = new DecimalFormat("0.00");
     String str_sf = df.format(sf);
     collect.setSf(str_sf);
     Tools.saveHj(collect);
     this.collectDao.update(collect);
   }
 
 
   
   public void update(String[] ids, String zzs, int pramid) {
     Collect collect = this.collectDao.findById(pramid); byte b; int i; String[] arrayOfString;
     for (i = (arrayOfString = ids).length, b = 0; b < i; ) { String id = arrayOfString[b];
       BeSF beSF = this.beSFDao.findById(Integer.parseInt(id));
       beSF.setZzs(zzs);
       this.beSFDao.update(beSF); b++; }
     
     Map<String, Object> map = new HashMap<>();
     map.put("pramid", Integer.valueOf(pramid));
     List<BeSF> list = this.beSFDao.findByPramid(map);
     float sf = 0.0F;
     for (BeSF beSF2 : list) {
       sf += Float.valueOf(beSF2.getZzs()).floatValue();
     }
     DecimalFormat df = new DecimalFormat("0.00");
     String str_sf = df.format(sf);
     collect.setSf(str_sf);
     Tools.saveHj(collect);
     this.collectDao.update(collect);
   }
 
 
   
   public void deleteByPramid(Map<String, Object> map) {
     this.beSFDao.deleteByPramid(map);
   }
 
 
   
   public void updateAll(int pramid) {
     DecimalFormat df = new DecimalFormat("0.00");
     Map<String, Object> map = new HashMap<>();
     map.put("pramid", Integer.valueOf(pramid));
     List<BeSF> beSFs = findByPramid(map);
     String str_zzs = null;
     double total = 0.0D;
     for (BeSF beSF : beSFs) {
       String str_sbje = beSF.getSbje();
       if (!"0.0".equals(beSF.getZzs())) {
         total += Double.parseDouble(beSF.getZzs());
         continue;
       } 
       if (StringUtils.isEmpty(str_sbje)) {
         str_zzs = "0";
       } else {
         double sbje = Double.parseDouble(str_sbje);
         double zzs = sbje * 0.119D;
         str_zzs = df.format(zzs);
         total += zzs;
       } 
       
       beSF.setZzs(str_zzs);
       this.beSFDao.update(beSF);
     } 
     Collect collect = this.collectDao.findById(pramid);
     collect.setSf(df.format(total));
     Tools.saveHj(collect);
     this.collectDao.update(collect);
   }
 }


