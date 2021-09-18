 package com.iwilley.b1ec2.sample;
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.request.AfterSalesServiceUpdateRequest;
 import com.iwilley.b1ec2.api.response.AfterSalesServiceUpdateResponse;
 
 public class AfterSalesServiceUpdateSample {
   public static void main(String[] args) throws ApiException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     AfterSalesServiceUpdateRequest request = new AfterSalesServiceUpdateRequest();
     
     request.setAfterSaleServiceId(Integer.valueOf(80));
     request.setUserDefinedField1("java �޸�1");
     request.setUserDefinedField2("java �޸�2");
     
     AfterSalesServiceUpdateResponse response = (AfterSalesServiceUpdateResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
     System.out.println("errorCode:" + response.getErrorCode() + ",errorMessage" + response.getErrorMsg());
   }
 }


