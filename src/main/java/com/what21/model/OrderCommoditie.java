 package com.what21.model;
 public class OrderCommoditie
 {
   private int id;
   private String wmsId;
   private String expressNum;
   private String commoditieCode;
   private String commoditieName;
   private int commoditieNum;
   private String createTime;
   
   public int getId() {
     return this.id;
   }
   
   public void setId(int id) {
     this.id = id;
   }
   
   public String getCreateTime() {
     return this.createTime;
   }
   
   public void setCreateTime(String createTime) {
     this.createTime = createTime;
   }
   
   public String getWmsId() {
     return this.wmsId;
   }
   
   public void setWmsId(String wmsId) {
     this.wmsId = wmsId;
   }
   
   public String getExpressNum() {
     return this.expressNum;
   }
   
   public void setExpressNum(String expressNum) {
     this.expressNum = expressNum;
   }
   
   public String getCommoditieCode() {
     return this.commoditieCode;
   }
   
   public void setCommoditieCode(String commoditieCode) {
     this.commoditieCode = commoditieCode;
   }
   
   public String getCommoditieName() {
     return this.commoditieName;
   }
   
   public void setCommoditieName(String commoditieName) {
     this.commoditieName = commoditieName;
   }
   
   public int getCommoditieNum() {
     return this.commoditieNum;
   }
   
   public void setCommoditieNum(int commoditieNum) {
     this.commoditieNum = commoditieNum;
   }
 }


