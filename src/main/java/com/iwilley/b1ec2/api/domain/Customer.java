 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import java.util.Date;
 public class Customer
   extends B1EC2Object
 {
   private static final long serialVersionUID = -7656982312624678740L;
   @ApiField("customerId")
   public int CustomerId;
   @ApiField("CustomerCode")
   public String customerCode;
   @ApiField("CustomerName")
   public String customerName;
   @ApiField("Mobile")
   public String mobile;
   @ApiField("ReceiverName")
   public String receiverName;
   @ApiField("Province")
   public String province;
   @ApiField("City")
   public String city;
   @ApiField("District")
   public String district;
   @ApiField("Address")
   public String address;
   @ApiField("Email")
   public String email;
   @ApiField("ZipCode")
   public String zipCode;
   @ApiField("ShopType")
   public String shopType;
   @ApiField("ShopId")
   public int shopId;
   @ApiField("Shop")
   public Shop shop;
   @ApiField("Memo")
   public String memo;
   @ApiField("BuyerCredit")
   public String buyerCredit;
   @ApiField("LevelId")
   public int levelId;
   @ApiField("IsFenXiao")
   public Boolean isFenXiao;
   @ApiField("LastModifiedTime")
   public Date lastModifiedTime;
   @ApiField("LastModifiedUser")
   public String lastModifiedUser;
   @ApiField("UserDefinedField1")
   public String userDefinedField1;
   @ApiField("UserDefinedField2")
   public String userDefinedField2;
   
   public int getCustomerId() {
     return this.CustomerId;
   }
   
   public String getCustomerCode() {
     return this.customerCode;
   }
   
   public String getCustomerName() {
     return this.customerName;
   }
   
   public String getMobile() {
     return this.mobile;
   }
   
   public String getReceiverName() {
     return this.receiverName;
   }
   
   public String getProvince() {
     return this.province;
   }
   
   public String getCity() {
     return this.city;
   }
   
   public String getDistrict() {
     return this.district;
   }
   
   public String getAddress() {
     return this.address;
   }
   
   public String getEmail() {
     return this.email;
   }
   
   public String getZipCode() {
     return this.zipCode;
   }
   
   public String getShopType() {
     return this.shopType;
   }
   
   public int getShopId() {
     return this.shopId;
   }
   
   public Shop getShop() {
     return this.shop;
   }
   
   public String getMemo() {
     return this.memo;
   }
   
   public String getBuyerCredit() {
     return this.buyerCredit;
   }
   
   public int getLevelId() {
     return this.levelId;
   }
   
   public Boolean getIsFenXiao() {
     return this.isFenXiao;
   }
   
   public Date getLastModifiedTime() {
     return this.lastModifiedTime;
   }
   
   public String getLastModifiedUser() {
     return this.lastModifiedUser;
   }
   
   public String getUserDefinedField1() {
     return this.userDefinedField1;
   }
   
   public String getUserDefinedField2() {
     return this.userDefinedField2;
   }
   
   public void setCustomerId(int customerId) {
     this.CustomerId = customerId;
   }
   
   public void setCustomerCode(String customerCode) {
     this.customerCode = customerCode;
   }
   
   public void setCustomerName(String customerName) {
     this.customerName = customerName;
   }
   
   public void setMobile(String mobile) {
     this.mobile = mobile;
   }
   
   public void setReceiverName(String receiverName) {
     this.receiverName = receiverName;
   }
   
   public void setProvince(String province) {
     this.province = province;
   }
   
   public void setCity(String city) {
     this.city = city;
   }
   
   public void setDistrict(String district) {
     this.district = district;
   }
   
   public void setAddress(String address) {
     this.address = address;
   }
   
   public void setEmail(String email) {
     this.email = email;
   }
   
   public void setZipCode(String zipCode) {
     this.zipCode = zipCode;
   }
   
   public void setShopType(String shopType) {
     this.shopType = shopType;
   }
   
   public void setShopId(int shopId) {
     this.shopId = shopId;
   }
   
   public void setShop(Shop shop) {
     this.shop = shop;
   }
   
   public void setMemo(String memo) {
     this.memo = memo;
   }
   
   public void setBuyerCredit(String buyerCredit) {
     this.buyerCredit = buyerCredit;
   }
   
   public void setLevelId(int levelId) {
     this.levelId = levelId;
   }
   
   public void setIsFenXiao(Boolean isFenXiao) {
     this.isFenXiao = isFenXiao;
   }
   
   public void setLastModifiedTime(Date lastModifiedTime) {
     this.lastModifiedTime = lastModifiedTime;
   }
   
   public void setLastModifiedUser(String lastModifiedUser) {
     this.lastModifiedUser = lastModifiedUser;
   }
   
   public void setUserDefinedField1(String userDefinedField1) {
     this.userDefinedField1 = userDefinedField1;
   }
   
   public void setUserDefinedField2(String userDefinedField2) {
     this.userDefinedField2 = userDefinedField2;
   }
 }


