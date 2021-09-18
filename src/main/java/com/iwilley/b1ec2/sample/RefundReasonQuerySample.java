 package com.iwilley.b1ec2.sample;
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.request.RefundReasonQueryRequest;
 import com.iwilley.b1ec2.api.response.RefundReasonQueryResponse;
 
 public class RefundReasonQuerySample {
   public static void main(String[] args) throws ApiException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     RefundReasonQueryRequest request = new RefundReasonQueryRequest();
     RefundReasonQueryResponse response = (RefundReasonQueryResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
   }
 }


