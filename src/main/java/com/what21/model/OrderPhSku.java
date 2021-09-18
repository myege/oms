 package com.what21.model;
 public class OrderPhSku
 {
   private int id;
   private String itemsku;
   private String txLogisticID;
   private String itemName;
   private Double itemWeight;
   private Integer itemCount;
   private Double unitPrice;
   private Double allPrice;
   private String merchart;
   private String hz;
   private int internalNumber;
   private String gylFinanceNumber;
   
   public String getGylFinanceNumber() {
     return this.gylFinanceNumber;
   }
   
   public void setGylFinanceNumber(String gylFinanceNumber) {
     this.gylFinanceNumber = gylFinanceNumber;
   }
   
   public int getInternalNumber() {
     return this.internalNumber;
   }
   
   public void setInternalNumber(int internalNumber) {
     this.internalNumber = internalNumber;
   }
   
   public String getHz() {
     return this.hz;
   }
   
   public void setHz(String hz) {
     this.hz = hz;
   }
   
   public String getItemsku() {
     return this.itemsku;
   }
   
   public void setItemsku(String itemsku) {
     this.itemsku = itemsku;
   }
   
   public String getMerchart() {
     return this.merchart;
   }
   
   public void setMerchart(String merchart) {
     this.merchart = merchart;
   }
   
   public int getId() {
     return this.id;
   }
   public void setId(int id) {
     this.id = id;
   }
   
   public String getTxLogisticID() {
     return this.txLogisticID;
   }
   
   public void setTxLogisticID(String txLogisticID) {
     this.txLogisticID = txLogisticID;
   }
   
   public String getItemName() {
     return this.itemName;
   }
   
   public void setItemName(String itemName) {
     this.itemName = itemName;
   }
   
   public Double getItemWeight() {
     return this.itemWeight;
   }
   
   public void setItemWeight(Double itemWeight) {
     this.itemWeight = itemWeight;
   }
   
   public Integer getItemCount() {
     return this.itemCount;
   }
   
   public void setItemCount(Integer itemCount) {
     this.itemCount = itemCount;
   }
   
   public Double getUnitPrice() {
     return this.unitPrice;
   }
   
   public void setUnitPrice(Double unitPrice) {
     this.unitPrice = unitPrice;
   }
   
   public Double getAllPrice() {
     return this.allPrice;
   }
   
   public void setAllPrice(Double allPrice) {
     this.allPrice = allPrice;
   }
 }


