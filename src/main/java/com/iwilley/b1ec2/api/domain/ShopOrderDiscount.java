 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 public class ShopOrderDiscount
   extends B1EC2Object
 {
   private static final long serialVersionUID = 6810011033383777398L;
   @ApiField("ShopOrderNo")
   public String shopOrderNo;
   @ApiField("LineNum")
   public int lineNum;
   @ApiField("ShopOrder")
   public ShopOrder shopOrder;
   @ApiField("DiscountName")
   public String discountName;
   @ApiField("DiscountFee")
   public double discountFee;
   
   public String getShopOrderNo() {
     return this.shopOrderNo;
   }
   
   public void setShopOrderNo(String shopOrderNo) {
     this.shopOrderNo = shopOrderNo;
   }
   
   public int getLineNum() {
     return this.lineNum;
   }
   
   public void setLineNum(int lineNum) {
     this.lineNum = lineNum;
   }
   
   public ShopOrder getShopOrder() {
     return this.shopOrder;
   }
   
   public void setShopOrder(ShopOrder shopOrder) {
     this.shopOrder = shopOrder;
   }
   
   public String getDiscountName() {
     return this.discountName;
   }
   
   public void setDiscountName(String discountName) {
     this.discountName = discountName;
   }
   
   public double getDiscountFee() {
     return this.discountFee;
   }
   
   public void setDiscountFee(double discountFee) {
     this.discountFee = discountFee;
   }
 }


