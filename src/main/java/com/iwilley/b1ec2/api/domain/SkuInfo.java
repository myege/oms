 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 public class SkuInfo
   extends B1EC2Object
 {
   private static final long serialVersionUID = -2423076069218435545L;
   @ApiField("SkuId")
   public int skuId;
   @ApiField("SkuCode")
   public String skuCode;
   @ApiField("SkuName")
   public String skuName;
   @ApiField("BarCode")
   public String barCode;
   @ApiField("Property1")
   public String property1;
   @ApiField("Property2")
   public String property2;
   @ApiField("PurchasePrice")
   public double purchasePrice;
   @ApiField("SalesPrice")
   public double salesPrice;
   @ApiField("LowestPrice")
   public double lowestPrice;
   @ApiField("LowestPrice")
   public double marketPrice;
   @ApiField("LowestPrice")
   public double size;
   @ApiField("Weight")
   public double weight;
   
   public int getSkuId() {
     return this.skuId;
   }
   
   public void setSkuId(int skuId) {
     this.skuId = skuId;
   }
   
   public String getSkuCode() {
     return this.skuCode;
   }
   
   public void setSkuCode(String skuCode) {
     this.skuCode = skuCode;
   }
   
   public String getSkuName() {
     return this.skuName;
   }
   
   public void setSkuName(String skuName) {
     this.skuName = skuName;
   }
   
   public String getBarCode() {
     return this.barCode;
   }
   
   public void setBarCode(String barCode) {
     this.barCode = barCode;
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
   
   public double getPurchasePrice() {
     return this.purchasePrice;
   }
   
   public void setPurchasePrice(double purchasePrice) {
     this.purchasePrice = purchasePrice;
   }
   
   public double getSalesPrice() {
     return this.salesPrice;
   }
   
   public void setSalesPrice(double salesPrice) {
     this.salesPrice = salesPrice;
   }
   
   public double getLowestPrice() {
     return this.lowestPrice;
   }
   
   public void setLowestPrice(double lowestPrice) {
     this.lowestPrice = lowestPrice;
   }
   
   public double getMarketPrice() {
     return this.marketPrice;
   }
   
   public void setMarketPrice(double marketPrice) {
     this.marketPrice = marketPrice;
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
 }


