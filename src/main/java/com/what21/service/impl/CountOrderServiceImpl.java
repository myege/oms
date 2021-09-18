 package com.what21.service.impl;
 
 import com.what21.dao.CountOrderDao;
 import com.what21.dao.CountRuleDao;
 import com.what21.dao.CountSkuDao;
 import com.what21.dao.OrderMailDao;
 import com.what21.model.CountRule;
 import com.what21.model.TCountorder;
 import com.what21.model.TCountsku;
 import com.what21.service.CountOrderService;
 import java.math.BigDecimal;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 @Service
 public class CountOrderServiceImpl implements CountOrderService {
   @Autowired
   private CountOrderDao countOrderDao;
   @Autowired
   private CountRuleDao countRuleDao;
   @Autowired
   private OrderMailDao orderMailDao;
   @Autowired
   private CountSkuDao countSkuDao;
   
   public List<TCountorder> findAll(TCountorder map) throws Exception {
     return this.countOrderDao.findAll(map);
   }
 
   
   public Integer count(TCountorder map) throws Exception {
     return this.countOrderDao.count(map);
   }
 
   
   public Integer add(TCountorder countOrder) throws Exception {
     List<TCountorder> list = this.countOrderDao.findMailNoOne(countOrder.getAllmailno());
     if (list != null && !list.isEmpty()) {
       return Integer.valueOf(-1);
     }
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     countOrder.setCreateData(formatter.format(new Date()));
     countOrder.setState("0");
     return this.countOrderDao.add(countOrder);
   }
 
   
   public Integer countOrder(String[] allmailno, String ruleId) throws Exception {
     List<TCountsku> listSku = new ArrayList<>();
     List<TCountorder> listorder = new ArrayList<>();
     
     List<CountRule> findCombobox = this.countRuleDao.findCombobox(Integer.valueOf(Integer.parseInt(ruleId)));
     CountRule countRule = findCombobox.get(0);
     String czf = countRule.getCzf();
     BigDecimal bf = new BigDecimal("100");
     String ptf = countRule.getPtf();
     BigDecimal ptf_2 = new BigDecimal(ptf);
     
     String qgf = countRule.getQgf();
     String tallage = countRule.getTallage();
     BigDecimal tallage_2 = new BigDecimal(tallage);
 
     
     String zgf = countRule.getZgf();
     int qgfCode = Integer.parseInt(String.valueOf(countRule.getCode().charAt(0)));
     int ptfCode = Integer.parseInt(String.valueOf(countRule.getCode().charAt(8)));
     
     byte b;
     int i;
     String[] arrayOfString;
     for (i = (arrayOfString = allmailno).length, b = 0; b < i; ) { String mailno = arrayOfString[b];
       TCountsku sku = new TCountsku();
       sku.setZgf(zgf);
       sku.setCzf(czf);
       sku.setQgf(qgf);
       sku.setSui(tallage_2.toString());
       sku.setPtf(ptf_2.toString());
       
       TCountorder orders = this.countOrderDao.findMailNo(mailno);
       if (orders.getState().equals("1")) {
         return Integer.valueOf(0);
       }
       
       String weigth = orders.getWeigth();
       Map<String, String> orderMailNo = this.orderMailDao.findByTotalMailNoS(mailno);
       Object sum = orderMailNo.get("count(0)");
       Object worth = orderMailNo.get("sum(worth)");
       if (sum == null || worth == null || sum == "" || worth == "") {
         return Integer.valueOf(2);
       }
       
       sku.setAllmaillno(mailno);
       
       BigDecimal zgf_1 = new BigDecimal(zgf);
       BigDecimal weigth_1 = new BigDecimal(weigth);
       
       if (Double.parseDouble(zgf) == 0.0D) {
         sku.setZgfw("0");
       } else if (Double.parseDouble(weigth) < 1000.0D) {
         sku.setZgfw("1000.00");
       } else {
         sku.setZgfw(zgf_1.multiply(weigth_1).setScale(2, 4).toString());
       } 
       
       BigDecimal czf_1 = new BigDecimal(czf);
       BigDecimal sum_1 = new BigDecimal(sum.toString());
       sku.setCzfw(czf_1.multiply(sum_1).toString());
       BigDecimal worth_1 = new BigDecimal(worth.toString());
       String tt = tallage_2.multiply(worth_1).setScale(2, 4).toString();
       sku.setTallagew(tt);
       
       sku.setWeigthsum(weigth);
       sku.setCountsum(Integer.valueOf(Integer.parseInt((String)sum)));
       sku.setWorthsum(worth_1.setScale(2, 4).toString());
       orders.setState("1");
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       orders.setPaydate(formatter.format(new Date()));
       String ruleCode = "";
       if (qgfCode == 1) {
         sku.setRuleid(ruleCode = String.valueOf(ruleCode) + "1,");
         BigDecimal qgf_1 = new BigDecimal(qgf);
         sku.setQgfw(qgf_1.multiply(weigth_1).setScale(2, 4).toString());
       } else {
         sku.setRuleid(ruleCode = String.valueOf(ruleCode) + "2,");
         BigDecimal qgf_1 = new BigDecimal(qgf);
         sku.setQgfw(qgf_1.multiply(sum_1).setScale(2, 4).toString());
       } 
       if (ptfCode == 1) {
         sku.setRuleid(ruleCode = String.valueOf(ruleCode) + "1");
         String pp = ptf_2.multiply(worth_1).setScale(2, 4).toString();
         sku.setPtfw(pp);
       } else {
         sku.setRuleid(ruleCode = String.valueOf(ruleCode) + "2");
         String pp = ptf_2.multiply(sum_1).setScale(2, 4).toString();
         sku.setPtfw(pp);
       } 
 
 
       
       BigDecimal add = (new BigDecimal(sku.getTallagew())).add(new BigDecimal(sku.getZgfw())).add(new BigDecimal(sku.getCzfw())).add((new BigDecimal(sku.getPtfw())).add(new BigDecimal(sku.getQgfw())));
 
 
 
       
       sku.setZong(add.toString());
       listSku.add(sku);
       listorder.add(orders);
       b++; }
     
     this.countSkuDao.addSku(listSku);
     this.countOrderDao.updateState(listorder);
     return Integer.valueOf(1);
   }
 
   
   public TCountsku findMail(String allmailno) throws Exception {
     TCountsku findMailNo = this.countSkuDao.findMailNo(allmailno);
     if (findMailNo == null) {
       return null;
     }
     
     return findMailNo;
   }
 
   
   public Integer delete(String[] ids) throws Exception {
     Integer delete = this.countOrderDao.delete(ids);
     this.countSkuDao.delete(ids);
     return delete;
   }
 }


