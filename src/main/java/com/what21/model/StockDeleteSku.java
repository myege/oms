 package com.what21.model;
 
 
 public class StockDeleteSku
 {
   private int id;
   private String stockDeleteId;
   private String inOutSeq;
   
   public int getId() {
     return this.id;
   }
   private String datatime; private String flag; private int userId;
   public void setId(int id) {
     this.id = id;
   }
   
   public String getStockDeleteId() {
     return this.stockDeleteId;
   }
   
   public void setStockDeleteId(String stockDeleteId) {
     this.stockDeleteId = stockDeleteId;
   }
   
   public String getInOutSeq() {
     return this.inOutSeq;
   }
   
   public void setInOutSeq(String inOutSeq) {
     this.inOutSeq = inOutSeq;
   }
   
   public String getDatatime() {
     return this.datatime;
   }
   
   public void setDatatime(String datatime) {
     this.datatime = datatime;
   }
   
   public String getFlag() {
     return this.flag;
   }
   
   public void setFlag(String flag) {
     this.flag = flag;
   }
   
   public int getUserId() {
     return this.userId;
   }
   
   public void setUserId(int userId) {
     this.userId = userId;
   }
 }


