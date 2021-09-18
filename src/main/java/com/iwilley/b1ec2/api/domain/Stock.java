 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 public class Stock
   extends B1EC2Object
 {
   private static final long serialVersionUID = -7681650858616054478L;
   @ApiField("WhsId")
   public int whsId;
   @ApiField("SkuCode")
   public String skuCode;
   @ApiField("Quantity")
   public double quantity;
   
   public Integer getWhsId() {
     return Integer.valueOf(this.whsId);
   }
   
   public void setWhsId(Integer whsId) {
     this.whsId = whsId.intValue();
   }
   
   public String getSkuCode() {
     return this.skuCode;
   }
   
   public void setSkuCode(String skuCode) {
     this.skuCode = skuCode;
   }
   
   public double getQuantity() {
     return this.quantity;
   }
   
   public void setQuantity(double quantity) {
     this.quantity = quantity;
   }
 }


