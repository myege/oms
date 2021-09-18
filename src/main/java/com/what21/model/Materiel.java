 package com.what21.model;
 
 import java.util.Date;
 public class Materiel
 {
   private int id;
   private String materielsku;
   private String materielName;
   private int totality;
   private int usedInventory;
   private int preUsedInventory;
   private int surplusInventory;
   private String pc;
   private String date;
   private String merchant;
   private String userId;
   private Date createDate;
   
   public Date getCreateDate() {
     return this.createDate;
   }
   
   public void setCreateDate(Date createDate) {
     this.createDate = createDate;
   }
   
   public int getId() {
     return this.id;
   }
   
   public void setId(int id) {
     this.id = id;
   }
   
   public String getMaterielsku() {
     return this.materielsku;
   }
   
   public void setMaterielsku(String materielsku) {
     this.materielsku = materielsku;
   }
   
   public String getMaterielName() {
     return this.materielName;
   }
   
   public void setMaterielName(String materielName) {
     this.materielName = materielName;
   }
   
   public int getTotality() {
     return this.totality;
   }
   
   public void setTotality(int totality) {
     this.totality = totality;
   }
   
   public int getUsedInventory() {
     return this.usedInventory;
   }
   
   public void setUsedInventory(int usedInventory) {
     this.usedInventory = usedInventory;
   }
   
   public int getPreUsedInventory() {
     return this.preUsedInventory;
   }
   
   public void setPreUsedInventory(int preUsedInventory) {
     this.preUsedInventory = preUsedInventory;
   }
   
   public int getSurplusInventory() {
     return this.surplusInventory;
   }
   
   public void setSurplusInventory(int surplusInventory) {
     this.surplusInventory = surplusInventory;
   }
   
   public String getPc() {
     return this.pc;
   }
   
   public void setPc(String pc) {
     this.pc = pc;
   }
   
   public String getDate() {
     return this.date;
   }
   
   public void setDate(String date) {
     this.date = date;
   }
   
   public String getMerchant() {
     return this.merchant;
   }
   
   public void setMerchant(String merchant) {
     this.merchant = merchant;
   }
   
   public String getUserId() {
     return this.userId;
   }
   
   public void setUserId(String userId) {
     this.userId = userId;
   }
 }


