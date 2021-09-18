 package com.what21.model;
 
 import java.util.List;
 public class OrderMailPush
 {
   private String address;
   private String buyerIdNumber;
   private String buyerName;
   private String company;
   private String consignee;
   private String invoiceNo;
   private String kdId;
   private Double ordeAmount;
   private String orderSn;
   private String purchaserTelNumber;
   private String signBuilding;
   private String tel;
   private String zsType = "直邮";
 
   
   private String voyageNo;
 
   
   private String billNo;
 
   
   private String trafNo = "5";
 
   
   private String iePort = "2992";
 
   
   private String customsField = "2992";
 
   
   private String declPort = "2992";
 
   
   private String destinationPort;
   
   private String createTime;
   
   private String toCompany = "杭州网趣科技有限公司";
   public String getToCompany() {
     return this.toCompany;
   }
   private List<OrderMailPushSku> goodsList;
   public void setToCompany(String toCompany) {
     this.toCompany = toCompany;
   }
   
   public String getCreateTime() {
     return this.createTime;
   }
   
   public void setCreateTime(String createTime) {
     this.createTime = createTime;
   }
 
 
 
   
   public String getAddress() {
     return this.address;
   }
   
   public void setAddress(String address) {
     this.address = address;
   }
   
   public String getBuyerIdNumber() {
     return this.buyerIdNumber;
   }
   
   public void setBuyerIdNumber(String buyerIdNumber) {
     this.buyerIdNumber = buyerIdNumber;
   }
   
   public String getBuyerName() {
     return this.buyerName;
   }
   
   public void setBuyerName(String buyerName) {
     this.buyerName = buyerName;
   }
   
   public String getCompany() {
     return this.company;
   }
   
   public void setCompany(String company) {
     this.company = company;
   }
   
   public String getConsignee() {
     return this.consignee;
   }
   
   public void setConsignee(String consignee) {
     this.consignee = consignee;
   }
   
   public String getInvoiceNo() {
     return this.invoiceNo;
   }
   
   public void setInvoiceNo(String invoiceNo) {
     this.invoiceNo = invoiceNo;
   }
   
   public String getKdId() {
     return this.kdId;
   }
   
   public void setKdId(String kdId) {
     this.kdId = kdId;
   }
   
   public Double getOrdeAmount() {
     return this.ordeAmount;
   }
   
   public void setOrdeAmount(Double ordeAmount) {
     this.ordeAmount = ordeAmount;
   }
   
   public String getOrderSn() {
     return this.orderSn;
   }
   
   public void setOrderSn(String orderSn) {
     this.orderSn = orderSn;
   }
   
   public String getPurchaserTelNumber() {
     return this.purchaserTelNumber;
   }
   
   public void setPurchaserTelNumber(String purchaserTelNumber) {
     this.purchaserTelNumber = purchaserTelNumber;
   }
   
   public String getSignBuilding() {
     return this.signBuilding;
   }
   
   public void setSignBuilding(String signBuilding) {
     this.signBuilding = signBuilding;
   }
   
   public String getTel() {
     return this.tel;
   }
   
   public void setTel(String tel) {
     this.tel = tel;
   }
   
   public String getZsType() {
     return this.zsType;
   }
   
   public void setZsType(String zsType) {
     this.zsType = zsType;
   }
   
   public List<OrderMailPushSku> getGoodsList() {
     return this.goodsList;
   }
   
   public void setGoodsList(List<OrderMailPushSku> goodsList) {
     this.goodsList = goodsList;
   }
   
   public String getVoyageNo() {
     return this.voyageNo;
   }
   
   public void setVoyageNo(String voyageNo) {
     this.voyageNo = voyageNo;
   }
   
   public String getBillNo() {
     return this.billNo;
   }
   
   public void setBillNo(String billNo) {
     this.billNo = billNo;
   }
   
   public String getTrafNo() {
     return this.trafNo;
   }
   
   public void setTrafNo(String trafNo) {
     this.trafNo = trafNo;
   }
   
   public String getIePort() {
     return this.iePort;
   }
   
   public void setIePort(String iePort) {
     this.iePort = iePort;
   }
   
   public String getCustomsField() {
     return this.customsField;
   }
   
   public void setCustomsField(String customsField) {
     this.customsField = customsField;
   }
   
   public String getDeclPort() {
     return this.declPort;
   }
   
   public void setDeclPort(String declPort) {
     this.declPort = declPort;
   }
   
   public String getDestinationPort() {
     return this.destinationPort;
   }
   
   public void setDestinationPort(String destinationPort) {
     this.destinationPort = destinationPort;
   }
 }


