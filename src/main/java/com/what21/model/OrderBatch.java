 package com.what21.model;
 public class OrderBatch
 {
   private int id;
   private String batchNumber;
   private String leadTime;
   private int leadOrderNumber;
   private Double leadOrderTotal;
   private Double leadOrderTax;
   private int isTax;
   private String zfTime;
   private int userId;
   private String money;
   private String dateTax;
   
   public String getZfTime() {
     return this.zfTime;
   }
   
   public void setZfTime(String zfTime) {
     this.zfTime = zfTime;
   }
   
   public String getMoney() {
     return this.money;
   }
   
   public void setMoney(String money) {
     this.money = money;
   }
   
   public int getId() {
     return this.id;
   }
   
   public void setId(int id) {
     this.id = id;
   }
   
   public String getBatchNumber() {
     return this.batchNumber;
   }
   
   public void setBatchNumber(String batchNumber) {
     this.batchNumber = batchNumber;
   }
   
   public String getLeadTime() {
     return this.leadTime;
   }
   
   public void setLeadTime(String leadTime) {
     this.leadTime = leadTime;
   }
   
   public int getLeadOrderNumber() {
     return this.leadOrderNumber;
   }
   
   public void setLeadOrderNumber(int leadOrderNumber) {
     this.leadOrderNumber = leadOrderNumber;
   }
   
   public Double getLeadOrderTotal() {
     return this.leadOrderTotal;
   }
   
   public void setLeadOrderTotal(Double leadOrderTotal) {
     this.leadOrderTotal = leadOrderTotal;
   }
   
   public Double getLeadOrderTax() {
     return this.leadOrderTax;
   }
   
   public void setLeadOrderTax(Double leadOrderTax) {
     this.leadOrderTax = leadOrderTax;
   }
   
   public int getIsTax() {
     return this.isTax;
   }
   
   public void setIsTax(int isTax) {
     this.isTax = isTax;
   }
   
   public int getUserId() {
     return this.userId;
   }
   
   public void setUserId(int userId) {
     this.userId = userId;
   }
 
 
 
   
   public String getDateTax() {
     return this.dateTax;
   }
   
   public void setDateTax(String dateTax) {
     this.dateTax = dateTax;
   }
 }


