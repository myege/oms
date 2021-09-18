 package com.what21.service.impl;
 
 import com.what21.dao.GylFinanceDao;
 import com.what21.model.GylFinance;
 import com.what21.model.OrderBonded;
 import com.what21.model.OrderBondedSku;
 import com.what21.service.GylFinanceService;
 import com.what21.util.StringUtil;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class GylFinanceServiceImpl
   implements GylFinanceService
 {
   @Autowired
   public GylFinanceDao gylFinanceDao;
   
   public List<GylFinance> findAll(Map<String, Object> map) {
     return this.gylFinanceDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return this.gylFinanceDao.count(map);
   }
 
   
   public List<OrderBonded> findOrderByNumber(Map<String, Object> map) {
     return this.gylFinanceDao.findOrderByNumber(map);
   }
 
   
   public int countByNumber(Map<String, Object> map) {
     return this.gylFinanceDao.countByNumber(map);
   }
 
   
   public int updateConfirm(String userName, String gylFinanceNumber) {
     int result = 0;
     try {
       if (StringUtil.isEmpty(gylFinanceNumber).booleanValue()) {
         return result;
       }
       String[] numberArr = gylFinanceNumber.split(",");
       List<GylFinance> list = new ArrayList<>(); byte b; int i; String[] arrayOfString1;
       for (i = (arrayOfString1 = numberArr).length, b = 0; b < i; ) { String number = arrayOfString1[b];
         GylFinance gylFinance = new GylFinance();
         gylFinance.setConfirmStatus(Integer.valueOf(1));
         gylFinance.setConfirmUser(userName);
         gylFinance.setGylFinanceNumber(number);
         list.add(gylFinance); b++; }
       
       List<OrderBonded> orderList = new ArrayList<>();
       List<OrderBondedSku> orderSkuList = new ArrayList<>();
       orderList = this.gylFinanceDao.findOrder(list);
       orderSkuList = this.gylFinanceDao.findOrderSku(list);
       this.gylFinanceDao.batchInsertToOrderBonded(orderList);
       this.gylFinanceDao.batchInsertToOrderBondedSku(orderSkuList);
       result = this.gylFinanceDao.updateConfirmStatus(list);
     } catch (Exception e) {
       return result;
     } 
     return result;
   }
 }


