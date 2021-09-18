 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.ShopSkuPushLine;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.ShopItemPushResponse;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 public class ShopItemPushRequest
   implements B1EC2Request<ShopItemPushResponse>
 {
   public String shopItemCode;
   public Integer shopId;
   public String shopItemName;
   public Boolean isVirtual;
   public String pictureUrl;
   public String outerId;
   public Integer quantity;
   public Double price;
   public Double size;
   public Double weight;
   public String status;
   public String memo;
   public Date createdTime;
   public Date updateTime;
   private List<ShopSkuPushLine> skusInfo;
   
   public String getApiMethodName() {
     return "B1EC2.ShopItem.Push";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("ShopItemCode", this.shopItemCode);
     parameters.put("ShopId", this.shopId);
     parameters.put("ShopItemName", this.shopItemName);
     parameters.put("IsVirtual", this.isVirtual);
     parameters.put("PictureUrl", this.pictureUrl);
     parameters.put("OuterId", this.outerId);
     parameters.put("Quantity", this.quantity);
     parameters.put("Price", this.price);
     parameters.put("Size", this.size);
     parameters.put("Weight", this.weight);
     parameters.put("Status", this.status);
     parameters.put("Memo", this.memo);
     parameters.put("CreatedTime", this.createdTime);
     parameters.put("UpdateTime", this.updateTime);
     if (this.skusInfo != null && this.skusInfo.size() > 0) {
       StringBuffer lineInfo = new StringBuffer();
       for (ShopSkuPushLine sku : this.skusInfo) {
         lineInfo.append(sku.getShopSkuCode());
         lineInfo.append(":");
         lineInfo.append(sku.getOuterId());
         lineInfo.append(":");
         lineInfo.append(sku.getProperty1());
         lineInfo.append(":");
         lineInfo.append(sku.getProperty2());
         lineInfo.append(":");
         lineInfo.append(sku.getPrice());
         lineInfo.append(":");
         lineInfo.append(sku.getSize());
         lineInfo.append(":");
         lineInfo.append(sku.getWeight());
         lineInfo.append(":");
         lineInfo.append(sku.getQuantity());
         lineInfo.append(";");
       } 
       parameters.put("SkusInfo", lineInfo.toString());
     } 
     return (Map<String, String>)parameters;
   }
 
   
   public Class<ShopItemPushResponse> getResponseClass() {
     return ShopItemPushResponse.class;
   }
   
   public String getShopItemCode() {
     return this.shopItemCode;
   }
   
   public Integer getShopId() {
     return this.shopId;
   }
   
   public String getShopItemName() {
     return this.shopItemName;
   }
   
   public Boolean getIsVirtual() {
     return this.isVirtual;
   }
   
   public String getPictureUrl() {
     return this.pictureUrl;
   }
   
   public String getOuterId() {
     return this.outerId;
   }
   
   public Integer getQuantity() {
     return this.quantity;
   }
   
   public Double getPrice() {
     return this.price;
   }
   
   public Double getSize() {
     return this.size;
   }
   
   public Double getWeight() {
     return this.weight;
   }
   
   public String getStatus() {
     return this.status;
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
   
   public List<ShopSkuPushLine> getSkusInfo() {
     return this.skusInfo;
   }
   
   public void setShopItemCode(String shopItemCode) {
     this.shopItemCode = shopItemCode;
   }
   
   public void setShopId(Integer shopId) {
     this.shopId = shopId;
   }
   
   public void setShopItemName(String shopItemName) {
     this.shopItemName = shopItemName;
   }
   
   public void setIsVirtual(Boolean isVirtual) {
     this.isVirtual = isVirtual;
   }
   
   public void setPictureUrl(String pictureUrl) {
     this.pictureUrl = pictureUrl;
   }
   
   public void setOuterId(String outerId) {
     this.outerId = outerId;
   }
   
   public void setQuantity(Integer quantity) {
     this.quantity = quantity;
   }
   
   public void setPrice(Double price) {
     this.price = price;
   }
   
   public void setSize(Double size) {
     this.size = size;
   }
   
   public void setWeight(Double weight) {
     this.weight = weight;
   }
   
   public void setStatus(String status) {
     this.status = status;
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
   
   public void setSkusInfo(List<ShopSkuPushLine> skusInfo) {
     this.skusInfo = skusInfo;
   }
 }


