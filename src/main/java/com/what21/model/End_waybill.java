 package com.what21.model;
 
 import java.math.BigDecimal;
 
 
 
 public class End_waybill
 {
   private int id;
   private String company;
   private BigDecimal sum;
   private String createdatetime;
   
   public String getStartDate() {
     return this.startDate;
   } private String qj; private String allhead; private String allend; private String startDate;
   public void setStartDate(String startDate) {
     this.startDate = startDate;
   }
   public int getId() {
     return this.id;
   }
   public void setId(int id) {
     this.id = id;
   }
   public String getCompany() {
     return this.company;
   }
   public void setCompany(String company) {
     this.company = company;
   }
   public BigDecimal getSum() {
     return this.sum;
   }
   public void setSum(BigDecimal sum) {
     this.sum = sum;
   }
   
   public String getCreatedatetime() {
     return this.createdatetime;
   }
   public void setCreatedatetime(String createdatetime) {
     this.createdatetime = createdatetime;
   }
   public String getQj() {
     return this.qj;
   }
   public void setQj(String qj) {
     this.qj = qj;
   }
   public String getAllhead() {
     return this.allhead;
   }
   public void setAllhead(String allhead) {
     this.allhead = allhead;
   }
   public String getAllend() {
     return this.allend;
   }
   public void setAllend(String allend) {
     this.allend = allend;
   }
 }


