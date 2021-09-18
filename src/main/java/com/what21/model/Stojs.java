 package com.what21.model;
 public class Stojs
 {
   private Integer id;
   private String company;
   private String number;
   private String qdzt;
   private String bbzt;
   
   public Integer getId() {
     return this.id;
   }
   
   public void setId(Integer id) {
     this.id = id;
   }
   
   public String getCompany() {
     return this.company;
   }
   
   public void setCompany(String company) {
     this.company = (company == null) ? null : company.trim();
   }
   
   public String getNumber() {
     return this.number;
   }
   
   public void setNumber(String number) {
     this.number = (number == null) ? null : number.trim();
   }
   
   public String getQdzt() {
     return this.qdzt;
   }
   
   public void setQdzt(String qdzt) {
     this.qdzt = (qdzt == null) ? null : qdzt.trim();
   }
   
   public String getBbzt() {
     return this.bbzt;
   }
   
   public void setBbzt(String bbzt) {
     this.bbzt = (bbzt == null) ? null : bbzt.trim();
   }
 }


