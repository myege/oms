 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 public class ItemInfo
   extends B1EC2Object
 {
   private static final long serialVersionUID = -330984541293111267L;
   @ApiField("ItemId")
   public int itemId;
   @ApiField("ItemCode")
   public String itemCode;
   @ApiField("ItemName")
   public String itemName;
   @ApiField("PictureUrl")
   public String pictureUrl;
   @ApiField("BarCode")
   public String barCode;
   @ApiField("CatCode")
   public Integer catCode;
   @ApiField("CatName")
   public String catName;
   @ApiField("CatName1")
   public String catName1;
   @ApiField("CatName2")
   public String catName2;
   @ApiField("CatPath")
   public String catPath;
   @ApiField("PurchasePrice")
   public double purchasePrice;
   @ApiField("SalesPrice")
   public double salesPrice;
   @ApiField("LowestPrice")
   public double lowestPrice;
   @ApiField("MarketPrice")
   public double marketPrice;
   @ApiField("Unit")
   public String unit;
   @ApiField("InvoiceName")
   public String invoiceName;
   @ApiField("Size")
   public double size;
   @ApiField("Weight")
   public double weight;
   @ApiField("BrandId")
   public Integer brandId;
   @ApiField("Brand")
   public Brand brand;
   @ApiField("IsAgent")
   public Boolean agent;
   @ApiField("IsVirtual")
   public Boolean virtual;
   @ApiField("IsStopProducte")
   public Boolean stopProducte;
   @ApiField("IsSerialNumber")
   public Boolean serialNumber;
   @ApiField("Memo")
   public String memo;
   @ApiField("CreatedTime")
   public Date createdTime;
   @ApiField("UpdateTime")
   public Date updateTime;
   @ApiField("UserSign")
   public String userSign;
   @ApiField("Property1")
   public String property1;
   @ApiField("Property2")
   public String property2;
   @ApiField("Property3")
   public String property3;
   @ApiField("Property4")
   public String property4;
   public String property5;
   @ApiField("Property6")
   public String property6;
   @ApiField("Property7")
   public String property7;
   @ApiField("Property8")
   public String property8;
   @ApiField("Property9")
   public String property9;
   @ApiField("Property10")
   public String property10;
   @ApiField("Property11")
   public String property11;
   @ApiField("Property12")
   public String property12;
   @ApiListField("Lines")
   @ApiField("SkuInfo")
   public List<SkuInfo> lines;
   
   public int getItemId() {
     return this.itemId;
   }
   
   public String getItemCode() {
     return this.itemCode;
   }
   
   public String getItemName() {
     return this.itemName;
   }
   
   public String getPictureUrl() {
     return this.pictureUrl;
   }
   
   public String getBarCode() {
     return this.barCode;
   }
   
   public Integer getCatCode() {
     return this.catCode;
   }
   
   public String getCatName() {
     return this.catName;
   }
   
   public String getCatName1() {
     return this.catName1;
   }
   
   public String getCatName2() {
     return this.catName2;
   }
   
   public String getCatPath() {
     return this.catPath;
   }
   
   public double getPurchasePrice() {
     return this.purchasePrice;
   }
   
   public double getSalesPrice() {
     return this.salesPrice;
   }
   
   public double getLowestPrice() {
     return this.lowestPrice;
   }
   
   public double getMarketPrice() {
     return this.marketPrice;
   }
   
   public String getUnit() {
     return this.unit;
   }
   
   public String getInvoiceName() {
     return this.invoiceName;
   }
   
   public double getSize() {
     return this.size;
   }
   
   public double getWeight() {
     return this.weight;
   }
   
   public Integer getBrandId() {
     return this.brandId;
   }
   
   public Brand getBrand() {
     return this.brand;
   }
   
   public Boolean getAgent() {
     return this.agent;
   }
   
   public Boolean getVirtual() {
     return this.virtual;
   }
   
   public Boolean getStopProducte() {
     return this.stopProducte;
   }
   
   public Boolean getSerialNumber() {
     return this.serialNumber;
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
   
   public String getProperty1() {
     return this.property1;
   }
   
   public String getProperty2() {
     return this.property2;
   }
   
   public String getProperty3() {
     return this.property3;
   }
   
   public String getProperty4() {
     return this.property4;
   }
   
   public String getProperty5() {
     return this.property5;
   }
   
   public String getProperty6() {
     return this.property6;
   }
   
   public String getProperty7() {
     return this.property7;
   }
   
   public String getProperty8() {
     return this.property8;
   }
   
   public String getProperty9() {
     return this.property9;
   }
   
   public String getProperty10() {
     return this.property10;
   }
   
   public String getProperty11() {
     return this.property11;
   }
   
   public String getProperty12() {
     return this.property12;
   }
   
   public List<SkuInfo> getLines() {
     return this.lines;
   }
   
   public void setItemId(int itemId) {
     this.itemId = itemId;
   }
   
   public void setItemCode(String itemCode) {
     this.itemCode = itemCode;
   }
   
   public void setItemName(String itemName) {
     this.itemName = itemName;
   }
   
   public void setPictureUrl(String pictureUrl) {
     this.pictureUrl = pictureUrl;
   }
   
   public void setBarCode(String barCode) {
     this.barCode = barCode;
   }
   
   public void setCatCode(Integer catCode) {
     this.catCode = catCode;
   }
   
   public void setCatName(String catName) {
     this.catName = catName;
   }
   
   public void setCatName1(String catName1) {
     this.catName1 = catName1;
   }
   
   public void setCatName2(String catName2) {
     this.catName2 = catName2;
   }
   
   public void setCatPath(String catPath) {
     this.catPath = catPath;
   }
   
   public void setPurchasePrice(double purchasePrice) {
     this.purchasePrice = purchasePrice;
   }
   
   public void setSalesPrice(double salesPrice) {
     this.salesPrice = salesPrice;
   }
   
   public void setLowestPrice(double lowestPrice) {
     this.lowestPrice = lowestPrice;
   }
   
   public void setMarketPrice(double marketPrice) {
     this.marketPrice = marketPrice;
   }
   
   public void setUnit(String unit) {
     this.unit = unit;
   }
   
   public void setInvoiceName(String invoiceName) {
     this.invoiceName = invoiceName;
   }
   
   public void setSize(double size) {
     this.size = size;
   }
   
   public void setWeight(double weight) {
     this.weight = weight;
   }
   
   public void setBrandId(Integer brandId) {
     this.brandId = brandId;
   }
   
   public void setBrand(Brand brand) {
     this.brand = brand;
   }
   
   public void setAgent(Boolean agent) {
     this.agent = agent;
   }
   
   public void setVirtual(Boolean virtual) {
     this.virtual = virtual;
   }
   
   public void setStopProducte(Boolean stopProducte) {
     this.stopProducte = stopProducte;
   }
   
   public void setSerialNumber(Boolean serialNumber) {
     this.serialNumber = serialNumber;
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
   
   public void setProperty1(String property1) {
     this.property1 = property1;
   }
   
   public void setProperty2(String property2) {
     this.property2 = property2;
   }
   
   public void setProperty3(String property3) {
     this.property3 = property3;
   }
   
   public void setProperty4(String property4) {
     this.property4 = property4;
   }
   
   public void setProperty5(String property5) {
     this.property5 = property5;
   }
   
   public void setProperty6(String property6) {
     this.property6 = property6;
   }
   
   public void setProperty7(String property7) {
     this.property7 = property7;
   }
   
   public void setProperty8(String property8) {
     this.property8 = property8;
   }
   
   public void setProperty9(String property9) {
     this.property9 = property9;
   }
   
   public void setProperty10(String property10) {
     this.property10 = property10;
   }
   
   public void setProperty11(String property11) {
     this.property11 = property11;
   }
   
   public void setProperty12(String property12) {
     this.property12 = property12;
   }
   
   public void setLines(List<SkuInfo> lines) {
     this.lines = lines;
   }
   
   public ItemInfo() {
     this.lines = new ArrayList<>();
   }
 }


