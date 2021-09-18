 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import java.util.Date;
 public class FinJournal
   extends B1EC2Object
 {
   private static final long serialVersionUID = 2985147578238179695L;
   @ApiField("FinJournalId")
   public int finJournalId;
   @ApiField("BusinessId")
   public String businessId;
   @ApiField("ShopOrderId")
   public String shopOrderId;
   @ApiField("ShopId")
   public int shopId;
   @ApiField("AccountCode")
   public String accountCode;
   @ApiField("FinAccount")
   public FinAccount finAccount;
   @ApiField("Amount")
   public double amount;
   @ApiField("AccountBalance")
   public double accountBalance;
   @ApiField("TransferStatus")
   public int transferStatus;
   @ApiField("PostDate")
   public Date postDate;
   @ApiField("CreateTime")
   public Date createTime;
   @ApiField("Memo")
   public String memo;
   @ApiField("LastModifiedTime")
   public Date lastModifiedTime;
   @ApiField("LastModifiedUser")
   public String lastModifiedUser;
   
   public int getFinJournalId() {
     return this.finJournalId;
   }
   
   public void setFinJournalId(int finJournalId) {
     this.finJournalId = finJournalId;
   }
   
   public String getBusinessId() {
     return this.businessId;
   }
   
   public void setBusinessId(String businessId) {
     this.businessId = businessId;
   }
   
   public String getShopOrderId() {
     return this.shopOrderId;
   }
   
   public void setShopOrderId(String shopOrderId) {
     this.shopOrderId = shopOrderId;
   }
   
   public int getShopId() {
     return this.shopId;
   }
   
   public void setShopId(int shopId) {
     this.shopId = shopId;
   }
   
   public String getAccountCode() {
     return this.accountCode;
   }
   
   public void setAccountCode(String accountCode) {
     this.accountCode = accountCode;
   }
   
   public FinAccount getFinAccount() {
     return this.finAccount;
   }
   
   public void setFinAccount(FinAccount finAccount) {
     this.finAccount = finAccount;
   }
   
   public double getAmount() {
     return this.amount;
   }
   
   public void setAmount(double amount) {
     this.amount = amount;
   }
   
   public double getAccountBalance() {
     return this.accountBalance;
   }
   
   public void setAccountBalance(double accountBalance) {
     this.accountBalance = accountBalance;
   }
   
   public int getTransferStatus() {
     return this.transferStatus;
   }
   
   public void setTransferStatus(int transferStatus) {
     this.transferStatus = transferStatus;
   }
   
   public Date getPostDate() {
     return this.postDate;
   }
   
   public void setPostDate(Date postDate) {
     this.postDate = postDate;
   }
   
   public Date getCreateTime() {
     return this.createTime;
   }
   
   public void setCreateTime(Date createTime) {
     this.createTime = createTime;
   }
   
   public String getMemo() {
     return this.memo;
   }
   
   public void setMemo(String memo) {
     this.memo = memo;
   }
   
   public Date getLastModifiedTime() {
     return this.lastModifiedTime;
   }
   
   public void setLastModifiedTime(Date lastModifiedTime) {
     this.lastModifiedTime = lastModifiedTime;
   }
   
   public String getLastModifiedUser() {
     return this.lastModifiedUser;
   }
   
   public void setLastModifiedUser(String lastModifiedUser) {
     this.lastModifiedUser = lastModifiedUser;
   }
 }


