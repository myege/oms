 package com.what21.model;
 
 import java.util.Date;
 public class OrderOutLogBS
 {
   private Integer id;
   private String waybillno;
   private String approveresult;
   private String approvecomment;
   private Date createtime;
   
   public Integer getId() {
     return this.id;
   }
   
   public void setId(Integer id) {
     this.id = id;
   }
   
   public String getWaybillno() {
     return this.waybillno;
   }
   
   public void setWaybillno(String waybillno) {
     this.waybillno = (waybillno == null) ? null : waybillno.trim();
   }
   
   public String getApproveresult() {
     return this.approveresult;
   }
   
   public void setApproveresult(String approveresult) {
     this.approveresult = (approveresult == null) ? null : approveresult.trim();
   }
   
   public String getApprovecomment() {
     return this.approvecomment;
   }
   
   public void setApprovecomment(String approvecomment) {
     this.approvecomment = (approvecomment == null) ? null : approvecomment.trim();
   }
   
   public Date getCreatetime() {
     return this.createtime;
   }
   
   public void setCreatetime(Date createtime) {
     this.createtime = createtime;
   }
 }


