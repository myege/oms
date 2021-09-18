 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 public class Supplier
   extends B1EC2Object
 {
   private static final long serialVersionUID = 2022420149618567354L;
   @ApiField("SupplierId")
   public int supplierId;
   @ApiField("SupplierCode")
   public String supplierCode;
   @ApiField("SupplierName")
   public String supplierName;
   @ApiField("Province")
   public String province;
   @ApiField("City")
   public String city;
   @ApiField("District")
   public String district;
   @ApiField("Address")
   public String address;
   @ApiField("UserDefinedField1")
   public String userDefinedField1;
   @ApiField("UserDefinedField2")
   public String userDefinedField2;
   
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
   
   public int getSupplierId() {
     return this.supplierId;
   }
   
   public void setSupplierId(int supplierId) {
     this.supplierId = supplierId;
   }
   
   public String getSupplierCode() {
     return this.supplierCode;
   }
   
   public void setSupplierCode(String supplierCode) {
     this.supplierCode = supplierCode;
   }
   
   public String getSupplierName() {
     return this.supplierName;
   }
   
   public void setSupplierName(String supplierName) {
     this.supplierName = supplierName;
   }
   
   public String getProvince() {
     return this.supplierCode;
   }
   
   public void setProvince(String province) {
     this.province = province;
   }
   
   public String getCity() {
     return this.city;
   }
   
   public void setCity(String city) {
     this.city = city;
   }
   
   public String getDistrict() {
     return this.supplierCode;
   }
   
   public void setDistrict(String district) {
     this.district = district;
   }
   
   public String getAddress() {
     return this.address;
   }
   
   public void setAddress(String address) {
     this.address = address;
   }
 }


