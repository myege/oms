 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.UnknownPackageItemLine;
 import com.iwilley.b1ec2.api.request.UnknownPackageCreateRequest;
 import com.iwilley.b1ec2.api.response.UnknownPackageCreateResponse;
 import java.util.ArrayList;
 import java.util.List;
 
 public class UnKnownPackageCreateSample {
   public static void main(String[] args) throws ApiException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     UnknownPackageCreateRequest request = new UnknownPackageCreateRequest();
     
     request.returnWhsId = Integer.valueOf(1);
     request.returnExpressId = Integer.valueOf(15);
     request.expressTrackNo = "1234567890";
     request.userDefinedField1 = "UserDefinedField1";
     request.userDefinedField2 = "UserDefinedField2";
     request.userDefinedField3 = "UserDefinedField3";
     request.userDefinedField4 = "UserDefinedField4";
     
     List<UnknownPackageItemLine> line1 = new ArrayList<>();
     UnknownPackageItemLine itemLine1 = new UnknownPackageItemLine();
     itemLine1.skuCode = "testSku1";
     itemLine1.quantity = 5;
     line1.add(itemLine1);
     UnknownPackageItemLine itemLine2 = new UnknownPackageItemLine();
     itemLine2.skuCode = "testSku1";
     itemLine2.quantity = 10;
     line1.add(itemLine2);
     
     request.setItemLines(line1);
     
     UnknownPackageCreateResponse response = (UnknownPackageCreateResponse)client.execute((B1EC2Request)request);
     
     System.out.println(response.getBody());
   }
 }


