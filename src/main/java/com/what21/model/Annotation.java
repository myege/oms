 package com.what21.model;
 
 public class Annotation
 {
   private int id;
   private String jobFormId;
   
   public int getId() {
     return this.id;
   }
   private String datatime; private String userId; private String mailno;
   public void setId(int id) {
     this.id = id;
   }
   
   public String getJobFormId() {
     return this.jobFormId;
   }
   
   public void setJobFormId(String jobFormId) {
     this.jobFormId = jobFormId;
   }
   
   public String getDatatime() {
     return this.datatime;
   }
   
   public void setDatatime(String datatime) {
     this.datatime = datatime;
   }
   
   public String getUserId() {
     return this.userId;
   }
   
   public void setUserId(String userId) {
     this.userId = userId;
   }
   
   public String getMailno() {
     return this.mailno;
   }
   
   public void setMailno(String mailno) {
     this.mailno = mailno;
   }
 }


