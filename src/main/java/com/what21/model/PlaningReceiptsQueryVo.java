 package com.what21.model;
 
 
 
 public class PlaningReceiptsQueryVo
 {
   private PlaningReceiptsCustom planingReceiptsCustom;
   private PageQuery pageQuery;
   
   public PlaningReceiptsCustom getPlaningReceiptsCustom() {
     return this.planingReceiptsCustom;
   }
   public void setPlaningReceiptsCustom(PlaningReceiptsCustom planingReceiptsCustom) {
     this.planingReceiptsCustom = planingReceiptsCustom;
   }
   public PageQuery getPageQuery() {
     return this.pageQuery;
   }
   public void setPageQuery(PageQuery pageQuery) {
     this.pageQuery = pageQuery;
   }
 }


