 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.AfterSaleItemLine;
 import com.iwilley.b1ec2.api.domain.AfterSaleService;
 import com.iwilley.b1ec2.api.request.AfterSalesServiceQueryRequest;
 import com.iwilley.b1ec2.api.response.AfterSalesServiceQueryResponse;
 import java.text.DateFormat;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Iterator;
 
 public class AfterSalesServiceQuerySample {
   public static void main(String[] args) throws ApiException, ParseException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     int pageSize = 5;
     DateFormat format = new SimpleDateFormat(
         "yyyy-MM-dd HH:mm:ss");
     
     AfterSalesServiceQueryRequest request = new AfterSalesServiceQueryRequest();
     request.setStartTime(format.parse("2014-05-20 00:00:00"));
     request.setEndTime(format.parse("2014-06-22 00:00:00"));
     request.setPageSize(Integer.valueOf(pageSize));
     
     AfterSalesServiceQueryResponse response = (AfterSalesServiceQueryResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
     System.out.println("�����:" + response.getTotalResults());
     
     if (response.getErrorCode() == 0 && response.getTotalResults() > 0) {
       
       int totalPages = (int)Math.ceil(response
           .getTotalResults() / pageSize);
       totalPages = (totalPages > 5) ? 5 : totalPages;
       
       for (int i = totalPages; i >= 1; i--) {
         request.setPageNum(Integer.valueOf(i));
         response = (AfterSalesServiceQueryResponse)client.execute((B1EC2Request)request);
         System.out.println("����ҳ��:" + i + "/" + totalPages);
 
         
         Iterator<AfterSaleService> iterator = response.getAfterSaleServices().iterator(); while (iterator.hasNext()) { AfterSaleService afterSaleService = iterator.next();
           System.out.println("������Ϣ:" + 
               afterSaleService.getAfterSaleServiceNo() + "," + 
               afterSaleService.getGoodsTotal());
 
           
           Iterator<AfterSaleItemLine> iterator1 = afterSaleService.getItemLines().iterator(); while (iterator1.hasNext()) { AfterSaleItemLine itemLine = iterator1.next();
             System.out.println("  ��������Ϣ:" + 
                 (itemLine.getItemInfo()).itemCode + "," + 
                 itemLine.getSkuCode() + "," + 
                 itemLine.getQuantity()); }
            }
         
         System.out.println();
       } 
     } 
   }
 }


