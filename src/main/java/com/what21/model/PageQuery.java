 package com.what21.model;
 public class PageQuery {
   private Integer startPage;
   
   public PageQuery(int page, int rows) {
     this.startPage = Integer.valueOf((page - 1) * rows);
     this.endPage = Integer.valueOf(rows);
   }
   private Integer endPage;
   
   public Integer getStartPage() {
     return this.startPage;
   }
   public void setStartPage(Integer startPage) {
     this.startPage = startPage;
   }
   public Integer getEndPage() {
     return this.endPage;
   }
   public void setEndPage(Integer endPage) {
     this.endPage = endPage;
   }
 }


