 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 public class Category
   extends B1EC2Object
 {
   private static final long serialVersionUID = -4486291141033132478L;
   @ApiField("CatCode")
   public int catCode;
   @ApiField("CatName")
   public String catName;
   @ApiField("FatherCode")
   public int fatherCode;
   @ApiField("CatPath")
   public String catPath;
   @ApiField("IsLeaf")
   public Boolean isLeaf;
   
   public int getCatCode() {
     return this.catCode;
   }
   
   public void setCatCode(int catCode) {
     this.catCode = catCode;
   }
   
   public String getCatName() {
     return this.catName;
   }
   
   public void setCatName(String catName) {
     this.catName = catName;
   }
   
   public int getFatherCode() {
     return this.fatherCode;
   }
   
   public void setFatherCode(int fatherCode) {
     this.fatherCode = fatherCode;
   }
   
   public String getCatPath() {
     return this.catPath;
   }
   
   public void setCatPath(String catPath) {
     this.catPath = catPath;
   }
   
   public Boolean getIsLeaf() {
     return this.isLeaf;
   }
   
   public void setIsLeaf(Boolean isLeaf) {
     this.isLeaf = isLeaf;
   }
 }


