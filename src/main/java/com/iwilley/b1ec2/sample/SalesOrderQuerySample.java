 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.SalesOrder;
 import com.iwilley.b1ec2.api.domain.SalesOrderLine;
 import com.iwilley.b1ec2.api.request.SalesOrderQueryRequest;
 import com.iwilley.b1ec2.api.response.SalesOrderQueryResponse;
 import java.text.DateFormat;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 
 public class SalesOrderQuerySample
 {
   public static void main(String[] args) throws ApiException, ParseException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     int pageSize = 5;
     DateFormat format = new SimpleDateFormat(
         "yyyy-MM-dd HH:mm:ss");
     
     SalesOrderQueryRequest request = new SalesOrderQueryRequest();
     request.setStartTime(format.parse("2010-04-20 00:00:00"));
     request.setEndTime(format.parse("2020-05-22 00:00:00"));
     request.setPageSize(Integer.valueOf(pageSize));
     request.setShopId(Integer.valueOf(2));
     request.setStatus(Integer.valueOf(40));
     request.setUserDefinedField1("δͬ��");
 
     
     SalesOrderQueryResponse response = (SalesOrderQueryResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
     System.out.println("�����:" + response.getTotalResults());
     
     if (response.getErrorCode() == 0 && response.getTotalResults() > 0) {
       
       int totalPages = (int)Math.ceil(response
           .getTotalResults() / pageSize);
       totalPages = (totalPages > 5) ? 5 : totalPages;
       
       for (int i = totalPages; i >= 1; i--) {
         request.setPageNum(Integer.valueOf(i));
         response = (SalesOrderQueryResponse)client.execute((B1EC2Request)request);
         System.out.println("����ҳ��:" + i + "/" + totalPages);
         
         for (SalesOrder salesOrder : response.getSalesOrders()) {
           System.out.println("������Ϣ:" + salesOrder.getOrderNo() + "," + 
               salesOrder.getOrderTotal());
           
           for (SalesOrderLine soLine : salesOrder.getLines()) {
             System.out.println("  ��������Ϣ:" + 
                 (soLine.getIteminfo()).itemCode + "," + 
                 soLine.getSkuCode() + "," + 
                 soLine.getQuantity());
           }
         } 
         System.out.println();
       } 
     } 
   }
 }


