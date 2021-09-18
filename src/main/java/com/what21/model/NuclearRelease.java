 package com.what21.model;
 public class NuclearRelease
 {
   private int id;
   private String totalMailNo;
   private String pc;
   private String createdatatime;
   private String username;
   private int userid;
   
   public int getUserid() {
     return this.userid;
   }
   public void setUserid(int userid) {
     this.userid = userid;
   }
   public int getId() {
     return this.id;
   }
   public String getTotalMailNo() {
     return this.totalMailNo;
   }
   public void setTotalMailNo(String totalMailNo) {
     this.totalMailNo = totalMailNo;
   }
   public String getPc() {
     return this.pc;
   }
   public void setPc(String pc) {
     this.pc = pc;
   }
   public String getCreatedatatime() {
     return this.createdatatime;
   }
   public void setCreatedatatime(String createdatatime) {
     this.createdatatime = createdatatime;
   }
   public String getUsername() {
     return this.username;
   }
   public void setUsername(String username) {
     this.username = username;
   }
   public void setId(int id) {
     this.id = id;
   }
 }


