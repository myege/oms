 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 public class Express
   extends B1EC2Object
 {
   private static final long serialVersionUID = 895457757675322378L;
   @ApiField("ExpressId")
   public int expressId;
   @ApiField("ExpressCode")
   public String expressCode;
   @ApiField("ExpressName")
   public String expressName;
   @ApiField("ShipLevel")
   public int shipLevel;
   
   public int getExpressId() {
     return this.expressId;
   }
   
   public void setExpressId(int expressId) {
     this.expressId = expressId;
   }
   
   public String getExpressCode() {
     return this.expressCode;
   }
   
   public void setExpressCode(String expressCode) {
     this.expressCode = expressCode;
   }
   
   public String getExpressName() {
     return this.expressName;
   }
   
   public void setExpressName(String expressName) {
     this.expressName = expressName;
   }
   
   public int getShipLevel() {
     return this.shipLevel;
   }
   
   public void setShipLevel(int shipLevel) {
     this.shipLevel = shipLevel;
   }
 }


