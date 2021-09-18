 package com.what21.model;
 public class ReceiveHtData
 {
   private int id;
   private String statusName;
   private String detailInfo;
   private String expressNo;
   private String isSuccess;
   private int isHandle;
   private String createTime;
   private String startTime;
   private String endTime;
   
   public int getId() {
     return this.id;
   }
   
   public void setId(int id) {
     this.id = id;
   }
   
   public int getIsHandle() {
     return this.isHandle;
   }
   
   public void setIsHandle(int isHandle) {
     this.isHandle = isHandle;
   }
   
   public String getStartTime() {
     return this.startTime;
   }
   
   public void setStartTime(String startTime) {
     this.startTime = startTime;
   }
   
   public String getEndTime() {
     return this.endTime;
   }
   
   public void setEndTime(String endTime) {
     this.endTime = endTime;
   }
   
   public String getCreateTime() {
     return this.createTime;
   }
   
   public void setCreateTime(String createTime) {
     this.createTime = createTime;
   }
   
   public String getStatusName() {
     return this.statusName;
   }
   
   public void setStatusName(String statusName) {
     this.statusName = statusName;
   }
   
   public String getDetailInfo() {
     return this.detailInfo;
   }
   
   public void setDetailInfo(String detailInfo) {
     this.detailInfo = detailInfo;
   }
   
   public String getExpressNo() {
     return this.expressNo;
   }
   
   public void setExpressNo(String expressNo) {
     this.expressNo = expressNo;
   }
   
   public String getIsSuccess() {
     return this.isSuccess;
   }
   
   public void setIsSuccess(String isSuccess) {
     this.isSuccess = isSuccess;
   }
 }


