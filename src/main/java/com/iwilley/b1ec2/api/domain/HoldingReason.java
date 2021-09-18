 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 public class HoldingReason
   extends B1EC2Object
 {
   private static final long serialVersionUID = 9087749773348764448L;
   @ApiField("ReasonId")
   public int reasonId;
   @ApiField("ReasonName")
   public String reasonName;
   
   public int getReasonId() {
     return this.reasonId;
   }
   
   public void setReasonId(int reasonId) {
     this.reasonId = reasonId;
   }
   
   public String getReasonName() {
     return this.reasonName;
   }
   
   public void setReasonName(String reasonName) {
     this.reasonName = reasonName;
   }
 }


