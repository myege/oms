 package com.what21.model;
 public class ExportChangeOdd
   extends ChangeOdd
 {
   private String pnname;
   private double nweight;
   private int newnum;
   private int changeoId;
   
   public String getPnname() {
     return this.pnname;
   }
   
   public void setPnname(String pnname) {
     this.pnname = pnname;
   }
   
   public double getNweight() {
     return this.nweight;
   }
   
   public void setNweight(double nweight) {
     this.nweight = nweight;
   }
   
   public int getNewnum() {
     return this.newnum;
   }
   
   public void setNewnum(int newnum) {
     this.newnum = newnum;
   }
   
   public int getChangeoId() {
     return this.changeoId;
   }
   
   public void setChangeoId(int changeoId) {
     this.changeoId = changeoId;
   }
 }


