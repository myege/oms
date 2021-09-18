 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.response.ExpressQueryResponse;
 import java.util.Map;
 
 
 public class ExpressQueryRequest
   implements B1EC2Request<ExpressQueryResponse>
 {
   public String getApiMethodName() {
     return "B1EC2.Express.Query";
   }
 
   
   public Map<String, String> GetParameters() {
     return null;
   }
 
   
   public Class<ExpressQueryResponse> getResponseClass() {
     return ExpressQueryResponse.class;
   }
 }

