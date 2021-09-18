 package com.what21.model;
 
 import java.util.Date;
 public class BeHc
 {
   private Integer id;
   private String pm;
   private String ddbh;
   private String ydbh;
   private Integer spjs;
   private String sjr;
   private String qpm;
   private String qzd;
   private String qzddj;
   private String zxgg;
   private String zxggdj;
   private Date drsj;
   private Integer pramid;
   private String hj;
   
   public String getHj() {
     return this.hj;
   }
   
   public void setHj(String hj) {
     this.hj = hj;
   }
   
   public Integer getId() {
     return this.id;
   }
   
   public void setId(Integer id) {
     this.id = id;
   }
   
   public String getPm() {
     return this.pm;
   }
   
   public void setPm(String pm) {
     this.pm = (pm == null) ? null : pm.trim();
   }
   
   public String getDdbh() {
     return this.ddbh;
   }
   
   public void setDdbh(String ddbh) {
     this.ddbh = (ddbh == null) ? null : ddbh.trim();
   }
   
   public String getYdbh() {
     return this.ydbh;
   }
   
   public void setYdbh(String ydbh) {
     this.ydbh = (ydbh == null) ? null : ydbh.trim();
   }
   
   public Integer getSpjs() {
     return this.spjs;
   }
   
   public void setSpjs(Integer spjs) {
     this.spjs = spjs;
   }
   
   public String getSjr() {
     return this.sjr;
   }
   
   public void setSjr(String sjr) {
     this.sjr = (sjr == null) ? null : sjr.trim();
   }
   
   public String getQpm() {
     return this.qpm;
   }
   
   public void setQpm(String qpm) {
     this.qpm = (qpm == null) ? null : qpm.trim();
   }
   
   public String getQzd() {
     return this.qzd;
   }
   
   public void setQzd(String qzd) {
     this.qzd = (qzd == null) ? null : qzd.trim();
   }
   
   public String getZxgg() {
     return this.zxgg;
   }
   
   public void setZxgg(String zxgg) {
     this.zxgg = (zxgg == null) ? null : zxgg.trim();
   }
 
 
   
   public String getQzddj() {
     return this.qzddj;
   }
   
   public void setQzddj(String qzddj) {
     this.qzddj = qzddj;
   }
   
   public String getZxggdj() {
     return this.zxggdj;
   }
   
   public void setZxggdj(String zxggdj) {
     this.zxggdj = zxggdj;
   }
   
   public Date getDrsj() {
     return this.drsj;
   }
   
   public void setDrsj(Date drsj) {
     this.drsj = drsj;
   }
   
   public Integer getPramid() {
     return this.pramid;
   }
   
   public void setPramid(Integer pramid) {
     this.pramid = pramid;
   }
 }


