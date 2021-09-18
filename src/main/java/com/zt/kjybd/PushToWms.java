 package com.zt.kjybd;
 
 import com.what21.model.OrderBonded;
 import com.what21.model.OrderBondedSku;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 
 public class PushToWms
 {
   static String sql = null;
   static DBHelper db1 = null;
   static ResultSet ret = null;
   
   public static void main(String[] args) {
     querySeller();
   }
   
   public static Map<String, String> querySeller() {
     sql = "select number,companybm from t_seller where companybm is not null";
     db1 = new DBHelper(sql);
     Map<String, String> map = new HashMap<>();
     try {
       ret = db1.pst.executeQuery();
       while (ret.next()) {
         String number = ret.getString(1);
         String companybm = ret.getString(2);
 
         
         map.put(companybm, number);
       } 
       ret.close();
       db1.close();
     } catch (SQLException e) {
       e.printStackTrace();
     } 
     
     return map;
   }
   
   public static String pushData(List<OrderBonded> obList, List<OrderBondedSku> obsList) {
     String result = "推送成功！";
     Map<String, String> map = querySeller();
     String orderValues = "";
     for (OrderBonded ob : obList) {
       String seller = map.get(ob.getMerchantNum());
       if (seller == null) {
         return "推送失败！<br>订单号为：" + ob.getTxLogisticID() + "，该订单【商家编码】在WMS系统商家表中不存在";
       }
       String express = "100028";
       if ("STO".equals(ob.getCarrier())) {
         express = "100024";
       } else if ("HTO".equals(ob.getCarrier())) {
         express = "100025";
       } 
       orderValues = String.valueOf(orderValues) + "(" + 
         "'" + ob.getTxLogisticID() + "'," + 
         "'" + "105701" + "'," + 
         "'" + seller + "'," + 
         "'" + "XSC" + "'," + 
         "'" + "12" + "'," + 
         "'" + "100801" + "'," + 
         "'" + ob.getItemCount() + "'," + 
         "'" + ob.getSkuCount() + "'," + 
         "'" + express + "'," + 
         "'" + ob.getMailNo() + "'," + 
         "'" + ob.getReceiveMan() + "'," + 
         "'" + ob.getReceiveMan() + "'," + 
         "'" + ob.getReceiveManPhone() + "'," + 
         "'" + ob.getArea() + "'," + 
         "'" + ob.getReceiveManAddress() + "'," + 
         "'" + "104101" + "'," + 
         "'" + "104401" + "'," + 
         "'" + "0" + "'," + 
         "'" + "0" + "'," + 
         "'" + "00000000" + "'," + 
         "now()" + "," + 
         "'" + ob.getMarkDestination() + "'," + 
         "'U'" + 
         "),";
     } 
     orderValues = orderValues.substring(0, orderValues.length() - 1);
     String orderSql = "insert into t_order(number,origType,seller,warehouse,platform,orderType,goodsCount,skuCount,express,expressNumber,buyerNickname,buyerName,buyerMobile,area,address,processNode,state,isFastOutOrder,isDeleted,creatorCode,createTime,postCode,syncFlag) values" + 
       
       orderValues;
     String orderSkuValues = "";
     String lockValues = "";
     for (OrderBondedSku obs : obsList) {
       if (obs.getInternalNumber() > 0) {
         orderSkuValues = String.valueOf(orderSkuValues) + "(" + 
           "'" + "100000" + "'," + 
           "'" + obs.getTxLogisticID() + "'," + 
           "'" + obs.getInternalNumber() + "'," + 
           "'" + obs.getItemCount() + "'," + 
           obs.getUnitPrice() + "," + 
           "'" + "104401" + "'," + 
           "'" + "0" + "'," + 
           "'" + "00000000" + "'," + 
           "now()" + 
           "),";
         lockValues = String.valueOf(lockValues) + "(" + 
           "'" + "100000" + "'," + 
           "'" + "XSC" + "'," + 
           "'" + "105503" + "'," + 
           "'" + obs.getTxLogisticID() + "'," + 
           "'" + obs.getInternalNumber() + "'," + 
           "'" + obs.getItemCount() + "'," + 
           "'" + "00000000" + "'," + 
           "now()" + "," + 
           "'" + "0" + "'," + 
           "'" + "00000000" + "'," + 
           "now()" + 
           "),"; continue;
       } 
       return "推送失败！<br>订单号为：" + obs.getTxLogisticID() + "，该订单SKU表【内部编号】的值有问题";
     } 
     
     orderSkuValues = orderSkuValues.substring(0, orderSkuValues.length() - 1);
     lockValues = lockValues.substring(0, lockValues.length() - 1);
     String orderSkuSql = "insert into t_order_sku(number,`order`,sku,num,dealPrice,state,isDeleted,creatorCode,createTime) values" + 
       orderSkuValues;
     String lockSql = "insert into t_stock_lock_adjust(number,warehouse,adjustCategory,billNumber,sku,num,operateEmployee,operateTime,isDeleted,creatorCode,createTime) values" + 
       lockValues;
 
 
     
     sql = String.valueOf(orderSql) + ";" + orderSkuSql + ";" + lockSql;
     
     int ret = insert(orderSql);
     if (ret >= 1) {
       ret = insert(orderSkuSql);
     }
     if (ret >= 1) {
       ret = insert(lockSql);
     }
     return result;
   }
   public static int insert(String sql) {
     int result = -1;
     db1 = new DBHelper(sql);
     try {
       result = db1.pst.executeUpdate();
       ret.close();
       db1.close();
     } catch (SQLException e) {
       e.printStackTrace();
     } 
     return result;
   }
 }


