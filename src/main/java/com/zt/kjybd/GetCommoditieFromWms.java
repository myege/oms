 package com.zt.kjybd;
 
 import com.what21.model.OrderCommoditie;
 import java.sql.ResultSet;
 import java.util.ArrayList;
 import java.util.List;
 
 
 public class GetCommoditieFromWms
 {
   static String sql = null;
   static DBHelper db1 = null;
   static ResultSet ret = null;
 
 
   
   public static void main(String[] args) {}
 
   
   public static List<OrderCommoditie> getCommoditie() throws Exception {
     sql = "select id,orderNum,commoditieCode,commoditieName,commoditieNum,createDate from t_order_commoditie where isToZtz=0";
     db1 = new DBHelper(sql);
     List<OrderCommoditie> ocList = new ArrayList<>();
     ret = db1.pst.executeQuery();
     while (ret.next()) {
       OrderCommoditie oc = new OrderCommoditie();
       oc.setWmsId(ret.getString(1));
       oc.setExpressNum(ret.getString(2));
       oc.setCommoditieCode(ret.getString(3));
       oc.setCommoditieName(ret.getString(4));
       oc.setCommoditieNum(Integer.parseInt(ret.getString(5)));
       oc.setCreateTime(ret.getString(6));
       ocList.add(oc);
     } 
     ret.close();
     db1.close();
     return ocList;
   }
   
   public static int updateOrderCommoditie(List<OrderCommoditie> ocList) throws Exception {
     if (ocList == null) {
       return 0;
     }
     StringBuffer idStr = new StringBuffer();
     for (OrderCommoditie orderCommoditie : ocList) {
       idStr.append(String.valueOf(orderCommoditie.getWmsId()) + ",");
     }
     String ids = "";
     ids = idStr.substring(0, idStr.length() - 1);
     sql = "update t_order_commoditie set isToZtz = 1 where id in (" + ids + ")";
     return update(sql);
   }
   
   public static int update(String sql) throws Exception {
     int result = -1;
     db1 = new DBHelper(sql);
     result = db1.pst.executeUpdate();
     ret.close();
     db1.close();
     return result;
   }
 }


