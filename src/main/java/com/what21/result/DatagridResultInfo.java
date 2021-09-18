 package com.what21.result;
 import java.util.ArrayList;
 import java.util.List;
 
 public class DatagridResultInfo {
   private int total;
   private List rows = new ArrayList();
   
   public List getRows() {
     return this.rows;
   }
   public void setRows(List rows) {
     this.rows = rows;
   }
   public int getTotal() {
     return this.total;
   }
   public void setTotal(int total) {
     this.total = total;
   }
 }


