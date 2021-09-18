 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.response.RefundReasonQueryResponse;
 import java.util.Map;
 
 
 public class RefundReasonQueryRequest
   implements B1EC2Request<RefundReasonQueryResponse>
 {
   public String getApiMethodName() {
     return "B1EC2.RefundReason.Query";
   }
 
   
   public Map<String, String> GetParameters() {
     return null;
   }
 
   
   public Class<RefundReasonQueryResponse> getResponseClass() {
     return RefundReasonQueryResponse.class;
   }
 }


