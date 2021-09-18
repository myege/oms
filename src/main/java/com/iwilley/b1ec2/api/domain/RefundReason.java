 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import java.util.Date;
 public class RefundReason
   extends B1EC2Object
 {
   private static final long serialVersionUID = -145901721452504280L;
   @ApiField("ReasonId")
   public int reasonId;
   @ApiField("ReasonName")
   public String reasonName;
   @ApiField("IsSystem")
   public Boolean isSystem;
   @ApiField("LastModifiedTime")
   public Date lastModifiedTime;
   @ApiField("LastModifiedUser")
   public String lastModifiedUser;
   
   public int getReasonId() {
     return this.reasonId;
   }
   
   public String getReasonName() {
     return this.reasonName;
   }
   
   public Boolean getIsSystem() {
     return this.isSystem;
   }
   
   public Date getLastModifiedTime() {
     return this.lastModifiedTime;
   }
   
   public String getLastModifiedUser() {
     return this.lastModifiedUser;
   }
   
   public void setReasonId(int reasonId) {
     this.reasonId = reasonId;
   }
   
   public void setReasonName(String reasonName) {
     this.reasonName = reasonName;
   }
   
   public void setIsSystem(Boolean isSystem) {
     this.isSystem = isSystem;
   }
   
   public void setLastModifiedTime(Date lastModifiedTime) {
     this.lastModifiedTime = lastModifiedTime;
   }
   
   public void setLastModifiedUser(String lastModifiedUser) {
     this.lastModifiedUser = lastModifiedUser;
   }
 }


