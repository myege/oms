 package com.what21.model;
 
 public class Tree
 {
   private static final long serialVersionUID = 1L;
   private String id;
   private String text;
   private String state = "open";
   
   private boolean checked;
   
   public Tree() {}
   
   public Tree(String id, String text, String state, boolean checked) {
     this.id = id;
     this.text = text;
     this.state = state;
     this.checked = checked;
   }
   
   public String getId() {
     return this.id;
   }
   
   public void setId(String id) {
     this.id = id;
   }
 
   
   public String getText() {
     return this.text;
   }
   
   public void setText(String text) {
     this.text = text;
   }
   
   public String getState() {
     return this.state;
   }
   
   public void setState(String state) {
     this.state = state;
   }
   
   public boolean isChecked() {
     return this.checked;
   }
   
   public void setChecked(boolean checked) {
     this.checked = checked;
   }
 }


