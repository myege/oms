 package com.what21.model;
 
 import java.util.List;
 public class OrderPhPush
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
   private String zsType = "保税";
   
   private List<OrderBondedPushSku> goodsList;
   
   private String createTime;
   
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
   
   public List<OrderBondedPushSku> getGoodsList() {
     return this.goodsList;
   }
   
   public void setGoodsList(List<OrderBondedPushSku> goodsList) {
     this.goodsList = goodsList;
   }
   
   public String getCreateTime() {
     return this.createTime;
   }
   
   public void setCreateTime(String createTime) {
     this.createTime = createTime;
   }
 }


