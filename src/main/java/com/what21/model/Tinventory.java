 package com.what21.model;
 public class Tinventory
 {
   private int id;
   private String itemsku;
   private String itemName;
   private int totality;
   private int usedInventory;
   private int preUsedInventory;
   private int surplusInventory;
   private int warningInventory;
   private String overduedate;
   private int dateOfExpiration;
   private String storage;
   private String createDate;
   private String pc;
   private String date;
   private String merchant;
   private String userId;
   private int tota;
   
   public int getWarningInventory() {
     return this.warningInventory;
   }
   
   public String getStorage() {
     return this.storage;
   }
   public void setStorage(String storage) {
     this.storage = storage;
   }
   public int getDateOfExpiration() {
     return this.dateOfExpiration;
   }
   public void setDateOfExpiration(int dateOfExpiration) {
     this.dateOfExpiration = dateOfExpiration;
   }
 
   
   public String getCreateDate() {
     return this.createDate;
   }
   public void setCreateDate(String createDate) {
     this.createDate = createDate;
   }
   public String getOverduedate() {
     return this.overduedate;
   }
   public void setOverduedate(String overduedate) {
     this.overduedate = overduedate;
   }
   public void setWarningInventory(int warningInventory) {
     this.warningInventory = warningInventory;
   }
   
   public String getUserId() {
     return this.userId;
   }
   public void setUserId(String userId) {
     this.userId = userId;
   }
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
   public String getItemName() {
     return this.itemName;
   }
   public void setItemName(String itemName) {
     this.itemName = itemName;
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
   public int getTota() {
     return this.tota;
   }
   public void setTota(int tota) {
     this.tota = tota;
   }
 }


