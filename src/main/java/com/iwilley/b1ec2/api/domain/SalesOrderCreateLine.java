 package com.iwilley.b1ec2.api.domain;
 public class SalesOrderCreateLine
   extends B1EC2Object
 {
   private static final long serialVersionUID = -4296386619688195917L;
   public String skuCode;
   public int quantity;
   public double price;
   public String lineMemo;
   public String lineUdf1;
   public double lineUdf2;
   public boolean isVirtual;
   public String parentSku;
   
   public String getSkuCode() {
     return this.skuCode;
   }
   
   public void setSkuCode(String skuCode) {
     this.skuCode = skuCode;
   }
   
   public int getQuantity() {
     return this.quantity;
   }
   
   public void setQuantity(int quantity) {
     this.quantity = quantity;
   }
   
   public double getPrice() {
     return this.price;
   }
   
   public void setPrice(double price) {
     this.price = price;
   }
   
   public String getLineMemo() {
     return this.lineMemo;
   }
   
   public void setLineMemo(String lineMemo) {
     this.lineMemo = lineMemo;
   }
   
   public String getLineUdf1() {
     return this.lineUdf1;
   }
   
   public void setLineUdf1(String lineUdf1) {
     this.lineUdf1 = lineUdf1;
   }
   
   public double getLineUdf2() {
     return this.lineUdf2;
   }
   
   public void setLineUdf2(double lineUdf2) {
     this.lineUdf2 = lineUdf2;
   }
   
   public boolean getIsVirtual() {
     return this.isVirtual;
   }
   
   public void setIsVirtual(boolean isVirtual) {
     this.isVirtual = isVirtual;
   }
   
   public String getParentSku() {
     return this.parentSku;
   }
   
   public void setParentSku(String parentSku) {
     this.parentSku = parentSku;
   }
 }


