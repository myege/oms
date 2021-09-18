 package com.what21.model;

import java.util.List;

 
 public class Ijdcgd {
   private Integer id;
   private String poNo;
   private String serialNo;
   private String billNo;
   
   public Integer getId() {
     return this.id;
   } private String provider; private Float status; private String notes; private List<Ijdcgditem> cgdList; private String createtime;
   public void setId(Integer id) {
     this.id = id;
   }
   public String getPoNo() {
     return this.poNo;
   }
   public void setPoNo(String poNo) {
     this.poNo = poNo;
   }
   public String getSerialNo() {
     return this.serialNo;
   }
   public void setSerialNo(String serialNo) {
     this.serialNo = serialNo;
   }
   public String getBillNo() {
     return this.billNo;
   }
   public void setBillNo(String billNo) {
     this.billNo = billNo;
   }
   public String getProvider() {
     return this.provider;
   }
   public void setProvider(String provider) {
     this.provider = provider;
   }
   public Float getStatus() {
     return this.status;
   }
   public void setStatus(Float status) {
     this.status = status;
   }
   public String getNotes() {
     return this.notes;
   }
   public void setNotes(String notes) {
     this.notes = notes;
   }
   
   public List<Ijdcgditem> getCgdList() {
     return this.cgdList;
   }
   public void setCgdList(List<Ijdcgditem> cgdList) {
     this.cgdList = cgdList;
   }
   public String getCreatetime() {
     return this.createtime;
   }
   public void setCreatetime(String createtime) {
     this.createtime = createtime;
   }
 }


