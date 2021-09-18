 package com.iwilley.b1ec2.api.domain;
 public class SkuCreateLine
   extends B1EC2Object
 {
   private static final long serialVersionUID = 3628741699151938463L;
   public String skuCode;
   public String barCode;
   public String property1;
   public String property2;
   public double salesPrice;
   public String unit;
   
   public String getSkuCode() {
     return this.skuCode;
   }
   
   public String getBarCode() {
     return this.barCode;
   }
   
   public String getProperty1() {
     return this.property1;
   }
   
   public String getProperty2() {
     return this.property2;
   }
   
   public double getSalesPrice() {
     return this.salesPrice;
   }
   
   public String getUnit() {
     return this.unit;
   }
   
   public void setSkuCode(String skuCode) {
     this.skuCode = skuCode;
   }
   
   public void setBarCode(String barCode) {
     this.barCode = barCode;
   }
   
   public void setProperty1(String property1) {
     this.property1 = property1;
   }
   
   public void setProperty2(String property2) {
     this.property2 = property2;
   }
   
   public void setSalesPrice(double salesPrice) {
     this.salesPrice = salesPrice;
   }
   
   public void setUnit(String unit) {
     this.unit = unit;
   }
 }


