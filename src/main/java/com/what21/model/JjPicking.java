 package com.what21.model;
 
 import java.util.Date;
 
 
 
 public class JjPicking
 {
   private Long id;
   private String pick;
   private Integer picksum;
   private String pickname = "";
   private Date picktime;
   
   public String getLx() {
     return this.lx;
   }
   private String lx; private Integer shop;
   public void setLx(String lx) {
     this.lx = lx;
   }
 
   
   public Integer getShop() {
     return this.shop;
   }
   
   public void setShop(Integer shop) {
     this.shop = shop;
   }
 
 
   
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
   
   public Integer getPicksum() {
     return this.picksum;
   }
   
   public void setPicksum(Integer picksum) {
     this.picksum = picksum;
   }
   
   public String getPickname() {
     return this.pickname;
   }
   
   public void setPickname(String pickname) {
     this.pickname = (pickname == null) ? null : pickname.trim();
   }
   
   public Date getPicktime() {
     return this.picktime;
   }
   
   public void setPicktime(Date picktime) {
     this.picktime = picktime;
   }
 }


