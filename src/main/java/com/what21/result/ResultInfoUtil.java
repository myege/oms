 package com.what21.result;
 
 import java.util.List;
 
 
 
 public class ResultInfoUtil
 {
   public static DatagridResultInfo createDatagrid(List list, int total) {
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setRows(list);
     datagridResultInfo.setTotal(total);
     return datagridResultInfo;
   }
 }


