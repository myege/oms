 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.ItemInfo;
 import com.iwilley.b1ec2.api.domain.SkuInfo;
 import com.iwilley.b1ec2.api.request.ItemInfoQueryRequest;
 import com.iwilley.b1ec2.api.response.ItemInfoQueryResponse;
 import java.text.DateFormat;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 
 public class ItemInfoQuerySample
 {
   public static void main(String[] args) throws ApiException, ParseException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     int pageSize = 5;
     DateFormat format = new SimpleDateFormat(
         "yyyy-MM-dd HH:mm:ss");
     
     ItemInfoQueryRequest request = new ItemInfoQueryRequest();
     request.setStartTime(format.parse("2014-05-20 00:00:00"));
     request.setEndTime(format.parse("2014-06-30 00:00:00"));
     
     request.setPageSize(Integer.valueOf(pageSize));
     
     ItemInfoQueryResponse response = (ItemInfoQueryResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
     System.out.println("�����:" + response.getTotalResults());
     
     if (response.getErrorCode() == 0 && response.getTotalResults() > 0) {
       int totalPages = (int)Math.ceil(response
           .getTotalResults() / pageSize);
 
       
       for (int i = 1; i <= totalPages && i <= 5; i++) {
         request.setPageNum(Integer.valueOf(i));
         response = (ItemInfoQueryResponse)client.execute((B1EC2Request)request);
         System.out.println("����ҳ��:" + i + "/" + totalPages);
         
         for (ItemInfo itemInfo : response.getItemInfos()) {
           System.out.println("��Ʒ��Ϣ:" + itemInfo.getItemCode() + "," + 
               itemInfo.getItemName());
           
           for (SkuInfo skuInfo : itemInfo.getLines()) {
             System.out.println("  SKU��Ϣ:" + skuInfo.getSkuCode() + 
                 "," + skuInfo.getSkuName());
           }
         } 
         System.out.println();
       } 
     } 
   }
 }


