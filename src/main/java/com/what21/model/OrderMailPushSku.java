 package com.what21.model;
 public class OrderMailPushSku
 {
   private Integer goodsNumber;
   private Double goodsPrice;
   private String goodsSn = "ZY1901101000";
   
   private String goodsName;
 
   
   public Integer getGoodsNumber() {
     return this.goodsNumber;
   }
   public void setGoodsNumber(Integer goodsNumber) {
     this.goodsNumber = goodsNumber;
   }
   public Double getGoodsPrice() {
     return this.goodsPrice;
   }
   public void setGoodsPrice(Double goodsPrice) {
     this.goodsPrice = goodsPrice;
   }
   public String getGoodsSn() {
     return "ZY1901101000";
   }
   public void setGoodsSn(String goodsSn) {
     this.goodsSn = goodsSn;
   }
   public String getGoodsName() {
     return this.goodsName;
   }
   public void setGoodsName(String goodsName) {
     this.goodsName = goodsName;
   }
 }


