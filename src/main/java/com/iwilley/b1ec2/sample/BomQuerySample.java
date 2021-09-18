 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.Bom;
 import com.iwilley.b1ec2.api.domain.BomLine;
 import com.iwilley.b1ec2.api.request.BomQueryRequest;
 import com.iwilley.b1ec2.api.response.BomQueryResponse;
 import java.text.DateFormat;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 
 public class BomQuerySample
 {
   public static void main(String[] args) throws ParseException, ApiException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     BomQueryRequest request = new BomQueryRequest();
     int pageSize = 5;
     DateFormat format = new SimpleDateFormat(
         "yyyy-MM-dd HH:mm:ss");
     request.setStartTime(format.parse("2014-05-20 00:00:00"));
     request.setEndTime(format.parse("2015-06-30 00:00:00"));
     request.setPageSize(Integer.valueOf(pageSize));
     
     BomQueryResponse response = (BomQueryResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
     System.out.println("errorCode:" + response.getErrorCode() + 
         ",errorMessage" + response.getErrorMsg());
     System.out.println("�����:" + response.getTotalResults());
     
     if (response.getErrorCode() == 0 && response.getTotalResults() > 0) {
       int totalPages = (int)Math.ceil(response
           .getTotalResults() / pageSize);
 
       
       for (int i = 1; i <= totalPages && i <= 5; i++) {
         request.setPageNum(Integer.valueOf(i));
         response = (BomQueryResponse)client.execute((B1EC2Request)request);
         System.out.println("����ҳ��:" + i + "/" + totalPages);
         
         for (Bom bom : response.getBoms()) {
           System.out.println("�����Ʒ��Ϣ:" + bom.getItemCode() + "," + 
               bom.getItemName());
           
           for (BomLine line : bom.getLines()) {
             System.out.println("�����Ʒ����Ϣ:" + line.getSkuCode() + "," + 
                 line.getSkuName());
           }
         } 
         System.out.println();
       } 
     } 
   }
 }


