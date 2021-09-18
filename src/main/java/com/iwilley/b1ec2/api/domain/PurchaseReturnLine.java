 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import java.util.Date;
 public class PurchaseReturnLine
   extends B1EC2Object
 {
   private static final long serialVersionUID = -8109499955122936213L;
   @ApiField("ReturnId")
   public int returnId;
   @ApiField("LineNum")
   public int lineNum;
   @ApiField("BaseOrderId")
   public Integer baseOrderId;
   @ApiField("BaseLineId")
   public Integer baseLineId;
   @ApiField("BaseOrderNo")
   public String baseOrderNo;
   @ApiField("ItemId")
   public Integer itemId;
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
   public double quantity;
   @ApiField("StockPrice")
   public double stockPrice;
   @ApiField("StockValue")
   public double stockValue;
   @ApiField("Price")
   public double price;
   @ApiField("LineTotal")
   public double lineTotal;
   @ApiField("WhsAreaId")
   public int whsAreaId;
   @ApiField("WhsArea")
   public WhsArea whsArea;
   @ApiField("LineMemo")
   public String lineMemo;
   @ApiField("ItemInfoMemo")
   public String itemInfoMemo;
   @ApiField("BatchProductDate")
   public Date batchProductDate;
   @ApiField("BatchExpiryDate")
   public Date batchExpiryDate;
   @ApiField("BatchMemo")
   public String batchMemo;
   @ApiField("NeedSerial")
   public Boolean needSerial;
   @ApiField("SerialNumbers")
   public String serialNumbers;
   @ApiField("Proportion")
   public int proportion;
   @ApiField("BarCode")
   public String barCode;
   
   public int getReturnId() {
     return this.returnId;
   }
   
   public int getLineNum() {
     return this.lineNum;
   }
   
   public Integer getBaseOrderId() {
     return this.baseOrderId;
   }
   
   public Integer getBaseLineId() {
     return this.baseLineId;
   }
   
   public String getBaseOrderNo() {
     return this.baseOrderNo;
   }
   
   public Integer getItemId() {
     return this.itemId;
   }
   
   public ItemInfo getItemInfo() {
     return this.itemInfo;
   }
   
   public String getSkuCode() {
     return this.skuCode;
   }
   
   public String getSkuName() {
     return this.skuName;
   }
   
   public String getItemName() {
     return this.itemName;
   }
   
   public String getUnit() {
     return this.unit;
   }
   
   public double getQuantity() {
     return this.quantity;
   }
   
   public double getStockPrice() {
     return this.stockPrice;
   }
   
   public double getStockValue() {
     return this.stockValue;
   }
   
   public double getPrice() {
     return this.price;
   }
   
   public double getLineTotal() {
     return this.lineTotal;
   }
   
   public int getWhsAreaId() {
     return this.whsAreaId;
   }
   
   public WhsArea getWhsArea() {
     return this.whsArea;
   }
   
   public String getLineMemo() {
     return this.lineMemo;
   }
   
   public String getItemInfoMemo() {
     return this.itemInfoMemo;
   }
   
   public Date getBatchProductDate() {
     return this.batchProductDate;
   }
   
   public Date getBatchExpiryDate() {
     return this.batchExpiryDate;
   }
   
   public String getBatchMemo() {
     return this.batchMemo;
   }
   
   public Boolean getNeedSerial() {
     return this.needSerial;
   }
   
   public String getSerialNumbers() {
     return this.serialNumbers;
   }
   
   public int getProportion() {
     return this.proportion;
   }
   
   public String getBarCode() {
     return this.barCode;
   }
   
   public void setReturnId(int returnId) {
     this.returnId = returnId;
   }
   
   public void setLineNum(int lineNum) {
     this.lineNum = lineNum;
   }
   
   public void setBaseOrderId(Integer baseOrderId) {
     this.baseOrderId = baseOrderId;
   }
   
   public void setBaseLineId(Integer baseLineId) {
     this.baseLineId = baseLineId;
   }
   
   public void setBaseOrderNo(String baseOrderNo) {
     this.baseOrderNo = baseOrderNo;
   }
   
   public void setItemId(Integer itemId) {
     this.itemId = itemId;
   }
   
   public void setItemInfo(ItemInfo itemInfo) {
     this.itemInfo = itemInfo;
   }
   
   public void setSkuCode(String skuCode) {
     this.skuCode = skuCode;
   }
   
   public void setSkuName(String skuName) {
     this.skuName = skuName;
   }
   
   public void setItemName(String itemName) {
     this.itemName = itemName;
   }
   
   public void setUnit(String unit) {
     this.unit = unit;
   }
   
   public void setQuantity(double quantity) {
     this.quantity = quantity;
   }
   
   public void setStockPrice(double stockPrice) {
     this.stockPrice = stockPrice;
   }
   
   public void setStockValue(double stockValue) {
     this.stockValue = stockValue;
   }
   
   public void setPrice(double price) {
     this.price = price;
   }
   
   public void setLineTotal(double lineTotal) {
     this.lineTotal = lineTotal;
   }
   
   public void setWhsAreaId(int whsAreaId) {
     this.whsAreaId = whsAreaId;
   }
   
   public void setWhsArea(WhsArea whsArea) {
     this.whsArea = whsArea;
   }
   
   public void setLineMemo(String lineMemo) {
     this.lineMemo = lineMemo;
   }
   
   public void setItemInfoMemo(String itemInfoMemo) {
     this.itemInfoMemo = itemInfoMemo;
   }
   
   public void setBatchProductDate(Date batchProductDate) {
     this.batchProductDate = batchProductDate;
   }
   
   public void setBatchExpiryDate(Date batchExpiryDate) {
     this.batchExpiryDate = batchExpiryDate;
   }
   
   public void setBatchMemo(String batchMemo) {
     this.batchMemo = batchMemo;
   }
   
   public void setNeedSerial(Boolean needSerial) {
     this.needSerial = needSerial;
   }
   
   public void setSerialNumbers(String serialNumbers) {
     this.serialNumbers = serialNumbers;
   }
   
   public void setProportion(int proportion) {
     this.proportion = proportion;
   }
   
   public void setBarCode(String barCode) {
     this.barCode = barCode;
   }
 }


