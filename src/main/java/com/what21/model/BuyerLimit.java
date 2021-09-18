 package com.what21.model;
 
 import java.util.Date;
 public class BuyerLimit
 {
   private Integer id;
   private String name;
   private String nameid;
   private Double totalused;
   private Date createtime;
   
   public Integer getId() {
     return this.id;
   }
   
   public void setId(Integer id) {
     this.id = id;
   }
   
   public String getName() {
     return this.name;
   }
   
   public void setName(String name) {
     this.name = (name == null) ? null : name.trim();
   }
   
   public String getNameid() {
     return this.nameid;
   }
   
   public void setNameid(String nameid) {
     this.nameid = (nameid == null) ? null : nameid.trim();
   }
   
   public Double getTotalused() {
     return this.totalused;
   }
   
   public void setTotalused(Double totalused) {
     this.totalused = totalused;
   }
   
   public Date getCreatetime() {
     return this.createtime;
   }
   
   public void setCreatetime(Date createtime) {
     this.createtime = createtime;
   }
 }


