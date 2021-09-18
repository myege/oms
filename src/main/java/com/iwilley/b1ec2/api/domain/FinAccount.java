 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 public class FinAccount
   extends B1EC2Object
 {
   private static final long serialVersionUID = 7316595180605690059L;
   @ApiField("FinJournalId")
   public int finJournalId;
   @ApiField("AccountCode")
   public String accountCode;
   @ApiField("Type")
   public int type;
   @ApiField("AccountName")
   public String accountName;
   @ApiField("Memo")
   public String memo;
   
   public int getFinJournalId() {
     return this.finJournalId;
   }
   
   public void setFinJournalId(int finJournalId) {
     this.finJournalId = finJournalId;
   }
   
   public String getAccountCode() {
     return this.accountCode;
   }
   
   public void setAccountCode(String accountCode) {
     this.accountCode = accountCode;
   }
   
   public int getType() {
     return this.type;
   }
   
   public void setType(int type) {
     this.type = type;
   }
   
   public String getAccountName() {
     return this.accountName;
   }
   
   public void setAccountName(String accountName) {
     this.accountName = accountName;
   }
   
   public String getMemo() {
     return this.memo;
   }
   
   public void setMemo(String memo) {
     this.memo = memo;
   }
 }


