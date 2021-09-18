 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.SalesOrderPickOutResponse;
 import java.util.Map;
 
 
 
 public class SalesOrderPickOutRequest
   implements B1EC2Request<SalesOrderPickOutResponse>
 {
   public int OrderId;
   public String OrderNo;
   public String ShopOrderNo;
   
   public String getApiMethodName() {
     return "B1EC2.SalesOrder.PickOut";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("OrderId", Integer.valueOf(this.OrderId));
     parameters.put("OrderNo", this.OrderNo);
     parameters.put("ShopOrderNo", this.ShopOrderNo);
     return (Map<String, String>)parameters;
   }
 
   
   public Class<SalesOrderPickOutResponse> getResponseClass() {
     return SalesOrderPickOutResponse.class;
   }
   
   public Integer getOrderId() {
     return Integer.valueOf(this.OrderId);
   }
   
   public void setOrderId(Integer OrderId) {
     this.OrderId = OrderId.intValue();
   }
   
   public String getOrderNo() {
     return this.OrderNo;
   }
   
   public void setOrderNo(String OrderNo) {
     this.OrderNo = OrderNo;
   }
   
   public String getShopOrderNo() {
     return this.ShopOrderNo;
   }
   
   public void setShopOrderNo(String ShopOrderNo) {
     this.ShopOrderNo = ShopOrderNo;
   }
 }


