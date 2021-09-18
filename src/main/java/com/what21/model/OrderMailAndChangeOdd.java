 package com.what21.model;
 
 public class OrderMailAndChangeOdd {
   private int itemCount;
   private double itemWeight;
   private String itemName;
   private int id;
   private String txLogisticID;
   
   public String getTxLogisticID() {
     return this.txLogisticID;
   }
   public void setTxLogisticID(String txLogisticID) {
     this.txLogisticID = txLogisticID;
   }
   public int getItemCount() {
     return this.itemCount;
   }
   public void setItemCount(int itemCount) {
     this.itemCount = itemCount;
   }
   public double getItemWeight() {
     return this.itemWeight;
   }
   public void setItemWeight(double itemWeight) {
     this.itemWeight = itemWeight;
   }
   public String getItemName() {
     return this.itemName;
   }
   public void setItemName(String itemName) {
     this.itemName = itemName;
   }
   public int getId() {
     return this.id;
   }
   public void setId(int id) {
     this.id = id;
   }
 }


