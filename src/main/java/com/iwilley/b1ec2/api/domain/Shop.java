 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 public class Shop
   extends B1EC2Object
 {
   private static final long serialVersionUID = 2634991288966105284L;
   @ApiField("ShopId")
   private int shopId;
   @ApiField("ShopName")
   private String shopName;
   @ApiField("ShopType")
   private String shopType;
   @ApiField("UserDefinedField1")
   private String userDefinedField1;
   @ApiField("UserDefinedField2")
   private String userDefinedField2;
   
   public int getShopId() {
     return this.shopId;
   }
   
   public void setShopId(int shopId) {
     this.shopId = shopId;
   }
   
   public String getShopName() {
     return this.shopName;
   }
   
   public void setShopName(String shopName) {
     this.shopName = shopName;
   }
   
   public String getShopType() {
     return this.shopType;
   }
   
   public void setShopType(String shopType) {
     this.shopType = shopType;
   }
   
   public String getUserDefinedField1() {
     return this.userDefinedField1;
   }
   
   public void setUserDefinedField1(String userDefinedField1) {
     this.userDefinedField1 = userDefinedField1;
   }
   
   public String getUserDefinedField2() {
     return this.userDefinedField2;
   }
   
   public void setUserDefinedField2(String userDefinedField2) {
     this.userDefinedField2 = userDefinedField2;
   }
 }


