 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.StockUpdateResponse;
 import java.util.Map;
 public class StockUpdateRequest
   implements B1EC2Request<StockUpdateResponse>
 {
   public String stock;
   public Boolean confirmed;
   
   public String getApiMethodName() {
     return "B1EC2.Stock.Update";
   }
   
   public StockUpdateRequest() {
     this.confirmed = Boolean.valueOf(true);
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("Stock", this.stock);
     parameters.put("Confirmed", this.confirmed);
     return (Map<String, String>)parameters;
   }
 
   
   public Class<StockUpdateResponse> getResponseClass() {
     return StockUpdateResponse.class;
   }
   
   public String getStock() {
     return this.stock;
   }
   
   public Boolean getConfirmed() {
     return this.confirmed;
   }
   
   public void setStock(String stock) {
     this.stock = stock;
   }
   
   public void setConfirmed(Boolean confirmed) {
     this.confirmed = confirmed;
   }
 }


