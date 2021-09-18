 package com.what21.model;
 
 import java.util.Date;
 public class TCustomsParam
 {
   private Integer id;
   private String hscode;
   private String name;
   private String licencecode;
   private String ordinaryrate;
   private String preferentialrate;
   private String remark;
   private String exportrate;
   private String consumptionrate;
   private String valueaddedrate;
   private String oneunitdesc;
   private String twounitdesc;
   private Date createtime;
   
   public Integer getId() {
     return this.id;
   }
   
   public void setId(Integer id) {
     this.id = id;
   }
   
   public String getHscode() {
     return this.hscode;
   }
   
   public void setHscode(String hscode) {
     this.hscode = (hscode == null) ? null : hscode.trim();
   }
   
   public String getName() {
     return this.name;
   }
   
   public void setName(String name) {
     this.name = (name == null) ? null : name.trim();
   }
   
   public String getLicencecode() {
     return this.licencecode;
   }
   
   public void setLicencecode(String licencecode) {
     this.licencecode = (licencecode == null) ? null : licencecode.trim();
   }
   
   public String getOrdinaryrate() {
     return this.ordinaryrate;
   }
   
   public void setOrdinaryrate(String ordinaryrate) {
     this.ordinaryrate = (ordinaryrate == null) ? null : ordinaryrate.trim();
   }
   
   public String getPreferentialrate() {
     return this.preferentialrate;
   }
   
   public void setPreferentialrate(String preferentialrate) {
     this.preferentialrate = (preferentialrate == null) ? null : preferentialrate.trim();
   }
   
   public String getRemark() {
     return this.remark;
   }
   
   public void setRemark(String remark) {
     this.remark = (remark == null) ? null : remark.trim();
   }
   
   public String getExportrate() {
     return this.exportrate;
   }
   
   public void setExportrate(String exportrate) {
     this.exportrate = (exportrate == null) ? null : exportrate.trim();
   }
   
   public String getConsumptionrate() {
     return this.consumptionrate;
   }
   
   public void setConsumptionrate(String consumptionrate) {
     this.consumptionrate = (consumptionrate == null) ? null : consumptionrate.trim();
   }
   
   public String getValueaddedrate() {
     return this.valueaddedrate;
   }
   
   public void setValueaddedrate(String valueaddedrate) {
     this.valueaddedrate = (valueaddedrate == null) ? null : valueaddedrate.trim();
   }
   
   public String getOneunitdesc() {
     return this.oneunitdesc;
   }
   
   public void setOneunitdesc(String oneunitdesc) {
     this.oneunitdesc = (oneunitdesc == null) ? null : oneunitdesc.trim();
   }
   
   public String getTwounitdesc() {
     return this.twounitdesc;
   }
   
   public void setTwounitdesc(String twounitdesc) {
     this.twounitdesc = (twounitdesc == null) ? null : twounitdesc.trim();
   }
   
   public Date getCreatetime() {
     return this.createtime;
   }
   
   public void setCreatetime(Date createtime) {
     this.createtime = createtime;
   }
 }


