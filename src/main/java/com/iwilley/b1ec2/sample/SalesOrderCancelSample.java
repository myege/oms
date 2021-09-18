 package com.iwilley.b1ec2.sample;
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.request.SalesOrderCancelRequest;
 import com.iwilley.b1ec2.api.response.SalesOrderCancelResponse;
 
 public class SalesOrderCancelSample {
   public static void main(String[] args) throws ApiException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     SalesOrderCancelRequest request = new SalesOrderCancelRequest();
     
     request.shopOrderNo = "969408746532838";
     SalesOrderCancelResponse response = (SalesOrderCancelResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
   }
 }


