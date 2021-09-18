 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.SalesOrderCreateLine;
 import com.iwilley.b1ec2.api.request.SalesOrderCreateRequest;
 import com.iwilley.b1ec2.api.response.SalesOrderCreateResponse;
 import java.util.ArrayList;
 import java.util.List;
 
 public class SalesOrderCreateSample {
   public static void main(String[] args) throws ApiException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     SalesOrderCreateRequest request = new SalesOrderCreateRequest();
     request.shopId = Integer.valueOf(4);
     request.whsId = Integer.valueOf(1);
     request.expressId = Integer.valueOf(8);
     request.customerName = "yoha";
     List<SalesOrderCreateLine> lineList = new ArrayList<>();
     SalesOrderCreateLine line1 = new SalesOrderCreateLine();
     line1.skuCode = "test01001002";
     line1.quantity = 10;
     line1.price = 10.2D;
     lineList.add(line1);
     SalesOrderCreateLine line2 = new SalesOrderCreateLine();
     line2.skuCode = "test01001002";
     line2.quantity = 10;
     line2.price = 10.2D;
     lineList.add(line2);
     request.setItemLines(lineList);
     SalesOrderCreateResponse response = (SalesOrderCreateResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
   }
 }


