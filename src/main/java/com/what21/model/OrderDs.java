 package com.what21.model;public class OrderDs {
   private int id;
   private String txlogisticid;
   private String mailno;
   private String lx;
   
   public String getTxlogisticid() {
     return this.txlogisticid;
   }
   private String payinfo; private String payback; private String createtime; private String vid;
   public void setTxlogisticid(String txlogisticid) {
     this.txlogisticid = txlogisticid;
   }
   
   public String getMailno() {
     return this.mailno;
   }
   
   public void setMailno(String mailno) {
     this.mailno = mailno;
   }
   
   public String getLx() {
     return this.lx;
   }
   
   public void setLx(String lx) {
     this.lx = lx;
   }
   
   public String getPayinfo() {
     return this.payinfo;
   }
   
   public void setPayinfo(String payinfo) {
     this.payinfo = payinfo;
   }
   
   public String getPayback() {
     return this.payback;
   }
   
   public void setPayback(String payback) {
     this.payback = payback;
   }
   
   public String getCreatetime() {
     return this.createtime;
   }
   
   public void setCreatetime(String createtime) {
     this.createtime = createtime;
   }
   
   public String getVid() {
     return this.vid;
   }
   
   public void setVid(String vid) {
     this.vid = vid;
   }
 }


