 package com.iwilley.b1ec2.api.domain;
 
 
 
 public class UnknownPackageItemLine
   extends B1EC2Object
 {
   private static final long serialVersionUID = 3404409893804963264L;
   public String skuCode;
   public int quantity;
   
   public String getSkuCode() {
     return this.skuCode;
   }
   
   public int getQuantity() {
     return this.quantity;
   }
   
   public void setSkuCode(String skuCode) {
     this.skuCode = skuCode;
   }
   
   public void setQuantity(int quantity) {
     this.quantity = quantity;
   }
 }


