 package com.what21.model;
 public class ReceiveHghzData
 {
   private int id;
   private String contentData;
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
   
   public String getContentData() {
     return this.contentData;
   }
   
   public void setContentData(String contentData) {
     this.contentData = contentData;
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
 }


