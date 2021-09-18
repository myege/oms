 package com.what21.model;
 public class StojsQd
 {
   private Integer id;
   private Integer pid;
   private String txlogisticid;
   private String mailno;
   private String weight;
   private String worth;
   private String province;
   private String city;
   private String address;
   
   public Integer getId() {
     return this.id;
   }
   
   public void setId(Integer id) {
     this.id = id;
   }
   
   public Integer getPid() {
     return this.pid;
   }
   
   public void setPid(Integer pid) {
     this.pid = pid;
   }
   
   public String getTxlogisticid() {
     return this.txlogisticid;
   }
   
   public void setTxlogisticid(String txlogisticid) {
     this.txlogisticid = (txlogisticid == null) ? null : txlogisticid.trim();
   }
   
   public String getMailno() {
     return this.mailno;
   }
   
   public void setMailno(String mailno) {
     this.mailno = (mailno == null) ? null : mailno.trim();
   }
   
   public String getWeight() {
     return this.weight;
   }
   
   public void setWeight(String weight) {
     this.weight = (weight == null) ? null : weight.trim();
   }
   
   public String getWorth() {
     return this.worth;
   }
   
   public void setWorth(String worth) {
     this.worth = (worth == null) ? null : worth.trim();
   }
   
   public String getProvince() {
     return this.province;
   }
   
   public void setProvince(String province) {
     this.province = (province == null) ? null : province.trim();
   }
   
   public String getCity() {
     return this.city;
   }
   
   public void setCity(String city) {
     this.city = (city == null) ? null : city.trim();
   }
   
   public String getAddress() {
     return this.address;
   }
   
   public void setAddress(String address) {
     this.address = (address == null) ? null : address.trim();
   }
 }


