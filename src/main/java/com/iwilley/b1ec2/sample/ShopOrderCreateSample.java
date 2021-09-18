 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.ShopOrderCreateDiscount;
 import com.iwilley.b1ec2.api.domain.ShopOrderCreateLine;
 import com.iwilley.b1ec2.api.domain.ShopOrderCreatePayment;
 import com.iwilley.b1ec2.api.request.ShopOrderCreateRequest;
 import com.iwilley.b1ec2.api.response.ShopOrderCreateResponse;
 import com.what21.model.OrderBondedPush;
 import com.what21.model.OrderBondedPushSku;
 import com.what21.model.OrderMailPush;
 import com.what21.model.OrderMailPushSku;
 import com.what21.tools.Tools;
 import java.text.DateFormat;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.List;
 
 public class ShopOrderCreateSample {
   public static void main(String[] args) throws ParseException, ApiException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     ShopOrderCreateRequest request = new ShopOrderCreateRequest();
     DateFormat format = new SimpleDateFormat(
         "yyyy-MM-dd HH:mm:ss");
     request.memberNick = "4";
     request.shopOrderNo = "20160519";
     request.shopId = Integer.valueOf(4);
     request.orderStatus = Integer.valueOf(10);
     request.shopCreatedTime = format.parse("2015-05-07 00:00:00");
     request.customPaymentName = "微信";
     request.customPaymentNo = "11";
     request.customTax = 1111.0D;
     request.customIdNo = "4489809284093284";
     request.customPaymentName = "微信";
     request.customPaymentNo = "123213";
     List<ShopOrderCreateLine> line1 = new ArrayList<>();
     ShopOrderCreateLine shopOrderCreateLine1 = new ShopOrderCreateLine();
     shopOrderCreateLine1.shopLineNo = "ShopLineNo";
     shopOrderCreateLine1.outerId = "OuterId";
     shopOrderCreateLine1.price = 123.45D;
     shopOrderCreateLine1.quantity = 3;
     shopOrderCreateLine1.lineUdf1 = "我是自定义字段1";
     shopOrderCreateLine1.lineUdf2 = "我是自定义字段2";
     shopOrderCreateLine1.skuName = "1";
     shopOrderCreateLine1.itemName = "2";
     shopOrderCreateLine1.lineTotal = 4.0D;
     shopOrderCreateLine1.lineCustomTax = 3.0D;
     line1.add(shopOrderCreateLine1);
     
     List<ShopOrderCreateDiscount> line2 = new ArrayList<>();
     ShopOrderCreateDiscount shopOrderCreateDiscount1 = new ShopOrderCreateDiscount();
     shopOrderCreateDiscount1.discountName = "老板结婚，所有东西打7折";
     shopOrderCreateDiscount1.discountFee = 2.0D;
     line2.add(shopOrderCreateDiscount1);
     
     List<ShopOrderCreatePayment> line3 = new ArrayList<>();
     ShopOrderCreatePayment shopOrderCreatePayment1 = new ShopOrderCreatePayment();
     shopOrderCreatePayment1.paymentId = 3;
     shopOrderCreatePayment1.paymentTotal = 3.0D;
     shopOrderCreatePayment1.paymentNo = "2134324234324";
     line3.add(shopOrderCreatePayment1);
     
     request.setItemLines(line1);
     request.setDiscountLines(line2);
     request.setPaymentLines(line3);
     
     ShopOrderCreateResponse response = (ShopOrderCreateResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
   }
   
   public static String push(OrderBondedPush orderBondedPush) throws Exception {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     List<OrderBondedPushSku> orderBondedPushSkus = orderBondedPush.getGoodsList();
     ShopOrderCreateRequest request = new ShopOrderCreateRequest();
     request.shopOrderNo = orderBondedPush.getOrderSn();
     request.memberNick = "乐仓电子商务";
     request.shopId = Integer.valueOf(4);
     request.orderStatus = Integer.valueOf(10);
     request.shopCreatedTime = Tools.getCurrentTime();
     request.customIdNo = orderBondedPush.getBuyerIdNumber();
     request.customName = orderBondedPush.getBuyerName();
     List<ShopOrderCreateLine> line1 = new ArrayList<>();
     for (OrderBondedPushSku orderBondedPushSku : orderBondedPushSkus) {
       ShopOrderCreateLine shopOrderCreateLine1 = new ShopOrderCreateLine();
       shopOrderCreateLine1.shopLineNo = orderBondedPush.getOrderSn();
       shopOrderCreateLine1.outerId = orderBondedPushSku.getGoodsSn();
       shopOrderCreateLine1.price = orderBondedPushSku.getGoodsPrice().doubleValue();
       shopOrderCreateLine1.quantity = orderBondedPushSku.getGoodsNumber().intValue();
       shopOrderCreateLine1.lineUdf1 = "自定义字段1";
       shopOrderCreateLine1.lineUdf2 = "自定义字段2";
       shopOrderCreateLine1.skuName = "无";
       shopOrderCreateLine1.itemName = orderBondedPushSku.getItemNameZi();
       shopOrderCreateLine1.lineTotal = 0.0D;
       shopOrderCreateLine1.lineCustomTax = 0.0D;
       line1.add(shopOrderCreateLine1);
     } 
     request.setItemLines(line1);
     
     ShopOrderCreateResponse response = (ShopOrderCreateResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
     return response.getBody();
   }
   
   public static String push(OrderMailPush orderMailPush) throws Exception {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     List<OrderMailPushSku> orderMailPushSkus = orderMailPush.getGoodsList();
     ShopOrderCreateRequest request = new ShopOrderCreateRequest();
     request.shopOrderNo = orderMailPush.getOrderSn();
     request.memberNick = "乐仓电子商务";
     request.shopId = Integer.valueOf(4);
     request.orderStatus = Integer.valueOf(10);
     request.shopCreatedTime = Tools.getCurrentTime();
     request.customIdNo = orderMailPush.getBuyerIdNumber();
     request.customName = orderMailPush.getBuyerName();
     List<ShopOrderCreateLine> line1 = new ArrayList<>();
     for (OrderMailPushSku orderMailPushSku : orderMailPushSkus) {
       ShopOrderCreateLine shopOrderCreateLine1 = new ShopOrderCreateLine();
       shopOrderCreateLine1.shopLineNo = orderMailPush.getOrderSn();
       shopOrderCreateLine1.outerId = orderMailPushSku.getGoodsSn();
       shopOrderCreateLine1.price = orderMailPushSku.getGoodsPrice().doubleValue();
       shopOrderCreateLine1.quantity = orderMailPushSku.getGoodsNumber().intValue();
       shopOrderCreateLine1.lineUdf1 = "自定义字段1";
       shopOrderCreateLine1.lineUdf2 = "自定义字段2";
       shopOrderCreateLine1.skuName = "无";
       shopOrderCreateLine1.itemName = orderMailPushSku.getGoodsName();
       shopOrderCreateLine1.lineTotal = 0.0D;
       shopOrderCreateLine1.lineCustomTax = 0.0D;
       line1.add(shopOrderCreateLine1);
     } 
     request.setItemLines(line1);
     
     ShopOrderCreateResponse response = (ShopOrderCreateResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
     return response.getBody();
   }
 }


