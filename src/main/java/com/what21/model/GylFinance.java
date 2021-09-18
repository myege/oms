 package com.what21.model;
 public class GylFinance
 {
   private int id;
   private String gylFinanceNumber;
   private Double totalWorth;
   private Integer totalNum;
   private String merchant;
   private String merchantNum;
   private String createTime;
   private Integer confirmStatus;
   private String confirmUser;
   private String confirmTime;
   private Integer userId;
   
   public String getCreateTime() {
     return this.createTime;
   }
   
   public void setCreateTime(String createTime) {
     this.createTime = createTime;
   }
   
   public String getConfirmTime() {
     return this.confirmTime;
   }
   
   public void setConfirmTime(String confirmTime) {
     this.confirmTime = confirmTime;
   }
   
   public int getId() {
     return this.id;
   }
   
   public void setId(int id) {
     this.id = id;
   }
   
   public String getGylFinanceNumber() {
     return this.gylFinanceNumber;
   }
   
   public void setGylFinanceNumber(String gylFinanceNumber) {
     this.gylFinanceNumber = gylFinanceNumber;
   }
   
   public Double getTotalWorth() {
     return this.totalWorth;
   }
   
   public void setTotalWorth(Double totalWorth) {
     this.totalWorth = totalWorth;
   }
   
   public Integer getTotalNum() {
     return this.totalNum;
   }
   
   public void setTotalNum(Integer totalNum) {
     this.totalNum = totalNum;
   }
   
   public String getMerchant() {
     return this.merchant;
   }
   
   public void setMerchant(String merchant) {
     this.merchant = merchant;
   }
   
   public String getMerchantNum() {
     return this.merchantNum;
   }
   
   public void setMerchantNum(String merchantNum) {
     this.merchantNum = merchantNum;
   }
   
   public Integer getConfirmStatus() {
     return this.confirmStatus;
   }
   
   public void setConfirmStatus(Integer confirmStatus) {
     this.confirmStatus = confirmStatus;
   }
   
   public String getConfirmUser() {
     return this.confirmUser;
   }
   
   public void setConfirmUser(String confirmUser) {
     this.confirmUser = confirmUser;
   }
   
   public Integer getUserId() {
     return this.userId;
   }
   
   public void setUserId(Integer userId) {
     this.userId = userId;
   }
 }


