 package com.what21.model.enums;
 public enum JdEnum
 {
   APPKEY("3B03D933A76CF26E84CD449B63FEE94F"),
   ACCESSTOKEN("a2b7cc35e5d240b3bd529a9fc9524610tjin"),
   APP_SECRET("33bd053e696c4cd9b6600148dea111ec");
   
   private String value;
   
   JdEnum(String value) {
     this.value = value;
   }
   
   public String getValue() {
     return this.value;
   }
 }


