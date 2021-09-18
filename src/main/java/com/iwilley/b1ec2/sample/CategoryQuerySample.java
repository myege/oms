 package com.iwilley.b1ec2.sample;
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.Category;
 import com.iwilley.b1ec2.api.request.CategoryQueryRequest;
 import com.iwilley.b1ec2.api.response.CategoryQueryResponse;
 
 public class CategoryQuerySample {
   public static void main(String[] args) throws ApiException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     CategoryQueryRequest request = new CategoryQueryRequest();
     
     CategoryQueryResponse response = (CategoryQueryResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
     System.out.println("errorCode:" + response.getErrorCode() + 
         ",errorMessage" + response.getErrorMsg());
     for (Category info : response.getCategories())
       System.out.println(String.valueOf(info.getCatCode()) + " , " + info.getCatName()); 
   }
 }


