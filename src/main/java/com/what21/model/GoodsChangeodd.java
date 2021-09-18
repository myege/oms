 package com.what21.model;
 public class GoodsChangeodd
 {
   private int id;
   private String expressNumber;
   private String orderNumber;
   private String pnname;
   private double nweight;
   private int newnum;
   private int userId;
   private int changeoId;
   
   public String getOrderNumber() {
     return this.orderNumber;
   }
   
   public void setOrderNumber(String orderNumber) {
     this.orderNumber = orderNumber;
   }
 
   
   public int getChangeoId() {
     return this.changeoId;
   }
   
   public void setChangeoId(int changeoId) {
     this.changeoId = changeoId;
   }
   
   public int getId() {
     return this.id;
   }
   
   public void setId(int id) {
     this.id = id;
   }
   
   public String getExpressNumber() {
     return this.expressNumber;
   }
   
   public void setExpressNumber(String expressNumber) {
     this.expressNumber = expressNumber;
   }
   
   public String getPnname() {
     return this.pnname;
   }
   
   public void setPnname(String pnname) {
     this.pnname = pnname;
   }
 
   
   public int getNewnum() {
     return this.newnum;
   }
   
   public double getNweight() {
     return this.nweight;
   }
   
   public void setNweight(double nweight) {
     this.nweight = nweight;
   }
   
   public void setNewnum(int newnum) {
     this.newnum = newnum;
   }
   
   public int getUserId() {
     return this.userId;
   }
   
   public void setUserId(int userId) {
     this.userId = userId;
   }
 }


