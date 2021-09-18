 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.ShopItemQueryResponse;
 import java.util.Date;
 import java.util.Map;
 public class ShopItemQueryRequest
   implements B1EC2Request<ShopItemQueryResponse>
 {
   public Date startTime;
   public Date endTime;
   public Integer pageNum;
   public Integer pageSize;
   public Integer shopId;
   
   public String getApiMethodName() {
     return "B1EC2.ShopItem.Query";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("StartTime", this.startTime);
     parameters.put("EndTime", this.endTime);
     parameters.put("PageNum", this.pageNum);
     parameters.put("PageSize", this.pageSize);
     parameters.put("ShopId", this.shopId);
     return (Map<String, String>)parameters;
   }
 
   
   public Class<ShopItemQueryResponse> getResponseClass() {
     return ShopItemQueryResponse.class;
   }
   
   public Date getStartTime() {
     return this.startTime;
   }
   
   public void setStartTime(Date startTime) {
     this.startTime = startTime;
   }
   
   public Date getEndTime() {
     return this.endTime;
   }
   
   public void setEndTime(Date endTime) {
     this.endTime = endTime;
   }
   
   public Integer getPageNum() {
     return this.pageNum;
   }
   
   public void setPageNum(Integer pageNum) {
     this.pageNum = pageNum;
   }
   
   public Integer getPageSize() {
     return this.pageSize;
   }
   
   public void setPageSize(Integer pageSize) {
     this.pageSize = pageSize;
   }
   
   public Integer getShopId() {
     return this.shopId;
   }
   
   public void setShopId(Integer shopId) {
     this.shopId = shopId;
   }
 }


