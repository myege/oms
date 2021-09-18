 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.SalesOrderCreateLine;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.SalesOrderCreateResponse;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 public class SalesOrderCreateRequest
   implements B1EC2Request<SalesOrderCreateResponse>
 {
   public Integer shopId;
   public Integer expressId;
   public Integer whsId;
   public String customerName;
   public String shopOrderNo;
   public Double actPostFee;
   public String sellerMemo;
   public Boolean isCod;
   public String receiverName;
   public String receiverMobile;
   public String receiverState;
   public String receiverCity;
   public String receiverDistrict;
   public String receiverAddress;
   public String receiverZip;
   public String invoiceName;
   public Integer invoiceType;
   public Double invoiceTotal;
   public String invoiceExpressNo;
   public String invoiceMemo;
   public String invoiceNo;
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
   public Double discountFee;
   public Double orderTotal;
   public String shopOrderStatus;
   public Date shopPayTime;
   public String adjustFee;
   public String goodsTotal;
   public double customTax;
   public String customIdNo;
   private List<SalesOrderCreateLine> itemLines;
   
   public String getApiMethodName() {
     return "B1EC2.SalesOrder.Create";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("ShopId", this.shopId);
     parameters.put("ExpressId", this.expressId);
     parameters.put("WhsId", this.whsId);
     parameters.put("CustomerName", this.customerName);
     parameters.put("ShopOrderNo", this.shopOrderNo);
     parameters.put("ActPostFee", this.actPostFee);
     parameters.put("SellerMemo", this.sellerMemo);
     parameters.put("IsCod", this.isCod);
     parameters.put("ReceiverName", this.receiverName);
     parameters.put("ReceiverMobile", this.receiverMobile);
     parameters.put("ReceiverState", this.receiverState);
     parameters.put("ReceiverCity", this.receiverCity);
     parameters.put("ReceiverDistrict", this.receiverDistrict);
     parameters.put("ReceiverAddress", this.receiverAddress);
     parameters.put("ReceiverZip", this.receiverZip);
     parameters.put("InvoiceName", this.invoiceName);
     parameters.put("InvoiceType", this.invoiceType);
     parameters.put("InvoiceTotal", this.invoiceTotal);
     parameters.put("InvoiceExpressNo", this.invoiceExpressNo);
     parameters.put("InvoiceMemo", this.invoiceMemo);
     parameters.put("InvoiceNo", this.invoiceNo);
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
     parameters.put("DiscountFee", this.discountFee);
     parameters.put("OrderTotal", this.orderTotal);
     parameters.put("ShopOrderStatus", this.shopOrderStatus);
     parameters.put("ShopPayTime", this.shopPayTime);
     parameters.put("AdjustFee", this.adjustFee);
     parameters.put("GoodsTotal", this.goodsTotal);
     parameters.put("CustomTax", Double.valueOf(this.customTax));
     parameters.put("CustomIdNo", this.customIdNo);
     if (this.itemLines != null && this.itemLines.size() > 0) {
       StringBuffer lineInfo = new StringBuffer();
       for (SalesOrderCreateLine itemLine : this.itemLines) {
         lineInfo.append(itemLine.getSkuCode());
         lineInfo.append("~");
         lineInfo.append(itemLine.getQuantity());
         lineInfo.append("~");
         lineInfo.append(itemLine.getPrice());
         lineInfo.append("~");
         lineInfo.append(itemLine.getLineMemo());
         lineInfo.append("~");
         lineInfo.append(itemLine.getLineUdf1());
         lineInfo.append("~");
         lineInfo.append(itemLine.getLineUdf2());
         lineInfo.append("~");
         lineInfo.append(itemLine.getIsVirtual());
         lineInfo.append("~");
         lineInfo.append(itemLine.getParentSku());
         lineInfo.append("^");
       } 
       parameters.put("ItemLineInfo", lineInfo.toString());
     } 
     return (Map<String, String>)parameters;
   }
 
   
   public Class<SalesOrderCreateResponse> getResponseClass() {
     return SalesOrderCreateResponse.class;
   }
   
   public Integer getShopId() {
     return this.shopId;
   }
   
   public Integer getExpressId() {
     return this.expressId;
   }
   
   public Integer getWhsId() {
     return this.whsId;
   }
   
   public String getCustomerName() {
     return this.customerName;
   }
   
   public String getShopOrderNo() {
     return this.shopOrderNo;
   }
   
   public Double getActPostFee() {
     return this.actPostFee;
   }
   
   public String getSellerMemo() {
     return this.sellerMemo;
   }
   
   public Boolean getIsCod() {
     return this.isCod;
   }
   
   public String getReceiverName() {
     return this.receiverName;
   }
   
   public String getReceiverMobile() {
     return this.receiverMobile;
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
   
   public String getInvoiceName() {
     return this.invoiceName;
   }
   
   public Integer getInvoiceType() {
     return this.invoiceType;
   }
   
   public Double getInvoiceTotal() {
     return this.invoiceTotal;
   }
   
   public String getInvoiceExpressNo() {
     return this.invoiceExpressNo;
   }
   
   public String getInvoiceMemo() {
     return this.invoiceMemo;
   }
   
   public String getInvoiceNo() {
     return this.invoiceNo;
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
   
   public String getUserDefinedField5() {
     return this.userDefinedField5;
   }
   
   public Double getUserDefinedField6() {
     return this.userDefinedField6;
   }
   
   public Double getUserDefinedField7() {
     return this.userDefinedField7;
   }
   
   public Date getUserDefinedField8() {
     return this.userDefinedField8;
   }
   
   public String getUserDefinedField9() {
     return this.userDefinedField9;
   }
   
   public String getUserDefinedField10() {
     return this.userDefinedField10;
   }
   
   public String getUserDefinedField11() {
     return this.userDefinedField11;
   }
   
   public Double getDiscountFee() {
     return this.discountFee;
   }
   
   public Double getOrderTotal() {
     return this.orderTotal;
   }
   
   public String getShopOrderStatus() {
     return this.shopOrderStatus;
   }
   
   public Date getShopPayTime() {
     return this.shopPayTime;
   }
   
   public String getAdjustFee() {
     return this.adjustFee;
   }
   
   public String getGoodsTotal() {
     return this.goodsTotal;
   }
   
   public List<SalesOrderCreateLine> getItemLines() {
     return this.itemLines;
   }
   
   public void setShopId(Integer shopId) {
     this.shopId = shopId;
   }
   
   public void setExpressId(Integer expressId) {
     this.expressId = expressId;
   }
   
   public void setWhsId(Integer whsId) {
     this.whsId = whsId;
   }
   
   public void setCustomerName(String customerName) {
     this.customerName = customerName;
   }
   
   public void setShopOrderNo(String shopOrderNo) {
     this.shopOrderNo = shopOrderNo;
   }
   
   public void setActPostFee(Double actPostFee) {
     this.actPostFee = actPostFee;
   }
   
   public void setSellerMemo(String sellerMemo) {
     this.sellerMemo = sellerMemo;
   }
   
   public void setIsCod(Boolean isCod) {
     this.isCod = isCod;
   }
   
   public void setReceiverName(String receiverName) {
     this.receiverName = receiverName;
   }
   
   public void setReceiverMobile(String receiverMobile) {
     this.receiverMobile = receiverMobile;
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
   
   public void setInvoiceName(String invoiceName) {
     this.invoiceName = invoiceName;
   }
   
   public void setInvoiceType(Integer invoiceType) {
     this.invoiceType = invoiceType;
   }
   
   public void setInvoiceTotal(Double invoiceTotal) {
     this.invoiceTotal = invoiceTotal;
   }
   
   public void setInvoiceExpressNo(String invoiceExpressNo) {
     this.invoiceExpressNo = invoiceExpressNo;
   }
   
   public void setInvoiceMemo(String invoiceMemo) {
     this.invoiceMemo = invoiceMemo;
   }
   
   public void setInvoiceNo(String invoiceNo) {
     this.invoiceNo = invoiceNo;
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
   
   public void setUserDefinedField5(String userDefinedField5) {
     this.userDefinedField5 = userDefinedField5;
   }
   
   public void setUserDefinedField6(Double userDefinedField6) {
     this.userDefinedField6 = userDefinedField6;
   }
   
   public void setUserDefinedField7(Double userDefinedField7) {
     this.userDefinedField7 = userDefinedField7;
   }
   
   public void setUserDefinedField8(Date userDefinedField8) {
     this.userDefinedField8 = userDefinedField8;
   }
   
   public void setUserDefinedField9(String userDefinedField9) {
     this.userDefinedField9 = userDefinedField9;
   }
   
   public void setUserDefinedField10(String userDefinedField10) {
     this.userDefinedField10 = userDefinedField10;
   }
   
   public void setUserDefinedField11(String userDefinedField11) {
     this.userDefinedField11 = userDefinedField11;
   }
   
   public void setDiscountFee(Double discountFee) {
     this.discountFee = discountFee;
   }
   
   public void setOrderTotal(Double orderTotal) {
     this.orderTotal = orderTotal;
   }
   
   public void setShopOrderStatus(String shopOrderStatus) {
     this.shopOrderStatus = shopOrderStatus;
   }
   
   public void setShopPayTime(Date shopPayTime) {
     this.shopPayTime = shopPayTime;
   }
   
   public void setAdjustFee(String adjustFee) {
     this.adjustFee = adjustFee;
   }
   public void setGoodsTotal(String goodsTotal) {
     this.goodsTotal = goodsTotal;
   }
 
   
   public String getCustomIdNo() {
     return this.customIdNo;
   }
   
   public void setCustomIdNo(String customIdNo) {
     this.customIdNo = customIdNo;
   }
   
   public double getCustomTax() {
     return this.customTax;
   }
   
   public void setCustomTax(int customTax) {
     this.customTax = customTax;
   }
 
   
   public void setItemLines(List<SalesOrderCreateLine> itemLines) {
     this.itemLines = itemLines;
   }
 }


