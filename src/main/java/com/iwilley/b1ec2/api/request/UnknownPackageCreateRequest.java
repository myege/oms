 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.UnknownPackageItemLine;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.UnknownPackageCreateResponse;
 import java.util.List;
 import java.util.Map;
 public class UnknownPackageCreateRequest
   implements B1EC2Request<UnknownPackageCreateResponse>
 {
   public Integer returnWhsId;
   public Integer returnExpressId;
   public String expressTrackNo;
   public String userDefinedField1;
   public String userDefinedField2;
   public String userDefinedField3;
   public String userDefinedField4;
   private List<UnknownPackageItemLine> itemLines;
   
   public String getApiMethodName() {
     return "B1EC2.UnknownPackage.Create";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("ReturnWhsId", this.returnWhsId);
     parameters.put("ReturnExpressId", this.returnExpressId);
     parameters.put("ExpressTrackNo", this.expressTrackNo);
     parameters.put("UserDefinedField1", this.userDefinedField1);
     parameters.put("UserDefinedField2", this.userDefinedField2);
     parameters.put("UserDefinedField3", this.userDefinedField3);
     parameters.put("UserDefinedField4", this.userDefinedField4);
     if (this.itemLines != null && this.itemLines.size() > 0) {
       StringBuffer lineInfo = new StringBuffer();
       for (UnknownPackageItemLine returnLine : this.itemLines) {
         lineInfo.append(returnLine.getSkuCode());
         lineInfo.append(":");
         lineInfo.append(returnLine.getQuantity());
         lineInfo.append(";");
       } 
       parameters.put("ItemLineInfo", lineInfo.toString());
     } 
     return (Map<String, String>)parameters;
   }
 
   
   public Class<UnknownPackageCreateResponse> getResponseClass() {
     return UnknownPackageCreateResponse.class;
   }
   
   public Integer getReturnWhsId() {
     return this.returnWhsId;
   }
   
   public Integer getReturnExpressId() {
     return this.returnExpressId;
   }
   
   public String getExpressTrackNo() {
     return this.expressTrackNo;
   }
   
   public String getUserDefinedField1() {
     return this.userDefinedField1;
   }
   
   public String getUserDefinedField2() {
     return this.userDefinedField2;
   }
   
   public String getUserDefinedField3() {
     return this.userDefinedField3;
   }
   
   public String getUserDefinedField4() {
     return this.userDefinedField4;
   }
   
   public List<UnknownPackageItemLine> getItemLines() {
     return this.itemLines;
   }
   
   public void setReturnWhsId(Integer returnWhsId) {
     this.returnWhsId = returnWhsId;
   }
   
   public void setReturnExpressId(Integer returnExpressId) {
     this.returnExpressId = returnExpressId;
   }
   
   public void setExpressTrackNo(String expressTrackNo) {
     this.expressTrackNo = expressTrackNo;
   }
   
   public void setUserDefinedField1(String userDefinedField1) {
     this.userDefinedField1 = userDefinedField1;
   }
   
   public void setUserDefinedField2(String userDefinedField2) {
     this.userDefinedField2 = userDefinedField2;
   }
   
   public void setUserDefinedField3(String userDefinedField3) {
     this.userDefinedField3 = userDefinedField3;
   }
   
   public void setUserDefinedField4(String userDefinedField4) {
     this.userDefinedField4 = userDefinedField4;
   }
   
   public void setItemLines(List<UnknownPackageItemLine> itemLines) {
     this.itemLines = itemLines;
   }
 }


