 package com.what21.util;
 
 import com.what21.model.Waybill;
 import com.what21.model.YtoBill;
 import java.sql.SQLException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 
 
 public class ImportXxlsForWaybill
   extends XxlsAbstract
 {
   private Integer pid;
   private List<Waybill> list = new ArrayList<>();
   private List<YtoBill> list1 = new ArrayList<>();
   
   public List<YtoBill> getList1() {
     return this.list1;
   }
 
   
   public void setList1(List<YtoBill> list1) {
     this.list1 = list1;
   }
 
   
   private StringBuffer sb = new StringBuffer();
   
   public void optRows(int sheetIndex, int curRow, List<String> rowlist) throws SQLException {
     if (curRow != 0) {
       
       String expressCode = rowlist.get(0);
       String expressNumber = rowlist.get(1);
       String business = rowlist.get(2);
       Waybill waybill = new Waybill();
       waybill.setExpressCode(expressCode);
       waybill.setExpressNumber(expressNumber);
       waybill.setBusiness(business);
       waybill.setUserId(this.pid.intValue());
       this.list.add(waybill);
     } 
   }
 
   
   public void optRows1(int sheetIndex, int curRow, List<String> rowlist) throws SQLException {
     if (curRow != 0) {
       
       String WaybillNo = rowlist.get(0);
       String OrderNo = rowlist.get(1);
       String ShipperName = rowlist.get(2);
       String ShipperTel = rowlist.get(3);
       String ShipperAddress = rowlist.get(4);
       String ConsigneeName = rowlist.get(5);
       String ConsigneeTel = rowlist.get(6);
       String ConsigneeAddress = rowlist.get(7);
       String DeliveryTime = rowlist.get(8);
       
       YtoBill yto = new YtoBill();
       yto.setWaybillNo(WaybillNo);
       yto.setOrderNo(OrderNo);
       yto.setShipperName(ShipperName);
       yto.setShipperTel(ShipperTel);
       yto.setShipperAddress(ShipperAddress);
       yto.setConsigneeName(ConsigneeName);
       yto.setConsigneeTel(ConsigneeTel);
       yto.setConsigneeAddress(ConsigneeAddress);
       yto.setDeliveryTime(DeliveryTime);
       Date date = new Date();
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       yto.setCreateTime(df.format(date));
       yto.setIsSigned("0");
       this.list1.add(yto);
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
 
   
   public List<Waybill> getList() {
     return this.list;
   }
 
   
   public void setList(List<Waybill> list) {
     this.list = list;
   }
 
   
   public static void main(String[] args) throws Exception {
     ImportXxlsForWaybill howto = new ImportXxlsForWaybill();
     howto.processOneSheet("C:/Users/Administrator/Desktop/sss.xlsx", 1);
   }
 }


