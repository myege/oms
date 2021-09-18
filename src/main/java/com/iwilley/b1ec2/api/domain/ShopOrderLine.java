 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import java.util.Date;
 public class ShopOrderLine
   extends B1EC2Object
 {
   private static final long serialVersionUID = 5628516418176760753L;
   @ApiField("ShopOrderNo")
   public String shopOrderNo;
   @ApiField("LineNum")
   public int lineNum;
   @ApiField("ShopOrder")
   public ShopOrder shopOrder;
   @ApiField("ShopLineNo")
   public String shopLineNo;
   @ApiField("LineStatusName")
   public String lineStatusName;
   @ApiField("OuterId")
   public String outerId;
   @ApiField("ItemName")
   public String itemName;
   @ApiField("SkuName")
   public String skuName;
   @ApiField("ShopItemId")
   public String shopItemId;
   @ApiField("ShopSkuId")
   public String shopSkuId;
   @ApiField("price")
   public double Price;
   @ApiField("Quantity")
   public double quantity;
   @ApiField("LineDiscountFee")
   public double lineDiscountFee;
   @ApiField("LineAdjustFee")
   public double lineAdjustFee;
   @ApiField("DeliveryTime")
   public Date deliveryTime;
   @ApiField("LineTotal")
   public double lineTotal;
   @ApiField("LineUdf1")
   public String lineUdf1;
   @ApiField("LineUdf2")
   public String lineUdf2;
   
   public String getShopOrderNo() {
     return this.shopOrderNo;
   }
   
   public void setShopOrderNo(String shopOrderNo) {
     this.shopOrderNo = shopOrderNo;
   }
   
   public int getLineNum() {
     return this.lineNum;
   }
   
   public void setLineNum(int lineNum) {
     this.lineNum = lineNum;
   }
   
   public ShopOrder getShopOrder() {
     return this.shopOrder;
   }
   
   public void setShopOrder(ShopOrder shopOrder) {
     this.shopOrder = shopOrder;
   }
   
   public String getShopLineNo() {
     return this.shopLineNo;
   }
   
   public void setShopLineNo(String shopLineNo) {
     this.shopLineNo = shopLineNo;
   }
   
   public String getLineStatusName() {
     return this.lineStatusName;
   }
   
   public void setLineStatusName(String lineStatusName) {
     this.lineStatusName = lineStatusName;
   }
   
   public String getOuterId() {
     return this.outerId;
   }
   
   public void setOuterId(String outerId) {
     this.outerId = outerId;
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
   
   public String getShopItemId() {
     return this.shopItemId;
   }
   
   public void setShopItemId(String shopItemId) {
     this.shopItemId = shopItemId;
   }
   
   public String getShopSkuId() {
     return this.shopSkuId;
   }
   
   public void setShopSkuId(String shopSkuId) {
     this.shopSkuId = shopSkuId;
   }
   
   public double getPrice() {
     return this.Price;
   }
   
   public void setPrice(double price) {
     this.Price = price;
   }
   
   public double getQuantity() {
     return this.quantity;
   }
   
   public void setQuantity(double quantity) {
     this.quantity = quantity;
   }
   
   public double getLineDiscountFee() {
     return this.lineDiscountFee;
   }
   
   public void setLineDiscountFee(double lineDiscountFee) {
     this.lineDiscountFee = lineDiscountFee;
   }
   
   public double getLineAdjustFee() {
     return this.lineAdjustFee;
   }
   
   public void setLineAdjustFee(double lineAdjustFee) {
     this.lineAdjustFee = lineAdjustFee;
   }
   
   public Date getDeliveryTime() {
     return this.deliveryTime;
   }
   
   public void setDeliveryTime(Date deliveryTime) {
     this.deliveryTime = deliveryTime;
   }
   
   public double getLineTotal() {
     return this.lineTotal;
   }
   
   public void setLineTotal(double lineTotal) {
     this.lineTotal = lineTotal;
   }
   
   public String getLineUdf1() {
     return this.lineUdf1;
   }
   
   public void setLineUdf1(String lineUdf1) {
     this.lineUdf1 = lineUdf1;
   }
   
   public String getLineUdf2() {
     return this.lineUdf2;
   }
   
   public void setLineUdf2(String lineUdf2) {
     this.lineUdf2 = lineUdf2;
   }
 }


