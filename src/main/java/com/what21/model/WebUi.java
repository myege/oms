 package com.what21.model;
 public class WebUi
 {
   private int id;
   private String data;
   private String code;
   private String begCode;
   private int page;
   private int rows;
   
   public int getPage() {
     return this.page;
   }
   public void setPage(int page) {
     this.page = (page - 1) * page;
   }
   public int getRows() {
     return this.rows;
   }
   public void setRows(int rows) {
     this.rows = rows;
   }
   public int getId() {
     return this.id;
   }
   public void setId(int id) {
     this.id = id;
   }
   public String getData() {
     return this.data;
   }
   public void setData(String data) {
     this.data = data;
   }
   public String getCode() {
     return this.code;
   }
   public void setCode(String code) {
     this.code = code;
   }
   public String getBegCode() {
     return this.begCode;
   }
   public void setBegCode(String begCode) {
     this.begCode = begCode;
   }
 }


