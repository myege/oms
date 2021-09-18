 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.BomCreateLine;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.BomCreateResponse;
 import java.util.List;
 import java.util.Map;
 public class BomCreateRequest
   implements B1EC2Request<BomCreateResponse>
 {
   public String skuCode;
   public List<BomCreateLine> bomLines;
   
   public String getApiMethodName() {
     return "B1EC2.Bom.Create";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("SkuCode", this.skuCode);
     if (this.bomLines != null && this.bomLines.size() > 0) {
       StringBuffer lineInfo = new StringBuffer();
       for (BomCreateLine bomLine : this.bomLines) {
         lineInfo.append(bomLine.getSkuCode());
         lineInfo.append(":");
         lineInfo.append(bomLine.getQuantity());
         lineInfo.append(";");
       } 
       parameters.put("BomLineInfo", lineInfo.toString());
     } 
     return (Map<String, String>)parameters;
   }
 
   
   public Class<BomCreateResponse> getResponseClass() {
     return BomCreateResponse.class;
   }
   
   public String getSkuCode() {
     return this.skuCode;
   }
   
   public List<BomCreateLine> getBomLines() {
     return this.bomLines;
   }
   
   public void setSkuCode(String skuCode) {
     this.skuCode = skuCode;
   }
   
   public void setBomLines(List<BomCreateLine> bomLines) {
     this.bomLines = bomLines;
   }
 }


