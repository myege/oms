 package com.what21.model;
 public class Log_inventory_action
 {
   private int id;
   private String itemsku;
   private String action;
   private String reason;
   private String number;
   private String createTime;
   private int userId;
   
   public int getId() {
     return this.id;
   }
   public void setId(int id) {
     this.id = id;
   }
   public String getItemsku() {
     return this.itemsku;
   }
   public void setItemsku(String itemsku) {
     this.itemsku = itemsku;
   }
   public String getAction() {
     return this.action;
   }
   public void setAction(String action) {
     this.action = action;
   }
   public String getNumber() {
     return this.number;
   }
   public void setNumber(String number) {
     this.number = number;
   }
   public String getCreateTime() {
     return this.createTime;
   }
   public void setCreateTime(String createTime) {
     this.createTime = createTime;
   }
   public int getUserId() {
     return this.userId;
   }
   public void setUserId(int userId) {
     this.userId = userId;
   }
   public String getReason() {
     return this.reason;
   }
   public void setReason(String reason) {
     this.reason = reason;
   }
 }


