 package com.what21.timer;
 
 import com.what21.dao.OrderBondedDao;
 import com.what21.model.OrderBonded;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
 @Component
 public class LcforYwKc
 {
   @Autowired
   private static OrderBondedDao orderBondedDao;
   
   public static void OrderYs() throws Exception {
     String A = "1=1 and auditstatus ='0' ";
     List<OrderBonded> list = orderBondedDao.findbyA(A);
     for (OrderBonded order : list)
       System.out.println("order=" + order.getTxLogisticID()); 
   }
   
   public static void OrderTokc() throws Exception {}
   
   public static void OrderToJz() throws Exception {}
   
   public static void OrderZtTokc() throws Exception {}
   
   public static void OrderZtToJz() throws Exception {}
   
   public static void main(String[] args) {}
 }


