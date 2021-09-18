 package com.what21.model;
 public class Outshiptosku
 {
   private String billno;
   private String logisticsno;
   private Integer totalpackageno;
   private String note;
   private Integer tabid;
   
   public String getBillno() {
     return this.billno;
   }
   
   public void setBillno(String billno) {
     this.billno = (billno == null) ? null : billno.trim();
   }
   
   public String getLogisticsno() {
     return this.logisticsno;
   }
   
   public void setLogisticsno(String logisticsno) {
     this.logisticsno = (logisticsno == null) ? null : logisticsno.trim();
   }
   
   public Integer getTotalpackageno() {
     return this.totalpackageno;
   }
   
   public void setTotalpackageno(Integer totalpackageno) {
     this.totalpackageno = totalpackageno;
   }
   
   public String getNote() {
     return this.note;
   }
   
   public void setNote(String note) {
     this.note = (note == null) ? null : note.trim();
   }
   
   public Integer getTabid() {
     return this.tabid;
   }
   
   public void setTabid(Integer tabid) {
     this.tabid = tabid;
   }
 }


