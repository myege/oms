 package com.what21.model;
 
 import java.util.Date;
 public class TCustomsDeposit
 {
   private Integer id;
   private String companyname;
   private String companycode;
   private String totalmoney;
   private String surplusmoney;
   private String usedmoney;
   private Date createtime;
   
   public Integer getId() {
     return this.id;
   }
   
   public void setId(Integer id) {
     this.id = id;
   }
   
   public String getCompanyname() {
     return this.companyname;
   }
   
   public void setCompanyname(String companyname) {
     this.companyname = (companyname == null) ? null : companyname.trim();
   }
   
   public String getCompanycode() {
     return this.companycode;
   }
   
   public void setCompanycode(String companycode) {
     this.companycode = (companycode == null) ? null : companycode.trim();
   }
   
   public String getTotalmoney() {
     return this.totalmoney;
   }
   
   public void setTotalmoney(String totalmoney) {
     this.totalmoney = (totalmoney == null) ? null : totalmoney.trim();
   }
   
   public String getSurplusmoney() {
     return this.surplusmoney;
   }
   
   public void setSurplusmoney(String surplusmoney) {
     this.surplusmoney = (surplusmoney == null) ? null : surplusmoney.trim();
   }
   
   public String getUsedmoney() {
     return this.usedmoney;
   }
   
   public void setUsedmoney(String usedmoney) {
     this.usedmoney = (usedmoney == null) ? null : usedmoney.trim();
   }
   
   public Date getCreatetime() {
     return this.createtime;
   }
   
   public void setCreatetime(Date createtime) {
     this.createtime = createtime;
   }
 }


