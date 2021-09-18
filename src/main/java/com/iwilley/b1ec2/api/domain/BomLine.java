 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 public class BomLine
   extends B1EC2Object
 {
   private static final long serialVersionUID = -2956796093790463332L;
   @ApiField("BomId")
   public int bomId;
   @ApiField("LineNum")
   public int lineNum;
   @ApiField("LineItemId")
   public int lineItemId;
   @ApiField("LineItem")
   public ItemInfo lineItem;
   @ApiField("SkuCode")
   public String skuCode;
   @ApiField("ItemName")
   public String itemName;
   @ApiField("SkuName")
   public String skuName;
   @ApiField("Quantity")
   public int quantity;
   @ApiField("Unit")
   public String unit;
   @ApiField("SalesPrice")
   public double salesPrice;
   
   public int getBomId() {
     return this.bomId;
   }
   
   public void setBomId(int bomId) {
     this.bomId = bomId;
   }
   
   public int getLineNum() {
     return this.lineNum;
   }
   
   public void setLineNum(int lineNum) {
     this.lineNum = lineNum;
   }
   
   public int getLineItemId() {
     return this.lineItemId;
   }
   
   public void setLineItemId(int lineItemId) {
     this.lineItemId = lineItemId;
   }
   
   public ItemInfo getLineItem() {
     return this.lineItem;
   }
   
   public void setLineItem(ItemInfo lineItem) {
     this.lineItem = lineItem;
   }
   
   public String getSkuCode() {
     return this.skuCode;
   }
   
   public void setSkuCode(String skuCode) {
     this.skuCode = skuCode;
   }
   
   public String getItemName() {
     return this.itemName;
   }
   
   public void setItemName(String itemName) {
     this.itemName = itemName;
   }
   
   public String getSkuName() {
     return this.skuName;
   }
   
   public void setSkuName(String skuName) {
     this.skuName = skuName;
   }
   
   public int getQuantity() {
     return this.quantity;
   }
   
   public void setQuantity(int quantity) {
     this.quantity = quantity;
   }
   
   public String getUnit() {
     return this.unit;
   }
   
   public void setUnit(String unit) {
     this.unit = unit;
   }
   
   public double getSalesPrice() {
     return this.salesPrice;
   }
   
   public void setSalesPrice(double salesPrice) {
     this.salesPrice = salesPrice;
   }
 }


