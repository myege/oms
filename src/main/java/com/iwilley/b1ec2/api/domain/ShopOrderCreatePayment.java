 package com.iwilley.b1ec2.api.domain;
 public class ShopOrderCreatePayment
   extends B1EC2Object
 {
   private static final long serialVersionUID = -6513874017378216251L;
   public int paymentId;
   public double paymentTotal;
   public String paymentNo;
   
   public int getPaymentId() {
     return this.paymentId;
   }
   
   public double getPaymentTotal() {
     return this.paymentTotal;
   }
   
   public void setPaymentId(int paymentId) {
     this.paymentId = paymentId;
   }
   
   public void setPaymentTotal(double paymentTotal) {
     this.paymentTotal = paymentTotal;
   }
   
   public String getPaymentNo() {
     return this.paymentNo;
   }
   
   public void setPaymentNo(String paymentNo) {
     this.paymentNo = paymentNo;
   }
 }


