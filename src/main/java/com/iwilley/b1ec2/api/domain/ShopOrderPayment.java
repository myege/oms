 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 
 public class ShopOrderPayment
   extends B1EC2Object {
   private static final long serialVersionUID = 9148055093585047262L;
   @ApiField("ShopOrderNo")
   public String shopOrderNo;
   @ApiField("LineNum")
   public int lineNum;
   @ApiField("ShopOrder")
   public ShopOrder shopOrder;
   @ApiField("paymentId")
   public int PaymentId;
   @ApiField("PaymentTotal")
   public double paymentTotal;
   
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
   
   public int getPaymentId() {
     return this.PaymentId;
   }
   
   public void setPaymentId(int paymentId) {
     this.PaymentId = paymentId;
   }
   
   public double getPaymentTotal() {
     return this.paymentTotal;
   }
   
   public void setPaymentTotal(double paymentTotal) {
     this.paymentTotal = paymentTotal;
   }
 }


