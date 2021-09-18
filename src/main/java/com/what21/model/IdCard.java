 package com.what21.model;
 
 public class IdCard {
   private String id;
   private String userName;
   private String userId;
   private int verification;
   private String dateTime;
   
   public String getId() {
     return this.id;
   }
   public void setId(String id) {
     this.id = id;
   }
   public String getUserName() {
     return this.userName;
   }
   public void setUserName(String userName) {
     this.userName = userName;
   }
   public String getUserId() {
     return this.userId;
   }
   public void setUserId(String userId) {
     this.userId = userId;
   }
   public int getVerification() {
     return this.verification;
   }
   public void setVerification(int verification) {
     this.verification = verification;
   }
   public String getDateTime() {
     return this.dateTime;
   }
   public void setDateTime(String dateTime) {
     this.dateTime = dateTime;
   }
 }


