 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.SalesOrderUpdateResponse;
 import java.util.Date;
 import java.util.Map;
 public class SalesOrderUpdateRequest
   implements B1EC2Request<SalesOrderUpdateResponse>
 {
   public Integer orderId;
   public String orderNo;
   public String orderMemo;
   public Integer reasonId;
   public String holdingMemo;
   public String userDefinedField1;
   public String userDefinedField2;
   public String userDefinedField3;
   public String userDefinedField4;
   public String userDefinedField5;
   public Double userDefinedField6;
   public Double userDefinedField7;
   public Date userDefinedField8;
   public String userDefinedField9;
   public String userDefinedField10;
   public String userDefinedField11;
   
   public String getApiMethodName() {
     return "B1EC2.SalesOrder.Update";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("OrderId", this.orderId);
     parameters.put("OrderNo", this.orderNo);
     parameters.put("OrderMemo", this.orderMemo);
     parameters.put("ReasonId", this.reasonId);
     parameters.put("HoldingMemo", this.holdingMemo);
     parameters.put("UserDefinedField1", this.userDefinedField1);
     parameters.put("UserDefinedField2", this.userDefinedField2);
     parameters.put("UserDefinedField3", this.userDefinedField3);
     parameters.put("UserDefinedField4", this.userDefinedField4);
     parameters.put("UserDefinedField5", this.userDefinedField5);
     parameters.put("UserDefinedField6", this.userDefinedField6);
     parameters.put("UserDefinedField7", this.userDefinedField7);
     parameters.put("UserDefinedField8", this.userDefinedField8);
     parameters.put("UserDefinedField9", this.userDefinedField9);
     parameters.put("UserDefinedField10", this.userDefinedField10);
     parameters.put("UserDefinedField11", this.userDefinedField11);
     return (Map<String, String>)parameters;
   }
 
   
   public Class<SalesOrderUpdateResponse> getResponseClass() {
     return SalesOrderUpdateResponse.class;
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
   
   public String getOrderMemo() {
     return this.orderMemo;
   }
   
   public void setOrderMemo(String orderMemo) {
     this.orderMemo = orderMemo;
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
   
   public String getUserDefinedField9() {
     return this.userDefinedField9;
   }
   
   public void setUserDefinedField9(String userDefinedField9) {
     this.userDefinedField9 = userDefinedField9;
   }
   
   public String getUserDefinedField10() {
     return this.userDefinedField10;
   }
   
   public void setUserDefinedField10(String userDefinedField10) {
     this.userDefinedField10 = userDefinedField10;
   }
   
   public String getUserDefinedField11() {
     return this.userDefinedField11;
   }
   
   public void setUserDefinedField11(String userDefinedField11) {
     this.userDefinedField11 = userDefinedField11;
   }
 }


