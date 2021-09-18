 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.ShopSkuPushLine;
 import com.iwilley.b1ec2.api.request.ShopItemPushRequest;
 import com.iwilley.b1ec2.api.response.ShopItemPushResponse;
 import java.text.DateFormat;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.List;
 
 public class ShopItemPushSample {
   public static void main(String[] args) throws ParseException, ApiException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     ShopItemPushRequest request = new ShopItemPushRequest();
     DateFormat format = new SimpleDateFormat(
         "yyyy-MM-dd HH:mm:ss");
     request.shopItemName = "ShopItemName11";
     request.shopItemCode = "1222";
     request.shopId = Integer.valueOf(4);
     request.status = "������";
     request.createdTime = format.parse("2015-05-07 00:00:00");
     request.updateTime = format.parse("2015-05-07 00:00:00");
     List<ShopSkuPushLine> lineList = new ArrayList<>();
     ShopSkuPushLine ShopSkuPushLine1 = new ShopSkuPushLine();
     ShopSkuPushLine1.shopSkuCode = "ShopSkuCode1";
     ShopSkuPushLine1.outerId = "OuterId1";
     ShopSkuPushLine1.price = 123.45D;
     ShopSkuPushLine1.quantity = 3;
     ShopSkuPushLine1.size = 21.0D;
     ShopSkuPushLine1.weight = 21.21D;
     ShopSkuPushLine1.property1 = "red";
     ShopSkuPushLine1.property2 = "xl";
     request.setSkusInfo(lineList);
     ShopItemPushResponse response = (ShopItemPushResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
   }
 }


