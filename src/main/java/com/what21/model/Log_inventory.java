 package com.what21.model;
 public class Log_inventory
 {
   private int id;
   private String txLogisticID;
   private String itemsku;
   private String itemName;
   private Integer itemCount;
   private String Stocklot;
   private String hz;
   private String motion;
   private String createDate;
   private String type;
   private String remark;
   private String storage;
   
   public String getStorage() {
     return this.storage;
   }
   
   public void setStorage(String storage) {
     this.storage = storage;
   }
   
   public String getType() {
     return this.type;
   }
   
   public void setType(String type) {
     this.type = type;
   }
   
   public String getRemark() {
     return this.remark;
   }
   
   public void setRemark(String remark) {
     this.remark = remark;
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
   
   public String getItemsku() {
     return this.itemsku;
   }
   
   public void setItemsku(String itemsku) {
     this.itemsku = itemsku;
   }
   
   public String getItemName() {
     return this.itemName;
   }
   
   public void setItemName(String itemName) {
     this.itemName = itemName;
   }
   
   public Integer getItemCount() {
     return this.itemCount;
   }
   
   public void setItemCount(Integer itemCount) {
     this.itemCount = itemCount;
   }
   
   public String getStocklot() {
     return this.Stocklot;
   }
   
   public void setStocklot(String stocklot) {
     this.Stocklot = stocklot;
   }
   
   public String getHz() {
     return this.hz;
   }
   
   public void setHz(String hz) {
     this.hz = hz;
   }
   
   public String getMotion() {
     return this.motion;
   }
   
   public void setMotion(String motion) {
     this.motion = motion;
   }
 
   
   public String getCreateDate() {
     return this.createDate;
   }
   
   public void setCreateDate(String createDate) {
     this.createDate = createDate;
   }
 }


