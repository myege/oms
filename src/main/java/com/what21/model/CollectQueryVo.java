 package com.what21.model;
 
 
 public class CollectQueryVo
 {
   private CollectCustom collectCustom;
   private PageQuery pageQuery;
   
   public PageQuery getPageQuery() {
     return this.pageQuery;
   }
   
   public void setPageQuery(PageQuery pageQuery) {
     this.pageQuery = pageQuery;
   }
   
   public CollectCustom getCollectCustom() {
     return this.collectCustom;
   }
   
   public void setCollectCustom(CollectCustom collectCustom) {
     this.collectCustom = collectCustom;
   }
 }


