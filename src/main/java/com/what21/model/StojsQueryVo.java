 package com.what21.model;
 
 public class StojsQueryVo
 {
   private PageQuery pageQuery;
   private Stojs stojs;
   
   public StojsBb getStojsBb() {
     return this.stojsBb;
   } private StojsQd stojsQd; private StojsBb stojsBb;
   public void setStojsBb(StojsBb stojsBb) {
     this.stojsBb = stojsBb;
   }
   public PageQuery getPageQuery() {
     return this.pageQuery;
   }
   public void setPageQuery(PageQuery pageQuery) {
     this.pageQuery = pageQuery;
   }
   public Stojs getStojs() {
     return this.stojs;
   }
   public void setStojs(Stojs stojs) {
     this.stojs = stojs;
   }
   public StojsQd getStojsQd() {
     return this.stojsQd;
   }
   public void setStojsQd(StojsQd stojsQd) {
     this.stojsQd = stojsQd;
   }
 }


