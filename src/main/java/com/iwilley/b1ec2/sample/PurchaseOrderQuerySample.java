 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.PurchaseOrder;
 import com.iwilley.b1ec2.api.domain.PurchaseOrderLine;
 import com.iwilley.b1ec2.api.request.PurchaseOrderQueryRequest;
 import com.iwilley.b1ec2.api.response.PurchaseOrderQueryResponse;
 import java.text.DateFormat;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 
 public class PurchaseOrderQuerySample
 {
   public static void main(String[] args) throws ApiException, ParseException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     int pageSize = 5;
     DateFormat format = new SimpleDateFormat(
         "yyyy-MM-dd HH:mm:ss");
     
     PurchaseOrderQueryRequest request = new PurchaseOrderQueryRequest();
     request.setStartTime(format.parse("2014-04-20 00:00:00"));
     request.setEndTime(format.parse("2014-07-30 00:00:00"));
     request.setPageSize(Integer.valueOf(pageSize));
     
     PurchaseOrderQueryResponse response = (PurchaseOrderQueryResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
     System.out.println("�����:" + response.getTotalResults());
     
     if (response.getErrorCode() == 0 && response.getTotalResults() > 0) {
       
       int totalPages = (int)Math.ceil(response
           .getTotalResults() / pageSize);
       totalPages = (totalPages > 5) ? 5 : totalPages;
       
       for (int i = totalPages; i >= 1; i--) {
         request.setPageNum(Integer.valueOf(i));
         response = (PurchaseOrderQueryResponse)client.execute((B1EC2Request)request);
         System.out.println("����ҳ��:" + i + "/" + totalPages);
         
         for (PurchaseOrder purchaseOrder : response.getPurchaseOrders()) {
           System.out.println("������Ϣ:" + purchaseOrder.getOrderNo() + "," + 
               purchaseOrder.getTotalQuantity());
           
           for (PurchaseOrderLine poLine : purchaseOrder.getLines()) {
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


