 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.FinJournalQueryResponse;
 import java.util.Date;
 import java.util.Map;
 public class FinJournalQueryRequest
   implements B1EC2Request<FinJournalQueryResponse>
 {
   public Integer shopId;
   public Date startTime;
   public Date endTime;
   public Integer pageNum;
   public Integer pageSize;
   
   public String getApiMethodName() {
     return "B1EC2.FinJournal.Query";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("ShopId", this.shopId);
     parameters.put("StartTime", this.startTime);
     parameters.put("EndTime", this.endTime);
     parameters.put("PageNum", this.pageNum);
     parameters.put("PageSize", this.pageSize);
     return (Map<String, String>)parameters;
   }
 
   
   public Class<FinJournalQueryResponse> getResponseClass() {
     return FinJournalQueryResponse.class;
   }
   
   public Integer getShopId() {
     return this.shopId;
   }
   
   public void setShopId(Integer shopId) {
     this.shopId = shopId;
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
 }


