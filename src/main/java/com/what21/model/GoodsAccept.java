 package com.what21.model;
 
 import java.math.BigDecimal;
 public class GoodsAccept
 {
   private int id;
   private int orderId;
   private String expressNumber;
   private String goodsName;
   private String goodsType;
   private Double weight;
   private BigDecimal price;
   private int num;
   private BigDecimal totalPrice;
   private String goodsSpec;
   private String goodsUnit;
   private String goodsDutyNumber;
   private String goodsHS;
   private String fromCountryCode;
   private String goodsSKU;
   private int userId;
   
   public int getId() {
     return this.id;
   }
   public void setId(int id) {
     this.id = id;
   }
   public int getOrderId() {
     return this.orderId;
   }
   public void setOrderId(int orderId) {
     this.orderId = orderId;
   }
   public String getExpressNumber() {
     return this.expressNumber;
   }
   public void setExpressNumber(String expressNumber) {
     this.expressNumber = expressNumber;
   }
   public String getGoodsName() {
     return this.goodsName;
   }
   public void setGoodsName(String goodsName) {
     this.goodsName = goodsName;
   }
   public String getGoodsType() {
     return this.goodsType;
   }
   public void setGoodsType(String goodsType) {
     this.goodsType = goodsType;
   }
   public Double getWeight() {
     return this.weight;
   }
   public void setWeight(Double weight) {
     this.weight = weight;
   }
   public BigDecimal getPrice() {
     return this.price;
   }
   public void setPrice(BigDecimal price) {
     this.price = price;
   }
   public int getNum() {
     return this.num;
   }
   public void setNum(int num) {
     this.num = num;
   }
   public BigDecimal getTotalPrice() {
     return this.totalPrice;
   }
   public void setTotalPrice(BigDecimal totalPrice) {
     this.totalPrice = totalPrice;
   }
   public String getGoodsSpec() {
     return this.goodsSpec;
   }
   public void setGoodsSpec(String goodsSpec) {
     this.goodsSpec = goodsSpec;
   }
   public String getGoodsUnit() {
     return this.goodsUnit;
   }
   public void setGoodsUnit(String goodsUnit) {
     this.goodsUnit = goodsUnit;
   }
   public String getGoodsDutyNumber() {
     return this.goodsDutyNumber;
   }
   public void setGoodsDutyNumber(String goodsDutyNumber) {
     this.goodsDutyNumber = goodsDutyNumber;
   }
   public String getGoodsHS() {
     return this.goodsHS;
   }
   public void setGoodsHS(String goodsHS) {
     this.goodsHS = goodsHS;
   }
   public String getFromCountryCode() {
     return this.fromCountryCode;
   }
   public void setFromCountryCode(String fromCountryCode) {
     this.fromCountryCode = fromCountryCode;
   }
   public String getGoodsSKU() {
     return this.goodsSKU;
   }
   public void setGoodsSKU(String goodsSKU) {
     this.goodsSKU = goodsSKU;
   }
   public int getUserId() {
     return this.userId;
   }
   public void setUserId(int userId) {
     this.userId = userId;
   }
 }


