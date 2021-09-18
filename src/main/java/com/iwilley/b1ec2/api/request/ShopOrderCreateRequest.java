 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.ShopOrderCreateDiscount;
 import com.iwilley.b1ec2.api.domain.ShopOrderCreateLine;
 import com.iwilley.b1ec2.api.domain.ShopOrderCreatePayment;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.ShopOrderCreateResponse;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 public class ShopOrderCreateRequest
   implements B1EC2Request<ShopOrderCreateResponse>
 {
   public String shopOrderNo;
   public Integer shopId;
   public Integer orderStatus;
   public String orderStatusName;
   public Boolean isCod;
   public Boolean isDistribution;
   public Boolean isJhs;
   public Boolean isPresale;
   public Boolean isMobile;
   public String memberNick;
   public Double discountFee;
   public Double postFee;
   public Double adjustFee;
   public Double goodsTotal;
   public Double orderTotal;
   public Double receivedTotal;
   public Date shopCreatedTime;
   public Date shopPayTime;
   public String buyerMemo;
   public String sellerMemo;
   public String shopFlag;
   public String invoiceName;
   public String invoiceMemo;
   public Integer invoiceType;
   public String expressName;
   public String expressTrackNo;
   public String buyerAlipayNo;
   public String buyerEmail;
   public String alipayOrderNo;
   public String receiverName;
   public String receiverState;
   public String receiverCity;
   public String receiverDistrict;
   public String receiverAddress;
   public String receiverZip;
   public String receiverMobile;
   public String receiverPhone;
   public Date reliveryTime;
   public Date endTime;
   public String userDefinedField1;
   public String userDefinedField2;
   public double customTax;
   public String customIdNo;
   public String customPaymentName;
   public String customPaymentNo;
   public String customName;
   private List<ShopOrderCreateLine> itemLines;
   private List<ShopOrderCreateDiscount> discountLines;
   private List<ShopOrderCreatePayment> paymentLines;
   
   public String getApiMethodName() {
     return "B1EC2.ShopOrder.Push";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("ShopOrderNo", this.shopOrderNo);
     parameters.put("ShopId", this.shopId);
     parameters.put("OrderStatus", this.orderStatus);
     parameters.put("OrderStatusName", this.orderStatusName);
     parameters.put("IsCod", this.isCod);
     parameters.put("IsDistribution", this.isDistribution);
     parameters.put("IsJhs", this.isJhs);
     parameters.put("IsPresale", this.isPresale);
     parameters.put("IsMobile", this.isMobile);
     parameters.put("MemberNick", this.memberNick);
     parameters.put("DiscountFee", this.discountFee);
     parameters.put("PostFee", this.postFee);
     parameters.put("AdjustFee", this.adjustFee);
     parameters.put("GoodsTotal", this.goodsTotal);
     parameters.put("OrderTotal", this.orderTotal);
     parameters.put("ReceivedTotal", this.receivedTotal);
     parameters.put("ShopCreatedTime", this.shopCreatedTime);
     parameters.put("ShopPayTime", this.shopPayTime);
     parameters.put("BuyerMemo", this.buyerMemo);
     parameters.put("SellerMemo", this.sellerMemo);
     parameters.put("ShopFlag", this.shopFlag);
     parameters.put("InvoiceName", this.invoiceName);
     parameters.put("InvoiceMemo", this.invoiceMemo);
     parameters.put("InvoiceType", this.invoiceType);
     parameters.put("ExpressName", this.expressName);
     parameters.put("ExpressTrackNo", this.expressTrackNo);
     parameters.put("BuyerAlipayNo", this.buyerAlipayNo);
     parameters.put("BuyerEmail", this.buyerEmail);
     parameters.put("AlipayOrderNo", this.alipayOrderNo);
     parameters.put("ReceiverName", this.receiverName);
     parameters.put("ReceiverState", this.receiverState);
     parameters.put("ReceiverCity", this.receiverCity);
     parameters.put("ReceiverDistrict", this.receiverDistrict);
     parameters.put("ReceiverAddress", this.receiverAddress);
     parameters.put("ReceiverZip", this.receiverZip);
     parameters.put("ReceiverMobile", this.receiverMobile);
     parameters.put("ReceiverPhone", this.receiverPhone);
     parameters.put("ReliveryTime", this.reliveryTime);
     parameters.put("EndTime", this.endTime);
     parameters.put("UserDefinedField1", this.userDefinedField1);
     parameters.put("UserDefinedField2", this.userDefinedField2);
     parameters.put("CustomTax", Double.valueOf(this.customTax));
     parameters.put("CustomIdNo", this.customIdNo);
     parameters.put("customName", this.customName);
     parameters.put("CustomPaymentName", this.customPaymentName);
     parameters.put("CustomPaymentNo", this.customPaymentNo);
     if (this.itemLines != null && this.itemLines.size() > 0) {
       StringBuffer lineInfo = new StringBuffer();
       for (ShopOrderCreateLine itemLine : this.itemLines) {
         lineInfo.append(itemLine.getShopLineNo());
         lineInfo.append(":");
         lineInfo.append(itemLine.getOuterId());
         lineInfo.append(":");
         lineInfo.append(itemLine.getQuantity());
         lineInfo.append(":");
         lineInfo.append(itemLine.getPrice());
         lineInfo.append(":");
         lineInfo.append(itemLine.getLineUdf1());
         lineInfo.append(":");
         lineInfo.append(itemLine.getLineUdf2());
         lineInfo.append(":");
         lineInfo.append(itemLine.getitemName());
         lineInfo.append(":");
         lineInfo.append(itemLine.getskuName());
         lineInfo.append(":");
         lineInfo.append(itemLine.getLineTotal());
         lineInfo.append(":");
         lineInfo.append(itemLine.getLineCustomTax());
         lineInfo.append(":");
         lineInfo.append("0.0");
         lineInfo.append(";");
       } 
       
       parameters.put("ItemLineInfo", lineInfo.toString());
     } 
     if (this.discountLines != null && this.discountLines.size() > 0) {
       StringBuffer lineInfo = new StringBuffer();
       for (ShopOrderCreateDiscount itemLine : this.discountLines) {
         lineInfo.append(itemLine.getDiscountName());
         lineInfo.append(":");
         lineInfo.append(itemLine.getDiscountFee());
         lineInfo.append(";");
       } 
       parameters.put("DiscountLineInfo", lineInfo.toString());
     } 
     if (this.paymentLines != null && this.paymentLines.size() > 0) {
       StringBuffer lineInfo = new StringBuffer();
       for (ShopOrderCreatePayment itemLine : this.paymentLines) {
         lineInfo.append(itemLine.getPaymentId());
         lineInfo.append(":");
         lineInfo.append(itemLine.getPaymentTotal());
         lineInfo.append(":");
         lineInfo.append(itemLine.getPaymentNo());
         lineInfo.append(";");
       } 
       parameters.put("PaymentLineInfo", lineInfo.toString());
     } 
     return (Map<String, String>)parameters;
   }
 
   
   public Class<ShopOrderCreateResponse> getResponseClass() {
     return ShopOrderCreateResponse.class;
   }
   
   public String getShopOrderNo() {
     return this.shopOrderNo;
   }
   
   public Integer getShopId() {
     return this.shopId;
   }
   
   public Integer getOrderStatus() {
     return this.orderStatus;
   }
   
   public String getOrderStatusName() {
     return this.orderStatusName;
   }
   
   public Boolean getIsCod() {
     return this.isCod;
   }
   
   public Boolean getIsDistribution() {
     return this.isDistribution;
   }
   
   public Boolean getIsJhs() {
     return this.isJhs;
   }
   
   public Boolean getIsPresale() {
     return this.isPresale;
   }
   
   public Boolean getIsMobile() {
     return this.isMobile;
   }
   
   public String getMemberNick() {
     return this.memberNick;
   }
   
   public Double getDiscountFee() {
     return this.discountFee;
   }
   
   public Double getPostFee() {
     return this.postFee;
   }
   
   public Double getAdjustFee() {
     return this.adjustFee;
   }
   
   public Double getGoodsTotal() {
     return this.goodsTotal;
   }
   
   public Double getOrderTotal() {
     return this.orderTotal;
   }
   
   public Double getReceivedTotal() {
     return this.receivedTotal;
   }
   
   public Date getShopCreatedTime() {
     return this.shopCreatedTime;
   }
   
   public Date getShopPayTime() {
     return this.shopPayTime;
   }
   
   public String getBuyerMemo() {
     return this.buyerMemo;
   }
   
   public String getSellerMemo() {
     return this.sellerMemo;
   }
   
   public String getShopFlag() {
     return this.shopFlag;
   }
   
   public String getInvoiceName() {
     return this.invoiceName;
   }
   
   public String getInvoiceMemo() {
     return this.invoiceMemo;
   }
   
   public Integer getInvoiceType() {
     return this.invoiceType;
   }
   
   public String getExpressName() {
     return this.expressName;
   }
   
   public String getExpressTrackNo() {
     return this.expressTrackNo;
   }
   
   public String getBuyerAlipayNo() {
     return this.buyerAlipayNo;
   }
   
   public String getBuyerEmail() {
     return this.buyerEmail;
   }
   
   public String getAlipayOrderNo() {
     return this.alipayOrderNo;
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
   
   public Date getReliveryTime() {
     return this.reliveryTime;
   }
   
   public Date getEndTime() {
     return this.endTime;
   }
   
   public String getUserDefinedField1() {
     return this.userDefinedField1;
   }
   
   public String getUserDefinedField2() {
     return this.userDefinedField2;
   }
   
   public List<ShopOrderCreateLine> getItemLines() {
     return this.itemLines;
   }
   
   public List<ShopOrderCreateDiscount> getDiscountLines() {
     return this.discountLines;
   }
   
   public List<ShopOrderCreatePayment> getPaymentLines() {
     return this.paymentLines;
   }
   
   public void setShopOrderNo(String shopOrderNo) {
     this.shopOrderNo = shopOrderNo;
   }
   
   public void setShopId(Integer shopId) {
     this.shopId = shopId;
   }
   
   public void setOrderStatus(Integer orderStatus) {
     this.orderStatus = orderStatus;
   }
   
   public void setOrderStatusName(String orderStatusName) {
     this.orderStatusName = orderStatusName;
   }
   
   public void setIsCod(Boolean isCod) {
     this.isCod = isCod;
   }
   
   public void setIsDistribution(Boolean isDistribution) {
     this.isDistribution = isDistribution;
   }
   
   public void setIsJhs(Boolean isJhs) {
     this.isJhs = isJhs;
   }
   
   public void setIsPresale(Boolean isPresale) {
     this.isPresale = isPresale;
   }
   
   public void setIsMobile(Boolean isMobile) {
     this.isMobile = isMobile;
   }
   
   public void setMemberNick(String memberNick) {
     this.memberNick = memberNick;
   }
   
   public void setDiscountFee(Double discountFee) {
     this.discountFee = discountFee;
   }
   
   public void setPostFee(Double postFee) {
     this.postFee = postFee;
   }
   
   public void setAdjustFee(Double adjustFee) {
     this.adjustFee = adjustFee;
   }
   
   public void setGoodsTotal(Double goodsTotal) {
     this.goodsTotal = goodsTotal;
   }
   
   public void setOrderTotal(Double orderTotal) {
     this.orderTotal = orderTotal;
   }
   
   public void setReceivedTotal(Double receivedTotal) {
     this.receivedTotal = receivedTotal;
   }
   
   public void setShopCreatedTime(Date shopCreatedTime) {
     this.shopCreatedTime = shopCreatedTime;
   }
   
   public void setShopPayTime(Date shopPayTime) {
     this.shopPayTime = shopPayTime;
   }
   
   public void setBuyerMemo(String buyerMemo) {
     this.buyerMemo = buyerMemo;
   }
   
   public void setSellerMemo(String sellerMemo) {
     this.sellerMemo = sellerMemo;
   }
   
   public void setShopFlag(String shopFlag) {
     this.shopFlag = shopFlag;
   }
   
   public void setInvoiceName(String invoiceName) {
     this.invoiceName = invoiceName;
   }
   
   public void setInvoiceMemo(String invoiceMemo) {
     this.invoiceMemo = invoiceMemo;
   }
   
   public void setInvoiceType(Integer invoiceType) {
     this.invoiceType = invoiceType;
   }
   
   public void setExpressName(String expressName) {
     this.expressName = expressName;
   }
   
   public void setExpressTrackNo(String expressTrackNo) {
     this.expressTrackNo = expressTrackNo;
   }
   
   public void setBuyerAlipayNo(String buyerAlipayNo) {
     this.buyerAlipayNo = buyerAlipayNo;
   }
   
   public void setBuyerEmail(String buyerEmail) {
     this.buyerEmail = buyerEmail;
   }
   
   public void setAlipayOrderNo(String alipayOrderNo) {
     this.alipayOrderNo = alipayOrderNo;
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
   
   public void setReliveryTime(Date reliveryTime) {
     this.reliveryTime = reliveryTime;
   }
   
   public void setEndTime(Date endTime) {
     this.endTime = endTime;
   }
   
   public void setUserDefinedField1(String userDefinedField1) {
     this.userDefinedField1 = userDefinedField1;
   }
   
   public void setUserDefinedField2(String userDefinedField2) {
     this.userDefinedField2 = userDefinedField2;
   }
   
   public void setItemLines(List<ShopOrderCreateLine> itemLines) {
     this.itemLines = itemLines;
   }
   
   public void setDiscountLines(List<ShopOrderCreateDiscount> discountLines) {
     this.discountLines = discountLines;
   }
   
   public void setPaymentLines(List<ShopOrderCreatePayment> paymentLines) {
     this.paymentLines = paymentLines;
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
   
   public void setCustomTax(double customTax) {
     this.customTax = customTax;
   }
 
   
   public String getCustomPaymentName() {
     return this.customPaymentName;
   }
   
   public void setCustomPaymentName(String customPaymentName) {
     this.customPaymentName = customPaymentName;
   }
   
   public String getCustomPaymentNo() {
     return this.customPaymentNo;
   }
   
   public void setCustomPaymentNo(String customPaymentNo) {
     this.customPaymentNo = customPaymentNo;
   }
 }


