 package com.what21.model;
 
 public class JjPickingCustom extends JjPicking {
   private String pick;
   private String pickname;
   
   public String getPick() {
     return this.pick;
   } private int shop; private int picksum; private String lx;
   public void setPick(String pick) {
     this.pick = pick;
   }
   public String getPickname() {
     return this.pickname;
   }
   public void setPickname(String pickname) {
     this.pickname = pickname;
   }
   public Integer getShop() {
     return Integer.valueOf(this.shop);
   }
   public void setShop(int shop) {
     this.shop = shop;
   }
   public Integer getPicksum() {
     return Integer.valueOf(this.picksum);
   }
   public void setPicksum(int picksum) {
     this.picksum = picksum;
   }
 
 
 
   
   public String getLx() {
     return this.lx;
   }
   public void setLx(String lx) {
     this.lx = lx;
   }
 }


