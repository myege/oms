 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.request.SalesOrderDeliverRequest;
 import com.iwilley.b1ec2.api.response.SalesOrderDeliverResponse;
 import java.text.ParseException;
 
 public class SalesOrderDeliverSample {
   public static void main(String[] args) throws ApiException, ParseException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     SalesOrderDeliverRequest request = new SalesOrderDeliverRequest();
     request.setOrderId(Integer.valueOf(63));
     request.setExpressId(Integer.valueOf(8));
     request.setExpressTrackNo("1234554321");
     request.setWeight(Double.valueOf(15.5D));
     
     SalesOrderDeliverResponse response = (SalesOrderDeliverResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
   }
 }


