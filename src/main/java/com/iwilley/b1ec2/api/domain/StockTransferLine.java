 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 public class StockTransferLine
   extends B1EC2Object
 {
   private static final long serialVersionUID = 1L;
   @ApiField("TransferId")
   public int transferId;
   @ApiField("StockTransfer")
   public StockTransfer stockTransfer;
   @ApiField("LineNum")
   public int lineNum;
   @ApiField("ItemId")
   public int itemId;
   @ApiField("ItemInfo")
   public ItemInfo itemInfo;
   @ApiField("SkuCode")
   public String skuCode;
   @ApiField("SkuName")
   public String skuName;
   @ApiField("ItemName")
   public String itemName;
   @ApiField("Unit")
   public String unit;
   @ApiField("Quantity")
   public int quantity;
   @ApiField("FromWhsArea")
   public int fromWhsArea;
   @ApiField("WhsAreaFrom")
   public WhsArea whsAreaFrom;
   @ApiField("FromWhsAreaName")
   public String fromWhsAreaName;
   @ApiField("ToWhsArea")
   public int toWhsArea;
   @ApiField("WhsAreaTo")
   public WhsArea whsAreaTo;
   @ApiField("ToWhsAreaName")
   public String toWhsAreaName;
   @ApiField("LineMemo")
   public String lineMemo;
   @ApiField("SerialNumbers")
   public String serialNumbers;
   
   public int getTransferId() {
     return this.transferId;
   }
   
   public void setTransferId(int transferId) {
     this.transferId = transferId;
   }
   
   public StockTransfer getStockTransfer() {
     return this.stockTransfer;
   }
   
   public void setStockTransfer(StockTransfer stockTransfer) {
     this.stockTransfer = stockTransfer;
   }
   
   public int getLineNum() {
     return this.lineNum;
   }
   
   public void setLineNum(int lineNum) {
     this.lineNum = lineNum;
   }
   
   public int getItemId() {
     return this.itemId;
   }
   
   public void setItemId(int itemId) {
     this.itemId = itemId;
   }
   
   public ItemInfo getItemInfo() {
     return this.itemInfo;
   }
   
   public void setItemInfo(ItemInfo itemInfo) {
     this.itemInfo = itemInfo;
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
   
   public String getItemName() {
     return this.itemName;
   }
   
   public void setItemName(String itemName) {
     this.itemName = itemName;
   }
   
   public String getUnit() {
     return this.unit;
   }
   
   public void setUnit(String unit) {
     this.unit = unit;
   }
   
   public int getQuantity() {
     return this.quantity;
   }
   
   public void setQuantity(int quantity) {
     this.quantity = quantity;
   }
   
   public int getFromWhsArea() {
     return this.fromWhsArea;
   }
   
   public void setFromWhsArea(int fromWhsArea) {
     this.fromWhsArea = fromWhsArea;
   }
   
   public WhsArea getWhsAreaFrom() {
     return this.whsAreaFrom;
   }
   
   public void setWhsAreaFrom(WhsArea whsAreaFrom) {
     this.whsAreaFrom = whsAreaFrom;
   }
   
   public String getFromWhsAreaName() {
     return this.fromWhsAreaName;
   }
   
   public void setFromWhsAreaName(String fromWhsAreaName) {
     this.fromWhsAreaName = fromWhsAreaName;
   }
   
   public int getToWhsArea() {
     return this.toWhsArea;
   }
   
   public void setToWhsArea(int toWhsArea) {
     this.toWhsArea = toWhsArea;
   }
   
   public WhsArea getWhsAreaTo() {
     return this.whsAreaTo;
   }
   
   public void setWhsAreaTo(WhsArea whsAreaTo) {
     this.whsAreaTo = whsAreaTo;
   }
   
   public String getToWhsAreaName() {
     return this.toWhsAreaName;
   }
   
   public void setToWhsAreaName(String toWhsAreaName) {
     this.toWhsAreaName = toWhsAreaName;
   }
   
   public String getLineMemo() {
     return this.lineMemo;
   }
   
   public void setLineMemo(String lineMemo) {
     this.lineMemo = lineMemo;
   }
   
   public String getSerialNumbers() {
     return this.serialNumbers;
   }
   
   public void setSerialNumbers(String serialNumbers) {
     this.serialNumbers = serialNumbers;
   }
 }


