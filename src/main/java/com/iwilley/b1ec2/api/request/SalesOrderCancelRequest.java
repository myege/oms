 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.SalesOrderCancelResponse;
 import java.util.Map;
 public class SalesOrderCancelRequest
   implements B1EC2Request<SalesOrderCancelResponse>
 {
   public Integer orderId;
   public String orderNo;
   public String shopOrderNo;
   
   public String getApiMethodName() {
     return "B1EC2.SalesOrder.Cancel";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("OrderId", this.orderId);
     parameters.put("OrderNo", this.orderNo);
     parameters.put("ShopOrderNo", this.shopOrderNo);
     return (Map<String, String>)parameters;
   }
 
   
   public Class<SalesOrderCancelResponse> getResponseClass() {
     return SalesOrderCancelResponse.class;
   }
   
   public Integer getOrderId() {
     return this.orderId;
   }
   
   public String getOrderNo() {
     return this.orderNo;
   }
   
   public void setOrderId(Integer orderId) {
     this.orderId = orderId;
   }
   
   public void setOrderNo(String orderNo) {
     this.orderNo = orderNo;
   }
   
   public String getShopOrderNo() {
     return this.shopOrderNo;
   }
   
   public void setShopOrderNo(String shopOrderNo) {
     this.shopOrderNo = shopOrderNo;
   }
 }


