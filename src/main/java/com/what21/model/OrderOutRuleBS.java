 package com.what21.model;
 
 
 public class OrderOutRuleBS
 {
   private Integer id;
   private String hscode;
   private String goodsname;
   
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
   
   public String getGoodsname() {
     return this.goodsname;
   }
   
   public void setGoodsname(String goodsname) {
     this.goodsname = (goodsname == null) ? null : goodsname.trim();
   }
 }


