 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 public class SalesOrder
   extends B1EC2Object
 {
   private static final long serialVersionUID = -8800777970088480989L;
   @ApiField("OrderId")
   public int orderId;
   @ApiField("Version")
   public int version;
   @ApiField("OrderNo")
   public String orderNo;
   @ApiField("ShopId")
   public int shopId;
   @ApiField("Shop")
   public Shop shop;
   @ApiField("CustomerCode")
   public String customerCode;
   @ApiField("CustomerName")
   public String customerName;
   @ApiField("WhsId")
   public int whsId;
   @ApiField("Warehouse")
   public Warehouse warehouse;
   @ApiField("OrderType")
   public int orderType;
   @ApiField("OrderStatus")
   public int orderStatus;
   @ApiField("OrderTag")
   public int orderTag;
   @ApiField("ReasonId")
   public Integer reasonId;
   @ApiField("HoldingReason")
   public HoldingReason holdingReason;
   @ApiField("HoldingMemo")
   public String holdingMemo;
   @ApiField("TotalNum")
   public double totalNum;
   @ApiField("DiscountFee")
   public double discountFee;
   @ApiField("AdjustFee")
   public double adjustFee;
   @ApiField("GoodsTotal")
   public double goodsTotal;
   @ApiField("OrderTotal")
   public double orderTotal;
   @ApiField("CalcTotal")
   public double calcTotal;
   @ApiField("StockTotal")
   public double stockTotal;
   @ApiField("ShopOrderNo")
   public String shopOrderNo;
   @ApiField("ShopOrderStatus")
   public String shopOrderStatus;
   @ApiField("ShopCreatedTime")
   public Date shopCreatedTime;
   @ApiField("ShopPayTime")
   public Date shopPayTime;
   @ApiField("BuyerMemo")
   public String buyerMemo;
   @ApiField("SellerMemo")
   public String sellerMemo;
   @ApiField("OrderMemo")
   public String orderMemo;
   @ApiField("ShopFlag")
   public String shopFlag;
   @ApiField("InvoiceStatus")
   public int invoiceStatus;
   @ApiField("InvoiceType")
   public int invoiceType;
   @ApiField("InvoiceTotal")
   public double invoiceTotal;
   @ApiField("InvoiceName")
   public String invoiceName;
   @ApiField("InvoiceMemo")
   public String invoiceMemo;
   @ApiField("InvoiceExpressNo")
   public String invoiceExpressNo;
   @ApiField("BuyerAlipayNo")
   public String buyerAlipayNo;
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
   @ApiField("ExpressId")
   public int expressId;
   @ApiField("Express")
   public Express express;
   @ApiField("ExpressTrackNo")
   public String expressTrackNo;
   @ApiField("PrintTag")
   public int printTag;
   @ApiField("PrintBatchNo")
   public String printBatchNo;
   @ApiField("ExpectedDeliveryDate")
   public Date expectedDeliveryDate;
   @ApiField("DeliveryTime")
   public Date deliveryTime;
   @ApiField("EndTime")
   public Date endTime;
   @ApiField("StdPostFee")
   public double stdPostFee;
   @ApiField("CostPostFee")
   public double costPostFee;
   @ApiField("ActPostFee")
   public double actPostFee;
   @ApiField("EstimatedWeight")
   public double estimatedWeight;
   @ApiField("ActualWeight")
   public double actualWeight;
   @ApiField("Creator")
   public String creator;
   @ApiField("CreatedTime")
   public Date createdTime;
   @ApiField("LastModifiedTime")
   public Date lastModifiedTime;
   @ApiField("LastModifiedUser")
   public String lastModifiedUser;
   @ApiField("LastModifiedType")
   public int lastModifiedType;
   @ApiField("UserDefinedField1")
   public String userDefinedField1;
   @ApiField("UserDefinedField2")
   public String userDefinedField2;
   @ApiField("UserDefinedField3")
   public String userDefinedField3;
   @ApiField("UserDefinedField4")
   public String userDefinedField4;
   @ApiField("UserDefinedField5")
   public String userDefinedField5;
   @ApiField("UserDefinedField6")
   public Double userDefinedField6;
   @ApiField("UserDefinedField7")
   public Double userDefinedField7;
   @ApiField("UserDefinedField8")
   public Date userDefinedField8;
   @ApiField("UserDefinedField9")
   public String userDefinedField9;
   @ApiField("UserDefinedField10")
   public String userDefinedField10;
   @ApiField("UserDefinedField11")
   public String userDefinedField11;
   @ApiListField("Lines")
   @ApiField("SalesOrderLine")
   public List<SalesOrderLine> lines;
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
 
   
   public int getOrderId() {
     return this.orderId;
   }
   
   public void setOrderId(int orderId) {
     this.orderId = orderId;
   }
   
   public int getVersion() {
     return this.version;
   }
   
   public void setVersion(int version) {
     this.version = version;
   }
   
   public String getOrderNo() {
     return this.orderNo;
   }
   
   public void setOrderNo(String orderNo) {
     this.orderNo = orderNo;
   }
   
   public int getShopId() {
     return this.shopId;
   }
   
   public void setShopId(int shopId) {
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
   
   public int getWhsId() {
     return this.whsId;
   }
   
   public void setWhsId(int whsId) {
     this.whsId = whsId;
   }
   
   public Warehouse getWarehouse() {
     return this.warehouse;
   }
   
   public void setWarehouse(Warehouse warehouse) {
     this.warehouse = warehouse;
   }
   
   public int getOrderType() {
     return this.orderType;
   }
   
   public void setOrderType(int orderType) {
     this.orderType = orderType;
   }
   
   public int getOrderStatus() {
     return this.orderStatus;
   }
   
   public void setOrderStatus(int orderStatus) {
     this.orderStatus = orderStatus;
   }
   
   public int getOrderTag() {
     return this.orderTag;
   }
   
   public void setOrderTag(int orderTag) {
     this.orderTag = orderTag;
   }
   
   public Integer getReasonId() {
     return this.reasonId;
   }
   
   public void setReasonId(Integer reasonId) {
     this.reasonId = reasonId;
   }
   
   public HoldingReason getHoldingReason() {
     return this.holdingReason;
   }
   
   public void setHoldingReason(HoldingReason holdingReason) {
     this.holdingReason = holdingReason;
   }
   
   public String getHoldingMemo() {
     return this.holdingMemo;
   }
   
   public void setHoldingMemo(String holdingMemo) {
     this.holdingMemo = holdingMemo;
   }
   
   public double getTotalNum() {
     return this.totalNum;
   }
   
   public void setTotalNum(double totalNum) {
     this.totalNum = totalNum;
   }
   
   public double getDiscountFee() {
     return this.discountFee;
   }
   
   public void setDiscountFee(double discountFee) {
     this.discountFee = discountFee;
   }
   
   public double getAdjustFee() {
     return this.adjustFee;
   }
   
   public void setAdjustFee(double adjustFee) {
     this.adjustFee = adjustFee;
   }
   
   public double getGoodsTotal() {
     return this.goodsTotal;
   }
   
   public void setGoodsTotal(double goodsTotal) {
     this.goodsTotal = goodsTotal;
   }
   
   public double getOrderTotal() {
     return this.orderTotal;
   }
   
   public void setOrderTotal(double orderTotal) {
     this.orderTotal = orderTotal;
   }
   
   public double getCalcTotal() {
     return this.calcTotal;
   }
   
   public void setCalcTotal(double calcTotal) {
     this.calcTotal = calcTotal;
   }
   
   public double getStockTotal() {
     return this.stockTotal;
   }
   
   public void setStockTotal(double stockTotal) {
     this.stockTotal = stockTotal;
   }
   
   public String getShopOrderNo() {
     return this.shopOrderNo;
   }
   
   public void setShopOrderNo(String shopOrderNo) {
     this.shopOrderNo = shopOrderNo;
   }
   
   public String getShopOrderStatus() {
     return this.shopOrderStatus;
   }
   
   public void setShopOrderStatus(String shopOrderStatus) {
     this.shopOrderStatus = shopOrderStatus;
   }
   
   public Date getShopCreatedTime() {
     return this.shopCreatedTime;
   }
   
   public void setShopCreatedTime(Date shopCreatedTime) {
     this.shopCreatedTime = shopCreatedTime;
   }
   
   public Date getShopPayTime() {
     return this.shopPayTime;
   }
   
   public void setShopPayTime(Date shopPayTime) {
     this.shopPayTime = shopPayTime;
   }
   
   public String getBuyerMemo() {
     return this.buyerMemo;
   }
   
   public void setBuyerMemo(String buyerMemo) {
     this.buyerMemo = buyerMemo;
   }
   
   public String getSellerMemo() {
     return this.sellerMemo;
   }
   
   public void setSellerMemo(String sellerMemo) {
     this.sellerMemo = sellerMemo;
   }
   
   public String getOrderMemo() {
     return this.orderMemo;
   }
   
   public void setOrderMemo(String orderMemo) {
     this.orderMemo = orderMemo;
   }
   
   public String getShopFlag() {
     return this.shopFlag;
   }
   
   public void setShopFlag(String shopFlag) {
     this.shopFlag = shopFlag;
   }
   
   public int getInvoiceStatus() {
     return this.invoiceStatus;
   }
   
   public void setInvoiceStatus(int invoiceStatus) {
     this.invoiceStatus = invoiceStatus;
   }
   
   public int getInvoiceType() {
     return this.invoiceType;
   }
   
   public void setInvoiceType(int invoiceType) {
     this.invoiceType = invoiceType;
   }
   
   public double getInvoiceTotal() {
     return this.invoiceTotal;
   }
   
   public void setInvoiceTotal(double invoiceTotal) {
     this.invoiceTotal = invoiceTotal;
   }
   
   public String getInvoiceName() {
     return this.invoiceName;
   }
   
   public void setInvoiceName(String invoiceName) {
     this.invoiceName = invoiceName;
   }
   
   public String getInvoiceMemo() {
     return this.invoiceMemo;
   }
   
   public void setInvoiceMemo(String invoiceMemo) {
     this.invoiceMemo = invoiceMemo;
   }
   
   public String getInvoiceExpressNo() {
     return this.invoiceExpressNo;
   }
   
   public void setInvoiceExpressNo(String invoiceExpressNo) {
     this.invoiceExpressNo = invoiceExpressNo;
   }
   
   public String getBuyerAlipayNo() {
     return this.buyerAlipayNo;
   }
   
   public void setBuyerAlipayNo(String buyerAlipayNo) {
     this.buyerAlipayNo = buyerAlipayNo;
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
   
   public int getExpressId() {
     return this.expressId;
   }
   
   public void setExpressId(int expressId) {
     this.expressId = expressId;
   }
   
   public Express getExpress() {
     return this.express;
   }
   
   public void setExpress(Express express) {
     this.express = express;
   }
   
   public String getExpressTrackNo() {
     return this.expressTrackNo;
   }
   
   public void setExpressTrackNo(String expressTrackNo) {
     this.expressTrackNo = expressTrackNo;
   }
   
   public int getPrintTag() {
     return this.printTag;
   }
   
   public void setPrintTag(int printTag) {
     this.printTag = printTag;
   }
   
   public String getPrintBatchNo() {
     return this.printBatchNo;
   }
   
   public void setPrintBatchNo(String printBatchNo) {
     this.printBatchNo = printBatchNo;
   }
   
   public Date getExpectedDeliveryDate() {
     return this.expectedDeliveryDate;
   }
   
   public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
     this.expectedDeliveryDate = expectedDeliveryDate;
   }
   
   public Date getDeliveryTime() {
     return this.deliveryTime;
   }
   
   public void setDeliveryTime(Date deliveryTime) {
     this.deliveryTime = deliveryTime;
   }
   
   public Date getEndTime() {
     return this.endTime;
   }
   
   public void setEndTime(Date endTime) {
     this.endTime = endTime;
   }
   
   public double getStdPostFee() {
     return this.stdPostFee;
   }
   
   public void setStdPostFee(double stdPostFee) {
     this.stdPostFee = stdPostFee;
   }
   
   public double getCostPostFee() {
     return this.costPostFee;
   }
   
   public void setCostPostFee(double costPostFee) {
     this.costPostFee = costPostFee;
   }
   
   public double getActPostFee() {
     return this.actPostFee;
   }
   
   public void setActPostFee(double actPostFee) {
     this.actPostFee = actPostFee;
   }
   
   public double getEstimatedWeight() {
     return this.estimatedWeight;
   }
   
   public void setEstimatedWeight(double estimatedWeight) {
     this.estimatedWeight = estimatedWeight;
   }
   
   public double getActualWeight() {
     return this.actualWeight;
   }
   
   public void setActualWeight(double actualWeight) {
     this.actualWeight = actualWeight;
   }
   
   public String getCreator() {
     return this.creator;
   }
   
   public void setCreator(String creator) {
     this.creator = creator;
   }
   
   public Date getCreatedTime() {
     return this.createdTime;
   }
   
   public void setCreatedTime(Date createdTime) {
     this.createdTime = createdTime;
   }
   
   public Date getLastModifiedTime() {
     return this.lastModifiedTime;
   }
   
   public void setLastModifiedTime(Date lastModifiedTime) {
     this.lastModifiedTime = lastModifiedTime;
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
   
   public String getUserDefinedField9() {
     return this.userDefinedField9;
   }
   
   public void setUserDefinedField9(String userDefinedField9) {
     this.userDefinedField9 = userDefinedField9;
   }
   
   public String getUserDefinedField10() {
     return this.userDefinedField10;
   }
   
   public void setUserDefinedField10(String userDefinedField10) {
     this.userDefinedField10 = userDefinedField10;
   }
   
   public String getUserDefinedField11() {
     return this.userDefinedField11;
   }
   
   public void setUserDefinedField11(String userDefinedField11) {
     this.userDefinedField11 = userDefinedField11;
   }
   
   public List<SalesOrderLine> getLines() {
     return this.lines;
   }
   
   public void setLines(List<SalesOrderLine> lines) {
     this.lines = lines;
   }
 
   
   public SalesOrder() {
     this.lines = new ArrayList<>();
   }
 }


