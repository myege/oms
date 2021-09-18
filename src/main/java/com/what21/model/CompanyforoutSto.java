 package com.what21.model;
 public class CompanyforoutSto
 {
   private Integer id;
   private String itemcode;
   private String itemname;
   private String declport;
   private String companycode;
   private String companyname;
   private String businessno;
   private String sendername;
   private String senderphone;
   private String senderaddress;
   private String senderpostcode;
   
   public Integer getId() {
     return this.id;
   }
   
   public void setId(Integer id) {
     this.id = id;
   }
   
   public String getItemcode() {
     return this.itemcode;
   }
   
   public void setItemcode(String itemcode) {
     this.itemcode = (itemcode == null) ? null : itemcode.trim();
   }
   
   public String getItemname() {
     return this.itemname;
   }
   
   public void setItemname(String itemname) {
     this.itemname = (itemname == null) ? null : itemname.trim();
   }
   
   public String getDeclport() {
     return this.declport;
   }
   
   public void setDeclport(String declport) {
     this.declport = (declport == null) ? null : declport.trim();
   }
   
   public String getCompanycode() {
     return this.companycode;
   }
   
   public void setCompanycode(String companycode) {
     this.companycode = (companycode == null) ? null : companycode.trim();
   }
   
   public String getCompanyname() {
     return this.companyname;
   }
   
   public void setCompanyname(String companyname) {
     this.companyname = (companyname == null) ? null : companyname.trim();
   }
   
   public String getBusinessno() {
     return this.businessno;
   }
   
   public void setBusinessno(String businessno) {
     this.businessno = (businessno == null) ? null : businessno.trim();
   }
   
   public String getSendername() {
     return this.sendername;
   }
   
   public void setSendername(String sendername) {
     this.sendername = (sendername == null) ? null : sendername.trim();
   }
   
   public String getSenderphone() {
     return this.senderphone;
   }
   
   public void setSenderphone(String senderphone) {
     this.senderphone = (senderphone == null) ? null : senderphone.trim();
   }
   
   public String getSenderaddress() {
     return this.senderaddress;
   }
   
   public void setSenderaddress(String senderaddress) {
     this.senderaddress = (senderaddress == null) ? null : senderaddress.trim();
   }
   
   public String getSenderpostcode() {
     return this.senderpostcode;
   }
   
   public void setSenderpostcode(String senderpostcode) {
     this.senderpostcode = (senderpostcode == null) ? null : senderpostcode.trim();
   }
 }


