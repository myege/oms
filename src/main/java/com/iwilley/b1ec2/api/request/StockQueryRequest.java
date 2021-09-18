 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.StockQueryResponse;
 import java.util.List;
 import java.util.Map;
 public class StockQueryRequest
   implements B1EC2Request<StockQueryResponse>
 {
   public Integer whsId;
   public List<String> skus;
   
   public String getApiMethodName() {
     return "B1EC2.Stock.Query";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("WhsId", this.whsId);
     if (this.skus != null && this.skus.size() > 0) {
       
       StringBuffer lineInfo = new StringBuffer();
       for (String sku : this.skus) {
         
         lineInfo.append(sku);
         lineInfo.append(";");
       } 
       parameters.put("SkuInfo", lineInfo.toString());
     } 
     return (Map<String, String>)parameters;
   }
 
   
   public Class<StockQueryResponse> getResponseClass() {
     return StockQueryResponse.class;
   }
   
   public Integer getWhsId() {
     return this.whsId;
   }
   
   public void setWhsId(Integer whsId) {
     this.whsId = whsId;
   }
   
   public List<String> getSkus() {
     return this.skus;
   }
   
   public void setSkus(List<String> skus) {
     this.skus = skus;
   }
 }


