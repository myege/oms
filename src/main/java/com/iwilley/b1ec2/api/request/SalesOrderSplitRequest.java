 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.SalesOrderSplitResponse;
 import java.util.Map;
 public class SalesOrderSplitRequest
   implements B1EC2Request<SalesOrderSplitResponse>
 {
   public Integer orderId;
   public String orderNo;
   public String lineNums;
   
   public String getApiMethodName() {
     return "B1EC2.SalesOrder.Split";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("OrderId", this.orderId);
     parameters.put("OrderNo", this.orderNo);
     parameters.put("LineNums", this.lineNums);
     return (Map<String, String>)parameters;
   }
 
   
   public Class<SalesOrderSplitResponse> getResponseClass() {
     return SalesOrderSplitResponse.class;
   }
 
 
   
   public Integer getOrderId() {
     return this.orderId;
   }
   
   public void setOrderId(Integer orderId) {
     this.orderId = orderId;
   }
   
   public String getOrderNo() {
     return this.orderNo;
   }
   
   public void setOrderNo(String orderNo) {
     this.orderNo = orderNo;
   }
   
   public String getLineNums() {
     return this.lineNums;
   }
   
   public void setLineNums(String lineNums) {
     this.lineNums = lineNums;
   }
 }


