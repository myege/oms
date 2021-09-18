 package com.what21.model;
 
 import java.util.Date;
 public class TCarrierMailno
 {
   private Integer id;
   private String carrier;
   private String mailno;
   private Date createtime;
   private Integer num;
   
   public Integer getId() {
     return this.id;
   }
   
   public void setId(Integer id) {
     this.id = id;
   }
   
   public String getCarrier() {
     return this.carrier;
   }
   
   public void setCarrier(String carrier) {
     this.carrier = (carrier == null) ? null : carrier.trim();
   }
   
   public String getMailno() {
     return this.mailno;
   }
   
   public void setMailno(String mailno) {
     this.mailno = (mailno == null) ? null : mailno.trim();
   }
   
   public Date getCreatetime() {
     return this.createtime;
   }
   
   public void setCreatetime(Date createtime) {
     this.createtime = createtime;
   }
   
   public Integer getNum() {
     return this.num;
   }
   
   public void setNum(Integer num) {
     this.num = num;
   }
 }


