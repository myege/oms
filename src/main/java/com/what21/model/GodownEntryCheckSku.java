 package com.what21.model;
 public class GodownEntryCheckSku
 {
   private int id;
   private String billcode;
   private String status;
   private int mailStatus;
   private String totalMailNo;
   private String pc;
   private String createdatatime;
   private String inflag;
   private String outflag;
   
   public int getMailStatus() {
     return this.mailStatus;
   }
   public void setMailStatus(int mailStatus) {
     this.mailStatus = mailStatus;
   }
   public String getInflag() {
     return this.inflag;
   }
   public void setInflag(String inflag) {
     this.inflag = inflag;
   }
   public String getOutflag() {
     return this.outflag;
   }
   public void setOutflag(String outflag) {
     this.outflag = outflag;
   }
   public int getId() {
     return this.id;
   }
   public void setId(int id) {
     this.id = id;
   }
   public String getBillcode() {
     return this.billcode;
   }
   public void setBillcode(String billcode) {
     this.billcode = billcode;
   }
   public String getStatus() {
     return this.status;
   }
   public void setStatus(String status) {
     this.status = status;
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
 }


