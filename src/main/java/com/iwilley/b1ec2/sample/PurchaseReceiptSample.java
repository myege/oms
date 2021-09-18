 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.PurchaseReceiptLine;
 import com.iwilley.b1ec2.api.request.PurchaseReceiptRequest;
 import com.iwilley.b1ec2.api.response.PurchaseReceiptResponse;
 import java.text.ParseException;
 import java.util.ArrayList;
 import java.util.List;
 
 public class PurchaseReceiptSample {
   public static void main(String[] args) throws ApiException, ParseException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     PurchaseReceiptRequest request = new PurchaseReceiptRequest();
     request.setPurchaseOrderId(6);
     request.setWhsAreaCode("1010");
     
     List<PurchaseReceiptLine> lines = new ArrayList<>();
     
     PurchaseReceiptLine line = new PurchaseReceiptLine();
     line.setLineNum(0);
     line.setQuantity(200);
     line.setSerialNumbers("dasdsadsad,fafdsfds");
     lines.add(line);
     
     PurchaseReceiptLine line2 = new PurchaseReceiptLine();
     line2.setLineNum(1);
     line2.setQuantity(20);
     line2.setSerialNumbers("fdsafsd,23232");
     lines.add(line2);
     
     request.setReceiptLines(lines);
     
     PurchaseReceiptResponse response = (PurchaseReceiptResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
   }
 }


