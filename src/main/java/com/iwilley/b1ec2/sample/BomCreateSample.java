 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.BomCreateLine;
 import com.iwilley.b1ec2.api.request.BomCreateRequest;
 import com.iwilley.b1ec2.api.response.BomCreateResponse;
 import java.util.ArrayList;
 import java.util.List;
 
 public class BomCreateSample {
   public static void main(String[] args) throws ApiException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     BomCreateRequest request = new BomCreateRequest();
     
     request.setSkuCode("CQ00411");
     
     List<BomCreateLine> bomLines = new ArrayList<>();
     BomCreateLine sku1 = new BomCreateLine();
     sku1.setSkuCode("cqcs001");
     sku1.setQuantity(10);
     bomLines.add(sku1);
     BomCreateLine sku2 = new BomCreateLine();
     sku2.setSkuCode("cqcs002");
     sku2.setQuantity(5);
     bomLines.add(sku2);
     request.setBomLines(bomLines);
     
     BomCreateResponse response = (BomCreateResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
     System.out.println("errorCode:" + response.getErrorCode() + ",errorMessage" + response.getErrorMsg());
   }
 }


