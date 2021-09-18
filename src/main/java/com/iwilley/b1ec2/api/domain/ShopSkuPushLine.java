 package com.iwilley.b1ec2.api.domain;
 public class ShopSkuPushLine
   extends B1EC2Object
 {
   private static final long serialVersionUID = 5589085414288678159L;
   public String shopSkuCode;
   public String outerId;
   public String property1;
   public String property2;
   public double price;
   public double size;
   public double weight;
   public int quantity;
   
   public String getShopSkuCode() {
     return this.shopSkuCode;
   }
   
   public String getOuterId() {
     return this.outerId;
   }
   
   public String getProperty1() {
     return this.property1;
   }
   
   public String getProperty2() {
     return this.property2;
   }
   
   public double getPrice() {
     return this.price;
   }
   
   public double getSize() {
     return this.size;
   }
   
   public double getWeight() {
     return this.weight;
   }
   
   public int getQuantity() {
     return this.quantity;
   }
   
   public void setShopSkuCode(String shopSkuCode) {
     this.shopSkuCode = shopSkuCode;
   }
   
   public void setOuterId(String outerId) {
     this.outerId = outerId;
   }
   
   public void setProperty1(String property1) {
     this.property1 = property1;
   }
   
   public void setProperty2(String property2) {
     this.property2 = property2;
   }
   
   public void setPrice(double price) {
     this.price = price;
   }
   
   public void setSize(double size) {
     this.size = size;
   }
   
   public void setWeight(double weight) {
     this.weight = weight;
   }
   
   public void setQuantity(int quantity) {
     this.quantity = quantity;
   }
 }


