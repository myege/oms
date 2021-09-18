 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import java.util.Date;
 public class PurchaseOrderLine
   extends B1EC2Object
 {
   private static final long serialVersionUID = 2182574868917945645L;
   @ApiField("OrderId")
   public int orderId;
   @ApiField("LineNum")
   public int lineNum;
   @ApiField("Version")
   public int version;
   @ApiField("LineStatus")
   public int lineStatus;
   @ApiField("ItemId")
   public Integer itemId;
   @ApiField("ItemInfo")
   public ItemInfo itemInfo;
   @ApiField("SkuCode")
   public String skuCode;
   @ApiField("ItemName")
   public String itemName;
   @ApiField("ItemCode")
   public String itemCode;
   @ApiField("SkuName")
   public String skuName;
   @ApiField("Quantity")
   public int quantity;
   @ApiField("Price")
   public double price;
   @ApiField("LineTotal")
   public double lineTotal;
   @ApiField("ExpectedReceiptDate")
   public Date expectedReceiptDate;
   @ApiField("LineMemo")
   public String lineMemo;
   @ApiField("ItemInfoMemo")
   public String itemInfoMemo;
   @ApiField("ReceivedQty")
   public int receivedQty;
   @ApiField("ReceiptDate")
   public Date receiptDate;
   @ApiField("Unit")
   public String unit;
   @ApiField("Proportion")
   public int proportion;
   @ApiField("BarCode")
   public String barCode;
   
   public String getBarCode() {
     return this.barCode;
   }
   
   public void setBarCode(String barCode) {
     this.barCode = barCode;
   }
   
   public int getOrderId() {
     return this.orderId;
   }
   
   public void setOrderId(int orderId) {
     this.orderId = orderId;
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
   
   public int getLineStatus() {
     return this.lineStatus;
   }
   
   public void setLineStatus(int lineStatus) {
     this.lineStatus = lineStatus;
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
   
   public String getItemCode() {
     return this.itemCode;
   }
   
   public void setItemCode(String itemCode) {
     this.itemCode = itemCode;
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
   
   public int getReceivedQty() {
     return this.receivedQty;
   }
   
   public void setReceivedQty(int receivedQty) {
     this.receivedQty = receivedQty;
   }
   
   public int getProportion() {
     return this.proportion;
   }
   
   public void setProportion(int proportion) {
     this.proportion = proportion;
   }
   
   public double getPrice() {
     return this.price;
   }
   
   public void setPrice(double price) {
     this.price = price;
   }
   
   public double getLineTotal() {
     return this.lineTotal;
   }
   
   public void setLineTotal(double lineTotal) {
     this.lineTotal = lineTotal;
   }
   
   public Date getExpectedReceiptDate() {
     return this.expectedReceiptDate;
   }
   
   public void setExpectedReceiptDate(Date expectedReceiptDate) {
     this.expectedReceiptDate = expectedReceiptDate;
   }
   
   public Date getReceiptDate() {
     return this.receiptDate;
   }
   
   public void setReceiptDate(Date receiptDate) {
     this.receiptDate = receiptDate;
   }
   
   public String getLineMemo() {
     return this.lineMemo;
   }
   
   public void setLineMemo(String lineMemo) {
     this.lineMemo = lineMemo;
   }
   
   public String getItemInfoMemo() {
     return this.itemInfoMemo;
   }
   
   public void setItemInfoMemo(String itemInfoMemo) {
     this.itemInfoMemo = itemInfoMemo;
   }
   
   public String getUnit() {
     return this.unit;
   }
   
   public void setUnit(String unit) {
     this.unit = unit;
   }
 }


