 package com.what21.model;
 
 import java.util.Date;
 
 
 public class OrderBondedRule
 {
   private Integer id;
   private String province;
   
   public Integer getId() {
     return this.id;
   } private Double weight; private String carrier; private Date createtime;
   public void setId(Integer id) {
     this.id = id;
   }
   public String getProvince() {
     return this.province;
   }
   public void setProvince(String province) {
     this.province = province;
   }
   public String getCarrier() {
     return this.carrier;
   }
   public void setCarrier(String carrier) {
     this.carrier = carrier;
   }
   public Date getCreatetime() {
     return this.createtime;
   }
   public void setCreatetime(Date createtime) {
     this.createtime = createtime;
   }
   public Double getWeight() {
     return this.weight;
   }
   public void setWeight(Double weight) {
     this.weight = weight;
   }
 }


