 package com.what21.model;
 
 import java.math.BigDecimal;
 public class OutEntryBillList
 {
   private int id;
   private String entry_no;
   private int g_no;
   private int pass_no;
   private String code_ts;
   private String g_name;
   private String g_model;
   private String origin_country;
   private BigDecimal g_qty;
   private String g_unit;
   private BigDecimal decl_price;
   private String trade_curr;
   private BigDecimal decl_total;
   private String duty_mode;
   private String note_s;
   
   public int getId() {
     return this.id;
   }
   
   public void setId(int id) {
     this.id = id;
   }
   
   public String getEntry_no() {
     return this.entry_no;
   }
   
   public void setEntry_no(String entry_no) {
     this.entry_no = entry_no;
   }
   
   public int getG_no() {
     return this.g_no;
   }
   
   public void setG_no(int g_no) {
     this.g_no = g_no;
   }
   
   public int getPass_no() {
     return this.pass_no;
   }
   
   public void setPass_no(int pass_no) {
     this.pass_no = pass_no;
   }
   
   public String getCode_ts() {
     return this.code_ts;
   }
   
   public void setCode_ts(String code_ts) {
     this.code_ts = code_ts;
   }
   
   public String getG_name() {
     return this.g_name;
   }
   
   public void setG_name(String g_name) {
     this.g_name = g_name;
   }
   
   public String getG_model() {
     return this.g_model;
   }
   
   public void setG_model(String g_model) {
     this.g_model = g_model;
   }
   
   public String getOrigin_country() {
     return this.origin_country;
   }
   
   public void setOrigin_country(String origin_country) {
     this.origin_country = origin_country;
   }
   
   public BigDecimal getG_qty() {
     return this.g_qty;
   }
   
   public void setG_qty(BigDecimal g_qty) {
     this.g_qty = g_qty;
   }
   
   public String getG_unit() {
     return this.g_unit;
   }
   
   public void setG_unit(String g_unit) {
     this.g_unit = g_unit;
   }
   
   public BigDecimal getDecl_price() {
     return this.decl_price;
   }
   
   public void setDecl_price(BigDecimal decl_price) {
     this.decl_price = decl_price;
   }
   
   public String getTrade_curr() {
     return this.trade_curr;
   }
   
   public void setTrade_curr(String trade_curr) {
     this.trade_curr = trade_curr;
   }
   
   public BigDecimal getDecl_total() {
     return this.decl_total;
   }
   
   public void setDecl_total(BigDecimal decl_total) {
     this.decl_total = decl_total;
   }
   
   public String getDuty_mode() {
     return this.duty_mode;
   }
   
   public void setDuty_mode(String duty_mode) {
     this.duty_mode = duty_mode;
   }
   
   public String getNote_s() {
     return this.note_s;
   }
   
   public void setNote_s(String note_s) {
     this.note_s = note_s;
   }
 }


