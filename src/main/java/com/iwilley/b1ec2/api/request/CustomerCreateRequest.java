 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.CustomerCreateResponse;
 import java.util.Map;
 public class CustomerCreateRequest
   implements B1EC2Request<CustomerCreateResponse>
 {
   public String customerName;
   public Integer shopId;
   public String mobile;
   public String receiverName;
   public String province;
   public String city;
   public String district;
   public String address;
   public String email;
   public String zipCode;
   public String memo;
   public String buyerCredit;
   public Integer levelId;
   public Boolean isFenXiao;
   public String userDefinedField1;
   public String userDefinedField2;
   public String userDefinedField3;
   public String userDefinedField4;
   public String userDefinedField5;
   public String userDefinedField6;
   public String customerCode;
   
   public String getApiMethodName() {
     return "B1EC2.Customer.Create";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("CustomerName", this.customerName);
     parameters.put("ShopId", this.shopId);
     parameters.put("Mobile", this.mobile);
     parameters.put("ReceiverName", this.receiverName);
     parameters.put("Province", this.province);
     parameters.put("City", this.city);
     parameters.put("District", this.district);
     parameters.put("Address", this.address);
     parameters.put("Email", this.email);
     parameters.put("ZipCode", this.zipCode);
     parameters.put("Memo", this.memo);
     parameters.put("BuyerCredit", this.buyerCredit);
     parameters.put("LevelId", this.levelId);
     parameters.put("IsFenXiao", this.isFenXiao);
     parameters.put("UserDefinedField1", this.userDefinedField1);
     parameters.put("UserDefinedField2", this.userDefinedField2);
     parameters.put("UserDefinedField3", this.userDefinedField3);
     parameters.put("UserDefinedField4", this.userDefinedField4);
     parameters.put("UserDefinedField5", this.userDefinedField5);
     parameters.put("UserDefinedField6", this.userDefinedField6);
     parameters.put("CustomerCode", this.customerCode);
     return (Map<String, String>)parameters;
   }
 
   
   public Class<CustomerCreateResponse> getResponseClass() {
     return CustomerCreateResponse.class;
   }
   
   public String getCustomerName() {
     return this.customerName;
   }
   
   public Integer getShopId() {
     return this.shopId;
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
   
   public String getMemo() {
     return this.memo;
   }
   
   public String getBuyerCredit() {
     return this.buyerCredit;
   }
   
   public Integer getLevelId() {
     return this.levelId;
   }
   
   public Boolean getIsFenXiao() {
     return this.isFenXiao;
   }
   
   public String getUserDefinedField1() {
     return this.userDefinedField1;
   }
   
   public String getUserDefinedField2() {
     return this.userDefinedField2;
   }
   
   public String getUserDefinedField3() {
     return this.userDefinedField3;
   }
   
   public String getUserDefinedField4() {
     return this.userDefinedField4;
   }
   
   public void setCustomerName(String customerName) {
     this.customerName = customerName;
   }
   
   public void setShopId(Integer shopId) {
     this.shopId = shopId;
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
   
   public void setMemo(String memo) {
     this.memo = memo;
   }
   
   public void setBuyerCredit(String buyerCredit) {
     this.buyerCredit = buyerCredit;
   }
   
   public void setLevelId(Integer levelId) {
     this.levelId = levelId;
   }
   
   public void setIsFenXiao(Boolean isFenXiao) {
     this.isFenXiao = isFenXiao;
   }
   
   public void setUserDefinedField1(String userDefinedField1) {
     this.userDefinedField1 = userDefinedField1;
   }
   
   public void setUserDefinedField2(String userDefinedField2) {
     this.userDefinedField2 = userDefinedField2;
   }
   
   public void setUserDefinedField3(String userDefinedField3) {
     this.userDefinedField3 = userDefinedField3;
   }
   
   public void setUserDefinedField4(String userDefinedField4) {
     this.userDefinedField4 = userDefinedField4;
   }
   
   public String getUserDefinedField5() {
     return this.userDefinedField5;
   }
   
   public String getUserDefinedField6() {
     return this.userDefinedField6;
   }
   
   public void setUserDefinedField5(String userDefinedField5) {
     this.userDefinedField5 = userDefinedField5;
   }
   
   public void setUserDefinedField6(String userDefinedField6) {
     this.userDefinedField6 = userDefinedField6;
   }
 
   
   public String getCustomerCode() {
     return this.customerCode;
   }
   public void setCustomerCode(String customerCode) {
     this.customerCode = customerCode;
   }
 }


