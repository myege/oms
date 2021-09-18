 package com.what21.model;
 
 import java.math.BigDecimal;
 
 
 public class PlaningReceiptsSku
 {
   private int id;
   private String planingReceiptsId;
   private String sourceNo;
   private String itemNo;
   private String itemType;
   private String goodsNo;
   
   public int getId() {
     return this.id;
   }
   private String declareAmount; private String countryCode; private String currency; private String datatime; private BigDecimal totalPrice; private BigDecimal price; private int userId;
   public void setId(int id) {
     this.id = id;
   }
   
   public String getPlaningReceiptsId() {
     return this.planingReceiptsId;
   }
   
   public void setPlaningReceiptsId(String planingReceiptsId) {
     this.planingReceiptsId = planingReceiptsId;
   }
   
   public String getSourceNo() {
     return this.sourceNo;
   }
   
   public void setSourceNo(String sourceNo) {
     this.sourceNo = sourceNo;
   }
   
   public String getItemNo() {
     return this.itemNo;
   }
   
   public void setItemNo(String itemNo) {
     this.itemNo = itemNo;
   }
   
   public String getItemType() {
     return this.itemType;
   }
   
   public void setItemType(String itemType) {
     this.itemType = itemType;
   }
   
   public String getGoodsNo() {
     return this.goodsNo;
   }
   
   public void setGoodsNo(String goodsNo) {
     this.goodsNo = goodsNo;
   }
   
   public String getDeclareAmount() {
     return this.declareAmount;
   }
   
   public void setDeclareAmount(String declareAmount) {
     this.declareAmount = declareAmount;
   }
   
   public String getCountryCode() {
     return this.countryCode;
   }
   
   public void setCountryCode(String countryCode) {
     this.countryCode = countryCode;
   }
   
   public String getCurrency() {
     return this.currency;
   }
   
   public void setCurrency(String currency) {
     this.currency = currency;
   }
   
   public String getDatatime() {
     return this.datatime;
   }
   
   public void setDatatime(String datatime) {
     this.datatime = datatime;
   }
   
   public BigDecimal getTotalPrice() {
     return this.totalPrice;
   }
   
   public void setTotalPrice(BigDecimal totalPrice) {
     this.totalPrice = totalPrice;
   }
   
   public BigDecimal getPrice() {
     return this.price;
   }
   
   public void setPrice(BigDecimal price) {
     this.price = price;
   }
   
   public int getUserId() {
     return this.userId;
   }
   
   public void setUserId(int userId) {
     this.userId = userId;
   }
 }


