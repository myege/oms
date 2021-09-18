 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.ItemInfoQueryResponse;
 import java.util.Date;
 import java.util.Map;
 public class ItemInfoQueryRequest
   implements B1EC2Request<ItemInfoQueryResponse>
 {
   public Date startTime;
   public Date endTime;
   public Integer pageNum;
   public Integer pageSize;
   public String property1;
   public String property2;
   
   public String getApiMethodName() {
     return "B1EC2.ItemInfo.Query";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("StartTime", this.startTime);
     parameters.put("EndTime", this.endTime);
     parameters.put("PageNum", this.pageNum);
     parameters.put("PageSize", this.pageSize);
     parameters.put("Property1", this.property1);
     parameters.put("Property2", this.property2);
     return (Map<String, String>)parameters;
   }
 
   
   public Class<ItemInfoQueryResponse> getResponseClass() {
     return ItemInfoQueryResponse.class;
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
   
   public String getProperty1() {
     return this.property1;
   }
   
   public void setProperty1(String property1) {
     this.property1 = property1;
   }
   
   public String getProperty2() {
     return this.property2;
   }
   
   public void setProperty2(String property2) {
     this.property2 = property2;
   }
 }


