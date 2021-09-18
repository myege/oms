 package com.iwilley.b1ec2.api.domain;
 public class PurchaseReceiptLine
   extends B1EC2Object
 {
   private static final long serialVersionUID = 895457757675322378L;
   public int lineNum;
   public int quantity;
   public String serialNumbers;
   
   public int getLineNum() {
     return this.lineNum;
   }
   
   public void setLineNum(int lineNum) {
     this.lineNum = lineNum;
   }
   
   public int getQuantity() {
     return this.quantity;
   }
   
   public void setQuantity(int quantity) {
     this.quantity = quantity;
   }
   
   public String getSerialNumbers() {
     return this.serialNumbers;
   }
   
   public void setSerialNumbers(String serialNumbers) {
     this.serialNumbers = serialNumbers;
   }
 }


