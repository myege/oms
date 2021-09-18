 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.SalesOrderDeliverResponse;
 import java.util.Date;
 import java.util.Map;
 public class SalesOrderDeliverRequest
   implements B1EC2Request<SalesOrderDeliverResponse>
 {
   public Integer orderId;
   public String orderNo;
   public Integer expressId;
   public String expressTrackNo;
   public Double weight;
   public Integer reasonId;
   public String holdingMemo;
   public String serialNumberList;
   public String invoiceNo;
   public Date invoiceDate;
   public String orderMemo;
   public String userDefinedField1;
   public String userDefinedField2;
   public String userDefinedField3;
   public String userDefinedField4;
   public String userDefinedField5;
   public Double userDefinedField6;
   public Double userDefinedField7;
   public Date userDefinedField8;
   
   public String getApiMethodName() {
     return "B1EC2.SalesOrder.Deliver";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("OrderId", this.orderId);
     parameters.put("OrderNo", this.orderNo);
     parameters.put("ExpressId", this.expressId);
     parameters.put("ExpressTrackNo", this.expressTrackNo);
     parameters.put("Weight", this.weight);
     parameters.put("InvoiceNo", this.invoiceNo);
     parameters.put("InvoiceDate", this.invoiceDate);
     parameters.put("OrderMemo", this.orderMemo);
     parameters.put("ReasonId", this.reasonId);
     parameters.put("HoldingMemo", this.holdingMemo);
     parameters.put("SerialNumberList", this.serialNumberList);
     parameters.put("UserDefinedField1", this.userDefinedField1);
     parameters.put("UserDefinedField2", this.userDefinedField2);
     parameters.put("UserDefinedField3", this.userDefinedField3);
     parameters.put("UserDefinedField4", this.userDefinedField4);
     parameters.put("UserDefinedField5", this.userDefinedField5);
     parameters.put("UserDefinedField6", this.userDefinedField6);
     parameters.put("UserDefinedField7", this.userDefinedField7);
     parameters.put("UserDefinedField8", this.userDefinedField8);
     return (Map<String, String>)parameters;
   }
 
   
   public Class<SalesOrderDeliverResponse> getResponseClass() {
     return SalesOrderDeliverResponse.class;
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
   
   public Integer getExpressId() {
     return this.expressId;
   }
   
   public void setExpressId(Integer expressId) {
     this.expressId = expressId;
   }
   
   public String getExpressTrackNo() {
     return this.expressTrackNo;
   }
   
   public void setExpressTrackNo(String expressTrackNo) {
     this.expressTrackNo = expressTrackNo;
   }
   
   public Double getWeight() {
     return this.weight;
   }
   
   public void setWeight(Double weight) {
     this.weight = weight;
   }
   
   public String getSerialNumberList() {
     return this.serialNumberList;
   }
   
   public void setSerialNumberList(String serialNumberList) {
     this.serialNumberList = serialNumberList;
   }
   
   public String getInvoiceNo() {
     return this.invoiceNo;
   }
   
   public void setInvoiceNo(String invoiceNo) {
     this.invoiceNo = invoiceNo;
   }
   
   public String getHoldingMemo() {
     return this.holdingMemo;
   }
   
   public void setHoldingMemo(String holdingMemo) {
     this.holdingMemo = holdingMemo;
   }
   
   public Integer getReasonId() {
     return this.reasonId;
   }
   
   public void setReasonId(Integer reasonId) {
     this.reasonId = reasonId;
   }
   
   public String getOrderMemo() {
     return this.orderMemo;
   }
   
   public void setOrderMemo(String orderMemo) {
     this.orderMemo = orderMemo;
   }
   
   public String getUserDefinedField1() {
     return this.userDefinedField1;
   }
   
   public void setUserDefinedField1(String userDefinedField1) {
     this.userDefinedField1 = userDefinedField1;
   }
   
   public String getUserDefinedField2() {
     return this.userDefinedField2;
   }
   
   public void setUserDefinedField2(String userDefinedField2) {
     this.userDefinedField2 = userDefinedField2;
   }
   
   public String getUserDefinedField3() {
     return this.userDefinedField3;
   }
   
   public void setUserDefinedField3(String userDefinedField3) {
     this.userDefinedField3 = userDefinedField3;
   }
   
   public String getUserDefinedField4() {
     return this.userDefinedField4;
   }
   
   public void setUserDefinedField4(String userDefinedField4) {
     this.userDefinedField4 = userDefinedField4;
   }
   
   public String getUserDefinedField5() {
     return this.userDefinedField5;
   }
   
   public void setUserDefinedField5(String userDefinedField5) {
     this.userDefinedField5 = userDefinedField5;
   }
   
   public Double getUserDefinedField6() {
     return this.userDefinedField6;
   }
   
   public void setUserDefinedField6(Double userDefinedField6) {
     this.userDefinedField6 = userDefinedField6;
   }
   
   public Double getUserDefinedField7() {
     return this.userDefinedField7;
   }
   
   public void setUserDefinedField7(Double userDefinedField7) {
     this.userDefinedField7 = userDefinedField7;
   }
   
   public Date getUserDefinedField8() {
     return this.userDefinedField8;
   }
   
   public void setUserDefinedField8(Date userDefinedField8) {
     this.userDefinedField8 = userDefinedField8;
   }
   
   public Date getInvoiceDate() {
     return this.invoiceDate;
   }
 
   
   public void setInvoiceDate(Date invoiceDate) {
     this.invoiceDate = invoiceDate;
   }
 }


