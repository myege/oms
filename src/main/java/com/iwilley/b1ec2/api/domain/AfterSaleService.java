 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 public class AfterSaleService
   extends B1EC2Object
 {
   private static final long serialVersionUID = 608467493460864875L;
   @ApiField("AfterSaleServiceId")
   public int afterSaleServiceId;
   @ApiField("Version")
   public int version;
   @ApiField("AfterSaleServiceNo")
   public String afterSaleServiceNo;
   @ApiField("ShopId")
   public Integer shopId;
   @ApiField("Shop")
   public Shop shop;
   @ApiField("CustomerCode")
   public String customerCode;
   @ApiField("CustomerName")
   public String customerName;
   @ApiField("Type")
   public int type;
   @ApiField("Status")
   public int status;
   @ApiField("DeliverWhsId")
   public Integer deliverWhsId;
   @ApiField("ReturnWhsId")
   public Integer returnWhsId;
   @ApiField("ReturnWarehouse")
   public Warehouse returnWarehouse;
   @ApiField("OrderId")
   public Integer orderId;
   @ApiField("OrderNo")
   public String orderNo;
   @ApiField("ShopOrderStatus")
   public String shopOrderStatus;
   @ApiField("ExchangeOrderId")
   public Integer exchangeOrderId;
   @ApiField("ShopRefundNo")
   public String shopRefundNo;
   @ApiField("ReasonId")
   public Integer reasonId;
   @ApiField("RefundMemo")
   public String refundMemo;
   @ApiField("RefundFeeTotal")
   public double refundFeeTotal;
   @ApiField("ActualRefundFee")
   public double actualRefundFee;
   @ApiField("ActualFillUpFee")
   public double actualFillUpFee;
   @ApiField("FillUpFee")
   public double fillUpFee;
   @ApiField("GoodsTotal")
   public double goodsTotal;
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
   @ApiField("SellerMemo")
   public String sellerMemo;
   @ApiField("DeliverExpressId")
   public Integer deliverExpressId;
   @ApiField("ReturnExpressId")
   public Integer returnExpressId;
   @ApiField("ReturnExpress")
   public Express returnExpress;
   @ApiField("ExpressTrackNo")
   public String expressTrackNo;
   @ApiField("BuyerAlipayNo")
   public String buyerAlipayNo;
   @ApiField("CreatedTime")
   public Date createdTime;
   @ApiField("UpdateTime")
   public Date updateTime;
   @ApiField("LastModifiedUser")
   public String lastModifiedUser;
   @ApiField("LastModifiedType")
   public int lastModifiedType;
   @ApiField("UserDefinedField1")
   public String userDefinedField1;
   @ApiListField("ItemLines")
   @ApiField("AfterSaleItemLine")
   public List<AfterSaleItemLine> itemLines = new ArrayList<>();
 
   
   public int getAfterSaleServiceId() {
     return this.afterSaleServiceId;
   }
   
   public void setAfterSaleServiceId(int afterSaleServiceId) {
     this.afterSaleServiceId = afterSaleServiceId;
   }
   
   public int getVersion() {
     return this.version;
   }
   
   public void setVersion(int version) {
     this.version = version;
   }
   
   public String getAfterSaleServiceNo() {
     return this.afterSaleServiceNo;
   }
   
   public void setAfterSaleServiceNo(String afterSaleServiceNo) {
     this.afterSaleServiceNo = afterSaleServiceNo;
   }
   
   public Integer getShopId() {
     return this.shopId;
   }
   
   public void setShopId(Integer shopId) {
     this.shopId = shopId;
   }
   
   public Shop getShop() {
     return this.shop;
   }
   
   public void setShop(Shop shop) {
     this.shop = shop;
   }
   
   public String getCustomerCode() {
     return this.customerCode;
   }
   
   public void setCustomerCode(String customerCode) {
     this.customerCode = customerCode;
   }
   
   public String getCustomerName() {
     return this.customerName;
   }
   
   public void setCustomerName(String customerName) {
     this.customerName = customerName;
   }
   
   public int getType() {
     return this.type;
   }
   
   public void setType(int type) {
     this.type = type;
   }
   
   public int getStatus() {
     return this.status;
   }
   
   public void setStatus(int status) {
     this.status = status;
   }
   
   public Integer getDeliverWhsId() {
     return this.deliverWhsId;
   }
   
   public void setDeliverWhsId(Integer deliverWhsId) {
     this.deliverWhsId = deliverWhsId;
   }
   
   public Integer getReturnWhsId() {
     return this.returnWhsId;
   }
   
   public void setReturnWhsId(Integer returnWhsId) {
     this.returnWhsId = returnWhsId;
   }
   
   public Warehouse getReturnWarehouse() {
     return this.returnWarehouse;
   }
   
   public void setReturnWarehouse(Warehouse returnWarehouse) {
     this.returnWarehouse = returnWarehouse;
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
   
   public String getShopOrderStatus() {
     return this.shopOrderStatus;
   }
   
   public void setShopOrderStatus(String shopOrderStatus) {
     this.shopOrderStatus = shopOrderStatus;
   }
   
   public Integer getExchangeOrderId() {
     return this.exchangeOrderId;
   }
   
   public void setExchangeOrderId(Integer exchangeOrderId) {
     this.exchangeOrderId = exchangeOrderId;
   }
   
   public String getShopRefundNo() {
     return this.shopRefundNo;
   }
   
   public void setShopRefundNo(String shopRefundNo) {
     this.shopRefundNo = shopRefundNo;
   }
   
   public Integer getReasonId() {
     return this.reasonId;
   }
   
   public void setReasonId(Integer reasonId) {
     this.reasonId = reasonId;
   }
   
   public String getRefundMemo() {
     return this.refundMemo;
   }
   
   public void setRefundMemo(String refundMemo) {
     this.refundMemo = refundMemo;
   }
   
   public double getRefundFeeTotal() {
     return this.refundFeeTotal;
   }
   
   public void setRefundFeeTotal(double refundFeeTotal) {
     this.refundFeeTotal = refundFeeTotal;
   }
   
   public double getActualRefundFee() {
     return this.actualRefundFee;
   }
   
   public void setActualRefundFee(double actualRefundFee) {
     this.actualRefundFee = actualRefundFee;
   }
   
   public double getActualFillUpFee() {
     return this.actualFillUpFee;
   }
   
   public void setActualFillUpFee(double actualFillUpFee) {
     this.actualFillUpFee = actualFillUpFee;
   }
   
   public double getFillUpFee() {
     return this.fillUpFee;
   }
   
   public void setFillUpFee(double fillUpFee) {
     this.fillUpFee = fillUpFee;
   }
   
   public double getGoodsTotal() {
     return this.goodsTotal;
   }
   
   public void setGoodsTotal(double goodsTotal) {
     this.goodsTotal = goodsTotal;
   }
   
   public String getReceiverName() {
     return this.receiverName;
   }
   
   public void setReceiverName(String receiverName) {
     this.receiverName = receiverName;
   }
   
   public String getReceiverState() {
     return this.receiverState;
   }
   
   public void setReceiverState(String receiverState) {
     this.receiverState = receiverState;
   }
   
   public String getReceiverCity() {
     return this.receiverCity;
   }
   
   public void setReceiverCity(String receiverCity) {
     this.receiverCity = receiverCity;
   }
   
   public String getReceiverDistrict() {
     return this.receiverDistrict;
   }
   
   public void setReceiverDistrict(String receiverDistrict) {
     this.receiverDistrict = receiverDistrict;
   }
   
   public String getReceiverAddress() {
     return this.receiverAddress;
   }
   
   public void setReceiverAddress(String receiverAddress) {
     this.receiverAddress = receiverAddress;
   }
   
   public String getReceiverZip() {
     return this.receiverZip;
   }
   
   public void setReceiverZip(String receiverZip) {
     this.receiverZip = receiverZip;
   }
   
   public String getReceiverMobile() {
     return this.receiverMobile;
   }
   
   public void setReceiverMobile(String receiverMobile) {
     this.receiverMobile = receiverMobile;
   }
   
   public String getReceiverPhone() {
     return this.receiverPhone;
   }
   
   public void setReceiverPhone(String receiverPhone) {
     this.receiverPhone = receiverPhone;
   }
   
   public String getSellerMemo() {
     return this.sellerMemo;
   }
   
   public void setSellerMemo(String sellerMemo) {
     this.sellerMemo = sellerMemo;
   }
   
   public Integer getDeliverExpressId() {
     return this.deliverExpressId;
   }
   
   public void setDeliverExpressId(Integer deliverExpressId) {
     this.deliverExpressId = deliverExpressId;
   }
   
   public Integer getReturnExpressId() {
     return this.returnExpressId;
   }
   
   public void setReturnExpressId(Integer returnExpressId) {
     this.returnExpressId = returnExpressId;
   }
   
   public Express getReturnExpress() {
     return this.returnExpress;
   }
   
   public void setReturnExpress(Express returnExpress) {
     this.returnExpress = returnExpress;
   }
   
   public String getExpressTrackNo() {
     return this.expressTrackNo;
   }
   
   public void setExpressTrackNo(String expressTrackNo) {
     this.expressTrackNo = expressTrackNo;
   }
   
   public String getBuyerAlipayNo() {
     return this.buyerAlipayNo;
   }
   
   public void setBuyerAlipayNo(String buyerAlipayNo) {
     this.buyerAlipayNo = buyerAlipayNo;
   }
   
   public Date getCreatedTime() {
     return this.createdTime;
   }
   
   public void setCreatedTime(Date createdTime) {
     this.createdTime = createdTime;
   }
   
   public Date getUpdateTime() {
     return this.updateTime;
   }
   
   public void setUpdateTime(Date updateTime) {
     this.updateTime = updateTime;
   }
   
   public String getLastModifiedUser() {
     return this.lastModifiedUser;
   }
   
   public void setLastModifiedUser(String lastModifiedUser) {
     this.lastModifiedUser = lastModifiedUser;
   }
   
   public int getLastModifiedType() {
     return this.lastModifiedType;
   }
   
   public void setLastModifiedType(int lastModifiedType) {
     this.lastModifiedType = lastModifiedType;
   }
   
   public List<AfterSaleItemLine> getItemLines() {
     return this.itemLines;
   }
   
   public void setItemLines(List<AfterSaleItemLine> itemLines) {
     this.itemLines = itemLines;
   }
   
   public String getUserDefinedField1() {
     return this.userDefinedField1;
   }
   
   public void setUserDefinedField1(String userDefinedField1) {
     this.userDefinedField1 = userDefinedField1;
   }
 }


