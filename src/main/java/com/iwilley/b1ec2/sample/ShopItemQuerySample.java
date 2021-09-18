 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.ShopItem;
 import com.iwilley.b1ec2.api.domain.ShopSku;
 import com.iwilley.b1ec2.api.request.ShopItemQueryRequest;
 import com.iwilley.b1ec2.api.response.ShopItemQueryResponse;
 import java.text.DateFormat;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 
 public class ShopItemQuerySample
 {
   public static void main(String[] args) throws ApiException, ParseException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     int pageSize = 5;
     DateFormat format = new SimpleDateFormat(
         "yyyy-MM-dd HH:mm:ss");
     
     ShopItemQueryRequest request = new ShopItemQueryRequest();
     request.setStartTime(format.parse("2014-01-20 00:00:00"));
     request.setEndTime(format.parse("2014-06-30 00:00:00"));
     
     request.setPageSize(Integer.valueOf(pageSize));
     
     ShopItemQueryResponse response = (ShopItemQueryResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
     System.out.println("�����:" + response.getTotalResults());
     
     if (response.getErrorCode() == 0 && response.getTotalResults() > 0) {
       int totalPages = (int)Math.ceil(response
           .getTotalResults() / pageSize);
 
       
       for (int i = 1; i <= totalPages && i <= 5; i++) {
         request.setPageNum(Integer.valueOf(i));
         response = (ShopItemQueryResponse)client.execute((B1EC2Request)request);
         System.out.println("����ҳ��:" + i + "/" + totalPages);
         
         for (ShopItem shopItem : response.getShopItems()) {
           System.out.println("��Ʒ��Ϣ:" + shopItem.getShopItemCode() + "," + 
               shopItem.getShopItemName());
           
           for (ShopSku shopSku : shopItem.getLines()) {
             System.out.println("  SKU��Ϣ:" + shopSku.getShopSkuCode() + 
                 "," + shopSku.getProperties());
           }
         } 
         System.out.println();
       } 
     } 
   }
 }


