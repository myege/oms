 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 public class ShopItem
   extends B1EC2Object
 {
   private static final long serialVersionUID = 3902836561933167147L;
   @ApiField("ShopItemCode")
   public String shopItemCode;
   @ApiField("ShopItemName")
   public String shopItemName;
   @ApiField("Type")
   public String type;
   @ApiField("ShopId")
   public int shopId;
   @ApiField("Shop")
   public Shop shop;
   @ApiField("IsVirtual")
   public Boolean virtual;
   @ApiField("IsAgent")
   public Boolean agent;
   @ApiField("IsViolative")
   public Boolean violative;
   @ApiField("PictureUrl")
   public String pictureUrl;
   @ApiField("OuterId")
   public String outerId;
   @ApiField("Quantity")
   public int quantity;
   @ApiField("Price")
   public double price;
   @ApiField("Size")
   public double size;
   @ApiField("Weight")
   public double weight;
   @ApiField("Status")
   public String status;
   @ApiField("Memo")
   public String memo;
   @ApiField("CreatedTime")
   public Date createdTime;
   @ApiField("UpdateTime")
   public Date updateTime;
   @ApiField("UserSign")
   public String userSign;
   @ApiListField("Lines")
   @ApiField("ShopSku")
   public List<ShopSku> lines = new ArrayList<>();
 
   
   public String getShopItemCode() {
     return this.shopItemCode;
   }
   
   public String getShopItemName() {
     return this.shopItemName;
   }
   
   public String getType() {
     return this.type;
   }
   
   public int getShopId() {
     return this.shopId;
   }
   
   public Shop getShop() {
     return this.shop;
   }
   
   public Boolean getVirtual() {
     return this.virtual;
   }
   
   public Boolean getAgent() {
     return this.agent;
   }
   
   public Boolean getViolative() {
     return this.violative;
   }
   
   public String getPictureUrl() {
     return this.pictureUrl;
   }
   
   public String getOuterId() {
     return this.outerId;
   }
   
   public int getQuantity() {
     return this.quantity;
   }
   
   public double getPrice() {
     return this.price;
   }
   
   public double getSize() {
     return this.size;
   }
   
   public double getWeight() {
     return this.weight;
   }
   
   public String getStatus() {
     return this.status;
   }
   
   public String getMemo() {
     return this.memo;
   }
   
   public Date getCreatedTime() {
     return this.createdTime;
   }
   
   public Date getUpdateTime() {
     return this.updateTime;
   }
   
   public String getUserSign() {
     return this.userSign;
   }
   
   public List<ShopSku> getLines() {
     return this.lines;
   }
   
   public void setShopItemCode(String shopItemCode) {
     this.shopItemCode = shopItemCode;
   }
   
   public void setShopItemName(String shopItemName) {
     this.shopItemName = shopItemName;
   }
   
   public void setType(String type) {
     this.type = type;
   }
   
   public void setShopId(int shopId) {
     this.shopId = shopId;
   }
   
   public void setShop(Shop shop) {
     this.shop = shop;
   }
   
   public void setVirtual(Boolean virtual) {
     this.virtual = virtual;
   }
   
   public void setAgent(Boolean agent) {
     this.agent = agent;
   }
   
   public void setViolative(Boolean violative) {
     this.violative = violative;
   }
   
   public void setPictureUrl(String pictureUrl) {
     this.pictureUrl = pictureUrl;
   }
   
   public void setOuterId(String outerId) {
     this.outerId = outerId;
   }
   
   public void setQuantity(int quantity) {
     this.quantity = quantity;
   }
   
   public void setPrice(double price) {
     this.price = price;
   }
   
   public void setSize(double size) {
     this.size = size;
   }
   
   public void setWeight(double weight) {
     this.weight = weight;
   }
   
   public void setStatus(String status) {
     this.status = status;
   }
   
   public void setMemo(String memo) {
     this.memo = memo;
   }
   
   public void setCreatedTime(Date createdTime) {
     this.createdTime = createdTime;
   }
   
   public void setUpdateTime(Date updateTime) {
     this.updateTime = updateTime;
   }
   
   public void setUserSign(String userSign) {
     this.userSign = userSign;
   }
   
   public void setLines(List<ShopSku> lines) {
     this.lines = lines;
   }
 }


