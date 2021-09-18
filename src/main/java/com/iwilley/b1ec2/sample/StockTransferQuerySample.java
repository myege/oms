 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.StockTransfer;
 import com.iwilley.b1ec2.api.domain.StockTransferLine;
 import com.iwilley.b1ec2.api.request.StockTransferQueryRequest;
 import com.iwilley.b1ec2.api.response.StockTransferQueryResponse;
 import java.text.DateFormat;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 
 public class StockTransferQuerySample
 {
   public static void main(String[] args) throws ApiException, ParseException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     int pageSize = 5;
     DateFormat format = new SimpleDateFormat(
         "yyyy-MM-dd HH:mm:ss");
     
     StockTransferQueryRequest request = new StockTransferQueryRequest();
     request.setStartTime(format.parse("2010-04-20 00:00:00"));
     request.setEndTime(format.parse("2020-05-22 00:00:00"));
     request.setPageSize(Integer.valueOf(pageSize));
     request.setStockTransferNo("34150423000017");
     request.setStockTransferStatus(Integer.valueOf(30));
     request.setStockTransferType(Integer.valueOf(10));
     
     StockTransferQueryResponse response = (StockTransferQueryResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
     System.out.println("�����:" + response.getTotalResults());
     if (response.getErrorCode() == 0 && response.getTotalResults() > 0) {
       
       int totalPages = (int)Math.ceil(response
           .getTotalResults() / pageSize);
       totalPages = (totalPages > 5) ? 5 : totalPages;
       for (int i = totalPages; i >= 1; i--) {
         request.setPageNum(Integer.valueOf(i));
         response = (StockTransferQueryResponse)client.execute((B1EC2Request)request);
         System.out.println("����ҳ��:" + i + "/" + totalPages);
         
         for (StockTransfer stockTransfer : response.getStockTransfers()) {
           System.out.println("ת������Ϣ:" + stockTransfer.getStockTransferNo() + "," + 
               stockTransfer.getCreateUser());
           
           for (StockTransferLine line : stockTransfer.getLines()) {
             System.out.println("  ת��������Ϣ:" + 
                 line.getTransferId() + "," + 
                 line.getSkuCode() + "," + 
                 line.getSkuName());
           }
         } 
         System.out.println();
       } 
     } 
   }
 }


