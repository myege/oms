 package com.what21.model;
 
 import net.sf.json.JSONObject;
 public class Address
 {
   private Long Id;
   private Long parent_id;
   private String name;
   private Integer priority;
   
   public Long getId() {
     return this.Id;
   }
   
   public void setId(Long id) {
     this.Id = id;
   }
   
   public Long getParent_id() {
     return this.parent_id;
   }
   
   public void setParent_id(Long parentId) {
     this.parent_id = parentId;
   }
   
   public String getName() {
     return this.name;
   }
   
   public void setName(String name) {
     this.name = name;
   }
   
   public Integer getPriority() {
     return this.priority;
   }
   
   public void setPriority(Integer priority) {
     this.priority = priority;
   }
 
   
   public String toString() {
     return JSONObject.fromObject(this).toString();
   }
 }


