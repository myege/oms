 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.SkuCreateLine;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.ItemCreateResponse;
 import java.util.List;
 import java.util.Map;
 public class ItemCreateRequest
   implements B1EC2Request<ItemCreateResponse>
 {
   public String itemCode;
   public String itemName;
   public Integer catCode;
   public Integer supplierId;
   public String supplierCode;
   public String pictureUrl;
   public String barCode;
   public Double purchasePrice;
   public Double salesPrice;
   public Double lowestPrice;
   public Double marketPrice;
   public String unit;
   public String purchaseUnit;
   public Double size;
   public Double weight;
   public Boolean isVirtual;
   public String property1;
   public String property2;
   public String property3;
   public String property4;
   public String property5;
   public String property6;
   public String property7;
   public String property8;
   public String property9;
   public String property10;
   public String property11;
   public String property12;
   private List<SkuCreateLine> skus;
   
   public String getApiMethodName() {
     return "B1EC2.Item.Create";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("ItemCode", this.itemCode);
     parameters.put("ItemName", this.itemName);
     parameters.put("CatCode", this.catCode);
     parameters.put("SupplierId", this.supplierId);
     parameters.put("SupplierCode", this.supplierCode);
     parameters.put("PictureUrl", this.pictureUrl);
     parameters.put("BarCode", this.barCode);
     parameters.put("PurchasePrice", this.purchasePrice);
     parameters.put("SalesPrice", this.salesPrice);
     parameters.put("LowestPrice", this.lowestPrice);
     parameters.put("MarketPrice", this.marketPrice);
     parameters.put("Unit", this.unit);
     parameters.put("PurchaseUnit", this.purchaseUnit);
     parameters.put("Size", this.size);
     parameters.put("Weight", this.weight);
     parameters.put("IsVirtual", this.isVirtual);
     parameters.put("Property1", this.property1);
     parameters.put("Property2", this.property2);
     parameters.put("Property3", this.property3);
     parameters.put("Property4", this.property4);
     parameters.put("Property5", this.property5);
     parameters.put("Property6", this.property6);
     parameters.put("Property7", this.property7);
     parameters.put("Property8", this.property8);
     parameters.put("Property9", this.property9);
     parameters.put("Property10", this.property10);
     parameters.put("Property11", this.property11);
     parameters.put("Property12", this.property12);
     if (this.skus != null && this.skus.size() > 0) {
       StringBuffer lineInfo = new StringBuffer();
       for (SkuCreateLine sku : this.skus) {
         lineInfo.append(sku.getSkuCode());
         lineInfo.append(":");
         lineInfo.append(sku.getBarCode());
         lineInfo.append(":");
         lineInfo.append(sku.getProperty1());
         lineInfo.append(":");
         lineInfo.append(sku.getProperty2());
         lineInfo.append(":");
         lineInfo.append(sku.getSalesPrice());
         lineInfo.append(":");
         lineInfo.append(sku.getUnit());
         lineInfo.append(";");
       } 
       parameters.put("SkuInfo", lineInfo.toString());
     } 
     return (Map<String, String>)parameters;
   }
 
   
   public Class<ItemCreateResponse> getResponseClass() {
     return ItemCreateResponse.class;
   }
   
   public String getItemCode() {
     return this.itemCode;
   }
   
   public String getItemName() {
     return this.itemName;
   }
   
   public Integer getCatCode() {
     return this.catCode;
   }
   
   public Integer getSupplierId() {
     return this.supplierId;
   }
   
   public String getSupplierCode() {
     return this.supplierCode;
   }
   
   public String getPictureUrl() {
     return this.pictureUrl;
   }
   
   public String getBarCode() {
     return this.barCode;
   }
   
   public Double getPurchasePrice() {
     return this.purchasePrice;
   }
   
   public Double getSalesPrice() {
     return this.salesPrice;
   }
   
   public Double getLowestPrice() {
     return this.lowestPrice;
   }
   
   public Double getMarketPrice() {
     return this.marketPrice;
   }
   
   public String getUnit() {
     return this.unit;
   }
   
   public String getPurchaseUnit() {
     return this.purchaseUnit;
   }
   
   public Double getSize() {
     return this.size;
   }
   
   public Double getWeight() {
     return this.weight;
   }
   
   public Boolean getIsVirtual() {
     return this.isVirtual;
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
   
   public List<SkuCreateLine> getSkus() {
     return this.skus;
   }
   
   public void setItemCode(String itemCode) {
     this.itemCode = itemCode;
   }
   
   public void setItemName(String itemName) {
     this.itemName = itemName;
   }
   
   public void setCatCode(Integer catCode) {
     this.catCode = catCode;
   }
   
   public void setSupplierId(Integer supplierId) {
     this.supplierId = supplierId;
   }
   
   public void setSupplierCode(String supplierCode) {
     this.supplierCode = supplierCode;
   }
   
   public void setPictureUrl(String pictureUrl) {
     this.pictureUrl = pictureUrl;
   }
   
   public void setBarCode(String barCode) {
     this.barCode = barCode;
   }
   
   public void setPurchasePrice(Double purchasePrice) {
     this.purchasePrice = purchasePrice;
   }
   
   public void setSalesPrice(Double salesPrice) {
     this.salesPrice = salesPrice;
   }
   
   public void setLowestPrice(Double lowestPrice) {
     this.lowestPrice = lowestPrice;
   }
   
   public void setMarketPrice(Double marketPrice) {
     this.marketPrice = marketPrice;
   }
   
   public void setUnit(String unit) {
     this.unit = unit;
   }
   
   public void setPurchaseUnit(String purchaseUnit) {
     this.purchaseUnit = purchaseUnit;
   }
   
   public void setSize(Double size) {
     this.size = size;
   }
   
   public void setWeight(Double weight) {
     this.weight = weight;
   }
   
   public void setIsVirtual(Boolean isVirtual) {
     this.isVirtual = isVirtual;
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
   
   public void setSkus(List<SkuCreateLine> skus) {
     this.skus = skus;
   }
 }


