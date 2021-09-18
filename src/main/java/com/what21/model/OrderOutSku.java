 package com.what21.model;
 public class OrderOutSku
 {
   private Integer id;
   private String goodsname;
   private String goodsno;
   private String weight;
   private String goodsamount;
   private String unitprice;
   private String totalprice;
   private String createtime;
   private String specifications;
   private String orderno;
   private String netweight;
   private String byZd;
   
   public String getByZd() {
     return this.byZd;
   }
   
   public void setByZd(String byZd) {
     this.byZd = byZd;
   }
   
   public String getNetweight() {
     return this.netweight;
   }
   
   public void setNetweight(String netweight) {
     this.netweight = netweight;
   }
   
   public String getOrderno() {
     return this.orderno;
   }
   
   public void setOrderno(String orderno) {
     this.orderno = (orderno == null) ? null : orderno.trim();
   }
   
   public Integer getId() {
     return this.id;
   }
   
   public void setId(Integer id) {
     this.id = id;
   }
   
   public String getGoodsname() {
     return this.goodsname;
   }
   
   public void setGoodsname(String goodsname) {
     this.goodsname = (goodsname == null) ? null : goodsname.trim();
   }
   
   public String getGoodsno() {
     return this.goodsno;
   }
   
   public void setGoodsno(String goodsno) {
     this.goodsno = (goodsno == null) ? null : goodsno.trim();
   }
   
   public String getWeight() {
     return this.weight;
   }
   
   public void setWeight(String weight) {
     this.weight = (weight == null) ? null : weight.trim();
   }
   
   public String getGoodsamount() {
     return this.goodsamount;
   }
   
   public void setGoodsamount(String goodsamount) {
     this.goodsamount = (goodsamount == null) ? null : goodsamount.trim();
   }
   
   public String getUnitprice() {
     return this.unitprice;
   }
   
   public void setUnitprice(String unitprice) {
     this.unitprice = (unitprice == null) ? null : unitprice.trim();
   }
   
   public String getTotalprice() {
     return this.totalprice;
   }
   
   public void setTotalprice(String totalprice) {
     this.totalprice = (totalprice == null) ? null : totalprice.trim();
   }
   
   public String getCreatetime() {
     return this.createtime;
   }
   
   public void setCreatetime(String createtime) {
     this.createtime = (createtime == null) ? null : createtime.trim();
   }
   
   public String getSpecifications() {
     return this.specifications;
   }
   
   public void setSpecifications(String specifications) {
     this.specifications = (specifications == null) ? null : specifications.trim();
   }
 }


