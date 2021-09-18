 package com.what21.model;public class YtoBillEx { private int id; private String isSigned; private String waybillNo; private String orderNo; private String shipperName; private String shipperTel; private String shipperAddress;
   private String consigneeName;
   private String consigneeTel;
   
   public int getId() {
     return this.id;
   } private String consigneeAddress; private String deliveryTime; private String eventCode; private String eventDetail; private String eventLocation; private String eventOperater; private String eventOperaterPhone; private String city; private String nextCity; private String eventTime;
   public void setId(int id) {
     this.id = id;
   }
 
 
   
   public String getIsSigned() {
     return this.isSigned;
   }
   public void setIsSigned(String isSigned) {
     this.isSigned = isSigned;
   }
   
   public String getWaybillNo() {
     return this.waybillNo;
   }
   public void setWaybillNo(String waybillNo) {
     this.waybillNo = waybillNo;
   }
   public String getOrderNo() {
     return this.orderNo;
   }
   public void setOrderNo(String orderNo) {
     this.orderNo = orderNo;
   }
   public String getShipperName() {
     return this.shipperName;
   }
   public void setShipperName(String shipperName) {
     this.shipperName = shipperName;
   }
   public String getShipperTel() {
     return this.shipperTel;
   }
   public void setShipperTel(String shipperTel) {
     this.shipperTel = shipperTel;
   }
   public String getShipperAddress() {
     return this.shipperAddress;
   }
   public void setShipperAddress(String shipperAddress) {
     this.shipperAddress = shipperAddress;
   }
   public String getConsigneeName() {
     return this.consigneeName;
   }
   public void setConsigneeName(String consigneeName) {
     this.consigneeName = consigneeName;
   }
   public String getConsigneeTel() {
     return this.consigneeTel;
   }
   public void setConsigneeTel(String consigneeTel) {
     this.consigneeTel = consigneeTel;
   }
   public String getConsigneeAddress() {
     return this.consigneeAddress;
   }
   public void setConsigneeAddress(String consigneeAddress) {
     this.consigneeAddress = consigneeAddress;
   }
   public String getDeliveryTime() {
     return this.deliveryTime;
   }
   public void setDeliveryTime(String deliveryTime) {
     this.deliveryTime = deliveryTime;
   }
   public String getEventCode() {
     return this.eventCode;
   }
   public void setEventCode(String eventCode) {
     this.eventCode = eventCode;
   }
   public String getEventDetail() {
     return this.eventDetail;
   }
   public void setEventDetail(String eventDetail) {
     this.eventDetail = eventDetail;
   }
   public String getEventLocation() {
     return this.eventLocation;
   }
   public void setEventLocation(String eventLocation) {
     this.eventLocation = eventLocation;
   }
   public String getEventOperater() {
     return this.eventOperater;
   }
   public void setEventOperater(String eventOperater) {
     this.eventOperater = eventOperater;
   }
   public String getEventOperaterPhone() {
     return this.eventOperaterPhone;
   }
   public void setEventOperaterPhone(String eventOperaterPhone) {
     this.eventOperaterPhone = eventOperaterPhone;
   }
   public String getCity() {
     return this.city;
   }
   public void setCity(String city) {
     this.city = city;
   }
   public String getNextCity() {
     return this.nextCity;
   }
   public void setNextCity(String nextCity) {
     this.nextCity = nextCity;
   }
   public String getEventTime() {
     return this.eventTime;
   }
   public void setEventTime(String eventTime) {
     this.eventTime = eventTime;
   }
 
 
 
   
   public YtoBillEx(String waybillNo, String orderNo, String shipperName, String shipperTel, String shipperAddress, String consigneeName, String consigneeTel, String consigneeAddress, String deliveryTime, String eventCode, String eventDetail, String eventLocation, String eventOperater, String eventOperaterPhone, String city, String nextCity, String eventTime) {
     this.waybillNo = waybillNo;
     this.orderNo = orderNo;
     this.shipperName = shipperName;
     this.shipperTel = shipperTel;
     this.shipperAddress = shipperAddress;
     this.consigneeName = consigneeName;
     this.consigneeTel = consigneeTel;
     this.consigneeAddress = consigneeAddress;
     this.deliveryTime = deliveryTime;
     this.eventCode = eventCode;
     this.eventDetail = eventDetail;
     this.eventLocation = eventLocation;
     this.eventOperater = eventOperater;
     this.eventOperaterPhone = eventOperaterPhone;
     this.city = city;
     this.nextCity = nextCity;
     this.eventTime = eventTime;
   }
   
   public YtoBillEx() {} }


