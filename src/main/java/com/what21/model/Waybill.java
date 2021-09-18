 package com.what21.model;
 public class Waybill
 {
   private int id;
   private String expressCode;
   private String expressNumber;
   private String acceptTime;
   private String acceptState;
   private String createTime;
   private int sc;
   private String endTime;
   private String endState;
   private String signTime;
   private String signState;
   private int userId;
   private int isSign;
   private String business;
   private String sx;
   
   public int getSc() {
     return this.sc;
   }
   
   public void setSc(int sc) {
     this.sc = sc;
   }
   
   public String getCreateTime() {
     return this.createTime;
   }
   
   public void setCreateTime(String createTime) {
     this.createTime = createTime;
   }
 
 
   
   public int getUserId() {
     return this.userId;
   }
   
   public void setUserId(int userId) {
     this.userId = userId;
   }
   
   public int getId() {
     return this.id;
   }
   
   public void setId(int id) {
     this.id = id;
   }
   
   public String getExpressCode() {
     return this.expressCode;
   }
   
   public void setExpressCode(String expressCode) {
     this.expressCode = expressCode;
   }
   
   public String getExpressNumber() {
     return this.expressNumber;
   }
   
   public void setExpressNumber(String expressNumber) {
     this.expressNumber = expressNumber;
   }
   
   public String getAcceptTime() {
     return this.acceptTime;
   }
   
   public void setAcceptTime(String acceptTime) {
     this.acceptTime = acceptTime;
   }
   
   public String getAcceptState() {
     return this.acceptState;
   }
   
   public void setAcceptState(String acceptState) {
     this.acceptState = acceptState;
   }
   
   public String getEndTime() {
     return this.endTime;
   }
   
   public void setEndTime(String endTime) {
     this.endTime = endTime;
   }
   
   public String getEndState() {
     return this.endState;
   }
   
   public void setEndState(String endState) {
     this.endState = endState;
   }
   
   public String getSignTime() {
     return this.signTime;
   }
   
   public void setSignTime(String signTime) {
     this.signTime = signTime;
   }
   
   public String getSignState() {
     return this.signState;
   }
   
   public void setSignState(String signState) {
     this.signState = signState;
   }
   
   public int getIsSign() {
     return this.isSign;
   }
   
   public void setIsSign(int isSign) {
     this.isSign = isSign;
   }
   
   public String getBusiness() {
     return this.business;
   }
   
   public void setBusiness(String business) {
     this.business = business;
   }
   
   public String getSx() {
     return this.sx;
   }
   
   public void setSx(String sx) {
     this.sx = sx;
   }
 }


