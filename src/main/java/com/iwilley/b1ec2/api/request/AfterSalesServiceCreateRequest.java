 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.AfterSaleServiceExchangeAndRedeliveryItemLine;
 import com.iwilley.b1ec2.api.domain.AfterSaleServiceReturnItemLine;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.AfterSalesServiceCreateResponse;
 import java.util.List;
 import java.util.Map;
 public class AfterSalesServiceCreateRequest
   implements B1EC2Request<AfterSalesServiceCreateResponse>
 {
   public int type;
   public Integer orderId;
   public String orderNo;
   public Integer returnWhsId;
   public Integer deliverWhsId;
   public Integer refundReasonId;
   public String refundMemo;
   public Double refundFeeTotal;
   public Double fillUpFee;
   public Integer returnExpressId;
   public String expressTrackNo;
   public String userDefinedField1;
   public String userDefinedField2;
   public String userDefinedField3;
   public String userDefinedField4;
   public String receiverName;
   public String receiverState;
   public String receiverCity;
   public String receiverDistrict;
   public String receiverAddress;
   public String receiverZip;
   public String receiverMobile;
   public String receiverPhone;
   public List<AfterSaleServiceReturnItemLine> returnItemLines;
   public List<AfterSaleServiceExchangeAndRedeliveryItemLine> exchangeAndRedeliveryItemLines;
   
   public String getApiMethodName() {
     return "B1EC2.AfterSalesService.Create";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("Type", Integer.valueOf(this.type));
     parameters.put("OrderId", this.orderId);
     parameters.put("RefundReasonId", this.refundReasonId);
     parameters.put("OrderNo", this.orderNo);
     parameters.put("RefundMemo", this.refundMemo);
     parameters.put("ReturnWhsId", this.returnWhsId);
     parameters.put("DeliverWhsId", this.deliverWhsId);
     parameters.put("RefundFeeTotal", this.refundFeeTotal);
     parameters.put("FillUpFee", this.fillUpFee);
     parameters.put("ReturnExpressId", this.returnExpressId);
     parameters.put("ExpressTrackNo", this.expressTrackNo);
     parameters.put("UserDefinedField1", this.userDefinedField1);
     parameters.put("UserDefinedField2", this.userDefinedField2);
     parameters.put("UserDefinedField3", this.userDefinedField3);
     parameters.put("UserDefinedField4", this.userDefinedField4);
     parameters.put("ReceiverName", this.receiverName);
     parameters.put("ReceiverState", this.receiverState);
     parameters.put("ReceiverCity", this.receiverCity);
     parameters.put("ReceiverDistrict", this.receiverDistrict);
     parameters.put("ReceiverAddress", this.receiverAddress);
     parameters.put("ReceiverZip", this.receiverZip);
     parameters.put("ReceiverMobile", this.receiverMobile);
     parameters.put("ReceiverPhone", this.receiverPhone);
     if (this.returnItemLines != null && this.returnItemLines.size() > 0) {
       StringBuffer lineInfo = new StringBuffer();
       for (AfterSaleServiceReturnItemLine returnItemLine : this.returnItemLines) {
         lineInfo.append(returnItemLine.getOrderLineNum());
         lineInfo.append(":");
         lineInfo.append(returnItemLine.getQuantity());
         lineInfo.append(":");
       } 
       parameters.put("ReturnLineInfo", lineInfo.toString());
     } 
     if (this.exchangeAndRedeliveryItemLines != null && 
       this.exchangeAndRedeliveryItemLines.size() > 0) {
       StringBuffer lineInfo = new StringBuffer();
       for (AfterSaleServiceExchangeAndRedeliveryItemLine exchangeAndRedeliveryItemLine : this.exchangeAndRedeliveryItemLines) {
         lineInfo.append(exchangeAndRedeliveryItemLine.getOrderLineNum());
         lineInfo.append(":");
         lineInfo.append(exchangeAndRedeliveryItemLine.getQuantity());
         lineInfo.append(":");
       } 
       parameters.put("ExchangeAndRedeliveryLineInfo", lineInfo.toString());
     } 
     return (Map<String, String>)parameters;
   }
 
   
   public Class<AfterSalesServiceCreateResponse> getResponseClass() {
     return AfterSalesServiceCreateResponse.class;
   }
   
   public int getType() {
     return this.type;
   }
   
   public void setType(int type) {
     this.type = type;
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
   
   public Integer getReturnWhsId() {
     return this.returnWhsId;
   }
   
   public void setReturnWhsId(Integer returnWhsId) {
     this.returnWhsId = returnWhsId;
   }
   
   public Integer getDeliverWhsId() {
     return this.deliverWhsId;
   }
   
   public void setDeliverWhsId(Integer deliverWhsId) {
     this.deliverWhsId = deliverWhsId;
   }
   
   public String getRefundMemo() {
     return this.refundMemo;
   }
   
   public void setRefundMemo(String refundMemo) {
     this.refundMemo = refundMemo;
   }
   
   public Double getRefundFeeTotal() {
     return this.refundFeeTotal;
   }
   
   public void setRefundFeeTotal(Double refundFeeTotal) {
     this.refundFeeTotal = refundFeeTotal;
   }
   
   public Double getFillUpFee() {
     return this.fillUpFee;
   }
   
   public void setFillUpFee(Double fillUpFee) {
     this.fillUpFee = fillUpFee;
   }
   
   public Integer getReturnExpressId() {
     return this.returnExpressId;
   }
   
   public void setReturnExpressId(Integer returnExpressId) {
     this.returnExpressId = returnExpressId;
   }
   
   public String getExpressTrackNo() {
     return this.expressTrackNo;
   }
   
   public void setExpressTrackNo(String expressTrackNo) {
     this.expressTrackNo = expressTrackNo;
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
   
   public String getReceiverName() {
     return this.receiverName;
   }
   
   public String getReceiverState() {
     return this.receiverState;
   }
   
   public String getReceiverCity() {
     return this.receiverCity;
   }
   
   public String getReceiverDistrict() {
     return this.receiverDistrict;
   }
   
   public String getReceiverAddress() {
     return this.receiverAddress;
   }
   
   public String getReceiverZip() {
     return this.receiverZip;
   }
   
   public String getReceiverMobile() {
     return this.receiverMobile;
   }
   
   public String getReceiverPhone() {
     return this.receiverPhone;
   }
   
   public void setReceiverName(String receiverName) {
     this.receiverName = receiverName;
   }
   
   public void setReceiverState(String receiverState) {
     this.receiverState = receiverState;
   }
   
   public void setReceiverCity(String receiverCity) {
     this.receiverCity = receiverCity;
   }
   
   public void setReceiverDistrict(String receiverDistrict) {
     this.receiverDistrict = receiverDistrict;
   }
   
   public void setReceiverAddress(String receiverAddress) {
     this.receiverAddress = receiverAddress;
   }
   
   public void setReceiverZip(String receiverZip) {
     this.receiverZip = receiverZip;
   }
   
   public void setReceiverMobile(String receiverMobile) {
     this.receiverMobile = receiverMobile;
   }
   
   public void setReceiverPhone(String receiverPhone) {
     this.receiverPhone = receiverPhone;
   }
   
   public List<AfterSaleServiceReturnItemLine> getReturnItemLines() {
     return this.returnItemLines;
   }
 
   
   public void setReturnItemLines(List<AfterSaleServiceReturnItemLine> returnItemLines) {
     this.returnItemLines = returnItemLines;
   }
   
   public List<AfterSaleServiceExchangeAndRedeliveryItemLine> getExchangeAndRedeliveryItemLines() {
     return this.exchangeAndRedeliveryItemLines;
   }
 
   
   public void setExchangeAndRedeliveryItemLines(List<AfterSaleServiceExchangeAndRedeliveryItemLine> exchangeAndRedeliveryItemLines) {
     this.exchangeAndRedeliveryItemLines = exchangeAndRedeliveryItemLines;
   }
   
   public Integer getRefundReasonId() {
     return this.refundReasonId;
   }
   
   public void setRefundReasonId(Integer refundReasonId) {
     this.refundReasonId = refundReasonId;
   }
 }


