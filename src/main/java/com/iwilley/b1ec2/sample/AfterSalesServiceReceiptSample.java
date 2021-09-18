 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.request.AfterSalesServiceReceiptRequest;
 import com.iwilley.b1ec2.api.response.AfterSalesServiceReceiptResponse;
 import java.text.ParseException;
 
 public class AfterSalesServiceReceiptSample
 {
   public static void main(String[] args) throws ApiException, ParseException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     AfterSalesServiceReceiptRequest request = new AfterSalesServiceReceiptRequest();
     request.setAfterSaleServiceId(Integer.valueOf(1));
     
     AfterSalesServiceReceiptResponse response = (AfterSalesServiceReceiptResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
   }
 }


