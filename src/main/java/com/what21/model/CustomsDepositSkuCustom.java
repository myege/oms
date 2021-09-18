 package com.what21.model;
 
 public class CustomsDepositSkuCustom extends TCustomsDepositSku {
   private String pid;
   
   public String getPid() {
     return this.pid;
   } private String startTime; private String endTime;
   public void setPid(String pid) {
     this.pid = pid;
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
 }


