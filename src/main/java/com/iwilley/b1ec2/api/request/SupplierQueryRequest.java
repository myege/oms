 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.SupplierQueryResponse;
 import java.util.Date;
 import java.util.Map;
 public class SupplierQueryRequest
   implements B1EC2Request<SupplierQueryResponse>
 {
   public Date startTime;
   public Date endTime;
   
   public String getApiMethodName() {
     return "B1EC2.Supplier.Query";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("StartTime", this.startTime);
     parameters.put("EndTime", this.endTime);
     return (Map<String, String>)parameters;
   }
 
   
   public Class<SupplierQueryResponse> getResponseClass() {
     return SupplierQueryResponse.class;
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
 }


