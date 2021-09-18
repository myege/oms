 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 public class Brand
   extends B1EC2Object
 {
   private static final long serialVersionUID = 7714556169907078758L;
   @ApiField("BrandId")
   public int brandId;
   @ApiField("BrandName")
   public String brandName;
   
   public int getBrandId() {
     return this.brandId;
   }
   
   public void setBrandId(int brandId) {
     this.brandId = brandId;
   }
   
   public String getBrandName() {
     return this.brandName;
   }
   
   public void setBrandName(String brandName) {
     this.brandName = brandName;
   }
 }


