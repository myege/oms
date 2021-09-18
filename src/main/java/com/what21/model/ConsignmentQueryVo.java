 package com.what21.model;
 
 
 public class ConsignmentQueryVo
 {
   private int startPage;
   private int endPage;
   private int code;
   private String erroMsg;
   private JjDelivery delivery;
   
   public String getErroMsg() {
     return this.erroMsg;
   }
   public void setErroMsg(String erroMsg) {
     this.erroMsg = erroMsg;
   }
   public int getCode() {
     return this.code;
   }
   public void setCode(int code) {
     this.code = code;
   }
   public JjDelivery getDelivery() {
     return this.delivery;
   }
   public void setDelivery(JjDelivery delivery) {
     this.delivery = delivery;
   }
   public int getStartPage() {
     return this.startPage;
   }
   public void setStartPage(int startPage) {
     this.startPage = startPage;
   }
   public int getEndPage() {
     return this.endPage;
   }
   public void setEndPage(int endPage) {
     this.endPage = endPage;
   }
 }


