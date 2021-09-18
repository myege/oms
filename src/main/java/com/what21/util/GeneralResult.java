 package com.what21.util;
 
 import java.io.Serializable;
 import java.util.List;
 
 
 
 public class GeneralResult
   implements Serializable
 {
   private static final long serialVersionUID = 1192593269008790325L;
   private Integer code = Integer.valueOf(0);
 
 
 
   
   private String message = "";
 
 
   
   private List<Object> data;
 
 
   
   private Object simpleData;
 
 
   
   private String set;
 
   
   private String url;
 
 
   
   public String getUrl() {
     return this.url;
   }
   
   public void setUrl(String url) {
     this.url = url;
   }
   
   public String getSet() {
     return this.set;
   }
   
   public void setSet(String set) {
     this.set = set;
   }
   
   public Object getSimpleData() {
     return this.simpleData;
   }
   
   public void setSimpleData(Object simpleData) {
     this.simpleData = simpleData;
   }
   
   public GeneralResult csetSimpleData(Object simpleData) {
     setSimpleData(simpleData);
     return this;
   }
   
   public Integer getCode() {
     return this.code;
   }
   
   public void setCode(Integer code) {
     this.code = code;
   }
   
   public GeneralResult csetCode(Integer code) {
     setCode(code);
     return this;
   }
   
   public String getMessage() {
     return this.message;
   }
   
   public void setMessage(String message) {
     this.message = message;
   }
   
   public GeneralResult csetMessage(String message) {
     setMessage(message);
     return this;
   }
   
   public List<Object> getData() {
     return this.data;
   }
   
   public void setData(List<Object> data) {
     this.data = data;
   }
   
   public GeneralResult csetData(List<Object> data) {
     setData(data);
     return this;
   }
 }


