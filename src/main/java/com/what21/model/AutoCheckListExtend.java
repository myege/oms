 package com.what21.model;
 public class AutoCheckListExtend
   extends AutoCheckList
 {
   private String mailNo;
   private String txLogisticID;
   private Integer itemCount;
   private String merchantNum;
   private Double worth;
   private String buyerName;
   private String buyerIdNumber;
   private String returninfo;
   
   public String getReturninfo() {
     return this.returninfo;
   }
   public void setReturninfo(String returninfo) {
     this.returninfo = returninfo;
   }
 
 
   
   public String getMailNo() {
     return this.mailNo;
   }
   public void setMailNo(String mailNo) {
     this.mailNo = mailNo;
   }
   public String getTxLogisticID() {
     return this.txLogisticID;
   }
   public void setTxLogisticID(String txLogisticID) {
     this.txLogisticID = txLogisticID;
   }
   public Integer getItemCount() {
     return this.itemCount;
   }
   public void setItemCount(Integer itemCount) {
     this.itemCount = itemCount;
   }
   public String getMerchantNum() {
     return this.merchantNum;
   }
   public void setMerchantNum(String merchantNum) {
     this.merchantNum = merchantNum;
   }
   public Double getWorth() {
     return this.worth;
   }
   public void setWorth(Double worth) {
     this.worth = worth;
   }
   public String getBuyerName() {
     return this.buyerName;
   }
   public void setBuyerName(String buyerName) {
     this.buyerName = buyerName;
   }
   public String getBuyerIdNumber() {
     return this.buyerIdNumber;
   }
   public void setBuyerIdNumber(String buyerIdNumber) {
     this.buyerIdNumber = buyerIdNumber;
   }
 }


