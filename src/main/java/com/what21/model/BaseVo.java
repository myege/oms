 package com.what21.model;
 public class BaseVo
 {
   private Integer page;
   private Integer rows;
   private String startTime;
   private String endTime;
   
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
   
   public Integer getPage() {
     return this.page;
   }
   
   public void setPage(Integer page) {
     this.page = page;
   }
   
   public Integer getRows() {
     return this.rows;
   }
   
   public void setRows(Integer rows) {
     this.rows = rows;
   }
 }


