 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 public class Bom
   extends B1EC2Object
 {
   private static final long serialVersionUID = 5209861128857751738L;
   @ApiField("BomId")
   public int bomId;
   @ApiField("ItemId")
   public int itemId;
   @ApiField("ItemInfo")
   public ItemInfo itemInfo;
   @ApiField("ItemCode")
   public String itemCode;
   @ApiField("ItemName")
   public String itemName;
   @ApiField("SkuCode")
   public String skuCode;
   @ApiField("SkuName")
   public String skuName;
   @ApiField("Unit")
   public String unit;
   @ApiField("StockNum")
   public int stockNum;
   @ApiField("SalesPrice")
   public double salesPrice;
   @ApiField("Memo")
   public String memo;
   @ApiField("LastModifiedTime")
   public Date lastModifiedTime;
   @ApiField("LastModifiedUser")
   public String lastModifiedUser;
   @ApiListField("Lines")
   @ApiField("BomLine")
   public List<BomLine> lines;
   
   public int getBomId() {
     return this.bomId;
   }
   
   public void setBomId(int bomId) {
     this.bomId = bomId;
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
   
   public String getItemCode() {
     return this.itemCode;
   }
   
   public void setItemCode(String itemCode) {
     this.itemCode = itemCode;
   }
   
   public String getItemName() {
     return this.itemName;
   }
   
   public void setItemName(String itemName) {
     this.itemName = itemName;
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
   
   public String getUnit() {
     return this.unit;
   }
   
   public void setUnit(String unit) {
     this.unit = unit;
   }
   
   public int getStockNum() {
     return this.stockNum;
   }
   
   public void setStockNum(int stockNum) {
     this.stockNum = stockNum;
   }
   
   public double getSalesPrice() {
     return this.salesPrice;
   }
   
   public void setSalesPrice(double salesPrice) {
     this.salesPrice = salesPrice;
   }
   
   public String getMemo() {
     return this.memo;
   }
   
   public void setMemo(String memo) {
     this.memo = memo;
   }
   
   public Date getLastModifiedTime() {
     return this.lastModifiedTime;
   }
   
   public void setLastModifiedTime(Date lastModifiedTime) {
     this.lastModifiedTime = lastModifiedTime;
   }
   
   public String getLastModifiedUser() {
     return this.lastModifiedUser;
   }
   
   public void setLastModifiedUser(String lastModifiedUser) {
     this.lastModifiedUser = lastModifiedUser;
   }
   
   public List<BomLine> getLines() {
     return this.lines;
   }
   
   public void setLines(List<BomLine> lines) {
     this.lines = lines;
   }
   
   public Bom() {
     this.lines = new ArrayList<>();
   }
 }


