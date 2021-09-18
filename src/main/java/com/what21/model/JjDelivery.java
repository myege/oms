 package com.what21.model;
 
 import java.util.Date;
 public class JjDelivery
 {
   private Long id;
   private String bill;
   private Date fhtime;
   private String pickname;
   private String pick;
   
   public Long getId() {
     return this.id;
   }
   
   public void setId(Long id) {
     this.id = id;
   }
   
   public String getBill() {
     return this.bill;
   }
   
   public void setBill(String bill) {
     this.bill = (bill == null) ? null : bill.trim();
   }
   
   public Date getFhtime() {
     return this.fhtime;
   }
   
   public void setFhtime(Date fhtime) {
     this.fhtime = fhtime;
   }
   
   public String getPickname() {
     return this.pickname;
   }
   
   public void setPickname(String pickname) {
     this.pickname = (pickname == null) ? null : pickname.trim();
   }
   
   public String getPick() {
     return this.pick;
   }
   
   public void setPick(String pick) {
     this.pick = (pick == null) ? null : pick.trim();
   }
 }


