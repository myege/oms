package com.iwilley.b1ec2.api.domain;
 
import com.iwilley.b1ec2.api.internal.mapping.ApiField;
public class AfterSaleItemLine
  extends B1EC2Object
{
  private static final long serialVersionUID = -6148452458041696445L;
  @ApiField("AfterSaleServiceId")
  public int afterSaleServiceId;
  @ApiField("LineNum")
  public int lineNum;
  @ApiField("Version")
  public int version;
  @ApiField("ShopOrderNo")
  public String shopOrderNo;
  @ApiField("ShopLineNo")
  public String shopLineNo;
  @ApiField("ItemId")
  public Integer itemId;
  @ApiField("ItemInfo")
  public ItemInfo itemInfo;
  @ApiField("SkuCode")
  public String skuCode;
  @ApiField("ItemName")
  public String itemName;
  @ApiField("BarCode")
  public String barCode;
  @ApiField("ExpressId")
  public String skuName;
  @ApiField("Quantity")
  public double quantity;
  @ApiField("Price")
  public double price;
  @ApiField("Unit")
  public String unit;
  @ApiField("LineTotal")
  public double lineTotal;
  @ApiField("StockPrice")
  public double stockPrice;
  @ApiField("StockValue")
  public double stockValue;
  @ApiField("NeedSerial")
  public boolean needSerial;
  @ApiField("SerialNumbers")
  public String serialNumbers;
  @ApiField("SalesOrderSN")
  public String salesOrderSN;
  
  public int getAfterSaleServiceId() {
    return this.afterSaleServiceId;
  }
  
  public void setAfterSaleServiceId(int afterSaleServiceId) {
    this.afterSaleServiceId = afterSaleServiceId;
  }
  
  public int getLineNum() {
    return this.lineNum;
  }
  
  public void setLineNum(int lineNum) {
    this.lineNum = lineNum;
  }
 
  
  public int getVersion() {
    return this.version;
  }
  
  public void setVersion(int version) {
    this.version = version;
  }
  
  public String getBarCode() {
    return this.barCode;
  }
  
  public void setBarCode(String barCode) {
    this.barCode = barCode;
  }
 
  
  public String getShopOrderNo() {
    return this.shopOrderNo;
  }
  
  public void setShopOrderNo(String shopOrderNo) {
    this.shopOrderNo = shopOrderNo;
  }
  
  public String getShopLineNo() {
    return this.shopLineNo;
  }
  
  public void setShopLineNo(String shopLineNo) {
    this.shopLineNo = shopLineNo;
  }
  
  public Integer getItemId() {
    return this.itemId;
  }
  
  public void setItemId(Integer itemId) {
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
  
  public double getQuantity() {
    return this.quantity;
  }
  
  public void setQuantity(double quantity) {
    this.quantity = quantity;
  }
  
  public double getPrice() {
    return this.price;
  }
  
  public void setPrice(double price) {
    this.price = price;
  }
  
  public String getUnit() {
    return this.unit;
  }
  
  public void setUnit(String unit) {
    this.unit = unit;
  }
  
  public double getLineTotal() {
    return this.lineTotal;
  }
  
  public void setLineTotal(double lineTotal) {
    this.lineTotal = lineTotal;
  }
  
  public double getStockPrice() {
    return this.stockPrice;
  }
  
  public void setStockPrice(double stockPrice) {
    this.stockPrice = stockPrice;
  }
  
  public double getStockValue() {
    return this.stockValue;
  }
  
  public void setStockValue(double stockValue) {
    this.stockValue = stockValue;
  }
  
  public boolean isNeedSerial() {
    return this.needSerial;
  }
  
  public void setNeedSerial(boolean needSerial) {
    this.needSerial = needSerial;
  }
  
  public String getSerialNumbers() {
    return this.serialNumbers;
  }
  
  public void setSerialNumbers(String serialNumbers) {
    this.serialNumbers = serialNumbers;
  }
  
  public String getSalesOrderSN() {
    return this.salesOrderSN;
  }
  
  public void setSalesOrderSN(String salesOrderSN) {
    this.salesOrderSN = salesOrderSN;
  }
}


