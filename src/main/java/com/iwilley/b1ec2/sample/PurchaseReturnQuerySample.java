 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.PurchaseReturn;
 import com.iwilley.b1ec2.api.domain.PurchaseReturnLine;
 import com.iwilley.b1ec2.api.request.PurchaseReturnQueryRequest;
 import com.iwilley.b1ec2.api.response.PurchaseReturnQueryResponse;
 import java.text.DateFormat;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 
 public class PurchaseReturnQuerySample
 {
   public static void main(String[] args) throws ApiException, ParseException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     int pageSize = 5;
     DateFormat format = new SimpleDateFormat(
         "yyyy-MM-dd HH:mm:ss");
     
     PurchaseReturnQueryRequest request = new PurchaseReturnQueryRequest();
     request.setStartTime(format.parse("2014-04-20 00:00:00"));
     request.setEndTime(format.parse("2014-07-30 00:00:00"));
     request.setPageSize(Integer.valueOf(pageSize));
     
     PurchaseReturnQueryResponse response = (PurchaseReturnQueryResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
     System.out.println("�����:" + response.getTotalResults());
     
     if (response.getErrorCode() == 0 && response.getTotalResults() > 0) {
       
       int totalPages = (int)Math.ceil(response
           .getTotalResults() / pageSize);
       totalPages = (totalPages > 5) ? 5 : totalPages;
       
       for (int i = totalPages; i >= 1; i--) {
         request.setPageNum(Integer.valueOf(i));
         response = (PurchaseReturnQueryResponse)client.execute((B1EC2Request)request);
         System.out.println("����ҳ��:" + i + "/" + totalPages);
         
         for (PurchaseReturn purchaseReturn : response.getPurchaseReturns()) {
           System.out.println("������Ϣ:" + purchaseReturn.getPurchaseReturnNo() + "," + 
               purchaseReturn.getOpenTotal());
           
           for (PurchaseReturnLine poLine : purchaseReturn.getLines()) {
             System.out.println("  ��������Ϣ:" + 
                 (poLine.getItemInfo()).itemCode + "," + 
                 poLine.getSkuCode() + "," + 
                 poLine.getQuantity());
           }
         } 
         System.out.println();
       } 
     } 
   }
 }


