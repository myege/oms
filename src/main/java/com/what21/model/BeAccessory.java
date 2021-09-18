 package com.what21.model;
 public class BeAccessory
 {
   private Integer id;
   private String itemname;
   private String resfilename;
   private String tarfilename;
   private Integer lzdid;
   private String createtime;
   
   public Integer getId() {
     return this.id;
   }
   
   public void setId(Integer id) {
     this.id = id;
   }
   
   public String getItemname() {
     return this.itemname;
   }
   
   public void setItemname(String itemname) {
     this.itemname = (itemname == null) ? null : itemname.trim();
   }
   
   public String getResfilename() {
     return this.resfilename;
   }
   
   public void setResfilename(String resfilename) {
     this.resfilename = (resfilename == null) ? null : resfilename.trim();
   }
   
   public String getTarfilename() {
     return this.tarfilename;
   }
   
   public void setTarfilename(String tarfilename) {
     this.tarfilename = (tarfilename == null) ? null : tarfilename.trim();
   }
   
   public Integer getLzdid() {
     return this.lzdid;
   }
   
   public void setLzdid(Integer lzdid) {
     this.lzdid = lzdid;
   }
   
   public String getCreatetime() {
     return this.createtime;
   }
   
   public void setCreatetime(String createtime) {
     this.createtime = (createtime == null) ? null : createtime.trim();
   }
 }


