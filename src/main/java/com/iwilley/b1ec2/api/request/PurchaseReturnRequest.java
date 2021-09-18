 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.PurchaseReturnLineInfo;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.PurchaseReturnResponse;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 public class PurchaseReturnRequest
   implements B1EC2Request<PurchaseReturnResponse>
 {
   public Integer returnId;
   public String purchaseReturnNo;
   public String whsAreaCode;
   public List<PurchaseReturnLineInfo> returnLines;
   
   public String getApiMethodName() {
     return "B1EC2.Purchase.Return";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("ReturnId", this.returnId);
     parameters.put("PurchaseReturnNo", this.purchaseReturnNo);
     parameters.put("WhsAreaCode", this.whsAreaCode);
     if (this.returnLines != null && this.returnLines.size() > 0) {
       
       StringBuffer lineInfo = new StringBuffer();
       for (PurchaseReturnLineInfo returnLine : this.returnLines) {
         
         lineInfo.append(returnLine.getLineNum());
         lineInfo.append(":");
         lineInfo.append(returnLine.getQuantity());
         lineInfo.append(":");
         lineInfo.append(returnLine.getSerialNumbers());
         lineInfo.append(";");
       } 
       parameters.put("LineInfo", lineInfo.toString());
     } 
     return (Map<String, String>)parameters;
   }
 
   
   public Class<PurchaseReturnResponse> getResponseClass() {
     return PurchaseReturnResponse.class;
   }
   
   public int getReturnId() {
     return this.returnId.intValue();
   }
   
   public void setReturnId(int returnId) {
     this.returnId = Integer.valueOf(returnId);
   }
   
   public String getPurchaseReturnNo() {
     return this.purchaseReturnNo;
   }
   
   public void setPurchaseReturnNo(String purchaseReturnNo) {
     this.purchaseReturnNo = purchaseReturnNo;
   }
   
   public String getWhsAreaCode() {
     return this.whsAreaCode;
   }
   
   public void setWhsAreaCode(String whsAreaCode) {
     this.whsAreaCode = whsAreaCode;
   }
   
   public List<PurchaseReturnLineInfo> getReceiptLines() {
     return this.returnLines;
   }
   
   public void setReceiptLines(List<PurchaseReturnLineInfo> returnLines) {
     this.returnLines = returnLines;
   }
 
   
   public PurchaseReturnRequest() {
     this.returnLines = new ArrayList<>();
   }
 }


