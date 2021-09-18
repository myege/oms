 package com.iwilley.b1ec2.api.domain;
 
 public class ShopOrderCreateDiscount
   extends B1EC2Object
 {
   private static final long serialVersionUID = -4461956473045589292L;
   public String discountName;
   public double discountFee;
   
   public String getDiscountName() {
     return this.discountName;
   }
   
   public double getDiscountFee() {
     return this.discountFee;
   }
   
   public void setDiscountName(String discountName) {
     this.discountName = discountName;
   }
   
   public void setDiscountFee(double discountFee) {
     this.discountFee = discountFee;
   }
 }


