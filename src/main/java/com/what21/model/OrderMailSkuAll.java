 package com.what21.model;
 
 public class OrderMailSkuAll
 {
   private String itemsku;
   private String itemClass;
   private Double allWeight;
   private String allNum;
   private String unitName;
   private Double allPrice;
   private String itemName;
   
   public String getItemName() {
     return this.itemName;
   }
   public void setItemName(String itemName) {
     this.itemName = itemName;
   }
   public String getItemsku() {
     return this.itemsku;
   }
   public void setItemsku(String itemsku) {
     this.itemsku = itemsku;
   }
   public String getItemClass() {
     return this.itemClass;
   }
   public void setItemClass(String itemClass) {
     this.itemClass = itemClass;
   }
   public String getAllNum() {
     return this.allNum;
   }
   public void setAllNum(String allNum) {
     this.allNum = allNum;
   }
   public String getUnitName() {
     return this.unitName;
   }
   public void setUnitName(String unitName) {
     this.unitName = unitName;
   }
   public Double getAllWeight() {
     return this.allWeight;
   }
   public void setAllWeight(Double allWeight) {
     this.allWeight = allWeight;
   }
   public Double getAllPrice() {
     return this.allPrice;
   }
   public void setAllPrice(Double allPrice) {
     this.allPrice = allPrice;
   }
 }


