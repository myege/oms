 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.AfterSalesServiceQueryResponse;
 import java.util.Date;
 import java.util.Map;
 public class AfterSalesServiceQueryRequest
   implements B1EC2Request<AfterSalesServiceQueryResponse>
 {
   public Integer status;
   public Integer type;
   public Date startTime;
   public Date endTime;
   public Integer pageNum;
   public Integer pageSize;
   
   public String getApiMethodName() {
     return "B1EC2.AfterSalesService.Query";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("Status", this.status);
     parameters.put("Type", this.type);
     parameters.put("StartTime", this.startTime);
     parameters.put("EndTime", this.endTime);
     parameters.put("PageNum", this.pageNum);
     parameters.put("PageSize", this.pageSize);
     return (Map<String, String>)parameters;
   }
 
   
   public Class<AfterSalesServiceQueryResponse> getResponseClass() {
     return AfterSalesServiceQueryResponse.class;
   }
   
   public Integer getStatus() {
     return this.status;
   }
   
   public void setStatus(Integer status) {
     this.status = status;
   }
   
   public Integer getType() {
     return this.type;
   }
   
   public void setType(Integer type) {
     this.type = type;
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


