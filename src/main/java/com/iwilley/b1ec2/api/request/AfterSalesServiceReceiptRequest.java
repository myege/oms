 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.AfterSalesServiceReceiptResponse;
 import java.util.Map;
 public class AfterSalesServiceReceiptRequest
   implements B1EC2Request<AfterSalesServiceReceiptResponse>
 {
   public Integer afterSaleServiceId;
   
   public String getApiMethodName() {
     return "B1EC2.AfterSalesService.Receipt";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("AfterSaleServiceId", this.afterSaleServiceId);
     return (Map<String, String>)parameters;
   }
 
   
   public Class<AfterSalesServiceReceiptResponse> getResponseClass() {
     return AfterSalesServiceReceiptResponse.class;
   }
   
   public Integer getAfterSaleServiceId() {
     return this.afterSaleServiceId;
   }
   
   public void setAfterSaleServiceId(Integer afterSaleServiceId) {
     this.afterSaleServiceId = afterSaleServiceId;
   }
 }


