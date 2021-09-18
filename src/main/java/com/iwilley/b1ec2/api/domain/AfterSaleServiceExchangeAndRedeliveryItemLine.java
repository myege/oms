 package com.iwilley.b1ec2.api.domain;
 public class AfterSaleServiceExchangeAndRedeliveryItemLine
   extends B1EC2Object
 {
   private static final long serialVersionUID = -5638139791392925877L;
   public int orderLineNum;
   public int quantity;
   
   public int getOrderLineNum() {
     return this.orderLineNum;
   }
   
   public void setOrderLineNum(int orderLineNum) {
     this.orderLineNum = orderLineNum;
   }
   
   public int getQuantity() {
     return this.quantity;
   }
   
   public void setQuantity(int quantity) {
     this.quantity = quantity;
   }
 }


