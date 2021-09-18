 package com.what21.util;
 
 import com.what21.model.StojsQd;
 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.List;
 
 
 
 public class ImportXxlsForStojsQd
   extends XxlsAbstract
 {
   private Integer pid;
   private List<StojsQd> list = new ArrayList<>();
   
   private StringBuffer sb = new StringBuffer();
 
   
   public void optRows(int sheetIndex, int curRow, List<String> rowlist) throws SQLException {
     if (curRow != 0) {
 
       
       if (rowlist.size() != 7) {
         this.sb.append("导入失败，失败原因：第" + (curRow + 1) + "行的字段数量不一致 并且必须都有数值");
         return;
       } 
       String txlogisticid = rowlist.get(0);
       String mailno = rowlist.get(1);
       Float weight = null;
       try {
         weight = Float.valueOf(Float.parseFloat(rowlist.get(2)));
       } catch (Exception e) {
         this.sb.append("导入失败，失败原因：第" + (curRow + 1) + "行重量必须写入并且必须是数字");
         return;
       } 
       Float worth = null;
       try {
         worth = Float.valueOf(Float.parseFloat(rowlist.get(3)));
       } catch (Exception e) {
         this.sb.append("导入失败，失败原因：第" + (curRow + 1) + "行重量必须写入并且必须是数字");
         return;
       } 
       String province = rowlist.get(4);
       String city = rowlist.get(5);
       String address = rowlist.get(6);
       StojsQd stojsQd = new StojsQd();
       stojsQd.setMailno(mailno);
       stojsQd.setTxlogisticid(txlogisticid);
       stojsQd.setAddress(address);
       stojsQd.setPid(this.pid);
       stojsQd.setWeight(weight.toString());
       stojsQd.setWorth(worth.toString());
       stojsQd.setCity(city);
       stojsQd.setProvince(province);
       this.list.add(stojsQd);
     } 
   }
 
 
   
   public StringBuffer getSb() {
     return this.sb;
   }
 
   
   public void setSb(StringBuffer sb) {
     this.sb = sb;
   }
 
   
   public Integer getPid() {
     return this.pid;
   }
 
   
   public void setPid(Integer pid) {
     this.pid = pid;
   }
 
   
   public List<StojsQd> getList() {
     return this.list;
   }
 
   
   public void setList(List<StojsQd> list) {
     this.list = list;
   }
 
   
   public static void main(String[] args) throws Exception {
     ImportXxlsForStojsQd howto = new ImportXxlsForStojsQd();
     howto.processOneSheet("C:/Users/Administrator/Desktop/sss.xlsx", 1);
   }
 }


