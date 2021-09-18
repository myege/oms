 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.AfterSalesServiceUpdateResponse;
 import java.util.Map;
 public class AfterSalesServiceUpdateRequest
   implements B1EC2Request<AfterSalesServiceUpdateResponse>
 {
   public Integer afterSaleServiceId;
   public String afterSaleServiceNo;
   public String refundMemo;
   public String userDefinedField1;
   public String userDefinedField2;
   public String userDefinedField3;
   public String userDefinedField4;
   
   public String getApiMethodName() {
     return "B1EC2.AfterSalesService.Update";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("AfterSaleServiceId", this.afterSaleServiceId);
     parameters.put("AfterSaleServiceNo", this.afterSaleServiceNo);
     parameters.put("RefundMemo", this.refundMemo);
     parameters.put("UserDefinedField1", this.userDefinedField1);
     parameters.put("UserDefinedField2", this.userDefinedField2);
     parameters.put("UserDefinedField3", this.userDefinedField3);
     parameters.put("UserDefinedField4", this.userDefinedField4);
     return (Map<String, String>)parameters;
   }
 
   
   public Class<AfterSalesServiceUpdateResponse> getResponseClass() {
     return AfterSalesServiceUpdateResponse.class;
   }
   
   public Integer getAfterSaleServiceId() {
     return this.afterSaleServiceId;
   }
   
   public void setAfterSaleServiceId(Integer afterSaleServiceId) {
     this.afterSaleServiceId = afterSaleServiceId;
   }
   
   public String getAfterSaleServiceNo() {
     return this.afterSaleServiceNo;
   }
   
   public void setAfterSaleServiceNo(String afterSaleServiceNo) {
     this.afterSaleServiceNo = afterSaleServiceNo;
   }
   
   public String getRefundMemo() {
     return this.refundMemo;
   }
   
   public void setRefundMemo(String refundMemo) {
     this.refundMemo = refundMemo;
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
 }


