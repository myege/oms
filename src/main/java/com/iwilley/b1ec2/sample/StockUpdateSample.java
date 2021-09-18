 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.request.StockUpdateRequest;
 import com.iwilley.b1ec2.api.response.StockUpdateResponse;
 import java.text.ParseException;
 
 public class StockUpdateSample {
   public static void main(String[] args) throws ApiException, ParseException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     StockUpdateRequest request = new StockUpdateRequest();
     request.setStock("1010:5040168250:1:2.5;1020:5040168250:2:2.5;");
     
     StockUpdateResponse response = (StockUpdateResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
   }
 }


