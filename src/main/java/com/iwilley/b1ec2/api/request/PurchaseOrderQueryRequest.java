 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.PurchaseOrderQueryResponse;
 import java.util.Date;
 import java.util.Map;
 public class PurchaseOrderQueryRequest
   implements B1EC2Request<PurchaseOrderQueryResponse>
 {
   public Integer whsId;
   public Integer status;
   public Date startTime;
   public Date endTime;
   public Integer pageNum;
   public Integer pageSize;
   
   public String getApiMethodName() {
     return "B1EC2.PurchaseOrder.Query";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("WhsId", this.whsId);
     parameters.put("Status", this.status);
     parameters.put("StartTime", this.startTime);
     parameters.put("EndTime", this.endTime);
     parameters.put("PageNum", this.pageNum);
     parameters.put("PageSize", this.pageSize);
     return (Map<String, String>)parameters;
   }
 
   
   public Class<PurchaseOrderQueryResponse> getResponseClass() {
     return PurchaseOrderQueryResponse.class;
   }
   
   public Integer getWhsId() {
     return this.whsId;
   }
   
   public void setWhsId(Integer whsId) {
     this.whsId = whsId;
   }
   
   public Integer getStatus() {
     return this.status;
   }
   
   public void setStatus(Integer status) {
     this.status = status;
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


