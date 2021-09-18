 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 public class WhsArea
   extends B1EC2Object
 {
   private static final long serialVersionUID = 2206401702136385704L;
   @ApiField("WhsAreaId")
   private int whsAreaId;
   @ApiField("WhsAreaCode")
   private String whsAreaCode;
   @ApiField("AreaName")
   private String areaName;
   @ApiField("IsAvailable")
   private Boolean available;
   
   public int getWhsAreaId() {
     return this.whsAreaId;
   }
   
   public String getWhsAreaCode() {
     return this.whsAreaCode;
   }
   
   public String getAreaName() {
     return this.areaName;
   }
   
   public Boolean getAvailable() {
     return this.available;
   }
   
   public void setWhsAreaId(int whsAreaId) {
     this.whsAreaId = whsAreaId;
   }
   
   public void setWhsAreaCode(String whsAreaCode) {
     this.whsAreaCode = whsAreaCode;
   }
   
   public void setAreaName(String areaName) {
     this.areaName = areaName;
   }
   
   public void setAvailable(Boolean available) {
     this.available = available;
   }
 }


