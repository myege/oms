 package com.iwilley.b1ec2.sample;
 import com.iwilley.b1ec2.api.ApiException;
import com.iwilley.b1ec2.api.B1EC2Client;
import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.request.SalesOrderUnapproveRequest;
import com.iwilley.b1ec2.api.response.SalesOrderUnapproveResponse;
 
 public class SalesOrderUnapproveSample {
   public static void main(String[] args) throws ApiException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     SalesOrderUnapproveRequest request = new SalesOrderUnapproveRequest();
     request.OrderNo = "11367907983";
     SalesOrderUnapproveResponse response = (SalesOrderUnapproveResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
   }
 }


