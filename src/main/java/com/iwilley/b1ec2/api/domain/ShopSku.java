 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 public class ShopSku
   extends B1EC2Object
 {
   private static final long serialVersionUID = 7180140976854749130L;
   @ApiField("ShopItemCode")
   public String shopItemCode;
   @ApiField("ShopSkuCode")
   public String shopSkuCode;
   @ApiField("OuterId")
   public String outerId;
   @ApiField("Property1")
   public String property1;
   @ApiField("Property2")
   public String property2;
   @ApiField("Properties")
   public String properties;
   @ApiField("Price")
   public double price;
   @ApiField("Size")
   public double size;
   @ApiField("Weight")
   public double weight;
   @ApiField("Quantity")
   public int quantity;
   
   public String getShopItemCode() {
     return this.shopItemCode;
   }
   
   public void setShopItemCode(String shopItemCode) {
     this.shopItemCode = shopItemCode;
   }
   
   public String getShopSkuCode() {
     return this.shopSkuCode;
   }
   
   public void setShopSkuCode(String shopSkuCode) {
     this.shopSkuCode = shopSkuCode;
   }
   
   public String getOuterId() {
     return this.outerId;
   }
   
   public void setOuterId(String outerId) {
     this.outerId = outerId;
   }
   
   public String getProperty1() {
     return this.property1;
   }
   
   public void setProperty1(String property1) {
     this.property1 = property1;
   }
   
   public String getProperty2() {
     return this.property2;
   }
   
   public void setProperty2(String property2) {
     this.property2 = property2;
   }
   
   public String getProperties() {
     return this.properties;
   }
   
   public void setProperties(String properties) {
     this.properties = properties;
   }
   
   public double getPrice() {
     return this.price;
   }
   
   public void setPrice(double price) {
     this.price = price;
   }
   
   public double getSize() {
     return this.size;
   }
   
   public void setSize(double size) {
     this.size = size;
   }
   
   public double getWeight() {
     return this.weight;
   }
   
   public void setWeight(double weight) {
     this.weight = weight;
   }
   
   public int getQuantity() {
     return this.quantity;
   }
   
   public void setQuantity(int quantity) {
     this.quantity = quantity;
   }
 }


