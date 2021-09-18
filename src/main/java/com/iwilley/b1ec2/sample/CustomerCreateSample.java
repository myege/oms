 package com.iwilley.b1ec2.sample;
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.request.CustomerCreateRequest;
 import com.iwilley.b1ec2.api.response.CustomerCreateResponse;
 
 public class CustomerCreateSample {
   public static void main(String[] args) throws ApiException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     CustomerCreateRequest request = new CustomerCreateRequest();
     request.setCustomerName("444");
     request.setShopId(Integer.valueOf(10));
     request.setCity("������");
     request.setDistrict("������");
     request.setAddress("�½ֿ�����4-103");
     request.setMobile("18614088260");
     request.setZipCode("200333");
     request.setReceiverName("����ɮ");
     request.userDefinedField1 = "u1";
     request.userDefinedField2 = "u2";
     
     CustomerCreateResponse response = (CustomerCreateResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
     System.out.println("errorCode:" + response.getErrorCode() + 
         ",errorMessage" + response.getErrorMsg());
   }
 }


