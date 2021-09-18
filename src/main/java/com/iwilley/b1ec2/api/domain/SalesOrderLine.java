 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 public class SalesOrderLine
   extends B1EC2Object
 {
   private static final long serialVersionUID = 3014118401019289394L;
   @ApiField("OrderId")
   public int orderId;
   @ApiField("LineNum")
   public int lineNum;
   @ApiField("Version")
   public int version;
   @ApiField("LineStatus")
   public int lineStatus;
   @ApiField("ShopOrderNo")
   public String shopOrderNo;
   @ApiField("ShopLineNo")
   public String shopLineNo;
   @ApiField("ShopLineStatus")
   public String shopLineStatus;
   @ApiField("ShopItemName")
   public String shopItemName;
   @ApiField("ItemId")
   public Integer itemId;
   @ApiField("ItemInfo")
   public ItemInfo iteminfo;
   @ApiField("ItemName")
   public String itemName;
   @ApiField("SkuCode")
   public String skuCode;
   @ApiField("SkuName")
   public String skuName;
   @ApiField("BarCode")
   public String barCode;
   @ApiField("Unit")
   public String unit;
   @ApiField("Price")
   public double price;
   @ApiField("Quantity")
   public double quantity;
   @ApiField("LineDiscountFee")
   public double lineDiscountFee;
   @ApiField("LineAdjustFee")
   public double lineAdjustFee;
   @ApiField("LineTotal")
   public double lineTotal;
   @ApiField("StockPrice")
   public double stockPrice;
   @ApiField("StockValue")
   public double stockValue;
   @ApiField("IsVirtual")
   public Boolean virtual;
   @ApiField("IsSerialNumber")
   public Boolean serialNumber;
   @ApiField("SerialNumbers")
   public String serialNumbers;
   @ApiField("Location")
   public String location;
   @ApiField("LineUdf1")
   public String lineUdf1;
   @ApiField("LineUdf2")
   public Double lineUdf2;
   @ApiField("LineMemo")
   public String lineMemo;
   
   public int getOrderId() {
     return this.orderId;
   }
   
   public int getLineNum() {
     return this.lineNum;
   }
   
   public int getVersion() {
     return this.version;
   }
   
   public int getLineStatus() {
     return this.lineStatus;
   }
   
   public String getShopOrderNo() {
     return this.shopOrderNo;
   }
   
   public String getShopLineNo() {
     return this.shopLineNo;
   }
   
   public String getShopLineStatus() {
     return this.shopLineStatus;
   }
   
   public String getShopItemName() {
     return this.shopItemName;
   }
   
   public Integer getItemId() {
     return this.itemId;
   }
   
   public ItemInfo getIteminfo() {
     return this.iteminfo;
   }
   
   public String getItemName() {
     return this.itemName;
   }
   
   public String getSkuCode() {
     return this.skuCode;
   }
   
   public String getSkuName() {
     return this.skuName;
   }
   
   public String getBarCode() {
     return this.barCode;
   }
   
   public String getUnit() {
     return this.unit;
   }
   
   public double getPrice() {
     return this.price;
   }
   
   public double getQuantity() {
     return this.quantity;
   }
   
   public double getLineDiscountFee() {
     return this.lineDiscountFee;
   }
   
   public double getLineAdjustFee() {
     return this.lineAdjustFee;
   }
   
   public double getLineTotal() {
     return this.lineTotal;
   }
   
   public double getStockPrice() {
     return this.stockPrice;
   }
   
   public double getStockValue() {
     return this.stockValue;
   }
   
   public Boolean getVirtual() {
     return this.virtual;
   }
   
   public Boolean getSerialNumber() {
     return this.serialNumber;
   }
   
   public String getSerialNumbers() {
     return this.serialNumbers;
   }
   
   public String getLocation() {
     return this.location;
   }
   
   public String getLineUdf1() {
     return this.lineUdf1;
   }
   
   public Double getLineUdf2() {
     return this.lineUdf2;
   }
   
   public String getLineMemo() {
     return this.lineMemo;
   }
   
   public void setOrderId(int orderId) {
     this.orderId = orderId;
   }
   
   public void setLineNum(int lineNum) {
     this.lineNum = lineNum;
   }
   
   public void setVersion(int version) {
     this.version = version;
   }
   
   public void setLineStatus(int lineStatus) {
     this.lineStatus = lineStatus;
   }
   
   public void setShopOrderNo(String shopOrderNo) {
     this.shopOrderNo = shopOrderNo;
   }
   
   public void setShopLineNo(String shopLineNo) {
     this.shopLineNo = shopLineNo;
   }
   
   public void setShopLineStatus(String shopLineStatus) {
     this.shopLineStatus = shopLineStatus;
   }
   
   public void setShopItemName(String shopItemName) {
     this.shopItemName = shopItemName;
   }
   
   public void setItemId(Integer itemId) {
     this.itemId = itemId;
   }
   
   public void setIteminfo(ItemInfo iteminfo) {
     this.iteminfo = iteminfo;
   }
   
   public void setItemName(String itemName) {
     this.itemName = itemName;
   }
   
   public void setSkuCode(String skuCode) {
     this.skuCode = skuCode;
   }
   
   public void setSkuName(String skuName) {
     this.skuName = skuName;
   }
   
   public void setBarCode(String barCode) {
     this.barCode = barCode;
   }
   
   public void setUnit(String unit) {
     this.unit = unit;
   }
   
   public void setPrice(double price) {
     this.price = price;
   }
   
   public void setQuantity(double quantity) {
     this.quantity = quantity;
   }
   
   public void setLineDiscountFee(double lineDiscountFee) {
     this.lineDiscountFee = lineDiscountFee;
   }
   
   public void setLineAdjustFee(double lineAdjustFee) {
     this.lineAdjustFee = lineAdjustFee;
   }
   
   public void setLineTotal(double lineTotal) {
     this.lineTotal = lineTotal;
   }
   
   public void setStockPrice(double stockPrice) {
     this.stockPrice = stockPrice;
   }
   
   public void setStockValue(double stockValue) {
     this.stockValue = stockValue;
   }
   
   public void setVirtual(Boolean virtual) {
     this.virtual = virtual;
   }
   
   public void setSerialNumber(Boolean serialNumber) {
     this.serialNumber = serialNumber;
   }
   
   public void setSerialNumbers(String serialNumbers) {
     this.serialNumbers = serialNumbers;
   }
   
   public void setLocation(String location) {
     this.location = location;
   }
   
   public void setLineUdf1(String lineUdf1) {
     this.lineUdf1 = lineUdf1;
   }
   
   public void setLineUdf2(Double lineUdf2) {
     this.lineUdf2 = lineUdf2;
   }
   
   public void setLineMemo(String lineMemo) {
     this.lineMemo = lineMemo;
   }
 }


