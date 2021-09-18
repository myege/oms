 package com.what21.model;
 
 import java.util.Date;
 public class TCustomsDepositSku
 {
   private Integer id;
   private String orderno;
   private String mailno;
   private String totalmoney;
   private String tax;
   private Date createtime;
   private String companycode;
   
   public Integer getId() {
     return this.id;
   }
   
   public void setId(Integer id) {
     this.id = id;
   }
   
   public String getOrderno() {
     return this.orderno;
   }
   
   public void setOrderno(String orderno) {
     this.orderno = (orderno == null) ? null : orderno.trim();
   }
   
   public String getMailno() {
     return this.mailno;
   }
   
   public void setMailno(String mailno) {
     this.mailno = (mailno == null) ? null : mailno.trim();
   }
   
   public String getTotalmoney() {
     return this.totalmoney;
   }
   
   public void setTotalmoney(String totalmoney) {
     this.totalmoney = (totalmoney == null) ? null : totalmoney.trim();
   }
   
   public String getTax() {
     return this.tax;
   }
   
   public void setTax(String tax) {
     this.tax = (tax == null) ? null : tax.trim();
   }
   
   public Date getCreatetime() {
     return this.createtime;
   }
   
   public void setCreatetime(Date createtime) {
     this.createtime = createtime;
   }
   
   public String getCompanycode() {
     return this.companycode;
   }
   
   public void setCompanycode(String companycode) {
     this.companycode = (companycode == null) ? null : companycode.trim();
   }
 }


