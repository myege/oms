 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 public class ShopOrder
   extends B1EC2Object
 {
   private static final long serialVersionUID = -2140339483271411744L;
   @ApiField("ShopOrderNo")
   public String shopOrderNo;
   @ApiField("ShopId")
   public int shopId;
   @ApiField("Shop")
   public Shop shop;
   @ApiField("ConvertStatus")
   public int convertStatus;
   @ApiField("OrderStatus")
   public int orderStatus;
   @ApiField("OrderStatusName")
   public String orderStatusName;
   @ApiField("IsDistribution")
   public Boolean isCod;
   @ApiField("IsDistribution")
   public Boolean isDistribution;
   @ApiField("IsJhs")
   public Boolean isJhs;
   @ApiField("IsPresale")
   public Boolean isPresale;
   @ApiField("IsMobile")
   public Boolean isMobile;
   @ApiField("MemberNick")
   public String memberNick;
   @ApiField("DiscountFee")
   public double discountFee;
   @ApiField("PostFee")
   public double postFee;
   @ApiField("AdjustFee")
   public double adjustFee;
   @ApiField("GoodsTotal")
   public double goodsTotal;
   @ApiField("OrderTotal")
   public double orderTotal;
   @ApiField("ReceivedTotal")
   public double receivedTotal;
   @ApiField("ShopCreatedTime")
   public Date shopCreatedTime;
   @ApiField("ShopPayTime")
   public Date shopPayTime;
   @ApiField("BuyerMemo")
   public String buyerMemo;
   @ApiField("SellerMemo")
   public String sellerMemo;
   @ApiField("ShopFlag")
   public String shopFlag;
   @ApiField("InvoiceName")
   public String invoiceName;
   @ApiField("InvoiceMemo")
   public String invoiceMemo;
   @ApiField("InvoiceType")
   public int invoiceType;
   @ApiField("ExpressName")
   public String expressName;
   @ApiField("ExpressTrackNo")
   public String expressTrackNo;
   @ApiField("BuyerAlipayNo")
   public String buyerAlipayNo;
   @ApiField("BuyerEmail")
   public String buyerEmail;
   @ApiField("AlipayOrderNo")
   public String alipayOrderNo;
   @ApiField("ReceiverName")
   public String receiverName;
   @ApiField("ReceiverState")
   public String receiverState;
   @ApiField("ReceiverCity")
   public String receiverCity;
   @ApiField("ReceiverDistrict")
   public String receiverDistrict;
   @ApiField("ReceiverAddress")
   public String receiverAddress;
   @ApiField("ReceiverZip")
   public String receiverZip;
   @ApiField("ReceiverMobile")
   public String receiverMobile;
   @ApiField("ReceiverPhone")
   public String receiverPhone;
   @ApiField("DeliveryTime")
   public Date deliveryTime;
   @ApiField("EndTime")
   public Date endTime;
   @ApiField("CreatedTime")
   public Date createdTime;
   @ApiField("UserDefinedField1")
   public String userDefinedField1;
   @ApiField("UserDefinedField2")
   public String userDefinedField2;
   @ApiListField("Lines")
   @ApiField("ShopOrderLine")
   public List<ShopOrderLine> lines;
   @ApiListField("Discounts")
   @ApiField("ShopOrderDiscount")
   public List<ShopOrderDiscount> discounts;
   @ApiListField("Payments")
   @ApiField("ShopOrderPayment")
   public List<ShopOrderPayment> payments;
   @ApiField("CustomTax")
   public double customTax;
   @ApiField("CustomIdNo")
   public String customIdNo;
   
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
   
   public ShopOrder() {
     this.lines = new ArrayList<>();
     this.discounts = new ArrayList<>();
     this.payments = new ArrayList<>();
   }
   
   public String getShopOrderNo() {
     return this.shopOrderNo;
   }
   
   public int getShopId() {
     return this.shopId;
   }
   
   public Shop getShop() {
     return this.shop;
   }
   
   public int getConvertStatus() {
     return this.convertStatus;
   }
   
   public int getOrderStatus() {
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
   
   public double getDiscountFee() {
     return this.discountFee;
   }
   
   public double getPostFee() {
     return this.postFee;
   }
   
   public double getAdjustFee() {
     return this.adjustFee;
   }
   
   public double getGoodsTotal() {
     return this.goodsTotal;
   }
   
   public double getOrderTotal() {
     return this.orderTotal;
   }
   
   public double getReceivedTotal() {
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
   
   public int getInvoiceType() {
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
   
   public Date getDeliveryTime() {
     return this.deliveryTime;
   }
   
   public Date getEndTime() {
     return this.endTime;
   }
   
   public Date getCreatedTime() {
     return this.createdTime;
   }
   
   public String getUserDefinedField1() {
     return this.userDefinedField1;
   }
   
   public String getUserDefinedField2() {
     return this.userDefinedField2;
   }
   
   public List<ShopOrderLine> getLines() {
     return this.lines;
   }
   
   public List<ShopOrderDiscount> getDiscounts() {
     return this.discounts;
   }
   
   public List<ShopOrderPayment> getPayments() {
     return this.payments;
   }
   
   public void setShopOrderNo(String shopOrderNo) {
     this.shopOrderNo = shopOrderNo;
   }
   
   public void setShopId(int shopId) {
     this.shopId = shopId;
   }
   
   public void setShop(Shop shop) {
     this.shop = shop;
   }
   
   public void setConvertStatus(int convertStatus) {
     this.convertStatus = convertStatus;
   }
   
   public void setOrderStatus(int orderStatus) {
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
   
   public void setDiscountFee(double discountFee) {
     this.discountFee = discountFee;
   }
   
   public void setPostFee(double postFee) {
     this.postFee = postFee;
   }
   
   public void setAdjustFee(double adjustFee) {
     this.adjustFee = adjustFee;
   }
   
   public void setGoodsTotal(double goodsTotal) {
     this.goodsTotal = goodsTotal;
   }
   
   public void setOrderTotal(double orderTotal) {
     this.orderTotal = orderTotal;
   }
   
   public void setReceivedTotal(double receivedTotal) {
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
   
   public void setInvoiceType(int invoiceType) {
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
   
   public void setDeliveryTime(Date deliveryTime) {
     this.deliveryTime = deliveryTime;
   }
   
   public void setEndTime(Date endTime) {
     this.endTime = endTime;
   }
   
   public void setCreatedTime(Date createdTime) {
     this.createdTime = createdTime;
   }
   
   public void setUserDefinedField1(String userDefinedField1) {
     this.userDefinedField1 = userDefinedField1;
   }
   
   public void setUserDefinedField2(String userDefinedField2) {
     this.userDefinedField2 = userDefinedField2;
   }
   
   public void setLines(List<ShopOrderLine> lines) {
     this.lines = lines;
   }
   
   public void setDiscounts(List<ShopOrderDiscount> discounts) {
     this.discounts = discounts;
   }
   
   public void setPayments(List<ShopOrderPayment> payments) {
     this.payments = payments;
   }
 }


