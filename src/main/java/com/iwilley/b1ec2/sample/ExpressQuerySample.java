 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.Express;
 import com.iwilley.b1ec2.api.request.ExpressQueryRequest;
 import com.iwilley.b1ec2.api.response.ExpressQueryResponse;
 
 public class ExpressQuerySample {
   public static void main(String[] args) throws ApiException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     ExpressQueryRequest request = new ExpressQueryRequest();
     ExpressQueryResponse response = (ExpressQueryResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
     
     if (response.getErrorCode() == 0)
       for (Express express : response.getExpresses())
         System.out.println("Express:" + express.getExpressId() + "," + 
             express.getExpressName());  
   }
 }


