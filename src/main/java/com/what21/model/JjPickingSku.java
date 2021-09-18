 package com.what21.model;
 
 
 
 public class JjPickingSku
 {
   private Long id;
   private String pick;
   private String pickname;
   private String bill;
   
   public Long getId() {
     return this.id;
   }
   
   public void setId(Long id) {
     this.id = id;
   }
   
   public String getPick() {
     return this.pick;
   }
   
   public void setPick(String pick) {
     this.pick = (pick == null) ? null : pick.trim();
   }
   
   public String getPickname() {
     return this.pickname;
   }
   
   public void setPickname(String pickname) {
     this.pickname = (pickname == null) ? null : pickname.trim();
   }
   
   public String getBill() {
     return this.bill;
   }
   
   public void setBill(String bill) {
     this.bill = (bill == null) ? null : bill.trim();
   }
 }


