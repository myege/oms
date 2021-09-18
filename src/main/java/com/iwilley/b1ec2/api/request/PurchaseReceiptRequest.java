 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.PurchaseReceiptLine;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.PurchaseReceiptResponse;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 public class PurchaseReceiptRequest
   implements B1EC2Request<PurchaseReceiptResponse>
 {
   public Integer purchaseOrderId;
   public String purchaseOrderNo;
   public String whsAreaCode;
   public List<PurchaseReceiptLine> receiptLines;
   public String memo;
   public String userDefinedField1;
   public String userDefinedField2;
   public String userDefinedField3;
   public String userDefinedField4;
   public String userDefinedField5;
   public Double userDefinedField6;
   public Double userDefinedField7;
   public Date userDefinedField8;
   
   public String getApiMethodName() {
     return "B1EC2.Purchase.Receipt";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("PurchaseOrderId", this.purchaseOrderId);
     parameters.put("PurchaseOrderNo", this.purchaseOrderNo);
     parameters.put("WhsAreaCode", this.whsAreaCode);
     parameters.put("Memo", this.memo);
     parameters.put("UserDefinedField1", this.userDefinedField1);
     parameters.put("UserDefinedField2", this.userDefinedField2);
     parameters.put("UserDefinedField3", this.userDefinedField3);
     parameters.put("UserDefinedField4", this.userDefinedField4);
     parameters.put("UserDefinedField5", this.userDefinedField5);
     parameters.put("UserDefinedField6", this.userDefinedField6);
     parameters.put("UserDefinedField7", this.userDefinedField7);
     parameters.put("UserDefinedField8", this.userDefinedField8);
     if (this.receiptLines != null && this.receiptLines.size() > 0) {
       
       StringBuffer lineInfo = new StringBuffer();
       for (PurchaseReceiptLine receiptLine : this.receiptLines) {
         
         lineInfo.append(receiptLine.getLineNum());
         lineInfo.append(":");
         lineInfo.append(receiptLine.getQuantity());
         lineInfo.append(":");
         lineInfo.append(receiptLine.getSerialNumbers());
         lineInfo.append(";");
       } 
       parameters.put("LineInfo", lineInfo.toString());
     } 
     return (Map<String, String>)parameters;
   }
 
   
   public Class<PurchaseReceiptResponse> getResponseClass() {
     return PurchaseReceiptResponse.class;
   }
   
   public int getPurchaseOrderId() {
     return this.purchaseOrderId.intValue();
   }
   
   public void setPurchaseOrderId(int purchaseOrderId) {
     this.purchaseOrderId = Integer.valueOf(purchaseOrderId);
   }
   
   public String getPurchaseOrderNo() {
     return this.purchaseOrderNo;
   }
   
   public void setPurchaseOrderNo(String purchaseOrderNo) {
     this.purchaseOrderNo = purchaseOrderNo;
   }
   
   public String getWhsAreaCode() {
     return this.whsAreaCode;
   }
   
   public void setWhsAreaCode(String whsAreaCode) {
     this.whsAreaCode = whsAreaCode;
   }
   
   public List<PurchaseReceiptLine> getReceiptLines() {
     return this.receiptLines;
   }
   
   public void setReceiptLines(List<PurchaseReceiptLine> receiptLines) {
     this.receiptLines = receiptLines;
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
   
   public String getMemo() {
     return this.memo;
   }
   
   public void setMemo(String memo) {
     this.memo = memo;
   }
   
   public PurchaseReceiptRequest() {
     this.receiptLines = new ArrayList<>();
   }
 }


