 package com.what21.model;
 
 public class ExpressOptions
 {
   private String optionName;
   private String optionValue;
   
   public String getOptionName() {
     return this.optionName;
   }
   
   public void setOptionName(String optionName) {
     this.optionName = (optionName == null) ? null : optionName.trim();
   }
   
   public String getOptionValue() {
     return this.optionValue;
   }
   
   public void setOptionValue(String optionValue) {
     this.optionValue = (optionValue == null) ? null : optionValue.trim();
   }
 }


